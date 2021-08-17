package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraftforge.common.Tags;

public class CoralCaveType extends BasicCaveType
{
	public static final Block[]
		CORAL_BLOCKS = new Block[] { Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK },
		CORAL = new Block[] { Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN },
		WALL_CORAL = new Block[] { Blocks.BRAIN_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN };

	public CoralCaveType(String domain, String path)
	{
		super(domain, path);
		this.defStairs = () -> Blocks.PRISMARINE_STAIRS;
		this.defSlab = () -> Blocks.PRISMARINE_SLAB;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < 0.2d)
				this.replaceBlock(world, pos, CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) CORAL_BLOCKS.length)].defaultBlockState());
			else if(d > 0.5d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.defaultBlockState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(SubWildConfig.GENERATE_FLOOR_CORAL.get() && this.getNoise(noise, pos, 0.15d) > 0.1d || rand.nextInt(14) == 0)
				this.genBlock(world, pos, CORAL[rand.nextInt(CORAL.length)].defaultBlockState());
			final double d = this.getNoise(noise, pos, 0.12d);
			if(d > 0.5d || rand.nextInt(18) == 0)
				this.genKelp(world, pos, 1 + rand.nextInt(10));
			else if(d > -0.2d || rand.nextInt(12) == 0)
			{
				if(this.getNoise(noise, pos, 0.13d) > 0d)
					this.genBlock(world, pos, Blocks.SEAGRASS.defaultBlockState());
				else
				{
					this.genBlock(world, pos, Blocks.TALL_SEAGRASS.defaultBlockState());
					this.genBlock(world, pos.above(), Blocks.TALL_SEAGRASS.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER));
				}
			}
		}
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < -0.2d)
				this.replaceBlock(world, pos, CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) CORAL_BLOCKS.length)].defaultBlockState());
			else if(d > 0.3d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.defaultBlockState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeilExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand) {}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < 0.2d)
				this.replaceBlock(world, pos, CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) CORAL_BLOCKS.length)].defaultBlockState());
			else if(d > 0.4d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.defaultBlockState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(ISeedReader world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(SubWildConfig.GENERATE_WALL_CORAL.get() && this.getNoise(noise, pos, 0.15d) > 0.1d || rand.nextInt(18) == 0)
				this.genBlock(world, pos, WALL_CORAL[rand.nextInt(WALL_CORAL.length)].defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, wallDir.getOpposite()));
		}
		super.genWallExtra(world, noise, pos, wallDir, depth, pass, rand);
	}

	@Override
	public void genFill(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand) {}

	@Override
	public boolean canGenExtra(ISeedReader world, BlockPos pos, BlockState state, BlockPos sidePos, BlockState sideState, float depth, int pass, Direction dir)
	{
		return pass == 1 && state.getBlock() == Blocks.WATER && (sideState.is(Tags.Blocks.ORES) || this.isNatural(world, sidePos, sideState));
	}

	@Override
	public boolean canGenFill(ISeedReader world, BlockPos pos, BlockState state, float depth, int pass)
	{
		return pass == 1 && state.getBlock() == Blocks.WATER;
	}
}