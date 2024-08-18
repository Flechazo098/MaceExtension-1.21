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

import java.util.*;

public class HenderArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>())
                    .put(HenderArmorMaterials.HENDER_TOOL.value(),
                            //这里第一个是效果时间，第二个是等级，第三个是环境显示，第四个粒子显示，第五个图标显示
                            Arrays.asList(new StatusEffectInstance(StatusEffects.SATURATION, -1, 5, false, false, false))).build();

    public HenderArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            evaluateArmorEffects(player);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        Set<ArmorMaterial> materialsWithEffects = new HashSet<>();

        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : MAP.entrySet()) {
            ArmorMaterial material = entry.getKey();
            List<StatusEffectInstance> effects = entry.getValue();

            if (hasCorrectArmorSet(player, material)) {
                materialsWithEffects.add(material);

                for (StatusEffectInstance effect : effects) {
                    addStatusEffectForMaterial(player, effect);
                }
            } else {
                removeStatusEffectsForMaterial(player, material);
            }
        }

        removeEffectsOfUnwornMaterials(player, materialsWithEffects);
    }

    private void addStatusEffectForMaterial(PlayerEntity player, StatusEffectInstance effect) {
        if (!player.hasStatusEffect(effect.getEffectType())) {
            player.addStatusEffect(new StatusEffectInstance(effect));
        }
    }

    private void removeStatusEffectsForMaterial(PlayerEntity player, ArmorMaterial material) {
        List<StatusEffectInstance> effects = MAP.get(material);

        if (effects != null) {
            for (StatusEffectInstance effect : effects) {
                if (player.hasStatusEffect(effect.getEffectType())) {
                    player.removeStatusEffect(effect.getEffectType());
                }
            }
        }
    }

    private void removeEffectsOfUnwornMaterials(PlayerEntity player, Set<ArmorMaterial> wornMaterials) {
        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : MAP.entrySet()) {
            ArmorMaterial material = entry.getKey();

            if (!wornMaterials.contains(material)) {
                removeStatusEffectsForMaterial(player, material);
            }
        }
    }

    private boolean hasCorrectArmorSet(PlayerEntity player, ArmorMaterial material) {
        ItemStack helmet = player.getInventory().getArmorStack(3);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack boots = player.getInventory().getArmorStack(0);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty()
                && helmet.getItem() instanceof ArmorItem && ((ArmorItem) helmet.getItem()).getMaterial().value() == material
                && chestplate.getItem() instanceof ArmorItem && ((ArmorItem) chestplate.getItem()).getMaterial().value() == material
                && leggings.getItem() instanceof ArmorItem && ((ArmorItem) leggings.getItem()).getMaterial().value() == material
                && boots.getItem() instanceof ArmorItem && ((ArmorItem) boots.getItem()).getMaterial().value() == material;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        if (Screen.hasShiftDown()) {
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
