package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;

public class MossyCaveType extends BasicCaveType
{
	public MossyCaveType(String domain, String path)
	{
		super(domain, path);
		this.ceilCh = 5f;
	}

	@Override
	public void genFloor(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) > 0.1d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(SubWildConfig.GENERATE_PUDDLES.get() && this.getNoise(noise, pos, 0.125d) < 0d)
				this.genBlock(world, pos, SubWildBlocks.WATER_PUDDLE.get().defaultBlockState());
			else if(rand.nextInt(36) == 0)
				world.setBlock(pos, LushCaveType.MUSHROOMS[rand.nextInt(LushCaveType.MUSHROOMS.length)].defaultBlockState(), 2);
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
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
	public void genCeilExtra(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.125d) < 0.4d)
				this.genVines(world, pos, Direction.UP, 1);
		}
		super.genCeilExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			if(this.getNoise(noise, pos, 0.125d) > 0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(WorldGenLevel world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			double ch = (1f - depth) * 0.3f;
			if(world.getBlockState(pos.below()).isAir())
				ch *= 2f;
			if(rand.nextFloat() < ch)
				this.genVines(world, pos, wallDir, 1 + rand.nextInt((int) (7f - depth * 7f) + 1) + rand.nextInt(2));
		}
		super.genWallExtra(world, noise, pos, wallDir, depth, pass, rand);
	}

	@Override
	public void genFill(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(SubWildConfig.GENERATE_LILYPADS.get() && rand.nextFloat() < (SubWildConfig.MOSSY_LILYPADS_CHANCE.get().floatValue() / 100) && world.getBlockState(pos.below()).getBlock() == Blocks.WATER)
				this.genBlock(world, pos, Blocks.LILY_PAD.defaultBlockState());
		}
	}
}