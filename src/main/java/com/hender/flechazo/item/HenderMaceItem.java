package com.hender.flechazo.item;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.World;

public class HenderMaceItem extends MaceItem {
    private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 50; // 自定义攻击伤害
    private static final float ATTACK_SPEED_MODIFIER_VALUE = 2.5F; // 自定义攻击速度
    public static final float MINING_SPEED_MULTIPLIER = 3.0F; // 自定义挖掘速度
    private static final float KNOCKBACK_POWER = 1.0F; // 自定义击退力量

    public HenderMaceItem(Item.Settings settings) {
        super(settings);
    }

    // 属性修饰符
    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, ATTACK_DAMAGE_MODIFIER_VALUE, Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, ATTACK_SPEED_MODIFIER_VALUE, Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .build();
    }

    // 组件
    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), MINING_SPEED_MULTIPLIER, 2);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }


    @Override
    public int getEnchantability() {
        return 20;
    }

    // 处理命中后的效果，包括击退、粒子效果和音效
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayerEntity serverPlayerEntity) {
            if (shouldDealAdditionalDamage(serverPlayerEntity)) {
                ServerWorld serverWorld = (ServerWorld)attacker.getWorld();
                if (serverPlayerEntity.shouldIgnoreFallDamageFromCurrentExplosion() && serverPlayerEntity.currentExplosionImpactPos != null) {
                    if (serverPlayerEntity.currentExplosionImpactPos.y > serverPlayerEntity.getPos().y) {
                        serverPlayerEntity.currentExplosionImpactPos = serverPlayerEntity.getPos();
                    }
                } else {
                    serverPlayerEntity.currentExplosionImpactPos = serverPlayerEntity.getPos();
                }

                serverPlayerEntity.setIgnoreFallDamageFromCurrentExplosion(true);
                serverPlayerEntity.setVelocity(serverPlayerEntity.getVelocity().withAxis(Axis.Y, 0.009999999776482582));
                serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                if (target.isOnGround()) {
                    serverPlayerEntity.setSpawnExtraParticlesOnFall(true);
                    SoundEvent soundEvent = serverPlayerEntity.fallDistance > 5.0F ? SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY : SoundEvents.ITEM_MACE_SMASH_GROUND;
                    serverWorld.playSound(null, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), soundEvent, serverPlayerEntity.getSoundCategory(), 1.0F, 1.0F);
                } else {
                    serverWorld.playSound(null, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), SoundEvents.ITEM_MACE_SMASH_AIR, serverPlayerEntity.getSoundCategory(), 1.0F, 1.0F);
                }

                knockbackNearbyEntities(serverWorld, serverPlayerEntity, target);
            }
        }
        return true;
    }


    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(0, attacker, EquipmentSlot.MAINHAND);
        if (shouldDealAdditionalDamage(attacker)) {
            attacker.onLanding();
        }
    }


    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.HENDER_TOOL);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity source = damageSource.getSource();
        if (source instanceof LivingEntity livingEntity) {
            if (!shouldDealAdditionalDamage(livingEntity)) {
                return 0.0F;
            } else {
                float fallDistance = livingEntity.fallDistance;
                return calculateBonusDamage(fallDistance);
            }
        }

        return 0.0F;
    }

    private float calculateBonusDamage(float fallDistance) {
        if (fallDistance <= 5.0F) {
            return 50.0F * fallDistance;
        } else if (fallDistance <= 10.0F) {
            return 35.0F + 25.0F * (fallDistance * 5.0F);
        } else {
            return 50.0F + fallDistance * 10.0F;
        }
    }

    public static boolean shouldDealAdditionalDamage(LivingEntity attacker) {
        return attacker.fallDistance > 1.5F && !attacker.isFallFlying();
    }

    private static void knockbackNearbyEntities(World world, PlayerEntity player, Entity attacked) {
        world.syncWorldEvent(2013, attacked.getSteppingPos(), 750);
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand(10.0),
                getKnockbackPredicate(player, attacked)).forEach(entity -> {
            Vec3d knockbackVec = entity.getPos().subtract(attacked.getPos()).normalize().multiply(KNOCKBACK_POWER);
            entity.addVelocity(knockbackVec.x, 5.7, knockbackVec.z);
            if (entity instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayer));
            }
        });
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(PlayerEntity player, Entity attacked) {
        return entity -> !entity.isSpectator() && entity != player && entity != attacked && !player.isTeammate(entity) &&
                attacked.squaredDistanceTo(entity) <= Math.pow(10.0,  2);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (Screen.hasShiftDown()){
            if (stack.getItem() == ModItems.HENDER_MACE){
                tooltip.add(Text.translatable("item.hender.hendermaceitem.shift_tooltip"));
            }
        }
        else {
            tooltip.add(Text.translatable("item.hender.hendermaceitem.tooltip"));
        }
    }
}
