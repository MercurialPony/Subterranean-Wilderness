package melonslise.subwild.common.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Maps;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.world.gen.feature.CaveDecoFeature;
import melonslise.subwild.common.world.gen.feature.CaveRangeConfig;
import melonslise.subwild.common.world.gen.feature.cavetype.BasicCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.CaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.CoralCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.DeadCoralCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.FungalCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.IcyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.IcyRockyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.LushCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.LushVolcanicCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.MesaCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.MossyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.MossyRockyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.MuddyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.PodzolCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.RockyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.SandyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.SandyRockyCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.SandyVolcanicCaveType;
import melonslise.subwild.common.world.gen.feature.cavetype.VolcanicCaveType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

public final class SubWildFeatures
{
	private SubWildFeatures() {}

	public static final HashMap<ResourceLocation, CaveType> CAVE_TYPES = Maps.newHashMap();

	public static final CaveType
		BASIC_CAVE = new BasicCaveType(SubWild.ID, "basic"),
		DEAD_CORAL_CAVE = new DeadCoralCaveType(SubWild.ID, "dead_coral"),
		SANDY_CAVE = new SandyCaveType(SubWild.ID, "sandy", false),
		SANDY_RED_CAVE = new SandyCaveType(SubWild.ID, "sandy_red", true),
		SANDY_ROCKY_CAVE = new SandyRockyCaveType(SubWild.ID, "sandy_rocky", false),
		SANDY_RED_ROCKY_CAVE = new SandyRockyCaveType(SubWild.ID, "sandy_red_rocky", true),
		MESA_CAVE = new MesaCaveType(SubWild.ID, "mesa"),
		LUSH_CAVE = new LushCaveType(SubWild.ID, "lush"),
		LUSH_VOLCANIC_CAVE = new LushVolcanicCaveType(SubWild.ID, "lush_volcanic"),
		ROCKY_CAVE = new RockyCaveType(SubWild.ID, "rocky"),
		ICY_CAVE = new IcyCaveType(SubWild.ID, "icy"),
		ICY_ROCKY_CAVE = new IcyRockyCaveType(SubWild.ID, "icy_rocky"),
		FUNGAL_CAVE = new FungalCaveType(SubWild.ID, "fungal"),
		MUDDY_CAVE = new MuddyCaveType(SubWild.ID, "muddy"),
		PODZOL_CAVE = new PodzolCaveType(SubWild.ID, "podzol"),
		MOSSY_CAVE = new MossyCaveType(SubWild.ID, "mossy"),
		MOSSY_ROCKY_CAVE = new MossyRockyCaveType(SubWild.ID, "mossy_rocky"),
		VOLCANIC_CAVE = new VolcanicCaveType(SubWild.ID, "volcanic"),
		SANDY_VOLCANIC_CAVE = new SandyVolcanicCaveType(SubWild.ID, "sandy_volcanic", false),
		SANDY_RED_VOLCANIC_CAVE = new SandyVolcanicCaveType(SubWild.ID, "sandy_red_volcanic", true),
		CORAL_CAVE = new CoralCaveType(SubWild.ID, "coral");

	public static final List<Feature> FEATURES = new ArrayList<>(1);

	public static final Feature
		CAVE_DECO = add("cave", new CaveDecoFeature(CaveRangeConfig::deserialize));

	public static void register(RegistryEvent.Register<Feature<?>> event)
	{
		for(Feature<?> feature : FEATURES)
			event.getRegistry().register(feature);
	}

	public static void addFeatures()
	{
		GenerationStage.Decoration dec = GenerationStage.Decoration.UNDERGROUND_DECORATION;
		ConfiguredPlacement liq = Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 2f));
		ConfiguredPlacement air = !SubWildConfig.EXPENSIVE_SCAN.get() ? Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.AIR, 2f)) : SubWildPlacements.CAVE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG);
		for(Biome biome : ForgeRegistries.BIOMES.getValues())
		{
			switch(biome.getCategory())
			{
			case BEACH:
			case OCEAN:
			case RIVER:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(DEAD_CORAL_CAVE, 0d, 0.8d).addCaveType(VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(CORAL_CAVE, 0d, 1d).build()).withPlacement(liq));
				break;
			case TAIGA:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(PODZOL_CAVE, 0d, 0.4d).addCaveType(MUDDY_CAVE, 0.4d, 0.8d).addCaveType(LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case EXTREME_HILLS:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(ROCKY_CAVE, 0d, 0.4d).addCaveType(ICY_ROCKY_CAVE, 0.4d, 0.8d).addCaveType(ROCKY_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case JUNGLE:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(LUSH_CAVE, 0d, 0.8d).addCaveType(LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case MESA:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(MESA_CAVE, 0d, 0.8d).addCaveType(SANDY_RED_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case PLAINS:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(MUDDY_CAVE, 0d, 0.8d).addCaveType(VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case SAVANNA:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(SANDY_ROCKY_CAVE, 0d, 0.8d).addCaveType(SANDY_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case ICY:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(ICY_CAVE, 0d, 0.8d).addCaveType(ICY_ROCKY_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case FOREST:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(MOSSY_ROCKY_CAVE, 0d, 0.8d).addCaveType(LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case DESERT:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(SANDY_CAVE, 0d, 0.8d).addCaveType(SANDY_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case SWAMP:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(MOSSY_CAVE, 0d, 0.8d).addCaveType(LUSH_VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			case MUSHROOM:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(FUNGAL_CAVE, 0d, 1d).build()).withPlacement(air));
				break;
			default:
				biome.addFeature(dec, CAVE_DECO.withConfiguration(CaveRangeConfig.builder().addCaveType(ROCKY_CAVE, 0d, 0.8d).addCaveType(VOLCANIC_CAVE, 0.8d, 1d).build()).withPlacement(air));
				break;
			}
		}
	}

	public static Feature add(String name, Feature feature)
	{
		FEATURES.add((Feature) feature.setRegistryName(SubWild.ID, name));
		return feature;
	}

	public static CaveType addCaveType(CaveType type)
	{
		CAVE_TYPES.put(type.name, type);
		return type;
	}
}