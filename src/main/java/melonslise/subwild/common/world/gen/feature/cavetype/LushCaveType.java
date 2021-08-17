package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class LushCaveType extends BasicCaveType
{
	// FIXME double block placement
	public static final Block[]
		LEAVES = new Block[] { Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, Blocks.OAK_LEAVES },
		SAPLINGS = new Block[] { Blocks.DARK_OAK_SAPLING, Blocks.SPRUCE_SAPLING, Blocks.JUNGLE_SAPLING, Blocks.ACACIA_SAPLING, Blocks.OAK_SAPLING, Blocks.BIRCH_SAPLING },
		PLANTS = new Block[] { Blocks.FERN, Blocks.GRASS }, // Blocks.LARGE_FERN, Blocks.TALL_GRASS
		MUSHROOMS = new Block[] { Blocks.RED_MUSHROOM, Blocks.BROWN_MUSHROOM };

	public LushCaveType(String domain, String path)
	{
		super(domain, path);
		this.floorCh = 5f;
		this.ceilCh = 15f;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(-0.2d < d && d < 0.4d)
				this.replaceBlock(world, pos, Blocks.DIRT.defaultBlockState());
			if(this.getNoise(noise, pos, 0.25d) < 0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(SubWildConfig.GENERATE_PATCHES.get() && d < -0.4d)
				this.genLayer(world, pos, SubWildBlocks.MOSSY_DIRT_PATCH.get().defaultBlockState(), d, -1d, -0.4d, 3);
			else if(SubWildConfig.GENERATE_PUDDLES.get() && d > 0.1d)
				this.genBlock(world, pos, SubWildBlocks.WATER_PUDDLE.get().defaultBlockState());
			if(this.getNoise(noise, pos, 0.125d) > 0d)
				this.genBlock(world, pos, PLANTS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) PLANTS.length)].defaultBlockState());
			if(SubWildConfig.GENERATE_SAPLINGS.get() && rand.nextFloat() < (SubWildConfig.LUSH_SAPLINGS_CHANCE.get().floatValue() / 100))
				this.genBlock(world, pos, SAPLINGS[rand.nextInt(SAPLINGS.length)].defaultBlockState());
			else if(rand.nextInt(34) == 0)
				world.setBlock(pos, MUSHROOMS[rand.nextInt(MUSHROOMS.length)].defaultBlockState(), 2);
			if(this.getNoise(noise, pos, 0.1d) > -0.2d)
				this.genBlock(world, pos, LEAVES[(int) (this.getClampedNoise(noise, pos, 0.015625d) * (double) LEAVES.length)].defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true));
			BlockPos up = pos.above();
			if(this.getNoise(noise, up, 0.1d) > 0.4d && world.getBlockState(up).isAir())
				this.genBlock(world, up, LEAVES[(int) (this.getClampedNoise(noise, up, 0.015625d) * (double) LEAVES.length)].defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true));
			BlockPos upup = up.above();
			if(this.getNoise(noise, up, 0.1d) > 0.7d && world.getBlockState(upup).isAir())
				this.genBlock(world, upup, LEAVES[(int) (this.getClampedNoise(noise, upup, 0.015625d) * (double) LEAVES.length)].defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true));
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) < -0.4d)
				this.replaceBlock(world, pos, Blocks.DIRT.defaultBlockState());
			if(this.getNoise(noise, pos, 0.25d) < 0.1d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeilExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.125d) < 0.1d)
				this.genVines(world, pos, Direction.UP, 1);
		}
		super.genCeilExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) < -0.4d)
				this.replaceBlock(world, pos, Blocks.DIRT.defaultBlockState());
			if(this.getNoise(noise, pos, 0.25d) < 0.1d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(ISeedReader world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			int len = 3 + rand.nextInt(3);
			float ch = 0.2f;
			if(world.getBlockState(pos.below()).isAir())
			{
				ch += 0.35f;
				len += rand.nextInt(14);
			}
			if(rand.nextFloat() < ch)
				this.genVines(world, pos, wallDir, len);
		}
		super.genWallExtra(world, noise, pos, wallDir, depth, pass, rand);
	}

	@Override
	public void genFill(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(SubWildConfig.GENERATE_LILYPADS.get() && rand.nextFloat() < (SubWildConfig.LUSH_LILYPADS_CHANCE.get().floatValue() / 100) && world.getBlockState(pos.below()).getBlock() == Blocks.WATER)
				this.genBlock(world, pos, Blocks.LILY_PAD.defaultBlockState());
		}
	}
}