package com.jeff.invpets.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.model.animal.feline.AdultCatModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

public final class ExampleModFabricClient implements ClientModInitializer {
    private LayerDefinition createCatBodyMesh() {
        AdultCatModel.createBodyMesh(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
    }
}
