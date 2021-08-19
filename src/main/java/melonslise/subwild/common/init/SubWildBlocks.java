package melonslise.subwild.common.init;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.block.DrippingBlock;
import melonslise.subwild.common.block.EncasedOreBlock;
import melonslise.subwild.common.block.EncasedSpeleothemBlock;
import melonslise.subwild.common.block.FoxfireBlock;
import melonslise.subwild.common.block.IcicleBlock;
import melonslise.subwild.common.block.MeltingPatchBlock;
import melonslise.subwild.common.block.MoltenBlock;
import melonslise.subwild.common.block.PatchBlock;
import melonslise.subwild.common.block.PuddleBlock;
import melonslise.subwild.common.block.RootsBlock;
import melonslise.subwild.common.block.SpeleothemBlock;
import melonslise.subwild.common.block.XpBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * 	Block#blocksMovement enables collision, while Material#blocksMovement is for certain logic
 * 	Block#isSolid means culling is performed on neighbors based on the shape, while Material#isSolid is also for certain logic
 * 	Material#isOpaque is used for certain logic too including chest logic
 */
public final class SubWildBlocks
{
	public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SubWild.ID);

	// TODO Reimplement harvestLevel and harvestTool
	// FIXME Speleothem pushreaction
	public static final RegistryObject<Block>
		DIRT_STAIRS = add("dirt_stairs", new StairBlock(Blocks.DIRT::defaultBlockState, Block.Properties.copy(Blocks.DIRT))),
		DIRT_SLAB = add("dirt_slab", new SlabBlock(Block.Properties.copy(Blocks.DIRT))),

		SHORT_FOXFIRE = add("short_foxfire", new FoxfireBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.NETHER_WART))),
		LONG_FOXFIRE = add("long_foxfire", new FoxfireBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.NETHER_WART))),

		LIGHT_BROWN_ROOTS = add("light_brown_roots", new RootsBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0.1f).sound(SoundType.NETHER_WART))),
		BROWN_ROOTS = add("brown_roots", new RootsBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0.1f).sound(SoundType.NETHER_WART))),
		WHITE_ROOTS = add("white_roots", new RootsBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0.1f).sound(SoundType.NETHER_WART))),
		LIGHT_ORANGE_ROOTS = add("light_orange_roots", new RootsBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0.1f).sound(SoundType.NETHER_WART))),
		DARK_BROWN_ROOTS = add("dark_brown_roots", new RootsBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0.1f).sound(SoundType.NETHER_WART))),
		ORANGE_ROOTS = add("orange_roots", new RootsBlock(Block.Properties.of(Material.PLANT).noCollission().strength(0.1f).sound(SoundType.NETHER_WART))),

		DIRT_PATCH = add("dirt_patch", new PatchBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.DIRT).strength(0.1f).sound(SoundType.GRAVEL).requiresCorrectToolForDrops())),
		MOSSY_DIRT_PATCH = add("mossy_dirt_patch", new PatchBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.PLANT).strength(0.1f).sound(SoundType.WET_GRASS).requiresCorrectToolForDrops())),
		PODZOL_PATCH = add("podzol_patch", new PatchBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.PODZOL).strength(0.1f).sound(SoundType.GRAVEL).requiresCorrectToolForDrops())),
		GRAVEL_PATCH = add("gravel_patch", new PatchBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.STONE).strength(0.15f).sound(SoundType.GRAVEL).requiresCorrectToolForDrops())),
		SAND_PATCH = add("sand_patch", new PatchBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.SAND).strength(0.1f).sound(SoundType.SAND).requiresCorrectToolForDrops())),
		RED_SAND_PATCH = add("red_sand_patch", new PatchBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.COLOR_ORANGE).strength(0.1f).sound(SoundType.SAND).requiresCorrectToolForDrops())),
		SNOW_PATCH = add("snow_patch", new PatchBlock(Block.Properties.of(Material.TOP_SNOW).randomTicks().strength(0.1f).sound(SoundType.SNOW).requiresCorrectToolForDrops())),
		ICE_PATCH = add("ice_patch", new MeltingPatchBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.ICE).friction(0.98f).strength(0.1f).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops())),
		WATER_PUDDLE = add("water_puddle", new PuddleBlock(Block.Properties.of(Material.TOP_SNOW, MaterialColor.WATER).noCollission().strength(0f).noDrops().sound(SubWildSoundTypes.WATER))),

		STONE_SPELEOTHEM = add("stone_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.STONE))),
		GRANITE_SPELEOTHEM = add("granite_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.GRANITE))),
		DIORITE_SPELEOTHEM = add("diorite_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.DIORITE))),
		ANDESITE_SPELEOTHEM = add("andesite_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.ANDESITE))),
		SANDSTONE_SPELEOTHEM = add("sandstone_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.SANDSTONE))),
		RED_SANDSTONE_SPELEOTHEM = add("red_sandstone_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.RED_SANDSTONE))),
		OBSIDIAN_SPELEOTHEM = add("obsidian_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.OBSIDIAN))),
		BLACKSTONE_SPELEOTHEM = add("blackstone_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.BLACKSTONE))),
		BASALT_SPELEOTHEM = add("basalt_speleothem", new SpeleothemBlock(Block.Properties.copy(Blocks.BASALT))),

		FROZEN_STONE_SPELEOTHEM = add("frozen_stone_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.STONE).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), STONE_SPELEOTHEM)),
		FROZEN_GRANITE_SPELEOTHEM = add("frozen_granite_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.DIRT).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), GRANITE_SPELEOTHEM)),
		FROZEN_DIORITE_SPELEOTHEM = add("frozen_diorite_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.QUARTZ).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), DIORITE_SPELEOTHEM)),
		FROZEN_ANDESITE_SPELEOTHEM = add("frozen_andesite_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.STONE).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), ANDESITE_SPELEOTHEM)),
		FROZEN_SANDSTONE_SPELEOTHEM = add("frozen_sandstone_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.SAND).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), SANDSTONE_SPELEOTHEM)),
		FROZEN_RED_SANDSTONE_SPELEOTHEM = add("frozen_red_sandstone_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.COLOR_ORANGE).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), RED_SANDSTONE_SPELEOTHEM)),
		FROZEN_OBSIDIAN_SPELEOTHEM = add("frozen_obsidian_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.COLOR_BLACK).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), OBSIDIAN_SPELEOTHEM)),
		FROZEN_BLACKSTONE_SPELEOTHEM = add("frozen_blackstone_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.COLOR_BLACK).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), BLACKSTONE_SPELEOTHEM)),
		FROZEN_BASALT_SPELEOTHEM = add("frozen_basalt_speleothem", new EncasedSpeleothemBlock(Block.Properties.of(Material.ICE, MaterialColor.COLOR_BLACK).friction(0.98f).strength(0.5f).sound(SoundType.GLASS).noOcclusion(), BASALT_SPELEOTHEM)),

		ICICLE = add("icicle", new IcicleBlock(Block.Properties.copy(Blocks.ICE))),

		MOLTEN_STONE = add("molten_stone", new MoltenBlock(Block.Properties.copy(Blocks.STONE).lightLevel(state -> 5))),
		MOLTEN_GRANITE = add("molten_granite", new MoltenBlock(Block.Properties.copy(Blocks.GRANITE).lightLevel(state -> 5))),
		MOLTEN_DIORITE = add("molten_diorite", new MoltenBlock(Block.Properties.copy(Blocks.DIORITE).lightLevel(state -> 5))),
		MOLTEN_ANDESITE = add("molten_andesite", new MoltenBlock(Block.Properties.copy(Blocks.ANDESITE).lightLevel(state -> 5))),
		MOLTEN_SANDSTONE = add("molten_sandstone", new MoltenBlock(Block.Properties.copy(Blocks.SANDSTONE).lightLevel(state -> 5))),
		MOLTEN_SMOOTH_SANDSTONE = add("molten_smooth_sandstone", new MoltenBlock(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE).lightLevel(state -> 5))),
		MOLTEN_RED_SANDSTONE = add("molten_red_sandstone", new MoltenBlock(Block.Properties.copy(Blocks.SANDSTONE).lightLevel(state -> 5))),
		MOLTEN_SMOOTH_RED_SANDSTONE = add("molten_smooth_red_sandstone", new MoltenBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).lightLevel(state -> 5))),
		MOLTEN_OBSIDIAN = add("molten_obsidian", new MoltenBlock(Block.Properties.copy(Blocks.OBSIDIAN).lightLevel(state -> 5))),
		MOLTEN_BLACKSTONE = add("molten_blackstone", new MoltenBlock(Block.Properties.copy(Blocks.BLACKSTONE).lightLevel(state -> 5))),
		MOLTEN_BASALT = add("molten_basalt", new MoltenBlock(Block.Properties.copy(Blocks.BASALT).lightLevel(state -> 5))),

		// TODO add to mossy tag?
		// TODO when 1.17 comes out: add extra recipe that uses a moss block instead of vines in line with Vanilla 1.17
		MOSSY_DIRT = add("mossy_dirt", new Block(Block.Properties.copy(Blocks.DIRT))),
		MOSSY_SAND = add("mossy_sand",  new SandBlock(0xDBD3A0, Block.Properties.copy(Blocks.SAND))),
		MOSSY_RED_SAND = add("mossy_red_sand", new SandBlock(0xA95821, Block.Properties.copy(Blocks.RED_SAND))),
		MOSSY_GRAVEL = add("mossy_gravel", new GravelBlock(Block.Properties.copy(Blocks.GRAVEL))),
		MOSSY_STONE = add("mossy_stone", new Block(Block.Properties.copy(Blocks.STONE))), // TODO: make this drop mossy cobble when mined normally, mossy stone when mined with silk touch
		MOSSY_GRANITE = add("mossy_granite", new Block(Block.Properties.copy(Blocks.GRANITE))),
		MOSSY_DIORITE = add("mossy_diorite", new Block(Block.Properties.copy(Blocks.DIORITE))),
		MOSSY_ANDESITE = add("mossy_andesite", new Block(Block.Properties.copy(Blocks.ANDESITE))),
		MOSSY_SANDSTONE = add("mossy_sandstone", new Block(Block.Properties.copy(Blocks.SANDSTONE))),
		MOSSY_SMOOTH_SANDSTONE = add("mossy_smooth_sandstone", new Block(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE))),
		MOSSY_RED_SANDSTONE = add("mossy_red_sandstone", new Block(Block.Properties.copy(Blocks.RED_SANDSTONE))),
		MOSSY_SMOOTH_RED_SANDSTONE = add("mossy_smooth_red_sandstone", new Block(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE))),
		MOSSY_OBSIDIAN = add("mossy_obsidian", new Block(Block.Properties.copy(Blocks.OBSIDIAN))),
		MOSSY_BLACKSTONE = add("mossy_blackstone", new Block(Block.Properties.copy(Blocks.BLACKSTONE))),
		MOSSY_BASALT = add("mossy_basalt", new Block(Block.Properties.copy(Blocks.BASALT))),

		// FIXME add the rest of the variants?
		WET_STONE = add("wet_stone", new DrippingBlock(Block.Properties.copy(Blocks.STONE), ParticleTypes.DRIPPING_WATER)),
		WET_GRANITE = add("wet_granite", new DrippingBlock(Block.Properties.copy(Blocks.GRANITE), ParticleTypes.DRIPPING_WATER)),
		WET_DIORITE = add("wet_diorite", new DrippingBlock(Block.Properties.copy(Blocks.DIORITE), ParticleTypes.DRIPPING_WATER)),
		WET_ANDESITE = add("wet_andesite", new DrippingBlock(Block.Properties.copy(Blocks.ANDESITE), ParticleTypes.DRIPPING_WATER)),
		WET_OBSIDIAN = add("wet_obsidian", new DrippingBlock(Block.Properties.copy(Blocks.OBSIDIAN), ParticleTypes.DRIPPING_WATER)),

		HOT_STONE = add("hot_stone", new DrippingBlock(Block.Properties.copy(Blocks.STONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_GRANITE = add("hot_granite", new DrippingBlock(Block.Properties.copy(Blocks.GRANITE), ParticleTypes.DRIPPING_LAVA)),
		HOT_DIORITE = add("hot_diorite", new DrippingBlock(Block.Properties.copy(Blocks.DIORITE), ParticleTypes.DRIPPING_LAVA)),
		HOT_ANDESITE = add("hot_andesite", new DrippingBlock(Block.Properties.copy(Blocks.ANDESITE), ParticleTypes.DRIPPING_LAVA)),
		HOT_SANDSTONE = add("hot_sandstone", new DrippingBlock(Block.Properties.copy(Blocks.SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_SMOOTH_SANDSTONE = add("hot_smooth_sandstone", new DrippingBlock(Block.Properties.copy(Blocks.SMOOTH_SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_RED_SANDSTONE = add("hot_red_sandstone", new DrippingBlock(Block.Properties.copy(Blocks.RED_SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_SMOOTH_RED_SANDSTONE = add("hot_smooth_red_sandstone", new DrippingBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_OBSIDIAN = add("hot_obsidian", new DrippingBlock(Block.Properties.copy(Blocks.OBSIDIAN), ParticleTypes.DRIPPING_LAVA)),
		HOT_BLACKSTONE = add("hot_blackstone", new DrippingBlock(Block.Properties.copy(Blocks.BLACKSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_BASALT = add("hot_basalt", new DrippingBlock(Block.Properties.copy(Blocks.BASALT), ParticleTypes.DRIPPING_LAVA)),

		SANDSTONE_COAL_ORE = add("sandstone_coal_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 0, 2)),
		SANDSTONE_IRON_ORE = add("sandstone_iron_ore", new OreBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f))),
		SANDSTONE_GOLD_ORE = add("sandstone_gold_ore", new OreBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f))),
		SANDSTONE_LAPIS_ORE = add("sandstone_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 2, 5)),
		SANDSTONE_REDSTONE_ORE = add("sandstone_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f))),
		SANDSTONE_DIAMOND_ORE = add("sandstone_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 3, 7)),
		SANDSTONE_EMERALD_ORE = add("sandstone_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 3, 7)),

		SMOOTH_SANDSTONE_COAL_ORE = add("smooth_sandstone_coal_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 0, 2)),
		SMOOTH_SANDSTONE_IRON_ORE = add("smooth_sandstone_iron_ore", new OreBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f))),
		SMOOTH_SANDSTONE_GOLD_ORE = add("smooth_sandstone_gold_ore", new OreBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f))),
		SMOOTH_SANDSTONE_LAPIS_ORE = add("smooth_sandstone_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 2, 5)),
		SMOOTH_SANDSTONE_REDSTONE_ORE = add("smooth_sandstone_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f))),
		SMOOTH_SANDSTONE_DIAMOND_ORE = add("smooth_sandstone_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 3, 7)),
		SMOOTH_SANDSTONE_EMERALD_ORE = add("smooth_sandstone_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.SANDSTONE).strength(1.6f), 3, 7)),

		RED_SANDSTONE_COAL_ORE = add("red_sandstone_coal_ore", new XpBlock(Block.Properties.copy(Blocks.RED_SANDSTONE).strength(1.6f), 0, 2)),
		RED_SANDSTONE_IRON_ORE = add("red_sandstone_iron_ore", new OreBlock(Block.Properties.copy(Blocks.RED_SANDSTONE).strength(1.6f))),
		RED_SANDSTONE_GOLD_ORE = add("red_sandstone_gold_ore", new OreBlock(Block.Properties.copy(Blocks.RED_SANDSTONE).strength(1.6f))),
		RED_SANDSTONE_LAPIS_ORE = add("red_sandstone_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.RED_SANDSTONE).strength(1.6f), 2, 5)),
		RED_SANDSTONE_REDSTONE_ORE = add("red_sandstone_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.RED_SANDSTONE).strength(1.6f))),
		RED_SANDSTONE_DIAMOND_ORE = add("red_sandstone_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.RED_SANDSTONE).strength(1.6f), 3, 7)),
		RED_SANDSTONE_EMERALD_ORE = add("red_sandstone_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.RED_SANDSTONE).strength(1.6f), 3, 7)),

		SMOOTH_RED_SANDSTONE_COAL_ORE = add("smooth_red_sandstone_coal_ore", new XpBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).strength(1.6f), 0, 2)),
		SMOOTH_RED_SANDSTONE_IRON_ORE = add("smooth_red_sandstone_iron_ore", new OreBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).strength(1.6f))),
		SMOOTH_RED_SANDSTONE_GOLD_ORE = add("smooth_red_sandstone_gold_ore", new OreBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).strength(1.6f))),
		SMOOTH_RED_SANDSTONE_LAPIS_ORE = add("smooth_red_sandstone_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).strength(1.6f), 2, 5)),
		SMOOTH_RED_SANDSTONE_REDSTONE_ORE = add("smooth_red_sandstone_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).strength(1.6f))),
		SMOOTH_RED_SANDSTONE_DIAMOND_ORE = add("smooth_red_sandstone_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).strength(1.6f), 3, 7)),
		SMOOTH_RED_SANDSTONE_EMERALD_ORE = add("smooth_red_sandstone_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.SMOOTH_RED_SANDSTONE).strength(1.6f), 3, 7)),

		TERRACOTTA_COAL_ORE = add("terracotta_coal_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 0, 2)),
		TERRACOTTA_IRON_ORE = add("terracotta_iron_ore", new OreBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f))),
		TERRACOTTA_GOLD_ORE = add("terracotta_gold_ore", new OreBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f))),
		TERRACOTTA_LAPIS_ORE = add("terracotta_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 2, 5)),
		TERRACOTTA_REDSTONE_ORE = add("terracotta_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f))),
		TERRACOTTA_DIAMOND_ORE = add("terracotta_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 3, 7)),
		TERRACOTTA_EMERALD_ORE = add("terracotta_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 3, 7)),

		WHITE_TERRACOTTA_COAL_ORE = add("white_terracotta_coal_ore", new XpBlock(Block.Properties.copy(Blocks.WHITE_TERRACOTTA).strength(2.5f), 0, 2)),
		WHITE_TERRACOTTA_IRON_ORE = add("white_terracotta_iron_ore", new OreBlock(Block.Properties.copy(Blocks.WHITE_TERRACOTTA).strength(2.5f))),
		WHITE_TERRACOTTA_GOLD_ORE = add("white_terracotta_gold_ore", new OreBlock(Block.Properties.copy(Blocks.WHITE_TERRACOTTA).strength(2.5f))),
		WHITE_TERRACOTTA_LAPIS_ORE = add("white_terracotta_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.WHITE_TERRACOTTA).strength(2.5f), 2, 5)),
		WHITE_TERRACOTTA_REDSTONE_ORE = add("white_terracotta_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.WHITE_TERRACOTTA).strength(2.5f))),
		WHITE_TERRACOTTA_DIAMOND_ORE = add("white_terracotta_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.WHITE_TERRACOTTA).strength(2.5f), 3, 7)),
		WHITE_TERRACOTTA_EMERALD_ORE = add("white_terracotta_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.WHITE_TERRACOTTA).strength(2.5f), 3, 7)),

		ORANGE_TERRACOTTA_COAL_ORE = add("orange_terracotta_coal_ore", new XpBlock(Block.Properties.copy(Blocks.ORANGE_TERRACOTTA).strength(2.5f), 0, 2)),
		ORANGE_TERRACOTTA_IRON_ORE = add("orange_terracotta_iron_ore", new OreBlock(Block.Properties.copy(Blocks.ORANGE_TERRACOTTA).strength(2.5f))),
		ORANGE_TERRACOTTA_GOLD_ORE = add("orange_terracotta_gold_ore", new OreBlock(Block.Properties.copy(Blocks.ORANGE_TERRACOTTA).strength(2.5f))),
		ORANGE_TERRACOTTA_LAPIS_ORE = add("orange_terracotta_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.ORANGE_TERRACOTTA).strength(2.5f), 2, 5)),
		ORANGE_TERRACOTTA_REDSTONE_ORE = add("orange_terracotta_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.ORANGE_TERRACOTTA).strength(2.5f))),
		ORANGE_TERRACOTTA_DIAMOND_ORE = add("orange_terracotta_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.ORANGE_TERRACOTTA).strength(2.5f), 3, 7)),
		ORANGE_TERRACOTTA_EMERALD_ORE = add("orange_terracotta_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.ORANGE_TERRACOTTA).strength(2.5f), 3, 7)),

		YELLOW_TERRACOTTA_COAL_ORE = add("yellow_terracotta_coal_ore", new XpBlock(Block.Properties.copy(Blocks.YELLOW_TERRACOTTA).strength(2.5f), 0, 2)),
		YELLOW_TERRACOTTA_IRON_ORE = add("yellow_terracotta_iron_ore", new OreBlock(Block.Properties.copy(Blocks.YELLOW_TERRACOTTA).strength(2.5f))),
		YELLOW_TERRACOTTA_GOLD_ORE = add("yellow_terracotta_gold_ore", new OreBlock(Block.Properties.copy(Blocks.YELLOW_TERRACOTTA).strength(2.5f))),
		YELLOW_TERRACOTTA_LAPIS_ORE = add("yellow_terracotta_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.YELLOW_TERRACOTTA).strength(2.5f), 2, 5)),
		YELLOW_TERRACOTTA_REDSTONE_ORE = add("yellow_terracotta_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.YELLOW_TERRACOTTA).strength(2.5f))),
		YELLOW_TERRACOTTA_DIAMOND_ORE = add("yellow_terracotta_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.YELLOW_TERRACOTTA).strength(2.5f), 3, 7)),
		YELLOW_TERRACOTTA_EMERALD_ORE = add("yellow_terracotta_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.YELLOW_TERRACOTTA).strength(2.5f), 3, 7)),

		LIGHT_GRAY_TERRACOTTA_COAL_ORE = add("light_gray_terracotta_coal_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 0, 2)),
		LIGHT_GRAY_TERRACOTTA_IRON_ORE = add("light_gray_terracotta_iron_ore", new OreBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f))),
		LIGHT_GRAY_TERRACOTTA_GOLD_ORE = add("light_gray_terracotta_gold_ore", new OreBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f))),
		LIGHT_GRAY_TERRACOTTA_LAPIS_ORE = add("light_gray_terracotta_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 2, 5)),
		LIGHT_GRAY_TERRACOTTA_REDSTONE_ORE = add("light_gray_terracotta_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f))),
		LIGHT_GRAY_TERRACOTTA_DIAMOND_ORE = add("light_gray_terracotta_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 3, 7)),
		LIGHT_GRAY_TERRACOTTA_EMERALD_ORE = add("light_gray_terracotta_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.TERRACOTTA).strength(2.5f), 3, 7)),

		BROWN_TERRACOTTA_COAL_ORE = add("brown_terracotta_coal_ore", new XpBlock(Block.Properties.copy(Blocks.BROWN_TERRACOTTA).strength(2.5f), 0, 2)),
		BROWN_TERRACOTTA_IRON_ORE = add("brown_terracotta_iron_ore", new OreBlock(Block.Properties.copy(Blocks.BROWN_TERRACOTTA).strength(2.5f))),
		BROWN_TERRACOTTA_GOLD_ORE = add("brown_terracotta_gold_ore", new OreBlock(Block.Properties.copy(Blocks.BROWN_TERRACOTTA).strength(2.5f))),
		BROWN_TERRACOTTA_LAPIS_ORE = add("brown_terracotta_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.BROWN_TERRACOTTA).strength(2.5f), 2, 5)),
		BROWN_TERRACOTTA_REDSTONE_ORE = add("brown_terracotta_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.BROWN_TERRACOTTA).strength(2.5f))),
		BROWN_TERRACOTTA_DIAMOND_ORE = add("brown_terracotta_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.BROWN_TERRACOTTA).strength(2.5f), 3, 7)),
		BROWN_TERRACOTTA_EMERALD_ORE = add("brown_terracotta_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.BROWN_TERRACOTTA).strength(2.5f), 3, 7)),

		RED_TERRACOTTA_COAL_ORE = add("red_terracotta_coal_ore", new XpBlock(Block.Properties.copy(Blocks.RED_TERRACOTTA).strength(2.5f), 0, 2)),
		RED_TERRACOTTA_IRON_ORE = add("red_terracotta_iron_ore", new OreBlock(Block.Properties.copy(Blocks.RED_TERRACOTTA).strength(2.5f))),
		RED_TERRACOTTA_GOLD_ORE = add("red_terracotta_gold_ore", new OreBlock(Block.Properties.copy(Blocks.RED_TERRACOTTA).strength(2.5f))),
		RED_TERRACOTTA_LAPIS_ORE = add("red_terracotta_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.RED_TERRACOTTA).strength(2.5f), 2, 5)),
		RED_TERRACOTTA_REDSTONE_ORE = add("red_terracotta_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.RED_TERRACOTTA).strength(2.5f))),
		RED_TERRACOTTA_DIAMOND_ORE = add("red_terracotta_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.RED_TERRACOTTA).strength(2.5f), 3, 7)),
		RED_TERRACOTTA_EMERALD_ORE = add("red_terracotta_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.RED_TERRACOTTA).strength(2.5f), 3, 7)),

		ICE_COAL_ORE = add("ice_coal_ore", new XpBlock(Block.Properties.of(Material.ICE).strength(1f).friction(0.98F).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops(), 0, 2)),
		ICE_IRON_ORE = add("ice_iron_ore", new EncasedOreBlock(Block.Properties.of(Material.ICE).strength(1f).friction(0.98F).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops())),
		ICE_GOLD_ORE = add("ice_gold_ore", new EncasedOreBlock(Block.Properties.of(Material.ICE).strength(1f).friction(0.98F).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops())),
		ICE_LAPIS_ORE = add("ice_lapis_ore", new XpBlock(Block.Properties.of(Material.ICE).strength(1f).friction(0.98F).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops(), 2, 5)),
		ICE_REDSTONE_ORE = add("ice_redstone_ore", new XpBlock(Block.Properties.of(Material.ICE).strength(1f).friction(0.98F).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops(), 1, 6)),
		ICE_DIAMOND_ORE = add("ice_diamond_ore", new XpBlock(Block.Properties.of(Material.ICE).strength(1f).friction(0.98F).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops(), 3, 7)),
		ICE_EMERALD_ORE = add("ice_emerald_ore", new XpBlock(Block.Properties.of(Material.ICE).strength(1f).friction(0.98F).noOcclusion().sound(SoundType.GLASS).requiresCorrectToolForDrops(), 3, 7)),

		BLACKSTONE_COAL_ORE = add("blackstone_coal_ore", new XpBlock(Block.Properties.copy(Blocks.BLACKSTONE).strength(3f), 0, 2)),
		BLACKSTONE_IRON_ORE = add("blackstone_iron_ore", new OreBlock(Block.Properties.copy(Blocks.BLACKSTONE).strength(3f))),
		BLACKSTONE_GOLD_ORE = add("blackstone_gold_ore", new OreBlock(Block.Properties.copy(Blocks.BLACKSTONE).strength(3f))),
		BLACKSTONE_LAPIS_ORE = add("blackstone_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.BLACKSTONE).strength(3f), 2, 5)),
		BLACKSTONE_REDSTONE_ORE = add("blackstone_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.BLACKSTONE).strength(3f))),
		BLACKSTONE_DIAMOND_ORE = add("blackstone_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.BLACKSTONE).strength(3f), 3, 7)),
		BLACKSTONE_EMERALD_ORE = add("blackstone_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.BLACKSTONE).strength(3f), 3, 7)),

		BASALT_COAL_ORE = add("basalt_coal_ore", new XpBlock(Block.Properties.copy(Blocks.BASALT).strength(2.5f), 0, 2)),
		BASALT_IRON_ORE = add("basalt_iron_ore", new OreBlock(Block.Properties.copy(Blocks.BASALT).strength(2.5f))),
		BASALT_GOLD_ORE = add("basalt_gold_ore", new OreBlock(Block.Properties.copy(Blocks.BASALT).strength(2.5f))),
		BASALT_LAPIS_ORE = add("basalt_lapis_ore", new XpBlock(Block.Properties.copy(Blocks.BASALT).strength(2.5f), 2, 5)),
		BASALT_REDSTONE_ORE = add("basalt_redstone_ore", new RedStoneOreBlock(Block.Properties.copy(Blocks.BASALT).strength(2.5f))),
		BASALT_DIAMOND_ORE = add("basalt_diamond_ore", new XpBlock(Block.Properties.copy(Blocks.BASALT).strength(2.5f), 3, 7)),
		BASALT_EMERALD_ORE = add("basalt_emerald_ore", new XpBlock(Block.Properties.copy(Blocks.BASALT).strength(2.5f), 3, 7));

	public static void register()
	{
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static RegistryObject<Block> add(String name, Block block)
	{
		SubWildItems.add(name, new BlockItem(block, new Item.Properties().tab(SubWildItems.TAB)));
		return BLOCKS.register(name, () -> block);
	}
}