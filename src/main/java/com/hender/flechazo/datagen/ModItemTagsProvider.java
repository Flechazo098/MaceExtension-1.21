package com.hender.flechazo.datagen;

import com.hender.flechazo.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagProvider.ItemTagProvider {
        public ModItemTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }
        @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup){
            getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                    .add(ModItems.HENDER_TOOL_HELMET)
                    .add(ModItems.HENDER_TOOL_CHESTPLATE)
                    .add(ModItems.HENDER_TOOL_LEGGINGS)
                    .add(ModItems.HENDER_TOOL_BOOTS);
            getOrCreateTagBuilder(ItemTags.MACE_ENCHANTABLE)
                    .add(ModItems.HENDER_MACE)
                    .add(ModItems.WOODEN_MACE)
                    .add(ModItems.STONE_MACE)
                    .add(ModItems.STONE_MACE)
                    .add(ModItems.GOLD_MACE)
                    .add(ModItems.IRON_MACE)
                    .add(ModItems.DIAMOND_MACE)
                    .add(ModItems.NETHERITE_MACE);

        }
}

