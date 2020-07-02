package melonslise.subwild.common.world.gen.feature.cavetype;

import java.util.Random;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class VolcanicCaveType extends BasicCaveType
{
	public VolcanicCaveType(String domain, String path)
	{
		super(domain, path);
		this.ceilCh = 7f;
		this.defSlab = SubWildBlocks.BLACKSTONE_SLAB;
		this.defStairs = SubWildBlocks.BLACKSTONE_STAIRS;
		this.defSpel = SubWildBlocks.BLACKSTONE_SPELEOTHEM;
	}

	@Override
	public void genFloor(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < 0.3d)
				this.replaceBlock(world, pos, SubWildBlocks.BLACKSTONE.getDefaultState());
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
		}
		super.genFloor(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genCeil(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < 0.3d)
				this.replaceBlock(world, pos, SubWildBlocks.BLACKSTONE.getDefaultState());
			if(d < -0.7d)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.HOT);
		}
		super.genCeil(world, noise, pos, depth, pass, rand);
	}

	@Override
	public void genWall(IWorld world, INoise noise, BlockPos pos, float depth, int pass, Random rand)
	{
		if(pass == 0)
		{
			double d = this.getNoise(noise, pos, 0.0625d);
			if(d < -0.85d )
				this.replaceBlock(world, pos, Blocks.MAGMA_BLOCK.getDefaultState());
			else if(d < 0.3d)
				this.replaceBlock(world, pos, SubWildBlocks.BLACKSTONE.getDefaultState());
			if(rand.nextFloat() < 0.2f)
				this.modifyBlock(world, pos, SubWildLookups.MOLTEN);
		}
		super.genWall(world, noise, pos, depth, pass, rand);
	}
}