package melonslise.subwild.common.init;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.world.gen.feature.CavePlacement;
import melonslise.subwild.common.world.gen.feature.LiquidCavePlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class SubWildPlacements
{
	public static final DeferredRegister<Placement<?>> PLACEMENTS = DeferredRegister.create(ForgeRegistries.DECORATORS, SubWild.ID);

	public static final RegistryObject<Placement<NoPlacementConfig>>
		CAVE = add("cave", new CavePlacement(NoPlacementConfig.CODEC)),
		LIQUID_CAVE = add("liquid_cave", new LiquidCavePlacement(NoPlacementConfig.CODEC));

	private SubWildPlacements() {}

	public static void register()
	{
		PLACEMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static <T extends IPlacementConfig> RegistryObject<Placement<T>> add(String name, Placement<T> pl)
	{
		return PLACEMENTS.register(name, () -> pl);
	}
}