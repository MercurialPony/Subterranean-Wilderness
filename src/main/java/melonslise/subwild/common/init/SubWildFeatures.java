package melonslise.subwild.common.init;

import java.util.HashMap;

import com.google.common.collect.Maps;

import melonslise.subwild.SubWild;
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
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class SubWildFeatures
{
	private SubWildFeatures() {}

	public static final HashMap<ResourceLocation, CaveType> CAVE_TYPES = Maps.newHashMap();

	public static final CaveType
		BASIC_CAVE = addCaveType(new BasicCaveType(SubWild.ID, "basic")),
		DEAD_CORAL_CAVE = addCaveType(new DeadCoralCaveType(SubWild.ID, "dead_coral")),
		SANDY_CAVE = addCaveType(new SandyCaveType(SubWild.ID, "sandy", false)),
		SANDY_RED_CAVE = addCaveType(new SandyCaveType(SubWild.ID, "sandy_red", true)),
		SANDY_ROCKY_CAVE = addCaveType(new SandyRockyCaveType(SubWild.ID, "sandy_rocky", false)),
		SANDY_RED_ROCKY_CAVE = addCaveType(new SandyRockyCaveType(SubWild.ID, "sandy_red_rocky", true)),
		MESA_CAVE = addCaveType(new MesaCaveType(SubWild.ID, "mesa")),
		LUSH_CAVE = addCaveType(new LushCaveType(SubWild.ID, "lush")),
		LUSH_VOLCANIC_CAVE = addCaveType(new LushVolcanicCaveType(SubWild.ID, "lush_volcanic")),
		ROCKY_CAVE = addCaveType(new RockyCaveType(SubWild.ID, "rocky")),
		ICY_CAVE = addCaveType(new IcyCaveType(SubWild.ID, "icy")),
		ICY_ROCKY_CAVE = addCaveType(new IcyRockyCaveType(SubWild.ID, "icy_rocky")),
		FUNGAL_CAVE = addCaveType(new FungalCaveType(SubWild.ID, "fungal")),
		MUDDY_CAVE = addCaveType(new MuddyCaveType(SubWild.ID, "muddy")),
		PODZOL_CAVE = addCaveType(new PodzolCaveType(SubWild.ID, "podzol")),
		MOSSY_CAVE = addCaveType(new MossyCaveType(SubWild.ID, "mossy")),
		MOSSY_ROCKY_CAVE = addCaveType(new MossyRockyCaveType(SubWild.ID, "mossy_rocky")),
		VOLCANIC_CAVE = addCaveType(new VolcanicCaveType(SubWild.ID, "volcanic")),
		SANDY_VOLCANIC_CAVE = addCaveType(new SandyVolcanicCaveType(SubWild.ID, "sandy_volcanic", false)),
		SANDY_RED_VOLCANIC_CAVE = addCaveType(new SandyVolcanicCaveType(SubWild.ID, "sandy_red_volcanic", true)),
		CORAL_CAVE = addCaveType(new CoralCaveType(SubWild.ID, "coral"));

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SubWild.ID);

	public static final RegistryObject<Feature<CaveRangeConfig>>
		CAVE_DECO = add("cave", new CaveDecoFeature(CaveRangeConfig.CODEC));

	public static void register()
	{
		FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static void addFeatures(BiomeLoadingEvent event)
	{
		GenerationStage.Decoration stage = GenerationStage.Decoration.UNDERGROUND_DECORATION;
		switch (event.getCategory())
		{
		case BEACH:
		case OCEAN:
		case RIVER:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_WET_AIR_CAVE_DECO);
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_WET_LIQUID_CAVE_DECO);
			break;
		case TAIGA:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_TAIGA_CAVE_DECO);
			break;
		case EXTREME_HILLS:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_EXTREME_HILLS_CAVE_DECO);
			break;
		case JUNGLE:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_JUNGLE_CAVE_DECO);
			break;
		case MESA:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_MESA_CAVE_DECO);
			break;
		case PLAINS:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_PLAINS_CAVE_DECO);
			break;
		case SAVANNA:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_SAVANNA_CAVE_DECO);
			break;
		case ICY:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_ICY_CAVE_DECO);
			break;
		case FOREST:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_FOREST_CAVE_DECO);
			break;
		case DESERT:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_DESERT_CAVE_DECO);
			break;
		case SWAMP:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_SWAMP_CAVE_DECO);
			break;
		case MUSHROOM:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_MUSHROOM_CAVE_DECO);
			break;
		default:
			event.getGeneration().withFeature(stage, SubWildConfiguredFeatures.CONFIGURED_ROCKY_CAVE_DECO);
			break;
		}
	}

	public static <T extends IFeatureConfig> RegistryObject<Feature<T>> add(String name, Feature<T> feature)
	{
		return FEATURES.register(name, () -> feature);
	}

	public static CaveType addCaveType(CaveType type)
	{
		CAVE_TYPES.put(type.name, type);
		return type;
	}
}