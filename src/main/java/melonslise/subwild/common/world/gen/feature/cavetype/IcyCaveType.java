package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;

public class IcyCaveType extends BasicCaveType
{
	public IcyCaveType(String domain, String path)
	{
		super(domain, path);
		this.defSpel = SubWildBlocks.FROZEN_STONE_SPELEOTHEM;
		this.ceilCh = 6f;
	}

	@Override
	public void genFloor(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.8d)
				this.replaceBlock(world, pos, Blocks.BLUE_ICE.defaultBlockState());
			else if(d > 0.6d)
				this.replaceBlock(world, pos, Blocks.PACKED_ICE.defaultBlockState());
			else if(d > 0d)
				this.replaceBlock(world, pos, Blocks.ICE.defaultBlockState());
			else
				this.replaceBlock(world, pos, Blocks.SNOW_BLOCK.defaultBlockState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1 && SubWildConfig.GENERATE_PATCHES.get())
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(d > -0.1d)
				this.genLayer(world, pos, SubWildBlocks.SNOW_PATCH.get().defaultBlockState(), d, -0.1d, 1d, 7);
			else if(d > -0.7d)
				this.genLayer(world, pos, SubWildBlocks.ICE_PATCH.get().defaultBlockState(), d, -0.7d, -0.1d, 5);
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.8d)
				this.replaceBlock(world, pos, Blocks.BLUE_ICE.defaultBlockState());
			else if(d > 0.6d)
				this.replaceBlock(world, pos, Blocks.PACKED_ICE.defaultBlockState());
			else if(d > 0d)
				this.replaceBlock(world, pos, Blocks.ICE.defaultBlockState());
			else
				this.replaceBlock(world, pos, Blocks.SNOW_BLOCK.defaultBlockState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.8d)
				this.replaceBlock(world, pos, Blocks.BLUE_ICE.defaultBlockState());
			else if(d > 0.6d)
				this.replaceBlock(world, pos, Blocks.PACKED_ICE.defaultBlockState());
			else if(d > 0d)
				this.replaceBlock(world, pos, Blocks.ICE.defaultBlockState());
			else
				this.replaceBlock(world, pos, Blocks.SNOW_BLOCK.defaultBlockState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(WorldGenLevel world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand) {}

	@Override
	public void genFill(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			BlockPos down = pos.below();
			if(world.getBlockState(down).getBlock() == Blocks.WATER)
				this.genBlock(world, down, Blocks.ICE.defaultBlockState());
		}
	}
}