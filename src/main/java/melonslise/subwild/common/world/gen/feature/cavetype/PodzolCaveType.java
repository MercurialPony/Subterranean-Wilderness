package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class PodzolCaveType extends BasicCaveType
{
	public PodzolCaveType(String domain, String path)
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
			if(d > 0.7d)
				this.replaceBlock(world, pos, Blocks.FARMLAND.getDefaultState());
			else if(d > 0.4d)
				this.replaceBlock(world, pos, (rand.nextBoolean() ? Blocks.COARSE_DIRT : Blocks.DIRT).getDefaultState());
			else if(d > -0.2d)
				this.replaceBlock(world, pos, Blocks.PODZOL.getDefaultState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.0625d);
			if(SubWildConfig.GENERATE_PATCHES.get() && d > 0d)
				this.genLayer(world, pos, SubWildBlocks.PODZOL_PATCH.get().getDefaultState(), d, 0.2d, 1d, 5);
			else if(SubWildConfig.GENERATE_PATCHES.get() && d > -0.3d)
				this.genLayer(world, pos, SubWildBlocks.DIRT_PATCH.get().getDefaultState(), d, -0.1d, 0.2d, 5);
			if(this.getNoise(noise, pos, 0.1d) > 0.5d)
				this.genBlock(world, pos, LushCaveType.PLANTS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) LushCaveType.PLANTS.length)].getDefaultState());
			if(rand.nextInt(34) == 0)
				world.setBlockState(pos, LushCaveType.MUSHROOMS[rand.nextInt(LushCaveType.MUSHROOMS.length)].getDefaultState(), 2);
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.1d) < -0.2d)
				this.replaceBlock(world, pos, (rand.nextBoolean() ? Blocks.COARSE_DIRT : Blocks.DIRT).getDefaultState());
			if(this.getNoise(noise, pos, 0.125d) < -0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.1d) < -0.2d)
				this.replaceBlock(world, pos, (rand.nextBoolean() ? Blocks.COARSE_DIRT : Blocks.DIRT).getDefaultState());
			if(this.getNoise(noise, pos, 0.125d) < -0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}
}