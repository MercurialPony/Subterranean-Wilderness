package melonslise.subwild.common.init;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public final class SubWildConfiguredPlacements
{
	public static final ConfiguredPlacement<?>
		CONFIGURED_AIR_CAVE = SubWildPlacements.CAVE.get().configure(NoPlacementConfig.NO_PLACEMENT_CONFIG),
		CONFIGURED_LIQUID_CAVE = Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 2f));

	private SubWildConfiguredPlacements() {}
}