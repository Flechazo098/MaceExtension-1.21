package com.hender.flechazo.block.entity;

import com.hender.Hender;
import com.hender.flechazo.block.ModBlocks;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class HenderBlockEntities {
    public static final BlockEntityType<SynthesizerBlockEntity> SYNTHESIZER_BLOCK_ENTITY = create("synthesizer_block_entity",
            BlockEntityType.Builder.create(SynthesizerBlockEntity::new, ModBlocks.SYNTHESIZER));

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Hender.MOD_ID, id), builder.build(type));
    }
    public static void registerBlockEntities(){

    }
}
