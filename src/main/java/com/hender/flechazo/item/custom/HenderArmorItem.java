package com.hender.flechazo.item.custom;

import com.google.common.collect.ImmutableMap;
import com.hender.flechazo.item.HenderArmorMaterials;
import com.hender.flechazo.item.ModItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HenderArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>())
                    .put(HenderArmorMaterials.HENDER_TOOL.value(),
                            //这里第一个是效果时间，第二个是等级，第三个是环境显示，第四个粒子显示，第五个图标显示
                            Arrays.asList(
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, -1, 10, false, false, false),
                                    new StatusEffectInstance(StatusEffects.RESISTANCE, -1, 10, false, false, false),
                                    new StatusEffectInstance(StatusEffects.STRENGTH, -1, 2, false, false, false))).build();
    public HenderArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player && hasFullSuitOfArmor(player)){
            evaluateArmorEffects(player);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial,  List<StatusEffectInstance>> entry : MAP.entrySet()){
            ArmorMaterial material = entry.getKey();
            List<StatusEffectInstance> effects = entry.getValue();

            if (hasCorrectArmorSet(player, material)){
                for (StatusEffectInstance effect : effects){
                    addStatusEffectForMaterial(player, effect);
                }
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, StatusEffectInstance effect) {
        boolean hasEffect = player.hasStatusEffect(effect.getEffectType());

        if (!hasEffect){
            player.addStatusEffect(new StatusEffectInstance(effect));
        }
    }

    private boolean hasCorrectArmorSet(PlayerEntity player, ArmorMaterial material) {
    for (ItemStack stack : player.getInventory().armor){
        if (!(stack.getItem() instanceof ArmorItem)){
            return false;
            }
        }

    ArmorItem helmet = (ArmorItem) player.getInventory().getArmorStack(3).getItem();
    ArmorItem chestplate = (ArmorItem) player.getInventory().getArmorStack(2).getItem();
    ArmorItem leggings = (ArmorItem) player.getInventory().getArmorStack(1).getItem();
    ArmorItem boots = (ArmorItem) player.getInventory().getArmorStack(0).getItem();

    return helmet.getMaterial().value() == material &&
            chestplate.getMaterial().value() == material &&
            leggings.getMaterial().value() == material &&
            boots.getMaterial().value() == material;
    }

    private boolean hasFullSuitOfArmor(PlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack boots = player.getInventory().getArmorStack(0);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        if (Screen.hasShiftDown()) {
            // Check the item type and add the corresponding tooltip
            if (stack.getItem() == ModItems.HENDER_TOOL_HELMET) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemhelmet.shift_tooltip"));
            } else if (stack.getItem() == ModItems.HENDER_TOOL_CHESTPLATE) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemchestplate.shift_tooltip"));
            } else if (stack.getItem() == ModItems.HENDER_TOOL_LEGGINGS) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemleggings.shift_tooltip"));
            } else if (stack.getItem() == ModItems.HENDER_TOOL_BOOTS) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemboots.shift_tooltip"));
            }
        } else {
            // Check the item type and add the corresponding tooltip
            if (stack.getItem() == ModItems.HENDER_TOOL_HELMET) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemhelmet.tooltip"));
            } else if (stack.getItem() == ModItems.HENDER_TOOL_CHESTPLATE) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemchestplate.tooltip"));
            } else if (stack.getItem() == ModItems.HENDER_TOOL_LEGGINGS) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemleggings.tooltip"));
            } else if (stack.getItem() == ModItems.HENDER_TOOL_BOOTS) {
                tooltip.add(Text.translatable("item.hender.henderarmoritemboots.tooltip"));
            }
        }
    }

}
