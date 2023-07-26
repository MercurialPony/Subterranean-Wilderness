package melonslise.subwild.common.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

public final class SubWildSoundTypes
{
	public static final SoundType WATER =
			new ForgeSoundType(1f, 1f, () -> SoundEvents.GENERIC_SPLASH,
					() -> SoundEvents.GENERIC_SWIM, () -> SoundEvents.GENERIC_SPLASH,
					() -> SoundEvents.BOAT_PADDLE_WATER, () -> SoundEvents.GENERIC_SPLASH);
}