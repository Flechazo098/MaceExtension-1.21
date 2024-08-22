package com.hender.flechazo.item;

import com.hender.Hender;
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
                                entries.add(ModItems.HENDER_MACE);
                                entries.add(ModItems.WOODEN_MACE);
                                entries.add(ModItems.STONE_MACE);
                                entries.add(ModItems.IRON_MACE);
                                entries.add(ModItems.GOLD_MACE);
                                entries.add(ModItems.DIAMOND_MACE);
                                entries.add(ModItems.NETHERITE_MACE);
                                entries.add(ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION);
                                entries.add(ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION);

                                entries.add(ModBlocks.HENDER_TOOL_BLOCK);
                                entries.add(ModBlocks.HENDER_TOOL_ORE);
                                entries.add(ModBlocks.SYNTHESIZER);

                                entries.add(ModItems.HENDER_TOOL_HELMET);
                                entries.add(ModItems.HENDER_TOOL_CHESTPLATE);
                                entries.add(ModItems.HENDER_TOOL_LEGGINGS);
                                entries.add(ModItems.HENDER_TOOL_BOOTS);

                                entries.add(ModItems.HENDER_CORE);
                                entries.add(ModItems.WOODEN_CORE);
                                entries.add(ModItems.STONE_CORE);
                                entries.add(ModItems.GOLD_CORE);
                                entries.add(ModItems.IRON_CORE);
                                entries.add(ModItems.DIAMOND_CORE);
                                entries.add(ModItems.NETHERITE_CORE);
                                entries.add(ModItems.HENDER_HELMET_CORE);
                                entries.add(ModItems.HENDER_CHESTPLATE_CORE);
                                entries.add(ModItems.HENDER_LEGGINGS_CORE);
                                entries.add(ModItems.HENDER_BOOTS_CORE);


                            }
                            )
                            .build());
        Hender.LOGGER.info("Registering Item Groups");
    }
}
