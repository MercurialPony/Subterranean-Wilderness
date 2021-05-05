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

public class DeadCoralCaveType extends BasicCaveType
{
	public static final Block[]
		DEAD_CORAL_BLOCKS = new Block[] { Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.DEAD_TUBE_CORAL_BLOCK },
		DEAD_CORAL = new Block[] { Blocks.DEAD_BRAIN_CORAL, Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL, Blocks.DEAD_FIRE_CORAL_FAN, Blocks.DEAD_HORN_CORAL, Blocks.DEAD_HORN_CORAL_FAN, Blocks.DEAD_TUBE_CORAL, Blocks.DEAD_TUBE_CORAL_FAN },
		DEAD_WALL_CORAL = new Block[] { Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL_WALL_FAN };

	public DeadCoralCaveType(String domain, String path)
	{
		super(domain, path);
		this.defStairs = () -> Blocks.PRISMARINE_STAIRS;
		this.defSlab = () -> Blocks.PRISMARINE_SLAB;
		this.ceilCh = 4f;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < -0.2d)
				this.replaceBlock(world, pos, DEAD_CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.015625d) * (double) DEAD_CORAL_BLOCKS.length)].getDefaultState());
			else if(d > 0.6d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.getDefaultState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(SubWildConfig.GENERATE_PUDDLES.get() && this.getNoise(noise, pos, 0.125d) < 0.4d)
				this.genBlock(world, pos, SubWildBlocks.WATER_PUDDLE.get().getDefaultState());
			if(this.getNoise(noise, pos, 0.15d) > 0.6d)
				this.genBlock(world, pos, DEAD_CORAL[rand.nextInt(DEAD_CORAL.length)].getDefaultState().with(BlockStateProperties.WATERLOGGED, false)); // (int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) DEAD_CORAL.length)
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(rand.nextDouble() < 0.2d)
				this.modifyBlock(world, pos, SubWildLookups.WET);
			else
			{
				final double d = this.getNoise(noise, pos, 0.125d);
				if(d < -0.4d)
					this.replaceBlock(world, pos, DEAD_CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.015625d) * (double) DEAD_CORAL_BLOCKS.length)].getDefaultState());
				else if(d > 0.2d)
					this.replaceBlock(world, pos, Blocks.PRISMARINE.getDefaultState());
			}
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < -0.2d)
				this.replaceBlock(world, pos, DEAD_CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.015625d) * (double) DEAD_CORAL_BLOCKS.length)].getDefaultState());
			else if(d > 0.4d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.getDefaultState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(ISeedReader world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(SubWildConfig.GENERATE_DEAD_WALL_CORAL.get() && this.getNoise(noise, pos, 0.15d) > 0.5d)
				this.genBlock(world, pos, DEAD_WALL_CORAL[rand.nextInt(DEAD_WALL_CORAL.length)].getDefaultState().with(BlockStateProperties.WATERLOGGED, false).with(BlockStateProperties.HORIZONTAL_FACING, wallDir.getOpposite())); // (int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) DEAD_WALL_CORAL.length)
		}
		super.genWallExtra(world, noise, pos, wallDir, depth, pass, rand);
	}
}