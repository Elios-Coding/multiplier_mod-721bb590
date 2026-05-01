package com.multiplier.command;

import com.multiplier.data.PlayerMultiplierData;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class MultiplierCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            dispatcher.register(CommandManager.literal("multiplier")
                .then(CommandManager.literal("ON")
                    .executes(context -> {
                        var player = context.getSource().getPlayer();
                        PlayerMultiplierData.setEnabled(player.getUuid(), true);

                        context.getSource().sendFeedback(
                            () -> Text.literal("Multiplier enabled"),
                            false
                        );
                        return 1;
                    })
                    .then(CommandManager.argument("value", net.minecraft.command.argument.IntegerArgumentType.integer(1))
                        .executes(context -> {
                            var player = context.getSource().getPlayer();
                            int value = net.minecraft.command.argument.IntegerArgumentType.getInteger(context, "value");

                            PlayerMultiplierData.setEnabled(player.getUuid(), true);
                            PlayerMultiplierData.setMultiplier(player.getUuid(), value);

                            context.getSource().sendFeedback(
                                () -> Text.literal("Multiplier enabled with value " + value),
                                false
                            );
                            return 1;
                        })
                    )
                )
                .then(CommandManager.literal("OFF")
                    .executes(context -> {
                        var player = context.getSource().getPlayer();
                        PlayerMultiplierData.setEnabled(player.getUuid(), false);

                        context.getSource().sendFeedback(
                            () -> Text.literal("Multiplier disabled"),
                            false
                        );
                        return 1;
                    })
                )
            );
        });
    }
}