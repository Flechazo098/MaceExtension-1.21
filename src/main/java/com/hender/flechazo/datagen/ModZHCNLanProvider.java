package com.hender.flechazo.datagen;

import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModZHCNLanProvider extends FabricLanguageProvider {
    public ModZHCNLanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup){
        super(dataOutput, "zh_cn", registryLookup);

    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.HENDER_TOOL_HELMET, "§6恒之头盔");
        translationBuilder.add(ModItems.HENDER_TOOL_CHESTPLATE, "§6恒之胸甲");
        translationBuilder.add(ModItems.HENDER_TOOL_LEGGINGS, "§6恒之护腿");
        translationBuilder.add(ModItems.HENDER_TOOL_BOOTS, "§6恒之靴子");

        translationBuilder.add("itemGroup.hender.hender_group", "§4Hender专属");

        translationBuilder.add(ModBlocks.HENDER_TOOL_BLOCK, "§6恒块");
        translationBuilder.add(ModBlocks.HENDER_TOOL_ORE, "§6恒矿");
        translationBuilder.add(ModBlocks.SYNTHESIZER, "合成器");

        translationBuilder.add("item.hender.henderarmoritemhelmet.shift_tooltip", "§6§o超高防御，穿着全套可获得永久饱和效果");
        translationBuilder.add("item.hender.henderarmoritemhelmet.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.henderarmoritemchestplate.shift_tooltip", "§6§o超高防御，穿着全套可获得永久饱和效果");
        translationBuilder.add("item.hender.henderarmoritemchestplate.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.henderarmoritemleggings.shift_tooltip", "§6§o超高防御，穿着全套可获得永久饱和效果");
        translationBuilder.add("item.hender.henderarmoritemleggings.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.henderarmoritemboots.shift_tooltip", "§6§o超高防御，穿着全套可获得永久饱和效果");
        translationBuilder.add("item.hender.henderarmoritemboots.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.hendermaceitem.shift_tooltip", "§6§o集合了所有重锤的能力");
        translationBuilder.add("item.hender.hendermaceitem.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.setitem.tooltip", "§6喝下去好像并没有什么效果，它是干嘛的呢？");


        translationBuilder.add("container.SynthesizerBlockEntity", "合成器");

        translationBuilder.add(ModItems.HENDER_TOOL, "§6恒之宝石");
        translationBuilder.add(ModItems.HENDER_MACE, "§6恒之重锤");
        translationBuilder.add(ModItems.SANCTUS_ELIXIR_OF_TRANSMUTATION, "§6转晶圣液");
        translationBuilder.add(ModItems.EMPTY_SANCTUS_ELIXIR_OF_TRANSMUTATION, "空药水瓶");
    }
}
