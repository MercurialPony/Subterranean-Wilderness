package melonslise.subwild.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class PuddleBlock extends Block
{

	public PuddleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		return VoxelShapes.empty();
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		return !state.isValidPosition(world, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, side, adjState, world, pos, adjPos);
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
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState down = world.getBlockState(pos.down());
		Block block = down.getBlock();
		if (block == Blocks.HONEY_BLOCK || block == Blocks.SOUL_SAND)
			return true;
		return Block.doesSideFillSquare(down.getCollisionShape(world, pos.down()), Direction.UP);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjState, Direction side)
	{
		return !this.isSolid(state) && adjState.getBlock() == this ? true : super.isSideInvisible(state, adjState, side);
	}
}