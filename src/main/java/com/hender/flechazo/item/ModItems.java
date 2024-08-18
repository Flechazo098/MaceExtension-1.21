package com.hender.flechazo.item;


import com.hender.Hender;
import com.hender.flechazo.item.custom.HenderArmorItem;
import com.hender.flechazo.item.custom.SETItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;


import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
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

    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.add(WOODEN_MACE);
        fabricItemGroupEntries.add(STONE_MACE);
        fabricItemGroupEntries.add(IRON_MACE);
        fabricItemGroupEntries.add(GOLD_MACE);
        fabricItemGroupEntries.add(DIAMOND_MACE);
        fabricItemGroupEntries.add(NETHERITE_MACE);
    }

    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemToIG);
        Hender.LOGGER.info("Registering Items");
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
    public static final Item HENDER_CORE = registerItems("hender_core",
            new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item HENDER_CORE_HELMET = registerItems("hender_core_helmet",
            new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item HENDER_CORE_CHESTPLATE = registerItems("hender_core_chestplate",
            new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item HENDER_CORE_LEGGINGS = registerItems("hender_core_leggings",
            new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item HENDER_CORE_BOOTS = registerItems("hender_core_boots",
            new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));



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
        new NetheriteMaceItem(new Item.Settings().rarity(Rarity.EPIC).maxDamage(1000).component(DataComponentTypes.TOOL, NetheriteMaceItem.createToolComponent()).attributeModifiers(NetheriteMaceItem.createAttributeModifiers())));
}
