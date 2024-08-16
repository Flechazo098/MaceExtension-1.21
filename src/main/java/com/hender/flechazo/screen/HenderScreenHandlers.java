package com.hender.flechazo.screen;

import com.hender.Hender;
import com.hender.flechazo.data.SynthesizerData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class HenderScreenHandlers {
    public static final ScreenHandlerType<SynthesizerScreenHandler> SYNTHESIZER_SCREEN_HANDLER=
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Hender.MOD_ID, "synthesizer"),
                    new ExtendedScreenHandlerType<>(SynthesizerScreenHandler::new, SynthesizerData.CODEC));

    public static void registerScreenHandler(){

    }
}
