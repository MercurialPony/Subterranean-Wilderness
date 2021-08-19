package melonslise.subwild.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class MeltingPatchBlock extends PatchBlock
{
	public MeltingPatchBlock(Properties properties)
	{
		super(properties.isValidSpawn((state, world, pos, type) -> type == EntityType.POLAR_BEAR));
	}

	@Override
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack)
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

	public boolean isHot(Level world, BlockPos pos, BlockState state)
	{
		return world.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(world, pos);
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random)
	{
		if(this.isHot(world, pos, state))
			this.melt(world, pos, state);
	}

	public void melt(Level world, BlockPos pos, BlockState state)
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