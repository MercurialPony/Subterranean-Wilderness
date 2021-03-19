package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class MuddyCaveType extends BasicCaveType
{
	public MuddyCaveType(String domain, String path)
	{
		super(domain, path);
		this.defSlab = SubWildBlocks.DIRT_SLAB;
		this.defStairs = SubWildBlocks.DIRT_STAIRS;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.4d)
				this.replaceBlock(world, pos, Blocks.FARMLAND.getDefaultState());
			else if(d > -0.2d)
				this.replaceBlock(world, pos, (rand.nextBoolean() ? Blocks.COARSE_DIRT : Blocks.DIRT).getDefaultState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.0625d);
			if(d < 0d)
				this.genBlock(world, pos, SubWildBlocks.WATER_PUDDLE.get().getDefaultState());
			else
				this.genLayer(world, pos, SubWildBlocks.DIRT_PATCH.get().getDefaultState(), d, 0.3d, 1d, 5);
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) > -0.2d)
				this.replaceBlock(world, pos, (rand.nextBoolean() ? Blocks.COARSE_DIRT : Blocks.DIRT).getDefaultState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) > -0.2d)
				this.replaceBlock(world, pos, (rand.nextBoolean() ? Blocks.COARSE_DIRT : Blocks.DIRT).getDefaultState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}
}