package com.hender.flechazo.item;


import com.hender.flechazo.Hender;
import com.hender.flechazo.item.custom.HenderArmorItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;


public class ModItems {
    public static final Item HENDER_TOOL = registerItems("hender_tool", new Item(new Item.Settings()));

    private static Item registerItems(String id, Item item) {
//        return Registry.register(Registries.ITEM, RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(NbhhMod.MOD_ID, id)), item);
        return Registry.register(Registries.ITEM, Identifier.of(Hender.MOD_ID, id), item);
    }

    private static void addItemToIG(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.add(HENDER_TOOL);
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
}
