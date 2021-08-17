package melonslise.subwild.common.block;

import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import melonslise.subwild.common.util.SubWildUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.block.AbstractBlock.Properties;

public class EncasedSpeleothemBlock extends SpeleothemBlock implements ITranslucent
{
	public final Supplier<Block> encased;

	// TODO
	public EncasedSpeleothemBlock(Properties properties, Supplier<Block> encased)
	{
		super(properties.randomTicks().isValidSpawn((state, world, pos, type) -> type == EntityType.POLAR_BEAR));
		this.encased = encased;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		return VoxelShapes.block();
	}

	@Override
	public boolean shouldDisplayFluidOverlay(BlockState state, IBlockDisplayReader world, BlockPos pos, FluidState fluidState)
	{
		return true;
	}

	@Override
	public boolean canSupport(IWorldReader world, BlockPos pos, BlockState state, BlockPos supPos, BlockState supState)
	{
		return true;
	}

	public boolean isHot(World world, BlockPos pos, BlockState state)
	{
		return world.getBrightness(LightType.BLOCK, pos) > 11 - state.getLightBlock(world, pos);
	}

	public void melt(World world, BlockPos pos, BlockState state)
	{
		world.removeBlock(pos, false);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		if(this.isHot(world, pos, state))
			this.melt(world, pos, state);
	}

	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		super.playerDestroy(world, player, pos, state, te, stack);
		if(!EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.SILK_TOUCH))
			world.setBlockAndUpdate(pos, SubWildUtil.copyStateProps(state, this.encased.get().defaultBlockState()));
	}

	@Override
	public boolean falling()
	{
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean skipRendering(BlockState state, BlockState adjState, Direction side)
	{
		return this.isIce(state) && ITranslucent.isAdjacentIce(adjState) || super.skipRendering(state, adjState, side);
	}

	@Override
	public boolean isIce(BlockState state)
	{
		return !state.canOcclude();
	}
}