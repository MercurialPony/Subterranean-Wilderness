package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.surfacebuilders.BadlandsSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class MesaCaveType extends BasicCaveType
{
	public MesaCaveType(String domain, String path)
	{
		super(domain, path);
		this.floorCh = 0f;
		this.defSpel = SubWildBlocks.RED_SANDSTONE_SPELEOTHEM;
		this.defStairs = () -> Blocks.RED_SANDSTONE_STAIRS;
		this.defSlab = () -> Blocks.RED_SANDSTONE_SLAB;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.2d)
				this.replaceBlock(world, pos, Blocks.RED_SAND.defaultBlockState());
			else
				this.genTerracotta(world, pos);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(SubWildConfig.GENERATE_PATCHES.get() && d > -0.5d && d < 0.5d)
				this.genLayer(world, pos, SubWildBlocks.RED_SAND_PATCH.get().defaultBlockState(), d, -0.5d, 0.5d, 5);
			else if(SubWildConfig.GENERATE_DEAD_BUSHES.get() && rand.nextFloat() < (SubWildConfig.MESA_DEAD_BUSHES_CHANCE.get().floatValue() / 100))
				this.genBlock(world, pos, Blocks.DEAD_BUSH.defaultBlockState());
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			this.genTerracotta(world, pos);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			this.genTerracotta(world, pos);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(ISeedReader world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand) {}

	public void genTerracotta(ISeedReader world, BlockPos pos)
	{
		BadlandsSurfaceBuilder builder = (BadlandsSurfaceBuilder) SurfaceBuilder.BADLANDS;
		builder.initNoise(world.getSeed());
		this.replaceBlock(world, pos, builder.getBand(pos.getX(), pos.getY(), pos.getZ()));
	}
}