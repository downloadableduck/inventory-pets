package com.jeff.invpets;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.camel.CamelHusk;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.animal.chicken.ChickenVariants;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.dolphin.Dolphin;
import net.minecraft.world.entity.animal.fish.Cod;
import net.minecraft.world.entity.animal.fish.Pufferfish;
import net.minecraft.world.entity.animal.fish.Salmon;
import net.minecraft.world.entity.animal.fish.TropicalFish;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.nautilus.Nautilus;
import net.minecraft.world.entity.animal.panda.Panda;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.polarbear.PolarBear;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.animal.squid.GlowSquid;
import net.minecraft.world.entity.animal.squid.Squid;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import static com.jeff.invpets.InventoryPets.MOD_ID;

public class Utils {

    public static ResourceKey<@NotNull Biome> biome;
    public static int randomX;
    public static boolean shouldTurnAround;
    public static boolean isFacingLeft;

    public static Identifier getBackground() {
        int random = (int) (Math.random() * 7);
        switch(random) {
            case 0 -> {
                biome = Biomes.THE_END;
                return withInventoryPetsNamespac("textures/gui/inventory/end.png");
            }
            case 1 -> {
                biome = Biomes.FOREST;
                return withInventoryPetsNamespac("textures/gui/inventory/forest.png");
            } case 2 -> {
                biome = Biomes.DESERT;
                return withInventoryPetsNamespac("textures/gui/inventory/desert.png");
            } case 3 -> {
                biome = Biomes.OCEAN;
                return withInventoryPetsNamespac("textures/gui/inventory/sea.png");
            } case 4 -> {
                biome = Biomes.CHERRY_GROVE;
                return withInventoryPetsNamespac("textures/gui/inventory/cherry_grove.png");
            } case 5 -> {
                biome = Biomes.NETHER_WASTES;
                return withInventoryPetsNamespac("textures/gui/inventory/nether.png");
            } case 6 -> {
                biome = Biomes.SNOWY_TAIGA;
                return withInventoryPetsNamespac("textures/gui/inventory/snowy_taiga.png");
            }
        }
        return null;
    }

    public static Identifier withInventoryPetsNamespac(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static LivingEntity getEntity(Level level) {
        int random = (int) (Math.random() * 15);
        if (biome.equals(Biomes.THE_END)) {
            return new EnderMan(EntityTypes.ENDERMAN, level);
        } else if (biome.equals(Biomes.FOREST)) {
            switch(random) {
                case 0 -> {
                    return new Panda(EntityTypes.PANDA, level);
                }
                case 1 -> {
                    return new Zombie(EntityTypes.ZOMBIE, level);
                }
                case 2 -> {
                    return new Skeleton(EntityTypes.SKELETON, level);
                }
                case 3 -> {
                    return new Drowned(EntityTypes.DROWNED, level);
                }
                case 4 -> {
                    return new Spider(EntityTypes.SPIDER, level);
                }
                case 5 -> {
                    return new Wolf(EntityTypes.WOLF, level);
                } case 6 -> {
                    return new Creeper(EntityTypes.CREEPER, level);
                } case 7 -> {
                    return new Fox(EntityTypes.FOX, level);
                } case 8 -> {
                    return new Villager(EntityTypes.VILLAGER, level);
                } case 9 -> {
                    return new Bee(EntityTypes.BEE, level);
                } case 10 -> {
                    return new Sheep(EntityTypes.SHEEP, level);
                } case 11 -> {
                    return new Chicken(EntityTypes.CHICKEN, level);
                } case 12 -> {
                    return new Cow(EntityTypes.COW, level);
                } case 13 -> {
                    return new Pig(EntityTypes.PIG, level);
                } case 14 -> {
                    return new Pillager(EntityTypes.PILLAGER, level);
                }
            }
        } else if (biome.equals(Biomes.DESERT)) {
            switch(random) {
                case 0, 1 -> {
                    return new Husk(EntityTypes.HUSK, level);
                } case 2, 3 -> {
                    return new Camel(EntityTypes.CAMEL, level);
                } case 4, 5 -> {
                    Rabbit rabbit = new Rabbit(EntityTypes.RABBIT, level);
                    rabbit.setVariant(Rabbit.Variant.GOLD);
                    return rabbit;
                } case 6, 7 -> {
                    return new Parched(EntityTypes.PARCHED, level);
                } case 8, 9 -> {
                    Villager villager = new Villager(EntityTypes.VILLAGER, level);
                    villager.setVillagerData(villager.getVillagerData().withType((Holder) villager.typeHolder()).withType(level.registryAccess(), VillagerType.DESERT));
                    return villager;
                } case 10, 11 -> {
                    return new Armadillo(EntityTypes.ARMADILLO, level);
                } case 12, 13 -> {
                    return new CamelHusk(EntityTypes.CAMEL_HUSK, level);
                } case 14, 15 -> {
                    Chicken chicken = new Chicken(EntityTypes.CHICKEN, level);

                    var access = level.registryAccess().lookupOrThrow(Registries.CHICKEN_VARIANT).getOrThrow(ChickenVariants.WARM);
                    chicken.setVariant(access);
                    return chicken;
                }
            }
        } else if (biome.equals(Biomes.OCEAN)) {
            switch(random) {
                case 0, 1 -> {
                    return new Salmon(EntityTypes.SALMON, level);
                } case 2, 14 -> {
                    return new Cod(EntityTypes.COD, level);
                } case 3 -> {
                    return new Guardian(EntityTypes.GUARDIAN, level);
                } case 4 -> {
                    return new ElderGuardian(EntityTypes.ELDER_GUARDIAN, level);
                } case 5 -> {
                    return new TropicalFish(EntityTypes.TROPICAL_FISH, level);
                } case 6 -> {
                    return new Dolphin(EntityTypes.DOLPHIN, level);
                } case 7 -> {
                    return new Nautilus(EntityTypes.NAUTILUS, level);
                } case 8 -> {
                    return new Drowned(EntityTypes.DROWNED, level);
                } case 9, 10 -> {
                    return new Squid(EntityTypes.SQUID, level);
                } case 11 -> {
                    return new GlowSquid(EntityTypes.GLOW_SQUID, level);
                } case 12 -> {
                    return new Axolotl(EntityTypes.AXOLOTL, level);
                } case 13 -> {
                    return new Pufferfish(EntityTypes.PUFFERFISH, level);
                }
            }
        } else if (biome.equals(Biomes.CHERRY_GROVE)) {
            switch(random) {
                case 0, 1, 2, 3 -> {
                    return new Bee(EntityTypes.BEE, level);
                } case 4, 5, 6 -> {
                    return new Chicken(EntityTypes.CHICKEN, level);
                } case 7, 8, 9 -> {
                    return new Cow(EntityTypes.COW, level);
                } case 10, 11, 12 -> {
                    return new Sheep(EntityTypes.SHEEP, level);
                } case 13, 14 -> {
                    return new Pig(EntityTypes.PIG, level);
                }
            }
        } else if (biome.equals(Biomes.NETHER_WASTES)) {
            switch(random) {
                case 0, 1 -> {
                    return new ZombifiedPiglin(EntityTypes.ZOMBIFIED_PIGLIN, level);
                } case 2, 3 -> {
                    return new Piglin(EntityTypes.PIGLIN, level);
                } case 4, 5 -> {
                    return new PiglinBrute(EntityTypes.PIGLIN_BRUTE, level);
                } case 6, 7 -> {
                    return new Ghast(EntityTypes.GHAST, level);
                } case 8, 9 -> {
                    return new Strider(EntityTypes.STRIDER, level);
                } case 10, 11 -> {
                    return new Blaze(EntityTypes.BLAZE, level);
                } case 12 -> {
                    return new WitherSkeleton(EntityTypes.WITHER_SKELETON, level);
                } case 13 -> {
                    return new Hoglin(EntityTypes.HOGLIN, level);
                } case 14 -> {
                    return new Zoglin(EntityTypes.ZOGLIN, level);
                }
            }
        } else if (biome.equals(Biomes.SNOWY_TAIGA)) {
            switch(random) {
                case 0, 1, 2 -> {
                    Fox fox = new Fox(EntityTypes.FOX, level);
                    fox.setVariant(Fox.Variant.SNOW);
                    return fox;
                } case 3, 4, 5 -> {
                    Rabbit rabbit = new Rabbit(EntityTypes.RABBIT, level);
                    rabbit.setVariant(Rabbit.Variant.WHITE);
                } case 6, 7, 8 -> {
                    return new Wolf(EntityTypes.WOLF, level);
                } case 9, 10, 11 -> {
                    return new Goat(EntityTypes.GOAT, level);
                } case 12, 13, 14 -> {
                    return new PolarBear(EntityTypes.POLAR_BEAR, level);
                }
            }
        }
        return new Zombie(EntityTypes.ZOMBIE, level);
    }
}
