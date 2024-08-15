//package com.hender.flechazo.compat;
//
//import me.shedaniel.rei.api.common.category.CategoryIdentifier;
//import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
//import me.shedaniel.rei.api.common.entry.EntryIngredient;
//import me.shedaniel.rei.api.common.entry.EntryStack;
//import me.shedaniel.rei.api.common.util.EntryStacks;
//import net.minecraft.recipe.RecipeEntry;
//import org.lwjgl.system.macosx.CGEventTapCallBack;
//
//import java.util.List;
//
//public class PolishingMachineDisplay extends BasicDisplay {
//    public PolishingMachineDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs) {
//        super(inputs, outputs);
//    }
//    public PolishingMachineDisplay(RecipeEntry<PolishingMachineRecipe> recipe){
//        super(getInputList(recipe.value()), List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult))));
//    }
//
//    private static List<EntryIngredient> getInputList(PolishingMachineRecipe value) {
//        if (value.getIngredients().isEmpty()){
//
//        }
//    }
//
//    @Override
//    public CategoryIdentifier<?> getCategoryIdentifier() {
//        return null;
//    }
//}
