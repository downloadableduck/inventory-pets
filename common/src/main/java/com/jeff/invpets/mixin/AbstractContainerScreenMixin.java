package com.jeff.invpets.mixin;

import com.jeff.invpets.Utils;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.biome.Biomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMixin {

    @Mutable
    @Shadow
    @Final
    public static Identifier INVENTORY_LOCATION;

    @Mutable
    @Shadow
    @Final
    private static Identifier SLOT_HIGHLIGHT_FRONT_SPRITE;

    @Inject(at = @At(value = "HEAD"), method = "<init>(Lnet/minecraft/world/inventory/AbstractContainerMenu;Lnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/network/chat/Component;)V")
    private static void init(AbstractContainerMenu menu, Inventory inventory, Component title, CallbackInfo ci) {
        INVENTORY_LOCATION = Utils.getBackground();
        if (Utils.biome != null) {
            if (Utils.biome.equals(Biomes.SNOWY_TAIGA)) {
                SLOT_HIGHLIGHT_FRONT_SPRITE = Utils.withInventoryPetsNamespac("container/highlight");
            }  else {
                SLOT_HIGHLIGHT_FRONT_SPRITE = Identifier.withDefaultNamespace("container/slot_highlight_front");
            }
        }
    }
}
