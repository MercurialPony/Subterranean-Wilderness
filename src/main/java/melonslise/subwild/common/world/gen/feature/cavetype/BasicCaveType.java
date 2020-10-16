package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import melonslise.subwild.common.init.SubWildProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraftforge.common.Tags;

public class BasicCaveType extends CaveType
{
	public static final Supplier<Block>[] FOXFIRE = new Supplier[] { SubWildBlocks.SHORT_FOXFIRE, SubWildBlocks.LONG_FOXFIRE };

	//  TODO Getters vs fields?
	public ImmutableSet<Direction> dirs = ImmutableSet.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);
	public float floorCh = 2f, ceilCh = 3f;
	public Supplier<Block>
		defSpel = SubWildBlocks.STONE_SPELEOTHEM,
		defStairs = () -> Blocks.STONE_STAIRS,
		defSlab = () -> Blocks.STONE_SLAB;

	public BasicCaveType(ResourceLocation name)
	{
		super(name);
	}

	public BasicCaveType(String domain, String path)
	{
		super(domain, path);
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand) {}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			BlockState support = world.getBlockState(pos.down());
			if(support.getMaterial() == Material.WOOD)
			{
				if(this.getNoise(noise, pos, 0.1d) > 0.6d)
					this.genBlock(world, pos, FOXFIRE[rand.nextInt(FOXFIRE.length)].get().getDefaultState().with(BlockStateProperties.FACING, Direction.UP).with(SubWildProperties.GLOWING, true));
			}
			else if(depth > 0d && !support.isIn(Tags.Blocks.DIRT) && this.getSpelChance(depth, this.floorCh, rand))
				this.genRandSpel(world, pos, SubWildLookups.SPELEOS.getOrDefault(support.getBlock(), this.defSpel.get()).getDefaultState().with(SubWildProperties.VERTICAL_FACING, Direction.UP), depth, rand);
		}
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand) {}

	@Override
	public void genCeilExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			BlockState support = world.getBlockState(pos.up());
			if(support.getMaterial() == Material.WOOD)
			{
				if(this.getNoise(noise, pos, 0.1d) > 0.6d)
					this.genBlock(world, pos, FOXFIRE[rand.nextInt(FOXFIRE.length)].get().getDefaultState().with(BlockStateProperties.FACING, Direction.DOWN).with(SubWildProperties.GLOWING, true));
			}
			else if(!support.isIn(Tags.Blocks.DIRT))
			{
				if(depth > 0d && this.getSpelChance(depth, this.ceilCh, rand))
					this.genRandSpel(world, pos, SubWildLookups.SPELEOS.getOrDefault(support.getBlock(), this.defSpel.get()).getDefaultState().with(SubWildProperties.VERTICAL_FACING, Direction.DOWN), depth, rand);
			}
			else if(this.getNoise(noise, pos, 0.125d) > 0.4d)
				this.genRoots(world, noise, pos);
		}
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand) {}

	@Override
	public void genWallExtra(ISeedReader world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(world.getBlockState(pos.offset(wallDir)).getMaterial() == Material.WOOD)
			{
				if(this.getNoise(noise, pos, 0.1d) > 0.6d)
					this.genBlock(world, pos, FOXFIRE[rand.nextInt(FOXFIRE.length)].get().getDefaultState().with(BlockStateProperties.FACING, wallDir.getOpposite()).with(SubWildProperties.GLOWING, true));
			}
			else
				this.genSlope(world, pos, wallDir, rand);
		}
	}

	@Override
	public void genFill(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand) {}

	@Override
	public boolean canGenSide(ISeedReader world, BlockPos pos, BlockState state, float depth, int pass, Direction dir)
	{
		return pass == 0 && (state.isIn(Tags.Blocks.STONE) || state.isIn(Tags.Blocks.DIRT) || state.isIn(Tags.Blocks.GRAVEL)) || state.isIn(Tags.Blocks.ORES);
	}

	@Override
	public boolean canGenExtra(ISeedReader world, BlockPos pos, BlockState state, BlockPos sidePos, BlockState sideState, float depth, int pass, Direction dir)
	{
		return pass == 1 && state.isAir() && (sideState.isIn(Tags.Blocks.ORES) || sideState.getMaterial() == Material.WOOD || this.isNatural(world, sidePos, sideState));
	}

	@Override
	public boolean canGenFill(ISeedReader world, BlockPos pos, BlockState state, float depth, int pass)
	{
		return pass == 1 && state.isAir();
	}

	@Override
	public Set<Direction> getGenOrder(int pass)
	{
		return this.dirs;
	}

	@Override
	public int getPasses()
	{
		return 2;
	}

	public boolean getSpelChance(float depth, float baseCh, Random rand)
	{
		return (float) rand.nextInt(100) < baseCh + depth * 3f;
	}

	public void genRandSpel(ISeedReader world, BlockPos pos, BlockState state, float depth, Random rand)
	{
		this.genSpel(world, pos, state, 1 + rand.nextInt(2) + rand.nextInt((int) (depth * 10f) + 1));
	}

	public void genSlope(ISeedReader world, BlockPos pos, Direction wallDir, Random rand)
	{
		BlockPos.Mutable mutPos = new BlockPos.Mutable().setPos(pos);
		Block wall = world.getBlockState(mutPos.setPos(pos).move(wallDir)).getBlock();
		boolean isDown = this.isNatural(world, mutPos.setPos(pos).move(0, -1, 0), world.getBlockState(mutPos));
		boolean isUp = this.isNatural(world, mutPos.setPos(pos).move(0, 1, 0), world.getBlockState(mutPos));
		if(!isDown && !isUp)
			return;
		mutPos.setPos(pos);
		int air = 0;
		Direction oppDir = wallDir.getOpposite();
		while(air < 16 && !world.getBlockState(mutPos.move(oppDir)).isSolidSide(world, mutPos, wallDir))
			++air;
		int chance = 6;
		if(air <= SubWildConfig.SLOPE_THRESHOLD.get())
			chance = 2;
		if(rand.nextInt(10) >= chance)
			return;
		if(rand.nextInt(5) <= 2)
			this.genBlock(world, pos, SubWildLookups.STAIRS.getOrDefault(wall, this.defStairs.get()).getDefaultState().with(BlockStateProperties.HORIZONTAL_FACING, wallDir).with(BlockStateProperties.HALF, isDown ? Half.BOTTOM : Half.TOP));
		else
			this.genBlock(world, pos, SubWildLookups.SLABS.getOrDefault(wall, this.defSlab.get()).getDefaultState().with(BlockStateProperties.SLAB_TYPE, isDown ? SlabType.BOTTOM : SlabType.TOP));
	}
}