package com.hender.flechazo.villager;

import com.hender.flechazo.Hender;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class HenderPointOfInterestTypes {
    public static final RegistryKey<PointOfInterestType> HENGMOU_KEY = of("hengmou_poi");
    private static RegistryKey<PointOfInterestType> of(String id){
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(Hender.MOD_ID, id));
    }
}
