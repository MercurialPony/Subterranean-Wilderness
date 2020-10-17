package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Plane;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class IcyRockyCaveType extends BasicCaveType
{
	public IcyRockyCaveType(final String domain, final String path)
	{
		super(domain, path);
		this.defSpel = SubWildBlocks.FROZEN_STONE_SPELEOTHEM;
		this.ceilCh = 6f;
	}

	@Override
	public void genFloor(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		genFloorCeilOrWall(world, noise, pos, pass);
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	private void genFloorCeilOrWall(final ISeedReader world, final INoise noise, final BlockPos pos, final int pass) {
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.9d)
				this.replaceBlock(world, pos, Blocks.BLUE_ICE.getDefaultState());
			else if(d > 0.8d)
				this.replaceBlock(world, pos, Blocks.PACKED_ICE.getDefaultState());
			else if(d > 0.3d)
				this.replaceBlock(world, pos, Blocks.ICE.getDefaultState());
			else if(d > -0.15d)
				this.replaceBlock(world, pos, Blocks.SNOW_BLOCK.getDefaultState());
			//else if(d > -0.65d);
				// NO OP - spawn stone
			else if(d > -0.85d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.getDefaultState());
			else
				this.replaceBlock(world, pos, Blocks.COBBLESTONE.getDefaultState());
		}
	}

	@Override
	public void genFloorExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(d > 0.1d)
				this.genLayer(world, pos, SubWildBlocks.SNOW_PATCH.get().getDefaultState(), d, 0.1d, 1d, 7);
			else if(d > -0.4d)
				this.genLayer(world, pos, SubWildBlocks.ICE_PATCH.get().getDefaultState(), d, -0.4d, 1d, 5);
			else if(d > -0.8d)
				this.genLayer(world, pos, SubWildBlocks.GRAVEL_PATCH.get().getDefaultState(), d, -0.8d, -0.4d, 4);
			else if(rand.nextInt(4) == 0)
				this.genBlock(world, pos, Blocks.STONE_BUTTON.getDefaultState().with(BlockStateProperties.FACE, AttachFace.FLOOR).with(BlockStateProperties.HORIZONTAL_FACING, Plane.HORIZONTAL.facingValues[rand.nextInt(Plane.HORIZONTAL.facingValues.length)]));
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		genFloorCeilOrWall(world, noise, pos, pass);
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		genFloorCeilOrWall(world, noise, pos, pass);
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWallExtra(final ISeedReader world, final INoise noise, final BlockPos pos, final Direction wallDir, final float depth, final int pass, final Random rand) {}

	@Override
	public void genFill(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 1)
		{
			final BlockPos down = pos.down();
			if(world.getBlockState(down).getBlock() == Blocks.WATER)
				this.genBlock(world, down, Blocks.ICE.getDefaultState());
		}
	}
}