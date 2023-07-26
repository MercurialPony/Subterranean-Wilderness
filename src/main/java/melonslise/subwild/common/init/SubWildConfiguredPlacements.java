package melonslise.subwild.common.init;

import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;

public final class SubWildConfiguredPlacements
{
	public static final ConfiguredDecorator<?>
		CONFIGURED_AIR_CAVE = SubWildPlacements.CAVE.get().configured(NoneDecoratorConfiguration.NONE),
		CONFIGURED_LIQUID_CAVE = SubWildPlacements.LIQUID_CAVE.get().configured(NoneDecoratorConfiguration.NONE);
}