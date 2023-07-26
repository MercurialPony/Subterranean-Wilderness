package melonslise.subwild.common.init;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.world.gen.feature.CavePlacement;
import melonslise.subwild.common.world.gen.feature.LiquidCavePlacement;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class SubWildPlacements
{
	public static final DeferredRegister<FeatureDecorator<?>> PLACEMENTS = DeferredRegister.create(ForgeRegistries.DECORATORS, SubWild.ID);

	public static final RegistryObject<FeatureDecorator<NoneDecoratorConfiguration>>
		CAVE = add("cave", new CavePlacement(NoneDecoratorConfiguration.CODEC)),
		LIQUID_CAVE = add("liquid_cave", new LiquidCavePlacement(NoneDecoratorConfiguration.CODEC));

	public static void register()
	{
		PLACEMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static <T extends DecoratorConfiguration> RegistryObject<FeatureDecorator<T>> add(String name, FeatureDecorator<T> pl)
	{
		return PLACEMENTS.register(name, () -> pl);
	}
}