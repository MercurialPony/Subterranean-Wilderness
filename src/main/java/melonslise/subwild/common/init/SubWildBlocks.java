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
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

	// FIXME Speleothem pushreaction
	public static final RegistryObject<Block>
		DIRT_STAIRS = add("dirt_stairs", new StairsBlock(Blocks.DIRT::getDefaultState, Block.Properties.from(Blocks.DIRT))),
		DIRT_SLAB = add("dirt_slab", new SlabBlock(Block.Properties.from(Blocks.DIRT))),

		SHORT_FOXFIRE = add("short_foxfire", new FoxfireBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.NETHER_WART))),
		LONG_FOXFIRE = add("long_foxfire", new FoxfireBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.NETHER_WART))),

		LIGHT_BROWN_ROOTS = add("light_brown_roots", new RootsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.1f).sound(SoundType.NETHER_WART))),
		BROWN_ROOTS = add("brown_roots", new RootsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.1f).sound(SoundType.NETHER_WART))),
		WHITE_ROOTS = add("white_roots", new RootsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.1f).sound(SoundType.NETHER_WART))),
		LIGHT_ORANGE_ROOTS = add("light_orange_roots", new RootsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.1f).sound(SoundType.NETHER_WART))),
		DARK_BROWN_ROOTS = add("dark_brown_roots", new RootsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.1f).sound(SoundType.NETHER_WART))),
		ORANGE_ROOTS = add("orange_roots", new RootsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.1f).sound(SoundType.NETHER_WART))),

		DIRT_PATCH = add("dirt_patch", new PatchBlock(Block.Properties.create(Material.SNOW, MaterialColor.DIRT).hardnessAndResistance(0.1f).sound(SoundType.GROUND).setRequiresTool().harvestTool(ToolType.SHOVEL))),
		MOSSY_DIRT_PATCH = add("mossy_dirt_patch", new PatchBlock(Block.Properties.create(Material.SNOW, MaterialColor.FOLIAGE).hardnessAndResistance(0.1f).sound(SoundType.WET_GRASS).setRequiresTool().harvestTool(ToolType.SHOVEL))),
		PODZOL_PATCH = add("podzol_patch", new PatchBlock(Block.Properties.create(Material.SNOW, MaterialColor.OBSIDIAN).hardnessAndResistance(0.1f).sound(SoundType.GROUND).setRequiresTool().harvestTool(ToolType.SHOVEL))),
		GRAVEL_PATCH = add("gravel_patch", new PatchBlock(Block.Properties.create(Material.SNOW, MaterialColor.STONE).hardnessAndResistance(0.15f).sound(SoundType.GROUND).setRequiresTool().harvestTool(ToolType.SHOVEL))),
		SAND_PATCH = add("sand_patch", new PatchBlock(Block.Properties.create(Material.SNOW, MaterialColor.SAND).hardnessAndResistance(0.1f).sound(SoundType.SAND).setRequiresTool().harvestTool(ToolType.SHOVEL))),
		RED_SAND_PATCH = add("red_sand_patch", new PatchBlock(Block.Properties.create(Material.SNOW, MaterialColor.ADOBE).hardnessAndResistance(0.1f).sound(SoundType.SAND).setRequiresTool().harvestTool(ToolType.SHOVEL))),
		SNOW_PATCH = add("snow_patch", new PatchBlock(Block.Properties.create(Material.SNOW).tickRandomly().hardnessAndResistance(0.1f).sound(SoundType.SNOW).setRequiresTool().harvestTool(ToolType.SHOVEL))),
		ICE_PATCH = add("ice_patch", new MeltingPatchBlock(Block.Properties.create(Material.SNOW, MaterialColor.ICE).slipperiness(0.98f).hardnessAndResistance(0.1f).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestTool(ToolType.PICKAXE))),
		WATER_PUDDLE = add("water_puddle", new PuddleBlock(Block.Properties.create(Material.SNOW, MaterialColor.WATER).doesNotBlockMovement().hardnessAndResistance(0f).noDrops().sound(SubWildSoundTypes.WATER))),

		STONE_SPELEOTHEM = add("stone_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.STONE))),
		GRANITE_SPELEOTHEM = add("granite_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.GRANITE))),
		DIORITE_SPELEOTHEM = add("diorite_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.DIORITE))),
		ANDESITE_SPELEOTHEM = add("andesite_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.ANDESITE))),
		SANDSTONE_SPELEOTHEM = add("sandstone_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.SANDSTONE))),
		RED_SANDSTONE_SPELEOTHEM = add("red_sandstone_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.RED_SANDSTONE))),
		OBSIDIAN_SPELEOTHEM = add("obsidian_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.OBSIDIAN))),
		BLACKSTONE_SPELEOTHEM = add("blackstone_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.BLACKSTONE))),
		BASALT_SPELEOTHEM = add("basalt_speleothem", new SpeleothemBlock(Block.Properties.from(Blocks.BASALT))),

		FROZEN_STONE_SPELEOTHEM = add("frozen_stone_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.STONE).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), STONE_SPELEOTHEM)),
		FROZEN_GRANITE_SPELEOTHEM = add("frozen_granite_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.DIRT).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), GRANITE_SPELEOTHEM)),
		FROZEN_DIORITE_SPELEOTHEM = add("frozen_diorite_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.QUARTZ).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), DIORITE_SPELEOTHEM)),
		FROZEN_ANDESITE_SPELEOTHEM = add("frozen_andesite_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.STONE).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), ANDESITE_SPELEOTHEM)),
		FROZEN_SANDSTONE_SPELEOTHEM = add("frozen_sandstone_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.SAND).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), SANDSTONE_SPELEOTHEM)),
		FROZEN_RED_SANDSTONE_SPELEOTHEM = add("frozen_red_sandstone_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.ADOBE).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), RED_SANDSTONE_SPELEOTHEM)),
		FROZEN_OBSIDIAN_SPELEOTHEM = add("frozen_obsidian_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.BLACK).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), OBSIDIAN_SPELEOTHEM)),
		FROZEN_BLACKSTONE_SPELEOTHEM = add("frozen_blackstone_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.BLACK).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), BLACKSTONE_SPELEOTHEM)),
		FROZEN_BASALT_SPELEOTHEM = add("frozen_basalt_speleothem", new EncasedSpeleothemBlock(Block.Properties.create(Material.ICE, MaterialColor.BLACK).slipperiness(0.98f).hardnessAndResistance(0.5f).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE), BASALT_SPELEOTHEM)),

		ICICLE = add("icicle", new IcicleBlock(Block.Properties.from(Blocks.ICE))),

		MOLTEN_STONE = add("molten_stone", new MoltenBlock(Block.Properties.from(Blocks.STONE).setLightLevel(state -> 5))),
		MOLTEN_GRANITE = add("molten_granite", new MoltenBlock(Block.Properties.from(Blocks.GRANITE).setLightLevel(state -> 5))),
		MOLTEN_DIORITE = add("molten_diorite", new MoltenBlock(Block.Properties.from(Blocks.DIORITE).setLightLevel(state -> 5))),
		MOLTEN_ANDESITE = add("molten_andesite", new MoltenBlock(Block.Properties.from(Blocks.ANDESITE).setLightLevel(state -> 5))),
		MOLTEN_SANDSTONE = add("molten_sandstone", new MoltenBlock(Block.Properties.from(Blocks.SANDSTONE).setLightLevel(state -> 5))),
		MOLTEN_SMOOTH_SANDSTONE = add("molten_smooth_sandstone", new MoltenBlock(Block.Properties.from(Blocks.SMOOTH_SANDSTONE).setLightLevel(state -> 5))),
		MOLTEN_RED_SANDSTONE = add("molten_red_sandstone", new MoltenBlock(Block.Properties.from(Blocks.SANDSTONE).setLightLevel(state -> 5))),
		MOLTEN_SMOOTH_RED_SANDSTONE = add("molten_smooth_red_sandstone", new MoltenBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).setLightLevel(state -> 5))),
		MOLTEN_OBSIDIAN = add("molten_obsidian", new MoltenBlock(Block.Properties.from(Blocks.OBSIDIAN).setLightLevel(state -> 5))),
		MOLTEN_BLACKSTONE = add("molten_blackstone", new MoltenBlock(Block.Properties.from(Blocks.BLACKSTONE).setLightLevel(state -> 5))),
		MOLTEN_BASALT = add("molten_basalt", new MoltenBlock(Block.Properties.from(Blocks.BASALT).setLightLevel(state -> 5))),

		// TODO add to mossy tag?
		// TODO when 1.17 comes out: add extra recipe that uses a moss block instead of vines in line with Vanilla 1.17
		MOSSY_DIRT = add("mossy_dirt", new Block(Block.Properties.from(Blocks.DIRT))),
		MOSSY_SAND = add("mossy_sand",  new SandBlock(0xDBD3A0, Block.Properties.from(Blocks.SAND))),
		MOSSY_RED_SAND = add("mossy_red_sand", new SandBlock(0xA95821, Block.Properties.from(Blocks.RED_SAND))),
		MOSSY_GRAVEL = add("mossy_gravel", new GravelBlock(Block.Properties.from(Blocks.GRAVEL))),
		MOSSY_STONE = add("mossy_stone", new Block(Block.Properties.from(Blocks.STONE))), // TODO: make this drop mossy cobble when mined normally, mossy stone when mined with silk touch
		MOSSY_GRANITE = add("mossy_granite", new Block(Block.Properties.from(Blocks.GRANITE))),
		MOSSY_DIORITE = add("mossy_diorite", new Block(Block.Properties.from(Blocks.DIORITE))),
		MOSSY_ANDESITE = add("mossy_andesite", new Block(Block.Properties.from(Blocks.ANDESITE))),
		MOSSY_SANDSTONE = add("mossy_sandstone", new Block(Block.Properties.from(Blocks.SANDSTONE))),
		MOSSY_SMOOTH_SANDSTONE = add("mossy_smooth_sandstone", new Block(Block.Properties.from(Blocks.SMOOTH_SANDSTONE))),
		MOSSY_RED_SANDSTONE = add("mossy_red_sandstone", new Block(Block.Properties.from(Blocks.RED_SANDSTONE))),
		MOSSY_SMOOTH_RED_SANDSTONE = add("mossy_smooth_red_sandstone", new Block(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE))),
		MOSSY_OBSIDIAN = add("mossy_obsidian", new Block(Block.Properties.from(Blocks.OBSIDIAN))),
		MOSSY_BLACKSTONE = add("mossy_blackstone", new Block(Block.Properties.from(Blocks.BLACKSTONE))),
		MOSSY_BASALT = add("mossy_basalt", new Block(Block.Properties.from(Blocks.BASALT))),

		// FIXME add the rest of the variants?
		WET_STONE = add("wet_stone", new DrippingBlock(Block.Properties.from(Blocks.STONE), ParticleTypes.DRIPPING_WATER)),
		WET_GRANITE = add("wet_granite", new DrippingBlock(Block.Properties.from(Blocks.GRANITE), ParticleTypes.DRIPPING_WATER)),
		WET_DIORITE = add("wet_diorite", new DrippingBlock(Block.Properties.from(Blocks.DIORITE), ParticleTypes.DRIPPING_WATER)),
		WET_ANDESITE = add("wet_andesite", new DrippingBlock(Block.Properties.from(Blocks.ANDESITE), ParticleTypes.DRIPPING_WATER)),
		WET_OBSIDIAN = add("wet_obsidian", new DrippingBlock(Block.Properties.from(Blocks.OBSIDIAN), ParticleTypes.DRIPPING_WATER)),

		HOT_STONE = add("hot_stone", new DrippingBlock(Block.Properties.from(Blocks.STONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_GRANITE = add("hot_granite", new DrippingBlock(Block.Properties.from(Blocks.GRANITE), ParticleTypes.DRIPPING_LAVA)),
		HOT_DIORITE = add("hot_diorite", new DrippingBlock(Block.Properties.from(Blocks.DIORITE), ParticleTypes.DRIPPING_LAVA)),
		HOT_ANDESITE = add("hot_andesite", new DrippingBlock(Block.Properties.from(Blocks.ANDESITE), ParticleTypes.DRIPPING_LAVA)),
		HOT_SANDSTONE = add("hot_sandstone", new DrippingBlock(Block.Properties.from(Blocks.SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_SMOOTH_SANDSTONE = add("hot_smooth_sandstone", new DrippingBlock(Block.Properties.from(Blocks.SMOOTH_SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_RED_SANDSTONE = add("hot_red_sandstone", new DrippingBlock(Block.Properties.from(Blocks.RED_SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_SMOOTH_RED_SANDSTONE = add("hot_smooth_red_sandstone", new DrippingBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_OBSIDIAN = add("hot_obsidian", new DrippingBlock(Block.Properties.from(Blocks.OBSIDIAN), ParticleTypes.DRIPPING_LAVA)),
		HOT_BLACKSTONE = add("hot_blackstone", new DrippingBlock(Block.Properties.from(Blocks.BLACKSTONE), ParticleTypes.DRIPPING_LAVA)),
		HOT_BASALT = add("hot_basalt", new DrippingBlock(Block.Properties.from(Blocks.BASALT), ParticleTypes.DRIPPING_LAVA)),

		SANDSTONE_COAL_ORE = add("sandstone_coal_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		SANDSTONE_IRON_ORE = add("sandstone_iron_ore", new OreBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		SANDSTONE_GOLD_ORE = add("sandstone_gold_ore", new OreBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		SANDSTONE_LAPIS_ORE = add("sandstone_lapis_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		SANDSTONE_REDSTONE_ORE = add("sandstone_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		SANDSTONE_DIAMOND_ORE = add("sandstone_diamond_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		SANDSTONE_EMERALD_ORE = add("sandstone_emerald_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		SMOOTH_SANDSTONE_COAL_ORE = add("smooth_sandstone_coal_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		SMOOTH_SANDSTONE_IRON_ORE = add("smooth_sandstone_iron_ore", new OreBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		SMOOTH_SANDSTONE_GOLD_ORE = add("smooth_sandstone_gold_ore", new OreBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		SMOOTH_SANDSTONE_LAPIS_ORE = add("smooth_sandstone_lapis_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		SMOOTH_SANDSTONE_REDSTONE_ORE = add("smooth_sandstone_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		SMOOTH_SANDSTONE_DIAMOND_ORE = add("smooth_sandstone_diamond_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		SMOOTH_SANDSTONE_EMERALD_ORE = add("smooth_sandstone_emerald_ore", new XpBlock(Block.Properties.from(Blocks.SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		RED_SANDSTONE_COAL_ORE = add("red_sandstone_coal_ore", new XpBlock(Block.Properties.from(Blocks.RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		RED_SANDSTONE_IRON_ORE = add("red_sandstone_iron_ore", new OreBlock(Block.Properties.from(Blocks.RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		RED_SANDSTONE_GOLD_ORE = add("red_sandstone_gold_ore", new OreBlock(Block.Properties.from(Blocks.RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		RED_SANDSTONE_LAPIS_ORE = add("red_sandstone_lapis_ore", new XpBlock(Block.Properties.from(Blocks.RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		RED_SANDSTONE_REDSTONE_ORE = add("red_sandstone_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		RED_SANDSTONE_DIAMOND_ORE = add("red_sandstone_diamond_ore", new XpBlock(Block.Properties.from(Blocks.RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		RED_SANDSTONE_EMERALD_ORE = add("red_sandstone_emerald_ore", new XpBlock(Block.Properties.from(Blocks.RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		SMOOTH_RED_SANDSTONE_COAL_ORE = add("smooth_red_sandstone_coal_ore", new XpBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		SMOOTH_RED_SANDSTONE_IRON_ORE = add("smooth_red_sandstone_iron_ore", new OreBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		SMOOTH_RED_SANDSTONE_GOLD_ORE = add("smooth_red_sandstone_gold_ore", new OreBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		SMOOTH_RED_SANDSTONE_LAPIS_ORE = add("smooth_red_sandstone_lapis_ore", new XpBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		SMOOTH_RED_SANDSTONE_REDSTONE_ORE = add("smooth_red_sandstone_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		SMOOTH_RED_SANDSTONE_DIAMOND_ORE = add("smooth_red_sandstone_diamond_ore", new XpBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		SMOOTH_RED_SANDSTONE_EMERALD_ORE = add("smooth_red_sandstone_emerald_ore", new XpBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE).hardnessAndResistance(1.6f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		TERRACOTTA_COAL_ORE = add("terracotta_coal_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		TERRACOTTA_IRON_ORE = add("terracotta_iron_ore", new OreBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		TERRACOTTA_GOLD_ORE = add("terracotta_gold_ore", new OreBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		TERRACOTTA_LAPIS_ORE = add("terracotta_lapis_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		TERRACOTTA_REDSTONE_ORE = add("terracotta_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		TERRACOTTA_DIAMOND_ORE = add("terracotta_diamond_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		TERRACOTTA_EMERALD_ORE = add("terracotta_emerald_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		WHITE_TERRACOTTA_COAL_ORE = add("white_terracotta_coal_ore", new XpBlock(Block.Properties.from(Blocks.WHITE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		WHITE_TERRACOTTA_IRON_ORE = add("white_terracotta_iron_ore", new OreBlock(Block.Properties.from(Blocks.WHITE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		WHITE_TERRACOTTA_GOLD_ORE = add("white_terracotta_gold_ore", new OreBlock(Block.Properties.from(Blocks.WHITE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		WHITE_TERRACOTTA_LAPIS_ORE = add("white_terracotta_lapis_ore", new XpBlock(Block.Properties.from(Blocks.WHITE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		WHITE_TERRACOTTA_REDSTONE_ORE = add("white_terracotta_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.WHITE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		WHITE_TERRACOTTA_DIAMOND_ORE = add("white_terracotta_diamond_ore", new XpBlock(Block.Properties.from(Blocks.WHITE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		WHITE_TERRACOTTA_EMERALD_ORE = add("white_terracotta_emerald_ore", new XpBlock(Block.Properties.from(Blocks.WHITE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		ORANGE_TERRACOTTA_COAL_ORE = add("orange_terracotta_coal_ore", new XpBlock(Block.Properties.from(Blocks.ORANGE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		ORANGE_TERRACOTTA_IRON_ORE = add("orange_terracotta_iron_ore", new OreBlock(Block.Properties.from(Blocks.ORANGE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		ORANGE_TERRACOTTA_GOLD_ORE = add("orange_terracotta_gold_ore", new OreBlock(Block.Properties.from(Blocks.ORANGE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		ORANGE_TERRACOTTA_LAPIS_ORE = add("orange_terracotta_lapis_ore", new XpBlock(Block.Properties.from(Blocks.ORANGE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		ORANGE_TERRACOTTA_REDSTONE_ORE = add("orange_terracotta_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.ORANGE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		ORANGE_TERRACOTTA_DIAMOND_ORE = add("orange_terracotta_diamond_ore", new XpBlock(Block.Properties.from(Blocks.ORANGE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		ORANGE_TERRACOTTA_EMERALD_ORE = add("orange_terracotta_emerald_ore", new XpBlock(Block.Properties.from(Blocks.ORANGE_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		YELLOW_TERRACOTTA_COAL_ORE = add("yellow_terracotta_coal_ore", new XpBlock(Block.Properties.from(Blocks.YELLOW_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		YELLOW_TERRACOTTA_IRON_ORE = add("yellow_terracotta_iron_ore", new OreBlock(Block.Properties.from(Blocks.YELLOW_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		YELLOW_TERRACOTTA_GOLD_ORE = add("yellow_terracotta_gold_ore", new OreBlock(Block.Properties.from(Blocks.YELLOW_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		YELLOW_TERRACOTTA_LAPIS_ORE = add("yellow_terracotta_lapis_ore", new XpBlock(Block.Properties.from(Blocks.YELLOW_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		YELLOW_TERRACOTTA_REDSTONE_ORE = add("yellow_terracotta_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.YELLOW_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		YELLOW_TERRACOTTA_DIAMOND_ORE = add("yellow_terracotta_diamond_ore", new XpBlock(Block.Properties.from(Blocks.YELLOW_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		YELLOW_TERRACOTTA_EMERALD_ORE = add("yellow_terracotta_emerald_ore", new XpBlock(Block.Properties.from(Blocks.YELLOW_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		LIGHT_GRAY_TERRACOTTA_COAL_ORE = add("light_gray_terracotta_coal_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		LIGHT_GRAY_TERRACOTTA_IRON_ORE = add("light_gray_terracotta_iron_ore", new OreBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		LIGHT_GRAY_TERRACOTTA_GOLD_ORE = add("light_gray_terracotta_gold_ore", new OreBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		LIGHT_GRAY_TERRACOTTA_LAPIS_ORE = add("light_gray_terracotta_lapis_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		LIGHT_GRAY_TERRACOTTA_REDSTONE_ORE = add("light_gray_terracotta_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		LIGHT_GRAY_TERRACOTTA_DIAMOND_ORE = add("light_gray_terracotta_diamond_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		LIGHT_GRAY_TERRACOTTA_EMERALD_ORE = add("light_gray_terracotta_emerald_ore", new XpBlock(Block.Properties.from(Blocks.TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		BROWN_TERRACOTTA_COAL_ORE = add("brown_terracotta_coal_ore", new XpBlock(Block.Properties.from(Blocks.BROWN_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		BROWN_TERRACOTTA_IRON_ORE = add("brown_terracotta_iron_ore", new OreBlock(Block.Properties.from(Blocks.BROWN_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		BROWN_TERRACOTTA_GOLD_ORE = add("brown_terracotta_gold_ore", new OreBlock(Block.Properties.from(Blocks.BROWN_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		BROWN_TERRACOTTA_LAPIS_ORE = add("brown_terracotta_lapis_ore", new XpBlock(Block.Properties.from(Blocks.BROWN_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		BROWN_TERRACOTTA_REDSTONE_ORE = add("brown_terracotta_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.BROWN_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		BROWN_TERRACOTTA_DIAMOND_ORE = add("brown_terracotta_diamond_ore", new XpBlock(Block.Properties.from(Blocks.BROWN_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		BROWN_TERRACOTTA_EMERALD_ORE = add("brown_terracotta_emerald_ore", new XpBlock(Block.Properties.from(Blocks.BROWN_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		RED_TERRACOTTA_COAL_ORE = add("red_terracotta_coal_ore", new XpBlock(Block.Properties.from(Blocks.RED_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		RED_TERRACOTTA_IRON_ORE = add("red_terracotta_iron_ore", new OreBlock(Block.Properties.from(Blocks.RED_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		RED_TERRACOTTA_GOLD_ORE = add("red_terracotta_gold_ore", new OreBlock(Block.Properties.from(Blocks.RED_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		RED_TERRACOTTA_LAPIS_ORE = add("red_terracotta_lapis_ore", new XpBlock(Block.Properties.from(Blocks.RED_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		RED_TERRACOTTA_REDSTONE_ORE = add("red_terracotta_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.RED_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		RED_TERRACOTTA_DIAMOND_ORE = add("red_terracotta_diamond_ore", new XpBlock(Block.Properties.from(Blocks.RED_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		RED_TERRACOTTA_EMERALD_ORE = add("red_terracotta_emerald_ore", new XpBlock(Block.Properties.from(Blocks.RED_TERRACOTTA).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		ICE_COAL_ORE = add("ice_coal_ore", new XpBlock(Block.Properties.create(Material.ICE).hardnessAndResistance(1f).slipperiness(0.98F).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		ICE_IRON_ORE = add("ice_iron_ore", new EncasedOreBlock(Block.Properties.create(Material.ICE).hardnessAndResistance(1f).slipperiness(0.98F).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		ICE_GOLD_ORE = add("ice_gold_ore", new EncasedOreBlock(Block.Properties.create(Material.ICE).hardnessAndResistance(1f).slipperiness(0.98F).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		ICE_LAPIS_ORE = add("ice_lapis_ore", new XpBlock(Block.Properties.create(Material.ICE).hardnessAndResistance(1f).slipperiness(0.98F).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		ICE_REDSTONE_ORE = add("ice_redstone_ore", new XpBlock(Block.Properties.create(Material.ICE).hardnessAndResistance(1f).slipperiness(0.98F).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestLevel(2).harvestTool(ToolType.PICKAXE), 1, 6)),
		ICE_DIAMOND_ORE = add("ice_diamond_ore", new XpBlock(Block.Properties.create(Material.ICE).hardnessAndResistance(1f).slipperiness(0.98F).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		ICE_EMERALD_ORE = add("ice_emerald_ore", new XpBlock(Block.Properties.create(Material.ICE).hardnessAndResistance(1f).slipperiness(0.98F).notSolid().sound(SoundType.GLASS).setRequiresTool().harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		BLACKSTONE_COAL_ORE = add("blackstone_coal_ore", new XpBlock(Block.Properties.from(Blocks.BLACKSTONE).hardnessAndResistance(3f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		BLACKSTONE_IRON_ORE = add("blackstone_iron_ore", new OreBlock(Block.Properties.from(Blocks.BLACKSTONE).hardnessAndResistance(3f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		BLACKSTONE_GOLD_ORE = add("blackstone_gold_ore", new OreBlock(Block.Properties.from(Blocks.BLACKSTONE).hardnessAndResistance(3f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		BLACKSTONE_LAPIS_ORE = add("blackstone_lapis_ore", new XpBlock(Block.Properties.from(Blocks.BLACKSTONE).hardnessAndResistance(3f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		BLACKSTONE_REDSTONE_ORE = add("blackstone_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.BLACKSTONE).hardnessAndResistance(3f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		BLACKSTONE_DIAMOND_ORE = add("blackstone_diamond_ore", new XpBlock(Block.Properties.from(Blocks.BLACKSTONE).hardnessAndResistance(3f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		BLACKSTONE_EMERALD_ORE = add("blackstone_emerald_ore", new XpBlock(Block.Properties.from(Blocks.BLACKSTONE).hardnessAndResistance(3f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),

		BASALT_COAL_ORE = add("basalt_coal_ore", new XpBlock(Block.Properties.from(Blocks.BASALT).hardnessAndResistance(2.5f).harvestLevel(0).harvestTool(ToolType.PICKAXE), 0, 2)),
		BASALT_IRON_ORE = add("basalt_iron_ore", new OreBlock(Block.Properties.from(Blocks.BASALT).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		BASALT_GOLD_ORE = add("basalt_gold_ore", new OreBlock(Block.Properties.from(Blocks.BASALT).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
		BASALT_LAPIS_ORE = add("basalt_lapis_ore", new XpBlock(Block.Properties.from(Blocks.BASALT).hardnessAndResistance(2.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), 2, 5)),
		BASALT_REDSTONE_ORE = add("basalt_redstone_ore", new RedstoneOreBlock(Block.Properties.from(Blocks.BASALT).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
		BASALT_DIAMOND_ORE = add("basalt_diamond_ore", new XpBlock(Block.Properties.from(Blocks.BASALT).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7)),
		BASALT_EMERALD_ORE = add("basalt_emerald_ore", new XpBlock(Block.Properties.from(Blocks.BASALT).hardnessAndResistance(2.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE), 3, 7));

	public static void register()
	{
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static RegistryObject<Block> add(String name, Block block)
	{
		SubWildItems.add(name, new BlockItem(block, new Item.Properties().group(SubWildItems.TAB)));
		return BLOCKS.register(name, () -> block);
	}
}