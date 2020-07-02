/*
package melonslise.expedition.unused;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import com.google.common.collect.Lists;

import melonslise.expedition.common.util.ExpeditionUtils;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;

public final class ExpeditionConfig
{
	private ExpeditionConfig() {}

	public static final ForgeConfigSpec SPEC;

	// TODO Parse to states right away if possible
	public static final ForgeConfigSpec.ConfigValue<List<String>> SPELEOTHEM_MATERIALS;

	static
	{
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		builder.push("Stone Types");
		SPELEOTHEM_MATERIALS = builder.comment("BLAH").define("Speleothem Materials", Lists.newArrayList("minecraft:stone", "minecraft:diorite", "minecraft:andesite", "minecraft:granite", "minecraft:sandstone", "minecraft:red_sandstone", "minecraft:obsidian", "minecraft:netherrack"), e -> e instanceof String);
		builder.pop();
		SPEC = builder.build();
	}

	public static final List<BlockState> SPELEOTHEM_STATES = Lists.newArrayList();

	public static void load()
	{
		SPELEOTHEM_STATES.clear();
		for(String str : SPELEOTHEM_MATERIALS.get())
		{
			BlockState state = ExpeditionUtils.stateFromString(str);
			if(state.isAir())
				LogManager.getLogger().error("Unknown blockstate: " + str);
			else
				SPELEOTHEM_STATES.add(state);
		}
	}
}
*/