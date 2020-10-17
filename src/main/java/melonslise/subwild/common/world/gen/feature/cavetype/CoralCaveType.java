package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
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

	public CoralCaveType(final String domain, final String path)
	{
		super(domain, path);
		this.defStairs = () -> Blocks.PRISMARINE_STAIRS;
		this.defSlab = () -> Blocks.PRISMARINE_SLAB;
	}

	@Override
	public void genFloor(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < 0.2d)
				this.replaceBlock(world, pos, CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) CORAL_BLOCKS.length)].getDefaultState());
			else if(d > 0.5d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.getDefaultState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.15d) > 0.1d || rand.nextInt(14) == 0)
				this.genBlock(world, pos, CORAL[rand.nextInt(CORAL.length)].getDefaultState());
			double d = this.getNoise(noise, pos, 0.12d);
			if(d > 0.5d || rand.nextInt(18) == 0)
				this.genKelp(world, pos, 1 + rand.nextInt(10));
			else if(d > -0.2d || rand.nextInt(12) == 0)
			{
				if(this.getNoise(noise, pos, 0.13d) > 0d)
					this.genBlock(world, pos, Blocks.SEAGRASS.getDefaultState());
				else
				{
					this.genBlock(world, pos, Blocks.TALL_SEAGRASS.getDefaultState());
					this.genBlock(world, pos.up(), Blocks.TALL_SEAGRASS.getDefaultState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER));
				}
			}
		}
	}

	@Override
	public void genCeil(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < -0.2d)
				this.replaceBlock(world, pos, CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) CORAL_BLOCKS.length)].getDefaultState());
			else if(d > 0.3d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.getDefaultState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeilExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand) {}

	@Override
	public void genWall(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < 0.2d)
				this.replaceBlock(world, pos, CORAL_BLOCKS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) CORAL_BLOCKS.length)].getDefaultState());
			else if(d > 0.4d)
				this.replaceBlock(world, pos, Blocks.PRISMARINE.getDefaultState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final Direction wallDir, final float depth, final int pass, final Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.15d) > 0.1d || rand.nextInt(18) == 0)
				this.genBlock(world, pos, WALL_CORAL[rand.nextInt(WALL_CORAL.length)].getDefaultState().with(BlockStateProperties.HORIZONTAL_FACING, wallDir.getOpposite()));
		}
		super.genWallExtra(world, noise, pos, wallDir, depth, pass, rand);
	}

	@Override
	public void genFill(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand) {}

	@Override
	public boolean canGenExtra(final ISeedReader world, final BlockPos pos, final BlockState state, final BlockPos sidePos, final BlockState sideState, final float depth, final int pass, Direction dir)
	{
		return pass == 1 && state.getBlock() == Blocks.WATER && (sideState.isIn(Tags.Blocks.ORES) || this.isNatural(world, sidePos, sideState));
	}

	@Override
	public boolean canGenFill(final ISeedReader world, final BlockPos pos, final BlockState state, final float depth, final int pass)
	{
		return pass == 1 && state.getBlock() == Blocks.WATER;
	}
}