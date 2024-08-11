package com.hender.flechazo.item;

import com.hender.flechazo.Hender;
import com.hender.flechazo.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final RegistryKey<ItemGroup> HENDER_GROUP = register("hender_group");
    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Hender.MOD_ID, id));
    }
    public static void registerModItemGroups(){
        Registry.register(Registries.ITEM_GROUP, HENDER_GROUP,
                    ItemGroup.create(ItemGroup.Row.TOP, 7)
                            .displayName(Text.translatable("itemGroup.hender.hender_group"))
                            .icon(() -> new ItemStack(ModItems.HENDER_TOOL))
                            .entries(
                                    (displayContext, entries) -> {
                                entries.add(ModItems.HENDER_TOOL);

                                entries.add(ModBlocks.HENDER_TOOL_BLOCK);
                                entries.add(ModBlocks.HENDER_TOOL_ORE);
                                entries.add(ModBlocks.SHIT_BLOCK);

                                entries.add(ModItems.HENDER_TOOL_HELMET);
                                entries.add(ModItems.HENDER_TOOL_CHESTPLATE);
                                entries.add(ModItems.HENDER_TOOL_LEGGINGS);
                                entries.add(ModItems.HENDER_TOOL_BOOTS);
                            }
                            )
                            .build());
        Hender.LOGGER.info("Registering Item Groups");
    }
}
