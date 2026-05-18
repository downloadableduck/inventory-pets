package com.jeff.invpets.mixin;


import com.jeff.invpets.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screens.Screen;
import java.util.Random;

import static com.jeff.invpets.Utils.shouldTurnAround;
import static com.jeff.invpets.Utils.isFacingLeft;

@Mixin(InventoryScreen.class)
public abstract class InventoryMixin extends AbstractRecipeBookScreen<@NotNull InventoryMenu> {

    private LivingEntity firstEntity;

    double dist = 0;

    public InventoryMixin(InventoryMenu menu, RecipeBookComponent component, Inventory inventory, Component title) {
        super(menu, component, inventory, title);
    }

    @Inject(at = @At("HEAD"), method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V")
    private void extract(GuiGraphicsExtractor graphics, int xMouse, int yMouse, float a, CallbackInfo ci) {
        Level level = Minecraft.getInstance().level;
        if (this.firstEntity == null) {
            this.firstEntity = Utils.getEntity(level);
        }

        this.inventory_pets$addBehaviour(graphics, this.firstEntity);
    }


    @Unique
    private void inventory_pets$addBehaviour(GuiGraphicsExtractor graphics, LivingEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity was null! Figure your sutff out.");
        }
        shouldTurnAround = (int) (Math.random() * 200) == 1;
        if (shouldTurnAround) {
            isFacingLeft = !isFacingLeft;
        }
        entity.tick();
        if (entity.walkAnimation.speed() != 0.3f) {
            entity.walkAnimation.setSpeed(0.3f);
        }
        int xo = this.leftPos;
        int yo = this.topPos;
        int angle;
        int scale = 30;
        if (isFacingLeft) {
            dist -= 0.25;
            angle = 80;
        } else {
            dist += 0.25;
            angle = 5000000;
        }
        xo += 120;
        int guiScale = Minecraft.getInstance().options.guiScale().get();
        if (guiScale == 1 && Minecraft.getInstance().options.fullscreen().get()) {
            xo += 750;
        } else if (guiScale == 2 && !Minecraft.getInstance().options.fullscreen().get()) {
            xo -= 300;
        }
        yo += 32;
        int yPos = yo + 78;
        int extraX = 500;

        if (guiScale != 1 && guiScale != 2) {
            extraX = 78;
            if (dist + Utils.randomX < -5) {
                isFacingLeft = false;
            }
            if (dist + Utils.randomX > 250) {
                isFacingLeft = true;
            }
       } else if (guiScale == 1) {
            extraX = 500;
            if (dist + Utils.randomX < 0) {
                isFacingLeft = false;
            } if (dist + Utils.randomX > 750) {
                isFacingLeft = true;
            }
        } else if (guiScale == 2) {
            extraX = 500;
            if (dist + Utils.randomX < -250) {
                isFacingLeft = false;
            } if (dist + Utils.randomX > 150) {
                isFacingLeft = true;
            }
        }

        InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, (int) (Utils.randomX + dist), yo, xo + extraX, yPos, scale, 0.0625F, angle, 111, entity);
    }

    @Mixin(Minecraft.class)
    public abstract static class MinecraftMixin {

        @Inject(at = @At("HEAD"), method = "setScreen")
        private void setScreen(Screen screen, CallbackInfo ci) {

             //if (Minecraft.getInstance().options.guiScale().get() == 1) {
               //Utils.randomX = new Random().nextInt(-250, 5);
            //} else {
                Utils.randomX = new Random().nextInt(-5, 250);
            //}
            isFacingLeft = (int) (Math.random() * 2) == 0;
        }
    }
} 