package com.jeff.invpets.mixin;

import com.jeff.invpets.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryMixin extends AbstractRecipeBookScreen<@NotNull InventoryMenu> {

    private LivingEntity firstEntity;
    private LivingEntity secondEntity;
    private LivingEntity thirdEntity;
    private LivingEntity fourthEntity;
    double catDist = 0;
    public InventoryMixin(InventoryMenu menu, RecipeBookComponent component, Inventory inventory, Component title) {
        super(menu, component, inventory, title);
    }

    @Inject(at = @At("HEAD"), method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V")
    private void extract(GuiGraphicsExtractor graphics, int xMouse, int yMouse, float a, CallbackInfo ci) {
        Level level = Minecraft.getInstance().level;
        if (this.firstEntity == null) {
            this.firstEntity = Utils.getEntity(level);
        } if (this.secondEntity == null) {
            this.secondEntity = Utils.getEntity(level);
        } if (this.thirdEntity == null) {
            this.thirdEntity = Utils.getEntity(level);
        } if (this.fourthEntity == null) {
            this.fourthEntity = Utils.getEntity(level);
        }
        this.inventory_pets$addBehaviour(graphics, this.firstEntity);
    }

    @Unique
    private void inventory_pets$addBehaviour(GuiGraphicsExtractor graphics, LivingEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity was null! Figure your sutff out.");
        }
        entity.tick();
        entity.walkAnimation.setSpeed(0.3f);
        int xo = this.leftPos;
        int yo = this.topPos;
        catDist -= 0.25;
        xo += 100;
        yo += 32;

        if (catDist < -250) {
            return;
        }
        InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, (int) (xo + catDist), yo, xo + 75, yo + 78, 30, 0.0625F, 80, 111, entity);
    }
}
