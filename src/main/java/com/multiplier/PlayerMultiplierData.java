package com.multiplier.data;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMultiplierData {

    public static class Data {
        public boolean enabled = false;
        public int multiplier = 1;
    }

    private static final HashMap<UUID, Data> PLAYER_DATA = new HashMap<>();

    public static Data get(UUID uuid) {
        return PLAYER_DATA.computeIfAbsent(uuid, id -> new Data());
    }

    public static void setEnabled(UUID uuid, boolean value) {
        get(uuid).enabled = value;
    }

    public static void setMultiplier(UUID uuid, int value) {
        get(uuid).multiplier = Math.max(1, value);
    }

    public static boolean isEnabled(UUID uuid) {
        return get(uuid).enabled;
    }

    public static int getMultiplier(UUID uuid) {
        return get(uuid).multiplier;
    }
}