package com.multiplier.events;

import com.multiplier.data.PlayerMultiplierData;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BlockBreakHandler {

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {

            if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

            var data = PlayerMultiplierData.get(player.getUuid());

            if (!data.enabled) return;

            int multiplier = data.multiplier;

            List<ItemStack> drops = Block.getDroppedStacks(state, (net.minecraft.server.world.ServerWorld) world, pos, blockEntity, player, player.getMainHandStack());

            for (ItemStack drop : drops) {
                ItemStack copy = drop.copy();
                copy.setCount(copy.getCount() * multiplier);
                player.getInventory().insertStack(copy);
            }
        });
    }
}