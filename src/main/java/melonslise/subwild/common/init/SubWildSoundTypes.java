package melonslise.subwild.common.init;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;

public final class SubWildSoundTypes
{
	public static final SoundType WATER =
			new ForgeSoundType(1f, 1f, () -> SoundEvents.GENERIC_SPLASH,
					() -> SoundEvents.GENERIC_SWIM, () -> SoundEvents.GENERIC_SPLASH,
					() -> SoundEvents.BOAT_PADDLE_WATER, () -> SoundEvents.GENERIC_SPLASH);
}