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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PuddleBlock extends Block
{
	public PuddleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx)
	{
		return Shapes.empty();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction side, BlockState adjState, LevelAccessor world, BlockPos pos, BlockPos adjPos)
	{
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, side, adjState, world, pos, adjPos);
	}

	/*
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockPos down = pos.down();
		return world.getBlockState(down).isNormalCube(world, down);
	}
	*/

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		BlockState down = world.getBlockState(pos.below());
		Block block = down.getBlock();
		if (block == Blocks.HONEY_BLOCK || block == Blocks.SOUL_SAND)
			return true;
		return Block.isFaceFull(down.getBlockSupportShape(world, pos.below()), Direction.UP);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean skipRendering(BlockState state, BlockState adjState, Direction side)
	{
		return !state.canOcclude() && adjState.getBlock() == this || super.skipRendering(state, adjState, side);
	}
}