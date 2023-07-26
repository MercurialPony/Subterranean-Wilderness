package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class RockyCaveType extends BasicCaveType
{
	public static final Block[] STONE = new Block[] { Blocks.GRANITE, Blocks.ANDESITE, Blocks.STONE, Blocks.COBBLESTONE, Blocks.DIORITE };

	public RockyCaveType(String domain, String path)
	{
		super(domain, path);
		this.ceilCh = 6f;
	}

	@Override
	public void genFloor(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < -0.2d)
				this.replaceBlock(world, pos, STONE[(int) (this.getClampedNoise(noise, pos, 0.1d) * (double) STONE.length)].defaultBlockState());
			else if(d < 0.3d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.defaultBlockState());
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genFloorExtra(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 1)
		{
			final double d = this.getNoise(noise, pos, 0.1d);
			if(SubWildConfig.GENERATE_PATCHES.get() && -0.4 < d && d < 0.1d)
				this.genLayer(world, pos, SubWildBlocks.GRAVEL_PATCH.get().defaultBlockState(), d, -0.4d, 0.1d, 5);
			if(SubWildConfig.GENERATE_BUTTONS.get() && rand.nextFloat() < (SubWildConfig.ROCKY_BUTTONS_CHANCE.get().floatValue() / 100))
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
			if(d < -0.3d)
				this.replaceBlock(world, pos, STONE[(int) (this.getClampedNoise(noise, pos, 0.1d) * (double) STONE.length)].defaultBlockState());
			else if(d < 0.2d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.defaultBlockState());
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(WorldGenLevel world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.125d);
			if(d < 0.1d)
				this.replaceBlock(world, pos, STONE[(int) (this.getClampedNoise(noise, pos, 0.1d) * (double) STONE.length)].defaultBlockState());
			else if(d < 0.4d)
				this.replaceBlock(world, pos, Blocks.GRAVEL.defaultBlockState());
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}
}