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
import net.minecraft.block.AbstractBlock.Properties;

public class PatchBlock extends Block
{
	public static final VoxelShape[] SHAPES = new VoxelShape[]
	{
		VoxelShapes.empty(),
		Block.box(0d, 0d, 0d, 16d, 2d, 16d),
		Block.box(0d, 0d, 0d, 16d, 4d, 16d),
		Block.box(0d, 0d, 0d, 16d, 6d, 16d),
		Block.box(0d, 0d, 0d, 16d, 8d, 16d),
		Block.box(0d, 0d, 0d, 16d, 10d, 16d),
		Block.box(0d, 0d, 0d, 16d, 12d, 16d),
		Block.box(0d, 0d, 0d, 16d, 14d, 16d),
		Block.box(0d, 0d, 0d, 16d, 16d, 16d)
	};

	public PatchBlock(Properties props)
	{
		super(props);
		this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.LAYERS, 1));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPES[state.getValue(BlockStateProperties.LAYERS)];
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.LAYERS);
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType type)
	{
		switch (type)
		{
		case LAND:
			return state.getValue(BlockStateProperties.LAYERS) < 5;
		case WATER:
			return false;
		case AIR:
			return false;
		default:
			return false;
		}
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState state)
	{
		return true;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState state = ctx.getLevel().getBlockState(ctx.getClickedPos());
		if (state.getBlock() == this)
			return state.setValue(BlockStateProperties.LAYERS, Math.min(8, state.getValue(BlockStateProperties.LAYERS) + 1));
		return super.getStateForPlacement(ctx);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, side, adjState, world, pos, adjPos);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState down = world.getBlockState(pos.below());
		Block block = down.getBlock();
		if (block == Blocks.HONEY_BLOCK || block == Blocks.SOUL_SAND)
			return true;
		return block == this && down.getValue(BlockStateProperties.LAYERS) == 8 || Block.isFaceFull(down.getBlockSupportShape(world, pos.below()), Direction.UP);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext ctx)
	{
		int layer = state.getValue(BlockStateProperties.LAYERS);
		if (ctx.getItemInHand().getItem() == this.asItem() && layer < 8)
		{
			if (ctx.replacingClickedOnBlock())
				return ctx.getClickedFace() == Direction.UP;
			return true;
		}
		return layer == 1;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean skipRendering(BlockState state, BlockState adjState, Direction side)
	{
		return !state.canOcclude() && adjState.getBlock() == this && state.getValue(BlockStateProperties.LAYERS) <= adjState.getValue(BlockStateProperties.LAYERS) || super.skipRendering(state, adjState, side);
	}
}