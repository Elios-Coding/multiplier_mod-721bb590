package com.multiplier;

import com.multiplier.command.MultiplierCommand;
import com.multiplier.events.BlockBreakHandler;
import net.fabricmc.api.ModInitializer;

public class MultiplierMod implements ModInitializer {

    @Override
    public void onInitialize() {
        MultiplierCommand.register();
        BlockBreakHandler.register();
    }
}