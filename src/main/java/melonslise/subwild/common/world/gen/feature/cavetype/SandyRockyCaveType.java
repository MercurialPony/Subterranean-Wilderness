package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class SandyRockyCaveType extends BasicCaveType
{
	public final boolean red;

	public SandyRockyCaveType(final String domain, final String path, boolean red)
	{
		super(domain, path);
		this.red = red;
	}

	@Override
	public void genFloor(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.7d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else if(d > 0.5d)
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			else if(d > 0.2d)
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SAND : Blocks.SAND).getDefaultState());
			else if(d > -0.2d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.getDefaultState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(0.1d < d && d < 0.6d)
				this.genLayer(world, pos, (this.red ? SubWildBlocks.RED_SAND_PATCH : SubWildBlocks.SAND_PATCH).get().getDefaultState(), d, 0.1d, 0.6d, 5);
			if(-0.3d < d && d < 0.1d)
				this.genLayer(world, pos, SubWildBlocks.GRAVEL_PATCH.get().getDefaultState(), d, -0.3d, 0.1d, 5);
			if(rand.nextInt(42) == 0)
				this.genBlock(world, pos, Blocks.DEAD_BUSH.getDefaultState());
			else if(rand.nextInt(14) == 0)
				this.genBlock(world, pos, Blocks.STONE_BUTTON.getDefaultState().with(BlockStateProperties.FACE, AttachFace.FLOOR).with(BlockStateProperties.HORIZONTAL_FACING, Plane.HORIZONTAL.facingValues[rand.nextInt(Plane.HORIZONTAL.facingValues.length)]));
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.6d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else if(d > 0.3d)
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			else if(d > -0.1d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.getDefaultState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(final ISeedReader world, final INoise noise, final BlockPos pos, float depth, final int pass, final Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.4d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else if(d > 0d)
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			else if(d > -0.2d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.getDefaultState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}
}