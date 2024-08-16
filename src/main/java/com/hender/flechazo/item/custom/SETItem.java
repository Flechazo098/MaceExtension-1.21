package com.hender.flechazo.item.custom;

import com.hender.flechazo.item.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class SETItem extends PotionItem {
    public SETItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;

        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity) playerEntity, stack);
        }

        if (!world.isClient) {
            PotionContentsComponent potionContentsComponent = (PotionContentsComponent) stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
            potionContentsComponent.forEachEffect(effect -> {
                if (((StatusEffect) effect.getEffectType().value()).isInstant()) {
                    ((StatusEffect) effect.getEffectType().value()).applyInstantEffect(playerEntity, playerEntity, user, effect.getAmplifier(), 1.0);
                } else {
                    user.addStatusEffect(effect);
                }
            });
        }


        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

            if (playerEntity.getAbilities().creativeMode) {
                ItemStack resultStack = new ItemStack(ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION);
                if (!playerEntity.getInventory().insertStack(resultStack)) {
                    playerEntity.dropItem(resultStack, false);
                }
                return stack;
            } else {
                int newCount = stack.getCount() - 1;
                if (newCount <= 0) {
                    ItemStack emptyBottle = new ItemStack(ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION);
                    if (!playerEntity.getInventory().insertStack(emptyBottle)) {
                        playerEntity.dropItem(emptyBottle, false);
                    }
                    return ItemStack.EMPTY;
                } else {
                    stack.setCount(newCount);
                    return stack;
                }
            }
        }

        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (stack.getItem() == ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION){
                tooltip.add(Text.translatable("item.hender.setitem.tooltip"));
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return "item.hender.sanctus_elixir_of_transmutation";
    }
}
