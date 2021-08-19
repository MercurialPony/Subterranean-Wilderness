package melonslise.subwild.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RootsBlock extends Block
{
	public static final VoxelShape SHAPE = Block.box(3d, 8d, 3d, 13d, 16d, 13d);

	public RootsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction side, BlockState adjState, LevelAccessor world, BlockPos pos, BlockPos adjPos)
	{
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, side, adjState, world, pos, adjPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		BlockPos up = pos.above();
		return world.getBlockState(up).isRedstoneConductor(world, up);
	}
}