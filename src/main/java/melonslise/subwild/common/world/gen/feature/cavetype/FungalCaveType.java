package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import com.google.common.collect.ImmutableSet;

import melonslise.subwild.common.capability.INoise;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class FungalCaveType extends BasicCaveType
{
	public FungalCaveType(final String domain, final String path)
	{
		super(domain, path);
		this.dirs = ImmutableSet.of(Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP);
	}

	@Override
	public void genFloor(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > -0.5d)
				this.replaceBlock(world, pos, Blocks.MYCELIUM.getDefaultState());
			else if( d > 0.8d)
				this.replaceBlock(world, pos, Blocks.DIRT.getDefaultState());
			else
				this.replaceBlock(world, pos, Blocks.COARSE_DIRT.getDefaultState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 1 && rand.nextInt(34) == 0)
		{
			int len = -2;
			BlockPos.Mutable next = new BlockPos.Mutable().setPos(pos);
			for(int a = 0; a < 6; ++a)
				if(world.getBlockState(next.move(0, 1, 0)).isAir())
					++len;
			if(len < 1)
				return;
			if(rand.nextBoolean())
				this.genBigBrownShroom(world, pos, 2 + rand.nextInt(len));
			else
				this.genBigRedShroom(world, pos, 1 + rand.nextInt(len));
		}
		else
		{
			if(pass == 1 && rand.nextInt(6) == 0)
				this.genBlock(world, pos, (rand.nextBoolean() ? Blocks.RED_MUSHROOM : Blocks.BROWN_MUSHROOM).getDefaultState());
			super.genFloorExtra(world, noise, pos, depth, pass, rand);
		}
	}

	@Override
	public void genCeil(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		genCeilOrWall(world, noise, pos, pass);
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		genCeilOrWall(world, noise, pos, pass);
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	private void genCeilOrWall(final ISeedReader world, final INoise noise, final BlockPos pos, final int pass) {
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0d)
				this.replaceBlock(world, pos, Blocks.TERRACOTTA.getDefaultState());
			else if(d > -0.5d)
				this.replaceBlock(world, pos, Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState());
			else if(d > -0.8d)
				this.replaceBlock(world, pos, Blocks.DIRT.getDefaultState());
			else
				this.replaceBlock(world, pos, Blocks.COARSE_DIRT.getDefaultState());
		}
	}

	@Override
	public void genWallExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final Direction wallDir, final float depth, final int pass, final Random rand)
	{
		/*
		if(rand.nextInt(14) == 0 && !world.getBlockState(pos.up()).isAir() || !world.getBlockState(pos.down()).isAir())
			this.genBlock(world, pos, Blocks.COBWEB.getDefaultState());
		*/
		if(pass == 1)
		{
			int len = 1 + rand.nextInt(3);
			float ch = 0.1f;
			if(world.getBlockState(pos.down()).isAir())
			{
				ch += 0.1f;
				len += rand.nextInt(6);
			}
			if(rand.nextFloat() < ch)
				this.genVines(world, pos, wallDir, len);
		}
		//super.genWallExtra(world, noise, pos, wallDir, depth, rand);
	}
}