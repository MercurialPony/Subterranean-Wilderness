package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class MossyRockyCaveType extends BasicCaveType
{
	public MossyRockyCaveType(String domain, String path)
	{
		super(domain, path);
	}

	@Override
	public void genFloor(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.125d);
			if(-0.4d < d && d < 0.7d)
				this.replaceBlock(world, pos, RockyCaveType.STONE[(int) (this.getClampedNoise(noise, pos, 0.1d) * (double) RockyCaveType.STONE.length)].getDefaultState());
			else if(d < -0.4d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.getDefaultState());
			if(-0.7d < d && d < 0.3d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.125d) < -0.2d)
				this.genBlock(world, pos, SubWildBlocks.WATER_PUDDLE.get().getDefaultState());
			else if(rand.nextInt(36) == 0)
				world.setBlockState(pos, LushCaveType.MUSHROOMS[rand.nextInt(LushCaveType.MUSHROOMS.length)].getDefaultState(), 2);
			double d = this.getNoise(noise, pos, 0.1d);
			if(-0.1d < d && d < 0.4d)
				this.genLayer(world, pos, SubWildBlocks.GRAVEL_PATCH.get().getDefaultState(), d, -0.1d, 0.4d, 5);
			if(rand.nextInt(14) == 0)
				this.genBlock(world, pos, Blocks.STONE_BUTTON.getDefaultState().with(BlockStateProperties.FACE, AttachFace.FLOOR).with(BlockStateProperties.HORIZONTAL_FACING, Plane.HORIZONTAL.random(rand)));
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.125d);
			if(-0.4d < d && d < 0.7d)
				this.replaceBlock(world, pos, RockyCaveType.STONE[(int) (this.getClampedNoise(noise, pos, 0.1d) * (double) RockyCaveType.STONE.length)].getDefaultState());
			else if(d < -0.4d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.getDefaultState());
			if(-0.7d < d && d < 0.3d)
				this.modifyBlock(world, pos, SubWildLookups.MOSSY);
			if(rand.nextFloat() < 0.15d)
				this.modifyBlock(world, pos, SubWildLookups.WET);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeilExtra(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			if(this.getNoise(noise, pos, 0.125d) < (double) -depth)
				this.genVines(world, pos, Direction.UP, 1);
		}
		super.genCeilExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(ISeedReader world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.125d);
			if(-0.4d < d && d < 0.7d)
				this.replaceBlock(world, pos, RockyCaveType.STONE[(int) (this.getClampedNoise(noise, pos, 0.1d) * (double) RockyCaveType.STONE.length)].getDefaultState());
			else if(d < -0.4d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.getDefaultState());
			if(-0.7d < d && d < 0.3d)
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