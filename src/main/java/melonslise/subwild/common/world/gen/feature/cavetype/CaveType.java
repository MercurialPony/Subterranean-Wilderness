package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import melonslise.subwild.common.init.SubWildProperties;
import melonslise.subwild.common.init.SubWildTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
//import net.minecraft.util.IDynamicSerializable; // replaced with com.mojang.serialization.Codec
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.Tags;

public abstract class CaveType //implements IDynamicSerializable
{
	//public static final Codec<CaveType> caveTypeCodec; // todo

	public final ResourceLocation name;

	public static final Block[] ROOTS = new Block[] { SubWildBlocks.LIGHT_BROWN_ROOTS, SubWildBlocks.BROWN_ROOTS, SubWildBlocks.WHITE_ROOTS, SubWildBlocks.LIGHT_ORANGE_ROOTS, SubWildBlocks.ORANGE_ROOTS, SubWildBlocks.DARK_BROWN_ROOTS };

	public CaveType(ResourceLocation name)
	{
		this.name = name;
	}

	public CaveType(String domain, String path)
	{
		this(new ResourceLocation(domain, path));
	}

	public abstract void genFloor(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand);

	public abstract void genFloorExtra(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand);

	public abstract void genCeil(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand);

	public abstract void genCeilExtra(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand);

	public abstract void genWall(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand);

	public abstract void genWallExtra(IWorld world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand);

	public abstract void genFill(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand);

	public abstract boolean canGenSide(IWorld world, BlockPos pos, BlockState state, float depth, int pass, Direction dir);

	public abstract boolean canGenExtra(IWorld world, BlockPos pos, BlockState state, BlockPos sidePos, BlockState sideState, float depth, int pass, Direction dir);

	public abstract boolean canGenFill(IWorld world, BlockPos pos, BlockState state, float depth, int pass);

	public abstract Set<Direction> getGenOrder(int pass);

	public abstract int getPasses();

	public boolean isNatural(IWorld world, BlockPos pos, BlockState state)
	{
		return state.isIn(Tags.Blocks.STONE) || state.getBlock() == SubWildBlocks.BLACKSTONE || state.getBlock() == Blocks.MAGMA_BLOCK || state.isIn(Tags.Blocks.COBBLESTONE) || state.isIn(BlockTags.CORAL_BLOCKS) || state.getBlock() == Blocks.SNOW_BLOCK || state.getBlock() == Blocks.PRISMARINE || state.isIn(BlockTags.ICE) || state.isIn(Tags.Blocks.SANDSTONE) || state.isIn(SubWildTags.TERRACOTTA) || state.isIn(Tags.Blocks.SAND) || state.isIn(Tags.Blocks.GRAVEL) || state.isIn(Tags.Blocks.DIRT) || state.isIn(Tags.Blocks.OBSIDIAN);
	}

	public boolean genBlock(IWorld world, BlockPos pos, BlockState state)
	{
		if(!state.isValidPosition(world, pos))
			return false;
		world.setBlockState(pos, state, 2);
		return true;
	}

	public boolean replaceBlock(IWorld world, BlockPos pos, BlockState state)
	{
		Block block = world.getBlockState(pos).getBlock();
		if(!block.isIn(Tags.Blocks.ORES))
			return this.genBlock(world, pos, state);
		else
			return Optional.ofNullable(SubWildLookups.ORE_TABLE.get(state.getBlock()))
				.map(lookup -> lookup.get(block))
				.map(newBlock -> this.genBlock(world, pos, newBlock.getDefaultState()))
				.orElse(false);
	}

	public boolean modifyBlock(IWorld world, BlockPos pos, Map<Block, Block> lookup)
	{
		Block block = world.getBlockState(pos).getBlock();
		Block newBlock = lookup.get(block);
		if(newBlock == null)
			return false;
		return this.replaceBlock(world, pos, newBlock.getDefaultState());
	}

	public boolean genLayer(IWorld world, BlockPos pos, BlockState state, double noise, double min, double max, int maxHgt)
	{
		if(noise < min || noise > max)
			return false;
		// Normalize our noise to range (0, 1)
		double nrm = (noise - min) / (max - min);
		// Use linear formula 1 - 2abs(x - 0.5) where x is normalized to 0-1
		this.genBlock(world, pos, state.with(BlockStateProperties.LAYERS_1_8, (int) ((1d - 2d * Math.abs(nrm - 0.5d)) * (double) maxHgt) + 1));
		return true;
	}

	public void genRoots(IWorld world, INoise noise, BlockPos pos)
	{
		this.genBlock(world, pos, ROOTS[(int) (this.getClampedNoise(noise, pos, 0.0625d) * (double) ROOTS.length)].getDefaultState());
	}

	public void genVines(IWorld world, BlockPos pos, Direction mainDir, int len)
	{
		BlockPos.Mutable next = new BlockPos.Mutable(pos);
		for(int a = 0; a < len; ++a)
		{
			BlockState newState = world.getBlockState(next);
			if(newState.getBlock() != Blocks.VINE)
				newState = Blocks.VINE.getDefaultState();
			newState = newState.with(SubWildProperties.FACING_LOOKUP.get(mainDir), true);
			this.genBlock(world, next, newState);
			newState = world.getBlockState(next.move(Direction.DOWN));
			if(!newState.isAir() && newState.getBlock() != Blocks.VINE || world.getBlockState(next.move(Direction.DOWN)).getBlock() == Blocks.LAVA || world.getBlockState(next.move(Direction.DOWN)).getBlock() == Blocks.LAVA)
				return;
			next.move(Direction.UP, 2);
		}
	}

	public void genKelp(IWorld world, BlockPos pos, int len)
	{
		BlockPos.Mutable next = new BlockPos.Mutable(pos);
		for(int a = 0; a < len; ++a)
		{
			this.genBlock(world, next, (a == len - 1 ? Blocks.KELP : Blocks.KELP_PLANT).getDefaultState());
			BlockState newState = world.getBlockState(next.move(Direction.UP));
			if(newState.getBlock() != Blocks.WATER && newState.getBlock() != Blocks.KELP && newState.getBlock() != Blocks.KELP_PLANT)
				return;
		}
	}

	public void genSpel(IWorld world, BlockPos pos, BlockState state, int size)
	{
		Direction dir = state.get(SubWildProperties.VERTICAL_FACING);
		BlockPos.Mutable next = new BlockPos.Mutable(pos);
		for(int a = 0; a < size; ++a)
		{
			BlockState nextState = world.getBlockState(next.move(dir));
			BlockState newState = state;
			if(nextState.isAir())
				newState = newState.with(SubWildProperties.FACING_LOOKUP.get(dir), a != size - 1);
			else if(a > 0 && this.isNatural(world, next, nextState)) // ((SpeleothemBlock) state.getBlock()).canSupport(world, pos, newState, next, nextState)
				newState = newState.with(SubWildProperties.VERTICAL_FACING, dir.getOpposite());
			if(a > 0)
				newState = newState.with(SubWildProperties.FACING_LOOKUP.get(dir.getOpposite()), true);
			this.genBlock(world, next.move(dir.getOpposite()), newState);
			next.move(dir);
			if(!nextState.isAir())
				return;
		}
	}

	public void genBigBrownShroom(IWorld world, BlockPos pos, int len)
	{
		if(len < 1)
			len = 1;
		BlockPos.Mutable next = new BlockPos.Mutable(pos).move(0, -1, 0);
		for(int a = 0; a < len + 1; ++a)
			this.genBlock(world, next.move(Direction.UP), Blocks.MUSHROOM_STEM.getDefaultState());
		next.setPos(pos).move(-2, len, 0);
		for(int a = 0; a < 8; ++a)
		{
			if(world.getBlockState(next).allowsMovement(world, next, PathType.LAND))
				this.genBlock(world, next, Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState());
			int i = a / 2;
			next.move(i == 0 || i == 1 ? 1 : -1, 0, i == 0 || i == 3 ? 1 : -1);
		}
		next.setPos(pos).move(-1, len + 1, 0);
		for(int a = 0; a < 4; ++a)
		{
			if(world.getBlockState(next).allowsMovement(world, next, PathType.LAND))
				this.genBlock(world, next, Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState());
			next.move(a == 0 || a == 1 ? 1 : -1, 0, a == 0 || a == 3 ? 1 : -1);
		}
		next.setPos(pos).move(0, len + 1, 0);
		if(world.getBlockState(next).allowsMovement(world, next, PathType.LAND))
			this.genBlock(world, next, Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState());
	}

	public void genBigRedShroom(IWorld world, BlockPos pos, int len)
	{
		if(len < 1)
			len = 1;
		BlockPos.Mutable next = new BlockPos.Mutable(pos).move(0, -1, 0);
		for(int a = 0; a < len + 2; ++a)
			this.genBlock(world, next.move(Direction.UP), Blocks.MUSHROOM_STEM.getDefaultState());
		next.setPos(pos).move(-1, len, 0);
		for(int a = 0; a < 4; ++a)
		{
			for(int b = 0; b < 2; ++b)
			{
				BlockPos next1 = next.add(0, b, 0);
				if(world.getBlockState(next1).allowsMovement(world, next, PathType.LAND))
					this.genBlock(world, next1, Blocks.RED_MUSHROOM_BLOCK.getDefaultState());
			}
			next.move(a == 0 || a == 1 ? 1 : -1, 0, a == 0 || a == 3 ? 1 : -1);
		}
		next.setPos(pos).move(0, len + 2, 0);
		if(world.getBlockState(next).allowsMovement(world, next, PathType.LAND))
			this.genBlock(world, next, Blocks.RED_MUSHROOM_BLOCK.getDefaultState());
	}

	/**
	 * Generally frequency means 1 / (number of blocks per feature). So for example a frequency of 1/8 or 0.125 means we are likely to get a feature every 8 tiles.
	 */
	public double getNoise(INoise noise, BlockPos pos, double frequency)
	{
		return noise.sample((double) pos.getX() * frequency, (double) pos.getY() * frequency, (double) pos.getZ() * frequency);
	}

	public double getClampedNoise(INoise noise, BlockPos pos, double frequency)
	{
		return this.getNoise(noise, pos, frequency) / 2d + 0.5d;
	}

	@Override
	public <T> T serialize(DynamicOps<T> ops)
	{
		return new Dynamic<>(ops,
			ops.createMap(ImmutableMap.of(ops.createString("name"), ops.createString(this.name.toString())))).getValue();
	}
}