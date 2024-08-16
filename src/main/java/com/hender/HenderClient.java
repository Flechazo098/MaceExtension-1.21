package com.hender;

import com.hender.flechazo.screen.HenderScreenHandlers;
import com.hender.flechazo.screen.SynthesizerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class HenderClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(HenderScreenHandlers.SYNTHESIZER_SCREEN_HANDLER, SynthesizerScreen::new);
    }
}
