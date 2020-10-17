package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class VolcanicCaveType extends BasicCaveType
{
	public VolcanicCaveType(String domain, String path)
	{
		super(domain, path);
		this.ceilCh = 7f;
		this.defSlab = () -> Blocks.BLACKSTONE_SLAB;
		this.defStairs = () -> Blocks.BLACKSTONE_STAIRS;
		this.defSpel = SubWildBlocks.BLACKSTONE_SPELEOTHEM;
	}

	@Override
	public void genFloor(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		genWallOrFloor(world, noise, pos, pass, rand);
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < 0d)
				this.replaceBlock(world, pos, Blocks.BLACKSTONE.getDefaultState());
			else if(d < 0.6d)
				this.replaceBlock(world, pos, Blocks.BASALT.getDefaultState());
			if(d < -0.7d)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.HOT);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(final ISeedReader world, final INoise noise, final BlockPos pos, final float depth, final int pass, final Random rand)
	{
		genWallOrFloor(world, noise, pos, pass, rand);
		super.genWall(world, noise, pos, depth, pass, rand);
	}

	private void genWallOrFloor(final ISeedReader world, final INoise noise, final BlockPos pos, final int pass, final Random rand) {
		if(pass == 0)
		{
			final double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < 0d)
				this.replaceBlock(world, pos, Blocks.BLACKSTONE.getDefaultState());
			else if(d < 0.6d)
				this.replaceBlock(world, pos, Blocks.BASALT.getDefaultState());
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
		}
	}
}