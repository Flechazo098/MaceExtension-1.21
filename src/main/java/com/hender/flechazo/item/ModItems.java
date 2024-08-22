package com.hender.flechazo.item;


import com.hender.Hender;
import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.item.custom.HenderArmorItem;
import com.hender.flechazo.item.custom.SETItem;
import dev.architectury.platform.Mod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;


import net.minecraft.block.Block;
import net.minecraft.block.HeavyCoreBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;


public class ModItems {
    public static final Item HENDER_TOOL = registerItems("hender_tool", new Item(new Item.Settings()));
    public static final Item EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION = registerItems("empty_sanctus_elixir_of_transmutation" , new Item(new Item.Settings().maxCount(1)));

    private static Item registerItems(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Hender.MOD_ID, id), item);
    }
    public static Item registerBlockItem(Block block, BlockItem blockItem) {
        return Registry.register(Registries.ITEM, Identifier.of(Hender.MOD_ID, blockItem.getBlock().getTranslationKey()), blockItem);
    }



    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
        content.addAfter(Items.MACE,  HENDER_MACE);
        content.addAfter(ModItems.HENDER_MACE, WOODEN_MACE);
        content.addAfter(ModItems.WOODEN_MACE, STONE_MACE);
        content.addAfter(ModItems.STONE_MACE, GOLD_MACE);
        content.addAfter(ModItems.GOLD_MACE, IRON_MACE);
        content.addAfter(ModItems.IRON_MACE, DIAMOND_MACE);
        content.addAfter(ModItems.DIAMOND_MACE, NETHERITE_MACE);
        });
    }

    public static void registerBlockItem(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(content -> {
            content.addAfter(Items.HEAVY_CORE, HENDER_CORE);
            content.addAfter(ModItems.HENDER_CORE, WOODEN_CORE);
            content.addAfter(ModItems.WOODEN_CORE, STONE_CORE);
            content.addAfter(ModItems.STONE_CORE, GOLD_CORE);
            content.addAfter(ModItems.GOLD_CORE, IRON_CORE);
            content.addAfter(ModItems.IRON_CORE, DIAMOND_CORE);
            content.addAfter(ModItems.DIAMOND_CORE, NETHERITE_CORE);
            content.addAfter(ModItems.NETHERITE_CORE, HENDER_HELMET_CORE);
            content.addAfter(ModItems.HENDER_HELMET_CORE, HENDER_CHESTPLATE_CORE);
            content.addAfter(ModItems.HENDER_CHESTPLATE_CORE, HENDER_LEGGINGS_CORE);
            content.addAfter(ModItems.HENDER_LEGGINGS_CORE, HENDER_BOOTS_CORE);
        });
    }
    public static final Item HENDER_TOOL_HELMET = registerItems("hender_tool_helmet",
            new HenderArmorItem(HenderArmorMaterials.HENDER_TOOL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(1919810))));
    public static final Item HENDER_TOOL_CHESTPLATE = registerItems("hender_tool_chestplate",
            new HenderArmorItem(HenderArmorMaterials.HENDER_TOOL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(1919810))));
    public static final  Item HENDER_TOOL_LEGGINGS = registerItems("hender_tool_leggings",
            new HenderArmorItem(HenderArmorMaterials.HENDER_TOOL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(1919810))));
    public static final Item HENDER_TOOL_BOOTS = registerItems("hender_tool_boots",
            new HenderArmorItem(HenderArmorMaterials.HENDER_TOOL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(1919810))));
    public static final Item SANCTUS_ELIXIR_OF_TRANSMUTATION = registerItems("sanctus_elixir_of_transmutation",
            new SETItem(new Item.Settings().maxCount(1).component(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT)));



public static final Item HENDER_MACE = registerItems("hender_mace",
        new HenderMaceItem(new Item.Settings().rarity(Rarity.EPIC).maxDamage(500).component(DataComponentTypes.TOOL, HenderMaceItem.createToolComponent()).attributeModifiers(HenderMaceItem.createAttributeModifiers())));
public static final Item WOODEN_MACE = registerItems("wooden_mace",
        new WoodenMaceItem(new Item.Settings().rarity(Rarity.COMMON).maxDamage(150).component(DataComponentTypes.TOOL, WoodenMaceItem.createToolComponent()).attributeModifiers(WoodenMaceItem.createAttributeModifiers())));
public static final Item STONE_MACE = registerItems("stone_mace",
        new StoneMaceItem(new Item.Settings().rarity(Rarity.COMMON).maxDamage(370).component(DataComponentTypes.TOOL, StoneMaceItem.createToolComponent()).attributeModifiers(StoneMaceItem.createAttributeModifiers())));
public static final Item IRON_MACE = registerItems("iron_mace",
        new IronMaceItem(new Item.Settings().rarity(Rarity.COMMON).maxDamage(500).component(DataComponentTypes.TOOL, IronMaceItem.createToolComponent()).attributeModifiers(IronMaceItem.createAttributeModifiers())));
public static final Item GOLD_MACE = registerItems("gold_mace",
        new GoldMaceItem(new Item.Settings().rarity(Rarity.COMMON).maxDamage(140).component(DataComponentTypes.TOOL, GoldMaceItem.createToolComponent()).attributeModifiers(GoldMaceItem.createAttributeModifiers())));
public static final Item DIAMOND_MACE = registerItems("diamond_mace",
        new DiamondMaceItem(new Item.Settings().rarity(Rarity.COMMON).maxDamage(850).component(DataComponentTypes.TOOL, DiamondMaceItem.createToolComponent()).attributeModifiers(DiamondMaceItem.createAttributeModifiers())));
public static final Item NETHERITE_MACE = registerItems("netherite_mace",
        new NetheriteMaceItem(new Item.Settings().rarity(Rarity.EPIC).maxDamage(1000).component(DataComponentTypes.TOOL, NetheriteMaceItem.createToolComponent()).attributeModifiers(NetheriteMaceItem.createAttributeModifiers()).fireproof()));

public static final Item HENDER_CORE = registerBlockItem(ModBlocks.HENDER_CORE, new BlockItem(ModBlocks.HENDER_CORE, (new Item.Settings().rarity(Rarity.EPIC)).fireproof()));
public static final Item WOODEN_CORE = registerBlockItem(ModBlocks.WOODEN_CORE, new BlockItem(ModBlocks.WOODEN_CORE, (new Item.Settings().rarity(Rarity.COMMON))));
public static final Item STONE_CORE = registerBlockItem(ModBlocks.STONE_CORE, new BlockItem(ModBlocks.STONE_CORE, (new Item.Settings().rarity(Rarity.COMMON))));
public static final Item GOLD_CORE = registerBlockItem(ModBlocks.GOLD_CORE, new BlockItem(ModBlocks.GOLD_CORE, (new Item.Settings().rarity(Rarity.COMMON))));
public static final Item IRON_CORE = registerBlockItem(ModBlocks.IRON_CORE, new BlockItem(ModBlocks.IRON_CORE, (new Item.Settings().rarity(Rarity.COMMON))));
public static final Item DIAMOND_CORE = registerBlockItem(ModBlocks.DIAMOND_CORE, new BlockItem(ModBlocks.DIAMOND_CORE, (new Item.Settings().rarity(Rarity.COMMON))));
public static final Item NETHERITE_CORE = registerBlockItem(ModBlocks.NETHERITE_CORE, new BlockItem(ModBlocks.NETHERITE_CORE, (new Item.Settings().rarity(Rarity.EPIC)).fireproof()));
public static final Item HENDER_HELMET_CORE = registerBlockItem(ModBlocks.HENDER_HELMET_CORE, new BlockItem(ModBlocks.HENDER_HELMET_CORE, (new Item.Settings().rarity(Rarity.EPIC)).fireproof()));
public static final Item HENDER_CHESTPLATE_CORE = registerBlockItem(ModBlocks.HENDER_CHESTPLATE_CORE, new BlockItem(ModBlocks.HENDER_CHESTPLATE_CORE, (new Item.Settings().rarity(Rarity.EPIC)).fireproof()));
public static final Item HENDER_LEGGINGS_CORE = registerBlockItem(ModBlocks.HENDER_LEGGINGS_CORE, new BlockItem(ModBlocks.HENDER_LEGGINGS_CORE, (new Item.Settings().rarity(Rarity.EPIC)).fireproof()));
public static final Item HENDER_BOOTS_CORE = registerBlockItem(ModBlocks.HENDER_BOOTS_CORE, new BlockItem(ModBlocks.HENDER_BOOTS_CORE, (new Item.Settings().rarity(Rarity.EPIC)).fireproof()));
}
