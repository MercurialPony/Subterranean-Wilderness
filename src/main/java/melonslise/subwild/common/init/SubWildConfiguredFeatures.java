package melonslise.subwild.common.init;

import melonslise.subwild.common.world.gen.feature.CaveRangeConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public final class SubWildConfiguredFeatures
{
	public static final ConfiguredFeature<?, ?>
		CONFIGURED_WET_AIR_CAVE_DECO = add("wet_air_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.DEAD_CORAL_CAVE, 0d, 0.8d).add(SubWildFeatures.VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_WET_LIQUID_CAVE_DECO = add("wet_liquic_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.CORAL_CAVE, 0d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_LIQUID_CAVE)),
		CONFIGURED_TAIGA_CAVE_DECO = add("taiga_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.PODZOL_CAVE, 0d, 0.4d).add(SubWildFeatures.MUDDY_CAVE, 0.4d, 0.8d).add(SubWildFeatures.LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_EXTREME_HILLS_CAVE_DECO = add("extreme_hills_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.ROCKY_CAVE, 0d, 0.4d).add(SubWildFeatures.ICY_ROCKY_CAVE, 0.4d, 0.8d).add(SubWildFeatures.ROCKY_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_JUNGLE_CAVE_DECO = add("jungle_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.LUSH_CAVE, 0d, 0.8d).add(SubWildFeatures.LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_MESA_CAVE_DECO = add("mesa_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.MESA_CAVE, 0d, 0.8d).add(SubWildFeatures.SANDY_RED_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_PLAINS_CAVE_DECO = add("plains_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.MUDDY_CAVE, 0d, 0.8d).add(SubWildFeatures.VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_SAVANNA_CAVE_DECO = add("savanna_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.SANDY_ROCKY_CAVE, 0d, 0.8d).add(SubWildFeatures.SANDY_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_ICY_CAVE_DECO = add("icy_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.ICY_CAVE, 0d, 0.8d).add(SubWildFeatures.ICY_ROCKY_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_FOREST_CAVE_DECO = add("forest_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.MOSSY_ROCKY_CAVE, 0d, 0.8d).add(SubWildFeatures.LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_DESERT_CAVE_DECO = add("desert_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.SANDY_CAVE, 0d, 0.8d).add(SubWildFeatures.SANDY_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_SWAMP_CAVE_DECO = add("swamp_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.MOSSY_CAVE, 0d, 0.8d).add(SubWildFeatures.LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_MUSHROOM_CAVE_DECO = add("mushroom_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.FUNGAL_CAVE, 0d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE)),
		CONFIGURED_ROCKY_CAVE_DECO = add("rockyt_cave_deco", SubWildFeatures.CAVE_DECO.get().withConfiguration(CaveRangeConfig.builder().add(SubWildFeatures.ROCKY_CAVE, 0d, 0.8d).add(SubWildFeatures.VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(SubWildConfiguredPlacements.CONFIGURED_AIR_CAVE));

	public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> add(String name, ConfiguredFeature<FC, ?> cf)
	{
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, cf);
	}
}