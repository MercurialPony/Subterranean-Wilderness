package melonslise.subwild.common.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

public class SubWildConfig
{
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue EXPENSIVE_SCAN;
	public static final ForgeConfigSpec.IntValue SLOPE_THRESHOLD;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> DIMENSION_WHITELIST;
	public static final ForgeConfigSpec.BooleanValue GENERATE_BUTTONS;

	static
	{
		ForgeConfigSpec.Builder cfg = new ForgeConfigSpec.Builder();

		EXPENSIVE_SCAN = cfg.comment("Enables the mod to decorate underground structures and allows for more compatibility with other terrain gen mods at the expense of slower performance. Note that caves in worlds with the same seed may be populated differently depending on this setting").define("Expensive Scan", true);
		SLOPE_THRESHOLD = cfg.comment("The amount of non-solid blocks in front of a cave wall at which stairs and slabs spawn at a reduced rate to avoid high density narrow caves").defineInRange("Slope Threshold", 2, 0, 16);
		DIMENSION_WHITELIST = cfg.comment("The dimensions in which cave biomes will generate in").defineList("Dimension Whitelist", Lists.newArrayList("minecraft:overworld"), e -> e instanceof String);
		GENERATE_BUTTONS = cfg.comment("Whether or not we should generate stone buttons in rocky cave biomes").define("Generate Buttons", true);

		SPEC = cfg.build();
	}

	public static boolean isAllowed(World world)
	{
		String name = world.getDimensionKey().getLocation().toString();
		for(String key : DIMENSION_WHITELIST.get())
			if(key.equals(name))
				return true;
		return false;
	}
}