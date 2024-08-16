package com.hender;

import com.hender.flechazo.block.ModBlocks;
import com.hender.flechazo.block.entity.HenderBlockEntities;
import com.hender.flechazo.item.ModItemGroups;
import com.hender.flechazo.item.ModItems;
import com.hender.flechazo.screen.HenderScreenHandlers;
import com.hender.flechazo.util.HenderCustomTraders;
import com.hender.flechazo.villager.HenderVillagers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hender implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public  static  final  String MOD_ID = "hender";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerModBlocks();
		HenderCustomTraders.registerHenderCustomTraders();
		HenderVillagers.registerHenderVillagers();
		HenderBlockEntities.registerBlockEntities();
		HenderScreenHandlers.registerScreenHandler();

		LOGGER.info("Hello Fabric world!");
	}
}