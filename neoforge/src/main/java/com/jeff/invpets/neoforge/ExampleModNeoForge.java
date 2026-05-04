package com.jeff.invpets.neoforge;

import net.neoforged.fml.common.Mod;

import com.jeff.invpets.ExampleMod;
import net.neoforged.neoforge.client.loading.NeoForgeLoadingOverlay;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        NeoForgeLoadingOverlay
        // Run our common setup.
        ExampleMod.init();
    }
}
