package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class MossyCaveType extends BasicCaveType
{
	public MossyCaveType(String domain, String path)
	{
		super(domain, path);
		this.ceilCh = 5f;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) > 0.1d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.125d) < 0d)
				this.genBlock(world, pos, SubWildBlocks.WATER_PUDDLE.getDefaultState());
			else if(rand.nextInt(36) == 0)
				world.setBlockState(pos, LushCaveType.MUSHROOMS[rand.nextInt(LushCaveType.MUSHROOMS.length)].getDefaultState(), 2);
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(rand.nextFloat() < 0.15d)
				this.modifyBlock(world, pos, SubWildLookups.WET);
			else if(this.getNoise(noise, pos, 0.125d) > 0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeilExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.125d) < 0.4d)
				this.genVines(world, pos, Direction.UP, 1);
		}
		super.genCeilExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) > 0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(ISeedReader world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			double ch = (1f - depth) * 0.3f;
			if(world.getBlockState(pos.down()).isAir())
				ch *= 2f;
			if(rand.nextFloat() < ch)
				this.genVines(world, pos, wallDir, 1 + rand.nextInt((int) (7f - depth * 7f) + 1) + rand.nextInt(2));
		}
		super.genWallExtra(world, noise, pos, wallDir, depth, pass, rand);
	}

	@Override
	public void genFill(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(rand.nextInt(12) == 0 && world.getBlockState(pos.down()).getBlock() == Blocks.WATER)
				this.genBlock(world, pos, Blocks.LILY_PAD.getDefaultState());
		}
	}
}