package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class SandyVolcanicCaveType extends BasicCaveType
{
	public final boolean red;

	public SandyVolcanicCaveType(String domain, String path, boolean red)
	{
		super(domain, path);
		this.red = red;
		this.ceilCh = 7f;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < -0.1d)
				this.replaceBlock(world, pos, Blocks.BLACKSTONE.getDefaultState());
			else if(d < 0.4d)
				this.replaceBlock(world, pos, Blocks.BASALT.getDefaultState());
			else if(d < 0.7d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1 && SubWildConfig.GENERATE_PATCHES.get())
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(d > 0.4d)
				this.genLayer(world, pos, (this.red ? SubWildBlocks.RED_SAND_PATCH : SubWildBlocks.SAND_PATCH).get().getDefaultState(), d, 0.4d, 1d, 5);
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < -0.1d)
				this.replaceBlock(world, pos, Blocks.BLACKSTONE.getDefaultState());
			else if(d < 0.4d)
				this.replaceBlock(world, pos, Blocks.BASALT.getDefaultState());
			else if(d < 0.7d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			if(d < -0.7d)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.HOT);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < -0.1d)
				this.replaceBlock(world, pos, Blocks.BLACKSTONE.getDefaultState());
			else if(d < 0.4d)
				this.replaceBlock(world, pos, Blocks.BASALT.getDefaultState());
			else if(d < 0.7d)
				this.replaceBlock(world, pos, (this.red ? Blocks.SMOOTH_RED_SANDSTONE : Blocks.SMOOTH_SANDSTONE).getDefaultState());
			else
				this.replaceBlock(world, pos, (this.red ? Blocks.RED_SANDSTONE : Blocks.SANDSTONE).getDefaultState());
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}
}