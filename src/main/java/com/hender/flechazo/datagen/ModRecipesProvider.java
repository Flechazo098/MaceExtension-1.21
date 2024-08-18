package com.hender.flechazo.datagen;

import com.hender.Hender;
import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {

    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.COMBAT, ModItems.HENDER_TOOL,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.HENDER_TOOL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WOODEN_MACE, 1)
                .pattern("###")
                .pattern("###")
                .pattern(" $ ")
                .input('#', Ingredient.ofItems(Items.OAK_PLANKS))
                .input('$', Ingredient.ofItems(Items.STICK))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.OAK_PLANKS))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "woodenmace"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STONE_MACE, 1)
                .pattern("###")
                .pattern("###")
                .pattern(" $ ")
                .input('#', Ingredient.ofItems(Items.COBBLESTONE))
                .input('$', Ingredient.ofItems(Items.STICK))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.COBBLESTONE))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "stonemace"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLD_MACE, 1)
                .pattern("###")
                .pattern("###")
                .pattern(" $ ")
                .input('#', Ingredient.ofItems(Items.GOLD_INGOT))
                .input('$', Ingredient.ofItems(Items.STICK))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.GOLD_INGOT))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "goldmace"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IRON_MACE, 1)
                .pattern("###")
                .pattern("###")
                .pattern(" $ ")
                .input('#', Ingredient.ofItems(Items.IRON_INGOT))
                .input('$', Ingredient.ofItems(Items.STICK))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.DIAMOND))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "ironmace"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_MACE, 1)
                .pattern("###")
                .pattern("###")
                .pattern(" $ ")
                .input('#', Ingredient.ofItems(Items.DIAMOND))
                .input('$', Ingredient.ofItems(Items.STICK))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.DIAMOND))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter, Identifier.of(Hender.MOD_ID, "diamondmace"));


    }
}
