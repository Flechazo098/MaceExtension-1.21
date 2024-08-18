package com.hender.flechazo.block;

import com.hender.Hender;
import com.hender.flechazo.block.custom.Synthesizer;
import com.hender.flechazo.block.entity.HenderBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block HENDER_TOOL_ORE = register("hender_tool_ore",new Block(AbstractBlock.Settings.create().strength(4.5f,12.0f)));
    public static final Block HENDER_TOOL_BLOCK = register("hender_tool_block",new Block(AbstractBlock.Settings.create().strength(4.5f,16.0f)));
    public static final Block SYNTHESIZER = register("synthesizer", new Synthesizer(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static void registerBlockItems(String id, Block block){

        BlockItem item = Registry.register(Registries.ITEM, Identifier.of(Hender.MOD_ID, id),new BlockItem(block, new Item.Settings()));
        if (item instanceof BlockItem) {
            item.appendBlocks(Item.BLOCK_ITEMS, item);
        }
    }
    public static Block register(String id, Block block) {
        registerBlockItems(id, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Hender.MOD_ID, id), block);
    }
    public static void registerModBlocks(){
        Hender.LOGGER.info("Registering Blocks");
    }
}

