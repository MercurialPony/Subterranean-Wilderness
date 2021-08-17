package melonslise.subwild.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import net.minecraft.block.AbstractBlock.Properties;

public class MeltingPatchBlock extends PatchBlock
{
	public MeltingPatchBlock(Properties properties)
	{
		super(properties.isValidSpawn((state, world, pos, type) -> type == EntityType.POLAR_BEAR));
	}

	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		super.playerDestroy(world, player, pos, state, te, stack);
		if (world.dimensionType().ultraWarm())
		{
			world.removeBlock(pos, false);
			return;
		}
		Material material = world.getBlockState(pos.below()).getMaterial();
		if (material.blocksMotion() || material.isLiquid())
			world.setBlockAndUpdate(pos, SubWildBlocks.WATER_PUDDLE.get().defaultBlockState());
	}

	public boolean isHot(World world, BlockPos pos, BlockState state)
	{
		return world.getBrightness(LightType.BLOCK, pos) > 11 - state.getLightBlock(world, pos);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		if(this.isHot(world, pos, state))
			this.melt(world, pos, state);
	}

	public void melt(World world, BlockPos pos, BlockState state)
	{
		if (world.dimensionType().ultraWarm())
			world.removeBlock(pos, false);
		else
		{
			world.setBlockAndUpdate(pos, SubWildBlocks.WATER_PUDDLE.get().defaultBlockState());
			world.neighborChanged(pos, SubWildBlocks.WATER_PUDDLE.get(), pos);
		}
	}
}