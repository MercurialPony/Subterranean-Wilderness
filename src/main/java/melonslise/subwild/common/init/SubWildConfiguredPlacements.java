package melonslise.subwild.common.init;

import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;

public final class SubWildConfiguredPlacements
{
	public static final ConfiguredPlacement<?>
		CONFIGURED_AIR_CAVE = SubWildPlacements.CAVE.get().configured(NoPlacementConfig.NONE),
		CONFIGURED_LIQUID_CAVE = SubWildPlacements.LIQUID_CAVE.get().configured(NoPlacementConfig.NONE);
}