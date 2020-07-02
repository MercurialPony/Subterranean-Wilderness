package melonslise.subwild.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class RootsBlock extends Block
{
	public static final VoxelShape SHAPE = Block.makeCuboidShape(3d, 8d, 3d, 13d, 16d, 13d);

	public RootsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		return !state.isValidPosition(world, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, side, adjState, world, pos, adjPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockPos up = pos.up();
		return world.getBlockState(up).isNormalCube(world, up);
	}
}