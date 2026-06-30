package com.jeff.invpets.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class STFUAgainEntityMixin {

    @Shadow
    private int id;

    @Inject(at = @At("HEAD"), method = "getId", cancellable = true)
    private void stfu(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.id);
    }
}
