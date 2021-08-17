package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class LushVolcanicCaveType extends BasicCaveType
{
	public LushVolcanicCaveType(String domain, String path)
	{
		super(domain, path);
		this.floorCh = 5f;
		this.ceilCh = 15f;
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < -0.55d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.defaultBlockState());
			else if(d < -0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
			else if(d > 0.4d)
				this.replaceBlock(world, pos, Blocks.DIRT.defaultBlockState());
			else if (d > 0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.2d) > 0.4d)
				this.genBlock(world, pos, LushCaveType.LEAVES[(int) (this.getClampedNoise(noise, pos, 0.015625d) * (double) LushCaveType.LEAVES.length)].defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true));
			if(this.getNoise(noise, pos, 0.16d) > 0.5d)
				this.genBlock(world, pos, LushCaveType.PLANTS[(int) (this.getClampedNoise(noise, pos, 0.03125d) * (double) LushCaveType.PLANTS.length)].defaultBlockState());
			if(SubWildConfig.GENERATE_SAPLINGS.get() && rand.nextFloat() < (SubWildConfig.LUSH_VOLCANIC_SAPLINGS_CHANCE.get().floatValue() / 100))
				this.genBlock(world, pos, LushCaveType.SAPLINGS[rand.nextInt(LushCaveType.SAPLINGS.length)].defaultBlockState());
			else if(rand.nextInt(45) == 0)
				world.setBlock(pos, LushCaveType.MUSHROOMS[rand.nextInt(LushCaveType.MUSHROOMS.length)].defaultBlockState(), 2);
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(d < -0.4d)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
			else if (d > 0.2d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeilExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.125d) < 0.1d)
				this.genVines(world, pos, Direction.UP, 1);
		}
		super.genCeilExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < -0.5d)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
			else if (d > 0.4d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(ISeedReader world, INoise noise, BlockPos pos, Direction wallDir, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			int len = 3 + rand.nextInt(2);
			float ch = 0.2f;
			if(world.getBlockState(pos.below()).isAir())
			{
				ch += 0.35f;
				len += rand.nextInt(8);
			}
			if(rand.nextFloat() < ch)
				this.genVines(world, pos, wallDir, len);
		}
		super.genWallExtra(world, noise, pos, wallDir, depth, pass, rand);
	}
}