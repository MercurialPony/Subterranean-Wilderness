package melonslise.subwild.common.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

public class SubWildConfig
{
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue EXPENSIVE_SCAN, GENERATE_BUTTONS, GENERATE_VINES;
	public static final ForgeConfigSpec.IntValue SLOPE_THRESHOLD, SLOPE_CHANCE, SLOPE_THRESHOLD_CHANCE;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> DIMENSION_WHITELIST;

	static
	{
		ForgeConfigSpec.Builder cfg = new ForgeConfigSpec.Builder();

		EXPENSIVE_SCAN = cfg.comment("Enables the mod to decorate underground structures and allows for more compatibility with other terrain gen mods at the expense of slower performance. Note that caves in worlds with the same seed may be populated differently depending on this setting").define("Expensive Scan", true);
		SLOPE_THRESHOLD = cfg.comment("The amount of non-solid blocks in front of a cave wall at which stairs and slabs spawn at a reduced rate to avoid high density narrow caves").defineInRange("Slope Threshold", 2, 0, 16);
		DIMENSION_WHITELIST = cfg.comment("The dimensions in which cave biomes will generate in").defineList("Dimension Whitelist", Lists.newArrayList("minecraft:overworld"), e -> e instanceof String);

		cfg.push("Features");
		GENERATE_BUTTONS = cfg.comment("Whether or not we should generate stone buttons in rocky cave biomes").define("Generate Buttons", true);
		GENERATE_VINES = cfg.comment("Whether or not we should generate vines in cave biomes").define("Generate Vines", true);
		cfg.pop();

		cfg.push("Frequencies");
		SLOPE_CHANCE = cfg.comment("The chance of a slab, stair or other type of slope generating normally in caves. Higher numbers increase the amount of slabs/stairs/slopes. Zero should stop generating them entirely.").defineInRange("Slope Generation Chance", 6, 0, 8);
		SLOPE_THRESHOLD_CHANCE = cfg.comment("The chance of a slab, stair or other type of slope generating when within the Slope Threshold. Should be lower than the Slope Generation Chance.").defineInRange("Slope Threshold Generation Chance", 2, 0, 7);
		cfg.pop();

		SPEC = cfg.build();
	}

	public static boolean isAllowed(final World world)
	{
		final String name = world.getDimensionKey().getLocation().toString();
		for(final String key : DIMENSION_WHITELIST.get())
			if(key.equals(name))
				return true;
		return false;
	}
}