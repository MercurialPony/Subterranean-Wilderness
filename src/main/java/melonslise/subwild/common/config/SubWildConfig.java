package melonslise.subwild.common.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

public class SubWildConfig
{
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue EXPENSIVE_SCAN, GENERATE_BUTTONS, GENERATE_VINES, GENERATE_PUDDLES, GENERATE_STAIRS, GENERATE_SLABS, GENERATE_PATCHES, GENERATE_SPELEOTHEMS, GENERATE_FOXFIRES;
	public static final ForgeConfigSpec.IntValue SLOPE_THRESHOLD, SLOPE_CHANCE, SLOPE_THRESHOLD_CHANCE;
	public static final ForgeConfigSpec.DoubleValue ROCKY_BUTTONS_CHANCE, ICY_ROCKY_BUTTONS_CHANCE, MOSSY_ROCKY_BUTTONS_CHANCE, SANDY_ROCKY_BUTTONS_CHANCE;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> DIMENSION_WHITELIST;

	static
	{
		final ForgeConfigSpec.Builder cfg = new ForgeConfigSpec.Builder();

		EXPENSIVE_SCAN = cfg.comment("Enables the mod to decorate underground structures and allows for more compatibility with other terrain gen mods at the expense of slower performance. Note that caves in worlds with the same seed may be populated differently depending on this setting").define("Expensive Scan", true);
		SLOPE_THRESHOLD = cfg.comment("The amount of non-solid blocks in front of a cave wall at which stairs and slabs spawn at a reduced rate to avoid high density narrow caves").defineInRange("Slope Threshold", 3, 0, 16);
		DIMENSION_WHITELIST = cfg.comment("The dimensions in which cave biomes will generate in").defineList("Dimension Whitelist", Lists.newArrayList("minecraft:overworld"), e -> e instanceof String);

		cfg.push("Features");
			GENERATE_BUTTONS = cfg.comment("Enable to generate stone buttons in rocky cave biomes").define("Generate Buttons", true);
			GENERATE_VINES = cfg.comment("Enable to generate vines in fungal, lush and mossy cave biomes").define("Generate Vines", true);
			GENERATE_PUDDLES = cfg.comment("Enable to generate puddles in lush, mossy muddy, dead coral cave biomes").define("Generate Puddles", true);
			GENERATE_STAIRS = cfg.comment("Enable to generate stairs in cave biomes. Slope Generation Chance must also be above 0 for stairs to generate.").define("Generate Stairs", true);
			GENERATE_SLABS = cfg.comment("Enable to generate slabs in cave biomes. Slope Generation Chance must also be above 0 for slabs to generate.").define("Generate Slabs", true);
			GENERATE_PATCHES = cfg.comment("Enable to generate patches (varying heights of slabs and snow) in cave biomes.").define("Generate Patches", true);
			GENERATE_SPELEOTHEMS = cfg.comment("Enable to generate speleothems in cave biomes.").define("Generate Speleothems", true);
			GENERATE_FOXFIRES = cfg.comment("Enable to generate glowing foxfires in cave biomes.").define("Generate Foxfires", true);
		cfg.pop();

		cfg.push("Frequencies");//.comment("Note that frequency calculation varies between different features, so a slope chance of 8 might not match a button chance of 8 for example.");
			SLOPE_CHANCE = cfg.comment("The chance of a slab or stairs generating normally in caves. Higher numbers increase the amount of slabs/stairs/slopes. Zero should stop generating them entirely.").defineInRange("Slope Generation Chance", 6, 0, 8);
			SLOPE_THRESHOLD_CHANCE = cfg.comment("The chance of a slab or stairs generating when within the Slope Threshold. Should be lower than the Slope Generation Chance.").defineInRange("Slope Threshold Generation Chance", 2, 0, 7);

			cfg.push("Rocky Caves").comment("The chance of things generating in rocky caves, as a percentage. Higher percentages increase the amount.");
				ROCKY_BUTTONS_CHANCE = cfg.comment("Use the \"Generate Buttons\" config option to stop generating them entirely.").defineInRange("Button Generation Chance", 7.14, 1.00, 100.00);
			cfg.pop();

			cfg.push("Icy Rocky Caves").comment("The chance of things generating in icy rocky caves, as a percentage. Higher percentages increase the amount.");
				ICY_ROCKY_BUTTONS_CHANCE = cfg.comment("Use the \"Generate Buttons\" config option to stop generating them entirely.").defineInRange("Button Generation Chance", 25.00, 1.00, 100.00);
			cfg.pop();

			cfg.push("Mossy Rocky Caves").comment("The chance of things generating in mossy rocky caves, as a percentage. Higher percentages increase the amount.");
				MOSSY_ROCKY_BUTTONS_CHANCE = cfg.comment("Use the \"Generate Buttons\" config option to stop generating them entirely.").defineInRange("Button Generation Chance", 7.14, 1.00, 100.00);
			cfg.pop();

			cfg.push("Sandy Rocky Caves").comment("The chance of things generating in sandy rocky caves, as a percentage. Higher percentages increase the amount.");
				SANDY_ROCKY_BUTTONS_CHANCE = cfg.comment("Use the \"Generate Buttons\" config option to stop generating them entirely.").defineInRange("Button Generation Chance", 7.14, 1.00, 100.00);
			cfg.pop();
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