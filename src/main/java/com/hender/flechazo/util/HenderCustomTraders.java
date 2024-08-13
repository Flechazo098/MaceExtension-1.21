package com.hender.flechazo.util;

import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.item.ModItems;
import com.hender.flechazo.villager.HenderVillagers;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.village.TradeOffers;



public class HenderCustomTraders {
    public static void registerHenderCustomTraders() {
        TradeOfferHelper.registerVillagerOffers(HenderVillagers.HENGMOU_MASTER, 1, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(ModItems.HENDER_TOOL, 32, 1, ModItems.HENDER_TOOL_HELMET, 1, 1, 20, 0.5f));
        });
        TradeOfferHelper.registerVillagerOffers(HenderVillagers.HENGMOU_MASTER, 2, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(ModItems.HENDER_TOOL, 55, 1, ModItems.HENDER_TOOL_CHESTPLATE, 1, 1, 20, 0.5f));
        });
        TradeOfferHelper.registerVillagerOffers(HenderVillagers.HENGMOU_MASTER, 3, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(ModItems.HENDER_TOOL, 46, 1, ModItems.HENDER_TOOL_LEGGINGS, 1, 1, 20, 0.5f));
        });
        TradeOfferHelper.registerVillagerOffers(HenderVillagers.HENGMOU_MASTER, 4, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(ModItems.HENDER_TOOL, 25, 1, ModItems.HENDER_TOOL_HELMET, 1, 1, 20, 0.5f));
        });
        TradeOfferHelper.registerVillagerOffers(HenderVillagers.HENGMOU_MASTER, 5, factories -> {
            factories.add(new TradeOffers.ProcessItemFactory(ModItems.HENDER_TOOL, 18, 1, ModBlocks.HENDER_TOOL_BLOCK.asItem(), 1, 1, 20, 0.5f));
        });
    }
}
