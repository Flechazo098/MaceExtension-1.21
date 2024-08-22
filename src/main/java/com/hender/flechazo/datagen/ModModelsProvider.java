package com.hender.flechazo.datagen;

import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;


public class ModModelsProvider extends FabricModelProvider {

    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HENDER_TOOL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HENDER_TOOL_BLOCK);
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HENDER_TOOL_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HENDER_TOOL_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HENDER_TOOL_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HENDER_TOOL_BOOTS);

        itemModelGenerator.register(ModItems.HENDER_TOOL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION, Models.GENERATED);

        itemModelGenerator.register(ModItems.HENDER_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLD_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_MACE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_MACE, Models.HANDHELD);

    }
}
