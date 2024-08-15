package com.hender.flechazo.datagen;

import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.item.ModItems;
import com.hender.flechazo.villager.HenderVillagers;
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
        translationBuilder.add(ModItems.HENDER_TOOL, "§8小恒某人");
        translationBuilder.add(ModItems.HENDER_TOOL_HELMET, "§6恒之头盔");
        translationBuilder.add(ModItems.HENDER_TOOL_CHESTPLATE, "§6恒之胸甲");
        translationBuilder.add(ModItems.HENDER_TOOL_LEGGINGS, "§6恒之护腿");
        translationBuilder.add(ModItems.HENDER_TOOL_BOOTS, "§6恒之靴子");

        translationBuilder.add("itemGroup.hender.hender_group", "§4Hender专属");

        translationBuilder.add(ModBlocks.SHIT_BLOCK, "§6豪赤的");
        translationBuilder.add(ModBlocks.HENDER_TOOL_BLOCK, "§6恒块");
        translationBuilder.add(ModBlocks.HENDER_TOOL_ORE, "§6恒矿");

        translationBuilder.add("item.hender.henderarmoritemhelmet.shift_tooltip", "§9§o此物为§6§m恒之头盔§r§9§o，含有§6超高防御§r§9§o，穿着全套可有§6不死之身§r");
        translationBuilder.add("item.hender.henderarmoritemhelmet.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.henderarmoritemchestplate.shift_tooltip", "§9§o此物为§6§m恒之胸甲§r§9§o，含有§6超高防御§r§9§o，穿着全套可有§6不死之身§r");
        translationBuilder.add("item.hender.henderarmoritemchestplate.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.henderarmoritemleggings.shift_tooltip", "§9§o此物为§6§m恒之护腿§r§9§o，含有§6超高防御§r§9§o，穿着全套可有§6不死之身§r");
        translationBuilder.add("item.hender.henderarmoritemleggings.tooltip", "§9按下§6§oshift§r§9显示工具提示");
        translationBuilder.add("item.hender.henderarmoritemboots.shift_tooltip", "§9§o此物为§6§m恒之靴子§r§9§o，含有§6超高防御§r§9§o，穿着全套可有§6不死之身§r");
        translationBuilder.add("item.hender.henderarmoritemboots.tooltip", "§9按下§6§oshift§r§9显示工具提示");

        translationBuilder.add(String.valueOf(HenderVillagers.HENGMOU_MASTER), "Hengmou233");

        translationBuilder.add("hender.box", "恒之箱");

        translationBuilder.add(ModItems.HENDER_MACE, "§6恒之重锤");

    }
}
