package com.hender.flechazo.villager;

import com.google.common.collect.ImmutableSet;
import com.hender.flechazo.Hender;
import com.hender.flechazo.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.jetbrains.annotations.Nullable;

public class HenderVillagers {
    public static final VillagerProfession HENGMOU_MASTER = register("hengmou_master",
            HenderPointOfInterestTypes.HENGMOU_KEY, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);
    public static final PointOfInterestType HENGMOU_POI = registerPointOfInterestType("hengmou_poi", ModBlocks.HENDER_TOOL_BLOCK);
    private static VillagerProfession register(String id, RegistryKey<PointOfInterestType> heldWorkstation, @Nullable SoundEvent workSound){
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(Hender.MOD_ID, id),
                new VillagerProfession(id, entry -> entry.matchesKey(heldWorkstation), entry -> entry.matchesKey(heldWorkstation),
                        ImmutableSet.of(), ImmutableSet.of(), workSound));
    }
    private static PointOfInterestType registerPointOfInterestType(String id, Block block){
        return PointOfInterestHelper.register(Identifier.of(Hender.MOD_ID, id), 1, 1, block);

    }    public static void registerHenderVillagers(){

    }
}
