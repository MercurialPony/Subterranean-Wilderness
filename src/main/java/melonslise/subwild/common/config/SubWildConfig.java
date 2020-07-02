package melonslise.subwild.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SubWildConfig
{
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue EXPENSIVE_SCAN;
	public static final ForgeConfigSpec.IntValue SLOPE_THRESHOLD;

	static
	{
		ForgeConfigSpec.Builder cfg = new ForgeConfigSpec.Builder();

		EXPENSIVE_SCAN = cfg.comment("Enables the mod to decorate underground structures and allows for more compatibility with other terrain gen mods at the expense of slower performance. Note that caves in worlds with the same seed may be populated differently depending on this setting").define("Expensive Scan", true);
		SLOPE_THRESHOLD = cfg.comment("The amount of non-solid blocks in front of a cave wall at which stairs and slabs spawn at a reduced rate to avoid high density narrow caves").defineInRange("Slope Threshold", 2, 0, 16);

		SPEC = cfg.build();
	}
}