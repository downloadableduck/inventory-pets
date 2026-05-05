package com.jeff.invpets.neoforge;

import net.neoforged.fml.common.Mod;

import com.jeff.invpets.InventoryPets;

@Mod(InventoryPets.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        InventoryPets.init();
    }
}
