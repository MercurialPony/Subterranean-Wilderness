package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class SandyCaveType extends BasicCaveType
{
	public final boolean red;

	public SandyCaveType(String domain, String path, boolean red)
	{
		super(domain, path);
		this.defSpel = red ? SubWildBlocks.RED_SANDSTONE_SPELEOTHEM : SubWildBlocks.SANDSTONE_SPELEOTHEM;
		this.defStairs = red ? Blocks.RED_SANDSTONE_STAIRS : Blocks.SANDSTONE_STAIRS;
		this.defSlab = red ? Blocks.RED_SANDSTONE_SLAB : Blocks.SANDSTONE_SLAB;
		this.red = red;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.6d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else if(d > 0d)
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			else
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SAND : Blocks.SAND).getDefaultState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			double d = this.getNoise(noise, pos, 0.1d);
			if(-0.5d < d && d < 0.5d)
				this.genLayer(world, pos, (this.red ? SubWildBlocks.RED_SAND_PATCH : SubWildBlocks.SAND_PATCH).getDefaultState(), d, -0.5d, 0.5d, 7);
			if(rand.nextInt(26) == 0)
				this.genBlock(world, pos, Blocks.DEAD_BUSH.getDefaultState());
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.4d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else if(d > -0.6d)
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			else
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SAND : Blocks.SAND).getDefaultState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.4d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else if(d > -0.6d)
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			else
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SAND : Blocks.SAND).getDefaultState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}
}