package melonslise.subwild.common.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// FIXME  add Silk touch pickup (new)? and noDrops in props?
public class PatchBlock extends Block
{
	public static final VoxelShape[] SHAPES = new VoxelShape[]
	{
		VoxelShapes.empty(),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 2d, 16d),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 4d, 16d),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 6d, 16d),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 8d, 16d),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 10d, 16d),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 12d, 16d),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 14d, 16d),
		Block.makeCuboidShape(0d, 0d, 0d, 16d, 16d, 16d)
	};

	public PatchBlock(Properties props)
	{
		super(props);
		this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.LAYERS_1_8, 1));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPES[state.get(BlockStateProperties.LAYERS_1_8)];
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.LAYERS_1_8);
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader world, BlockPos pos, PathType type)
	{
		switch (type)
		{
		case LAND:
			return state.get(BlockStateProperties.LAYERS_1_8) < 5;
		case WATER:
			return false;
		case AIR:
			return false;
		default:
			return false;
		}
	}

	@Override
	public boolean isTransparent(BlockState state)
	{
		return true;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState state = ctx.getWorld().getBlockState(ctx.getPos());
		if (state.getBlock() == this)
			return state.with(BlockStateProperties.LAYERS_1_8, Integer.valueOf(Math.min(8, state.get(BlockStateProperties.LAYERS_1_8) + 1)));
		return super.getStateForPlacement(ctx);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		return !state.isValidPosition(world, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, side, adjState, world, pos, adjPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState down = world.getBlockState(pos.down());
		Block block = down.getBlock();
		if (block == Blocks.HONEY_BLOCK || block == Blocks.SOUL_SAND)
			return true;
		return block == this && down.get(BlockStateProperties.LAYERS_1_8) == 8 || Block.doesSideFillSquare(down.getCollisionShape(world, pos.down()), Direction.UP);
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext ctx)
	{
		int layer = state.get(BlockStateProperties.LAYERS_1_8);
		if (ctx.getItem().getItem() == this.asItem() && layer < 8)
		{
			if (ctx.replacingClickedOnBlock())
				return ctx.getFace() == Direction.UP;
			return true;
		}
		return layer == 1;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjState, Direction side)
	{
		return !state.isSolid() && adjState.getBlock() == this && state.get(BlockStateProperties.LAYERS_1_8) <= adjState.get(BlockStateProperties.LAYERS_1_8) ? true : super.isSideInvisible(state, adjState, side);
	}
}