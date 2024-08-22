package com.hender.flechazo.block;

import com.hender.Hender;
import com.hender.flechazo.block.custom.Synthesizer;
import com.hender.flechazo.block.entity.HenderBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block HENDER_TOOL_ORE = register("hender_tool_ore",new Block(AbstractBlock.Settings.create().strength(4.5f,12.0f)));
    public static final Block HENDER_TOOL_BLOCK = register("hender_tool_block",new Block(AbstractBlock.Settings.create().strength(4.5f,16.0f)));
    public static final Block SYNTHESIZER = register("synthesizer", new Synthesizer(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final Block HENDER_CORE = register("hender_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).sounds(BlockSoundGroup.HEAVY_CORE).strength(10.0F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block WOODEN_CORE = register("wooden_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).instrument(NoteBlockInstrument.BASS).sounds(BlockSoundGroup.WOOD).strength(2.5F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block STONE_CORE = register("stone_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).sounds(BlockSoundGroup.STONE).strength(4.5F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block GOLD_CORE = register("gold_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BELL).sounds(BlockSoundGroup.METAL).strength(4.8F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block IRON_CORE = register("iron_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).sounds(BlockSoundGroup.METAL).strength(6.5F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block DIAMOND_CORE = register("diamond_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIAMOND_BLUE).instrument(NoteBlockInstrument.COW_BELL).sounds(BlockSoundGroup.METAL).strength(8.5F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block NETHERITE_CORE = register("netherite_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.PIGLIN).sounds(BlockSoundGroup.NETHERITE).strength(9.8F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block HENDER_HELMET_CORE = register("hender_helmet_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).sounds(BlockSoundGroup.HEAVY_CORE).strength(10.0F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block HENDER_CHESTPLATE_CORE = register("hender_chestplate_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).sounds(BlockSoundGroup.HEAVY_CORE).strength(10.0F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block HENDER_LEGGINGS_CORE = register("hender_leggings_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).sounds(BlockSoundGroup.HEAVY_CORE).strength(10.0F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
    public static final Block HENDER_BOOTS_CORE = register("hender_boots_core", new HeavyCoreBlock(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE).instrument(NoteBlockInstrument.SNARE).sounds(BlockSoundGroup.HEAVY_CORE).strength(10.0F).pistonBehavior(PistonBehavior.NORMAL).resistance(1200.0F)));
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

