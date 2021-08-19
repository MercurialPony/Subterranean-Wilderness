package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class IcyRockyCaveType extends BasicCaveType
{
	public IcyRockyCaveType(String domain, String path)
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
			if(d > 0.9d)
				this.replaceBlock(world, pos, Blocks.BLUE_ICE.defaultBlockState());
			else if(d > 0.8d)
				this.replaceBlock(world, pos, Blocks.PACKED_ICE.defaultBlockState());
			else if(d > 0.3d)
				this.replaceBlock(world, pos, Blocks.ICE.defaultBlockState());
			else if(d > -0.15d)
				this.replaceBlock(world, pos, Blocks.SNOW_BLOCK.defaultBlockState());
			else if(d > -0.65d);
				// NO OP - spawn stone
			else if(d > -0.85d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.defaultBlockState());
			else 
				this.replaceBlock(world, pos, Blocks.COBBLESTONE.defaultBlockState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(SubWildConfig.GENERATE_PATCHES.get() && d > 0.1d)
				this.genLayer(world, pos, SubWildBlocks.SNOW_PATCH.get().defaultBlockState(), d, 0.1d, 1d, 7);
			else if(SubWildConfig.GENERATE_PATCHES.get() && d > -0.4d)
				this.genLayer(world, pos, SubWildBlocks.ICE_PATCH.get().defaultBlockState(), d, -0.4d, 1d, 5);
			else if(SubWildConfig.GENERATE_PATCHES.get() && d > -0.8d)
				this.genLayer(world, pos, SubWildBlocks.GRAVEL_PATCH.get().defaultBlockState(), d, -0.8d, -0.4d, 4);
			else if(SubWildConfig.GENERATE_BUTTONS.get() && rand.nextFloat() < (SubWildConfig.ICY_ROCKY_BUTTONS_CHANCE.get().floatValue() / 100))
				this.genBlock(world, pos, Blocks.STONE_BUTTON.defaultBlockState().setValue(BlockStateProperties.ATTACH_FACE, AttachFace.FLOOR).setValue(BlockStateProperties.HORIZONTAL_FACING, Plane.HORIZONTAL.getRandomDirection(rand)));
		}
		super.genFloorExtra(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.9d)
				this.replaceBlock(world, pos, Blocks.BLUE_ICE.defaultBlockState());
			else if(d > 0.8d)
				this.replaceBlock(world, pos, Blocks.PACKED_ICE.defaultBlockState());
			else if(d > 0.3d)
				this.replaceBlock(world, pos, Blocks.ICE.defaultBlockState());
			else if(d > -0.15d)
				this.replaceBlock(world, pos, Blocks.SNOW_BLOCK.defaultBlockState());
			else if(d > -0.65d);
				// NO OP - spawn stone
			else if(d > -0.85d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.defaultBlockState());
			else 
				this.replaceBlock(world, pos, Blocks.COBBLESTONE.defaultBlockState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d > 0.9d)
				this.replaceBlock(world, pos, Blocks.BLUE_ICE.defaultBlockState());
			else if(d > 0.8d)
				this.replaceBlock(world, pos, Blocks.PACKED_ICE.defaultBlockState());
			else if(d > 0.3d)
				this.replaceBlock(world, pos, Blocks.ICE.defaultBlockState());
			else if(d > -0.15d)
				this.replaceBlock(world, pos, Blocks.SNOW_BLOCK.defaultBlockState());
			else if(d > -0.65d);
				// NO OP - spawn stone
			else if(d > -0.85d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.defaultBlockState());
			else 
				this.replaceBlock(world, pos, Blocks.COBBLESTONE.defaultBlockState());
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