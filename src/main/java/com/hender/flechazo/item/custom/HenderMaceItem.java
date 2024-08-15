package com.hender.flechazo.item.custom;

import java.util.List;
import java.util.function.Predicate;

import com.hender.flechazo.item.ModItems;
import net.minecraft.block.BlockState;
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
import net.minecraft.item.Items;
import net.minecraft.item.MaceItem;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.World;

public class HenderMaceItem extends MaceItem {
    private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 35; // 自定义攻击伤害
    private static final float ATTACK_SPEED_MODIFIER_VALUE = 2.5F; // 自定义攻击速度
    public static final float MINING_SPEED_MULTIPLIER = 3.0F; // 自定义挖掘速度
    public static final float KNOCKBACK_RANGE = 4.0F; // 自定义击退范围
    private static final float KNOCKBACK_POWER = 1.0F; // 自定义击退力量

    public HenderMaceItem(Item.Settings settings) {
        super(settings);
    }

    // 创建属性修饰符
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

    // 创建工具组件
    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), MINING_SPEED_MULTIPLIER, 2);
    }

    // 检查工具是否可以挖掘特定的方块
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative(); // 只有非创意模式下的玩家可以使用
    }

    // 获取附魔值
    @Override
    public int getEnchantability() {
        return 20; // 自定义附魔值
    }

    // 处理命中后的效果，包括击退、粒子效果和音效
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayerEntity serverPlayerEntity) {
            if (shouldDealAdditionalDamage(serverPlayerEntity)) {
                // 设置角色的速度和发送网络数据包
                ServerWorld serverWorld = (ServerWorld) attacker.getWorld();
                serverPlayerEntity.setVelocity(serverPlayerEntity.getVelocity().withAxis(Axis.Y, 0.01));
                serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                // 击退附近的实体
                knockbackNearbyEntities(serverWorld, serverPlayerEntity, target);
            }
        }
        return true;
    }

    // 处理对目标实体的伤害
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND); // 对工具的耐久度造成伤害
        if (shouldDealAdditionalDamage(attacker)) {
            attacker.onLanding(); // 处理额外伤害
        }
    }

    // 检查工具是否可以被修理
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.HENDER_TOOL); // 使用特定的修理材料
    }

    // 获取额外的攻击伤害
    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity source = damageSource.getSource();
        if (source instanceof LivingEntity livingEntity) {
            if (!shouldDealAdditionalDamage(livingEntity)) {
                return 0.0F;
            } else {
                float fallDistance = livingEntity.fallDistance;
                return calculateBonusDamage(fallDistance); // 计算额外伤害
            }
        }
        return 0.0F;
    }

    // 根据跌落距离计算额外的攻击伤害
    private float calculateBonusDamage(float fallDistance) {
        if (fallDistance <= 5.0F) {
            return 10.0F * fallDistance;
        } else if (fallDistance <= 10.0F) {
            return 18.0F + 3.0F * (fallDistance - 5.0F);
        } else {
            return 30.0F + fallDistance - 10.0F;
        }
    }

    // 判断是否应该造成额外伤害
    public static boolean shouldDealAdditionalDamage(LivingEntity attacker) {
        return attacker.fallDistance > 1.5F && !attacker.isFallFlying();
    }

    // 击退附近的实体
    private static void knockbackNearbyEntities(World world, PlayerEntity player, Entity attacked) {
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand(KNOCKBACK_RANGE),
                getKnockbackPredicate(player, attacked)).forEach(entity -> {
            Vec3d knockbackVec = entity.getPos().subtract(attacked.getPos()).normalize().multiply(KNOCKBACK_POWER);
            entity.addVelocity(knockbackVec.x, 0.7, knockbackVec.z); // 添加击退效果
            if (entity instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayer));
            }
        });
    }

    // 获取击退实体的条件
    private static Predicate<LivingEntity> getKnockbackPredicate(PlayerEntity player, Entity attacked) {
        return entity -> !entity.isSpectator() && entity != player && entity != attacked && !player.isTeammate(entity) &&
                attacked.squaredDistanceTo(entity) <= Math.pow(KNOCKBACK_RANGE, 2);
    }
}