package com.hender.flechazo.datagen;

import com.hender.Hender;
import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> HENDER_TOOL = List.of(
            ModBlocks.HENDER_TOOL_ORE,
            ModItems.HENDER_TOOL_HELMET,
            ModItems.HENDER_TOOL_CHESTPLATE,
            ModItems.HENDER_TOOL_LEGGINGS,
            ModItems.HENDER_TOOL_BOOTS
    );

    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.COMBAT, ModItems.HENDER_TOOL,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.HENDER_TOOL_BLOCK);
        offerSmelting(exporter, HENDER_TOOL, RecipeCategory.COMBAT, ModItems.HENDER_TOOL,
                0.7f, 200, "hender_tool");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HENDER_TOOL_HELMET, 1)
                .pattern("###")
                .pattern("# #")
                .input('#', Ingredient.ofItems(ModItems.HENDER_TOOL))
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.HENDER_TOOL))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "hender_tool-to-hender_tool_helmet"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HENDER_TOOL_CHESTPLATE, 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Ingredient.ofItems(ModItems.HENDER_TOOL))
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.HENDER_TOOL))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "hender_tool-to-hender_tool_chestplate"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HENDER_TOOL_LEGGINGS, 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Ingredient.ofItems(ModItems.HENDER_TOOL))
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.HENDER_TOOL))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "hender_tool-to-hender_tool_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HENDER_TOOL_BOOTS, 1)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Ingredient.ofItems(ModItems.HENDER_TOOL))
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.HENDER_TOOL))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "hender_tool-to-hender_tool_boots"));
    }
}
