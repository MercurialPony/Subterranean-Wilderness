package melonslise.subwild.common.init;

import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;

public final class SubWildConfiguredPlacements
{
	public static final ConfiguredPlacement<?>
		CONFIGURED_AIR_CAVE = SubWildPlacements.CAVE.get().configure(NoPlacementConfig.NO_PLACEMENT_CONFIG),
		CONFIGURED_LIQUID_CAVE = SubWildPlacements.LIQUID_CAVE.get().configure(NoPlacementConfig.NO_PLACEMENT_CONFIG);

	private SubWildConfiguredPlacements() {}
}