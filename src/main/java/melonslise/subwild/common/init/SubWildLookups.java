
package melonslise.subwild.common.init;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.Tags;

// FIXME Use tag sizes for map init
public final class SubWildLookups
{
	public static HashMap<Block, Block> STAIRS, SLABS, SPELEOS, FROZEN_SPELEOS, MOLTEN, MOSSY, WET, HOT;

	public static HashMap<Block, HashMap<Block, Block>> ORE_TABLE = new HashMap<>(13 + 4); // 4 for ice

	public static void init()
	{
		STAIRS = new HashMap<>(13);
		SLABS = new HashMap<>(13);
		SPELEOS = new HashMap<>(9);
		FROZEN_SPELEOS = new HashMap<>(9 + 1 * 4); // 1 icicle and 4 tagged ice blocks
		MOLTEN = new HashMap<>(11);
		MOSSY = new HashMap<>(15);
		WET = new HashMap<>(5);
		HOT = new HashMap<>(11);

		ORE_TABLE.put(Blocks.SANDSTONE, new HashMap<>(7));
		ORE_TABLE.put(Blocks.SMOOTH_SANDSTONE, new HashMap<>(7));
		ORE_TABLE.put(Blocks.RED_SANDSTONE, new HashMap<>(7));
		ORE_TABLE.put(Blocks.SMOOTH_RED_SANDSTONE, new HashMap<>(7));
		ORE_TABLE.put(Blocks.TERRACOTTA, new HashMap<>(7));
		ORE_TABLE.put(Blocks.WHITE_TERRACOTTA, new HashMap<>(7));
		ORE_TABLE.put(Blocks.ORANGE_TERRACOTTA, new HashMap<>(7));
		ORE_TABLE.put(Blocks.YELLOW_TERRACOTTA, new HashMap<>(7));
		ORE_TABLE.put(Blocks.LIGHT_GRAY_TERRACOTTA, new HashMap<>(7));
		ORE_TABLE.put(Blocks.BROWN_TERRACOTTA, new HashMap<>(7));
		ORE_TABLE.put(Blocks.RED_TERRACOTTA, new HashMap<>(7));
		ORE_TABLE.put(Blocks.BLACKSTONE, new HashMap<>(7));
		ORE_TABLE.put(Blocks.BASALT, new HashMap<>(7));

		STAIRS.put(Blocks.STONE, Blocks.STONE_STAIRS);
		STAIRS.put(Blocks.GRANITE, Blocks.GRANITE_STAIRS);
		STAIRS.put(Blocks.DIORITE, Blocks.DIORITE_STAIRS);
		STAIRS.put(Blocks.ANDESITE, Blocks.ANDESITE_STAIRS);
		STAIRS.put(Blocks.COBBLESTONE, Blocks.COBBLESTONE_STAIRS);
		STAIRS.put(Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE_STAIRS);
		STAIRS.put(Blocks.SANDSTONE, Blocks.SANDSTONE_STAIRS);
		STAIRS.put(Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_SANDSTONE_STAIRS);
		STAIRS.put(Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE_STAIRS);
		STAIRS.put(Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE_STAIRS);
		STAIRS.put(Blocks.PRISMARINE, Blocks.PRISMARINE_STAIRS);
		STAIRS.put(Blocks.BLACKSTONE, Blocks.BLACKSTONE_STAIRS);
		STAIRS.put(Blocks.DIRT, SubWildBlocks.DIRT_STAIRS.get());
		STAIRS.put(SubWildBlocks.MOSSY_DIRT.get(), SubWildBlocks.DIRT_STAIRS.get());

		SLABS.put(Blocks.STONE, Blocks.STONE_SLAB);
		SLABS.put(Blocks.GRANITE, Blocks.GRANITE_SLAB);
		SLABS.put(Blocks.DIORITE, Blocks.DIORITE_SLAB);
		SLABS.put(Blocks.ANDESITE, Blocks.ANDESITE_SLAB);
		SLABS.put(Blocks.COBBLESTONE, Blocks.COBBLESTONE_SLAB);
		SLABS.put(Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE_SLAB);
		SLABS.put(Blocks.SANDSTONE, Blocks.SANDSTONE_SLAB);
		SLABS.put(Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_SANDSTONE_SLAB);
		SLABS.put(Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE_SLAB);
		SLABS.put(Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE_SLAB);
		SLABS.put(Blocks.PRISMARINE, Blocks.PRISMARINE_SLAB);
		SLABS.put(Blocks.BLACKSTONE, Blocks.BLACKSTONE_SLAB);
		SLABS.put(Blocks.DIRT, SubWildBlocks.DIRT_SLAB.get());
		SLABS.put(SubWildBlocks.MOSSY_DIRT.get(), SubWildBlocks.DIRT_SLAB.get());

		SPELEOS.put(Blocks.STONE, SubWildBlocks.STONE_SPELEOTHEM.get());
		SPELEOS.put(Blocks.GRANITE, SubWildBlocks.GRANITE_SPELEOTHEM.get());
		SPELEOS.put(Blocks.DIORITE, SubWildBlocks.DIORITE_SPELEOTHEM.get());
		SPELEOS.put(Blocks.ANDESITE, SubWildBlocks.ANDESITE_SPELEOTHEM.get());
		SPELEOS.put(Blocks.SANDSTONE, SubWildBlocks.SANDSTONE_SPELEOTHEM.get());
		SPELEOS.put(Blocks.RED_SANDSTONE, SubWildBlocks.RED_SANDSTONE_SPELEOTHEM.get());
		SPELEOS.put(Blocks.OBSIDIAN, SubWildBlocks.OBSIDIAN_SPELEOTHEM.get());
		SPELEOS.put(Blocks.BLACKSTONE, SubWildBlocks.BLACKSTONE_SPELEOTHEM.get());
		SPELEOS.put(Blocks.BASALT, SubWildBlocks.BASALT_SPELEOTHEM.get());

		FROZEN_SPELEOS.put(Blocks.STONE, SubWildBlocks.FROZEN_STONE_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.GRANITE, SubWildBlocks.FROZEN_GRANITE_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.DIORITE, SubWildBlocks.FROZEN_DIORITE_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.ANDESITE, SubWildBlocks.FROZEN_ANDESITE_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.SANDSTONE, SubWildBlocks.FROZEN_SANDSTONE_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.RED_SANDSTONE, SubWildBlocks.FROZEN_RED_SANDSTONE_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.OBSIDIAN, SubWildBlocks.FROZEN_OBSIDIAN_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.BLACKSTONE, SubWildBlocks.FROZEN_BLACKSTONE_SPELEOTHEM.get());
		FROZEN_SPELEOS.put(Blocks.BASALT, SubWildBlocks.FROZEN_BASALT_SPELEOTHEM.get());

		put(FROZEN_SPELEOS, BlockTags.ICE, SubWildBlocks.ICICLE.get());

		MOLTEN.put(Blocks.STONE, SubWildBlocks.MOLTEN_STONE.get());
		MOLTEN.put(Blocks.GRANITE, SubWildBlocks.MOLTEN_GRANITE.get());
		MOLTEN.put(Blocks.DIORITE, SubWildBlocks.MOLTEN_DIORITE.get());
		MOLTEN.put(Blocks.ANDESITE, SubWildBlocks.MOLTEN_ANDESITE.get());
		MOLTEN.put(Blocks.SANDSTONE, SubWildBlocks.MOLTEN_SANDSTONE.get());
		MOLTEN.put(Blocks.SMOOTH_SANDSTONE, SubWildBlocks.MOLTEN_SMOOTH_SANDSTONE.get());
		MOLTEN.put(Blocks.RED_SANDSTONE, SubWildBlocks.MOLTEN_RED_SANDSTONE.get());
		MOLTEN.put(Blocks.SMOOTH_RED_SANDSTONE, SubWildBlocks.MOLTEN_SMOOTH_RED_SANDSTONE.get());
		MOLTEN.put(Blocks.OBSIDIAN, SubWildBlocks.MOLTEN_OBSIDIAN.get());
		MOLTEN.put(Blocks.BLACKSTONE, SubWildBlocks.MOLTEN_BLACKSTONE.get());
		MOLTEN.put(Blocks.BASALT, SubWildBlocks.MOLTEN_BASALT.get());

		MOSSY.put(Blocks.DIRT, SubWildBlocks.MOSSY_DIRT.get());
		MOSSY.put(Blocks.SAND, SubWildBlocks.MOSSY_SAND.get());
		MOSSY.put(Blocks.RED_SAND, SubWildBlocks.MOSSY_RED_SAND.get());
		MOSSY.put(Blocks.GRAVEL, SubWildBlocks.MOSSY_GRAVEL.get());
		MOSSY.put(Blocks.STONE, SubWildBlocks.MOSSY_STONE.get());
		MOSSY.put(Blocks.GRANITE, SubWildBlocks.MOSSY_GRANITE.get());
		MOSSY.put(Blocks.DIORITE, SubWildBlocks.MOSSY_DIORITE.get());
		MOSSY.put(Blocks.ANDESITE, SubWildBlocks.MOSSY_ANDESITE.get());
		MOSSY.put(Blocks.SANDSTONE, SubWildBlocks.MOSSY_SANDSTONE.get());
		MOSSY.put(Blocks.SMOOTH_SANDSTONE, SubWildBlocks.MOSSY_SMOOTH_SANDSTONE.get());
		MOSSY.put(Blocks.RED_SANDSTONE, SubWildBlocks.MOSSY_RED_SANDSTONE.get());
		MOSSY.put(Blocks.SMOOTH_RED_SANDSTONE, SubWildBlocks.MOSSY_SMOOTH_RED_SANDSTONE.get());
		MOSSY.put(Blocks.OBSIDIAN, SubWildBlocks.MOSSY_OBSIDIAN.get());
		MOSSY.put(Blocks.BLACKSTONE, SubWildBlocks.MOSSY_BLACKSTONE.get());
		MOSSY.put(Blocks.BASALT, SubWildBlocks.MOSSY_BASALT.get());

		WET.put(Blocks.STONE, SubWildBlocks.WET_STONE.get());
		WET.put(Blocks.GRANITE, SubWildBlocks.WET_GRANITE.get());
		WET.put(Blocks.DIORITE, SubWildBlocks.WET_DIORITE.get());
		WET.put(Blocks.ANDESITE, SubWildBlocks.WET_ANDESITE.get());
		WET.put(Blocks.OBSIDIAN, SubWildBlocks.WET_OBSIDIAN.get());

		HOT.put(Blocks.STONE, SubWildBlocks.HOT_STONE.get());
		HOT.put(Blocks.GRANITE, SubWildBlocks.HOT_GRANITE.get());
		HOT.put(Blocks.DIORITE, SubWildBlocks.HOT_DIORITE.get());
		HOT.put(Blocks.ANDESITE, SubWildBlocks.HOT_ANDESITE.get());
		HOT.put(Blocks.SANDSTONE, SubWildBlocks.HOT_SANDSTONE.get());
		HOT.put(Blocks.SMOOTH_SANDSTONE, SubWildBlocks.HOT_SMOOTH_SANDSTONE.get());
		HOT.put(Blocks.RED_SANDSTONE, SubWildBlocks.HOT_RED_SANDSTONE.get());
		HOT.put(Blocks.SMOOTH_RED_SANDSTONE, SubWildBlocks.HOT_SMOOTH_RED_SANDSTONE.get());
		HOT.put(Blocks.OBSIDIAN, SubWildBlocks.HOT_OBSIDIAN.get());
		HOT.put(Blocks.BLACKSTONE, SubWildBlocks.HOT_BLACKSTONE.get());
		HOT.put(Blocks.BASALT, SubWildBlocks.HOT_BASALT.get());

		put(ORE_TABLE.get(Blocks.SANDSTONE), Tags.Blocks.ORES_COAL, SubWildBlocks.SANDSTONE_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.SANDSTONE), Tags.Blocks.ORES_IRON, SubWildBlocks.SANDSTONE_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.SANDSTONE), Tags.Blocks.ORES_GOLD, SubWildBlocks.SANDSTONE_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.SANDSTONE), Tags.Blocks.ORES_LAPIS, SubWildBlocks.SANDSTONE_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.SANDSTONE), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.SANDSTONE_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.SANDSTONE), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.SANDSTONE_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.SANDSTONE), Tags.Blocks.ORES_EMERALD, SubWildBlocks.SANDSTONE_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.SMOOTH_SANDSTONE), Tags.Blocks.ORES_COAL, SubWildBlocks.SMOOTH_SANDSTONE_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_SANDSTONE), Tags.Blocks.ORES_IRON, SubWildBlocks.SMOOTH_SANDSTONE_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_SANDSTONE), Tags.Blocks.ORES_GOLD, SubWildBlocks.SMOOTH_SANDSTONE_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_SANDSTONE), Tags.Blocks.ORES_LAPIS, SubWildBlocks.SMOOTH_SANDSTONE_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_SANDSTONE), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.SMOOTH_SANDSTONE_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_SANDSTONE), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.SMOOTH_SANDSTONE_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_SANDSTONE), Tags.Blocks.ORES_EMERALD, SubWildBlocks.SMOOTH_SANDSTONE_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.RED_SANDSTONE), Tags.Blocks.ORES_COAL, SubWildBlocks.RED_SANDSTONE_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_SANDSTONE), Tags.Blocks.ORES_IRON, SubWildBlocks.RED_SANDSTONE_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_SANDSTONE), Tags.Blocks.ORES_GOLD, SubWildBlocks.RED_SANDSTONE_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_SANDSTONE), Tags.Blocks.ORES_LAPIS, SubWildBlocks.RED_SANDSTONE_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_SANDSTONE), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.RED_SANDSTONE_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_SANDSTONE), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.RED_SANDSTONE_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_SANDSTONE), Tags.Blocks.ORES_EMERALD, SubWildBlocks.RED_SANDSTONE_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.SMOOTH_RED_SANDSTONE), Tags.Blocks.ORES_COAL, SubWildBlocks.SMOOTH_RED_SANDSTONE_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_RED_SANDSTONE), Tags.Blocks.ORES_IRON, SubWildBlocks.SMOOTH_RED_SANDSTONE_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_RED_SANDSTONE), Tags.Blocks.ORES_GOLD, SubWildBlocks.SMOOTH_RED_SANDSTONE_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_RED_SANDSTONE), Tags.Blocks.ORES_LAPIS, SubWildBlocks.SMOOTH_RED_SANDSTONE_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_RED_SANDSTONE), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.SMOOTH_RED_SANDSTONE_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_RED_SANDSTONE), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.SMOOTH_RED_SANDSTONE_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.SMOOTH_RED_SANDSTONE), Tags.Blocks.ORES_EMERALD, SubWildBlocks.SMOOTH_RED_SANDSTONE_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.TERRACOTTA), Tags.Blocks.ORES_COAL, SubWildBlocks.TERRACOTTA_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.TERRACOTTA), Tags.Blocks.ORES_IRON, SubWildBlocks.TERRACOTTA_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.TERRACOTTA), Tags.Blocks.ORES_GOLD, SubWildBlocks.TERRACOTTA_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.TERRACOTTA), Tags.Blocks.ORES_LAPIS, SubWildBlocks.TERRACOTTA_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.TERRACOTTA), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.TERRACOTTA_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.TERRACOTTA), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.TERRACOTTA_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.TERRACOTTA), Tags.Blocks.ORES_EMERALD, SubWildBlocks.TERRACOTTA_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.WHITE_TERRACOTTA), Tags.Blocks.ORES_COAL, SubWildBlocks.WHITE_TERRACOTTA_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.WHITE_TERRACOTTA), Tags.Blocks.ORES_IRON, SubWildBlocks.WHITE_TERRACOTTA_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.WHITE_TERRACOTTA), Tags.Blocks.ORES_GOLD, SubWildBlocks.WHITE_TERRACOTTA_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.WHITE_TERRACOTTA), Tags.Blocks.ORES_LAPIS, SubWildBlocks.WHITE_TERRACOTTA_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.WHITE_TERRACOTTA), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.WHITE_TERRACOTTA_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.WHITE_TERRACOTTA), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.WHITE_TERRACOTTA_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.WHITE_TERRACOTTA), Tags.Blocks.ORES_EMERALD, SubWildBlocks.WHITE_TERRACOTTA_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.ORANGE_TERRACOTTA), Tags.Blocks.ORES_COAL, SubWildBlocks.ORANGE_TERRACOTTA_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.ORANGE_TERRACOTTA), Tags.Blocks.ORES_IRON, SubWildBlocks.ORANGE_TERRACOTTA_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.ORANGE_TERRACOTTA), Tags.Blocks.ORES_GOLD, SubWildBlocks.ORANGE_TERRACOTTA_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.ORANGE_TERRACOTTA), Tags.Blocks.ORES_LAPIS, SubWildBlocks.ORANGE_TERRACOTTA_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.ORANGE_TERRACOTTA), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.ORANGE_TERRACOTTA_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.ORANGE_TERRACOTTA), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.ORANGE_TERRACOTTA_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.ORANGE_TERRACOTTA), Tags.Blocks.ORES_EMERALD, SubWildBlocks.ORANGE_TERRACOTTA_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.YELLOW_TERRACOTTA), Tags.Blocks.ORES_COAL, SubWildBlocks.YELLOW_TERRACOTTA_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.YELLOW_TERRACOTTA), Tags.Blocks.ORES_IRON, SubWildBlocks.YELLOW_TERRACOTTA_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.YELLOW_TERRACOTTA), Tags.Blocks.ORES_GOLD, SubWildBlocks.YELLOW_TERRACOTTA_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.YELLOW_TERRACOTTA), Tags.Blocks.ORES_LAPIS, SubWildBlocks.YELLOW_TERRACOTTA_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.YELLOW_TERRACOTTA), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.YELLOW_TERRACOTTA_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.YELLOW_TERRACOTTA), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.YELLOW_TERRACOTTA_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.YELLOW_TERRACOTTA), Tags.Blocks.ORES_EMERALD, SubWildBlocks.YELLOW_TERRACOTTA_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.LIGHT_GRAY_TERRACOTTA), Tags.Blocks.ORES_COAL, SubWildBlocks.LIGHT_GRAY_TERRACOTTA_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.LIGHT_GRAY_TERRACOTTA), Tags.Blocks.ORES_IRON, SubWildBlocks.LIGHT_GRAY_TERRACOTTA_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.LIGHT_GRAY_TERRACOTTA), Tags.Blocks.ORES_GOLD, SubWildBlocks.LIGHT_GRAY_TERRACOTTA_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.LIGHT_GRAY_TERRACOTTA), Tags.Blocks.ORES_LAPIS, SubWildBlocks.LIGHT_GRAY_TERRACOTTA_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.LIGHT_GRAY_TERRACOTTA), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.LIGHT_GRAY_TERRACOTTA_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.LIGHT_GRAY_TERRACOTTA), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.LIGHT_GRAY_TERRACOTTA_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.LIGHT_GRAY_TERRACOTTA), Tags.Blocks.ORES_EMERALD, SubWildBlocks.LIGHT_GRAY_TERRACOTTA_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.BROWN_TERRACOTTA), Tags.Blocks.ORES_COAL, SubWildBlocks.BROWN_TERRACOTTA_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.BROWN_TERRACOTTA), Tags.Blocks.ORES_IRON, SubWildBlocks.BROWN_TERRACOTTA_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.BROWN_TERRACOTTA), Tags.Blocks.ORES_GOLD, SubWildBlocks.BROWN_TERRACOTTA_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.BROWN_TERRACOTTA), Tags.Blocks.ORES_LAPIS, SubWildBlocks.BROWN_TERRACOTTA_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.BROWN_TERRACOTTA), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.BROWN_TERRACOTTA_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.BROWN_TERRACOTTA), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.BROWN_TERRACOTTA_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.BROWN_TERRACOTTA), Tags.Blocks.ORES_EMERALD, SubWildBlocks.BROWN_TERRACOTTA_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.RED_TERRACOTTA), Tags.Blocks.ORES_COAL, SubWildBlocks.RED_TERRACOTTA_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_TERRACOTTA), Tags.Blocks.ORES_IRON, SubWildBlocks.RED_TERRACOTTA_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_TERRACOTTA), Tags.Blocks.ORES_GOLD, SubWildBlocks.RED_TERRACOTTA_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_TERRACOTTA), Tags.Blocks.ORES_LAPIS, SubWildBlocks.RED_TERRACOTTA_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_TERRACOTTA), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.RED_TERRACOTTA_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_TERRACOTTA), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.RED_TERRACOTTA_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.RED_TERRACOTTA), Tags.Blocks.ORES_EMERALD, SubWildBlocks.RED_TERRACOTTA_EMERALD_ORE.get());

		HashMap<Block, Block> lookup = new HashMap<>(7);
		put(lookup, Tags.Blocks.ORES_COAL, SubWildBlocks.ICE_COAL_ORE.get());
		put(lookup, Tags.Blocks.ORES_IRON, SubWildBlocks.ICE_IRON_ORE.get());
		put(lookup, Tags.Blocks.ORES_GOLD, SubWildBlocks.ICE_GOLD_ORE.get());
		put(lookup, Tags.Blocks.ORES_LAPIS, SubWildBlocks.ICE_LAPIS_ORE.get());
		put(lookup, Tags.Blocks.ORES_REDSTONE, SubWildBlocks.ICE_REDSTONE_ORE.get());
		put(lookup, Tags.Blocks.ORES_DIAMOND, SubWildBlocks.ICE_DIAMOND_ORE.get());
		put(lookup, Tags.Blocks.ORES_EMERALD, SubWildBlocks.ICE_EMERALD_ORE.get());
		put(ORE_TABLE, BlockTags.ICE, lookup);

		put(ORE_TABLE.get(Blocks.BLACKSTONE), Tags.Blocks.ORES_COAL, SubWildBlocks.BLACKSTONE_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.BLACKSTONE), Tags.Blocks.ORES_IRON, SubWildBlocks.BLACKSTONE_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.BLACKSTONE), Tags.Blocks.ORES_GOLD, SubWildBlocks.BLACKSTONE_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.BLACKSTONE), Tags.Blocks.ORES_LAPIS, SubWildBlocks.BLACKSTONE_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.BLACKSTONE), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.BLACKSTONE_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.BLACKSTONE), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.BLACKSTONE_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.BLACKSTONE), Tags.Blocks.ORES_EMERALD, SubWildBlocks.BLACKSTONE_EMERALD_ORE.get());

		put(ORE_TABLE.get(Blocks.BASALT), Tags.Blocks.ORES_COAL, SubWildBlocks.BASALT_COAL_ORE.get());
		put(ORE_TABLE.get(Blocks.BASALT), Tags.Blocks.ORES_IRON, SubWildBlocks.BASALT_IRON_ORE.get());
		put(ORE_TABLE.get(Blocks.BASALT), Tags.Blocks.ORES_GOLD, SubWildBlocks.BASALT_GOLD_ORE.get());
		put(ORE_TABLE.get(Blocks.BASALT), Tags.Blocks.ORES_LAPIS, SubWildBlocks.BASALT_LAPIS_ORE.get());
		put(ORE_TABLE.get(Blocks.BASALT), Tags.Blocks.ORES_REDSTONE, SubWildBlocks.BASALT_REDSTONE_ORE.get());
		put(ORE_TABLE.get(Blocks.BASALT), Tags.Blocks.ORES_DIAMOND, SubWildBlocks.BASALT_DIAMOND_ORE.get());
		put(ORE_TABLE.get(Blocks.BASALT), Tags.Blocks.ORES_EMERALD, SubWildBlocks.BASALT_EMERALD_ORE.get());
	}

	public static <K, V> void put(Map<K, V> lookup, Tag.Named<K> keys, V value)
	{
		for(K key : keys.getValues())
			lookup.put(key, value);
	}
}