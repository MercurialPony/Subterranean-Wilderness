package melonslise.subwild.common.block;

import java.util.Random;

import melonslise.subwild.common.init.SubWildProperties;
import melonslise.subwild.common.init.SubWildTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.item.BlockItemUseContext;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpeleothemBlock extends FallingBlock
{
	public static final VoxelShape
		SMALL_STALAGMITE_SHAPE = Block.makeCuboidShape(7d, 0d, 7d, 9d, 6d, 9d),
		SMALL_STALACTITE_SHAPE = Block.makeCuboidShape(7d, 10d, 7d, 9d, 16d, 9d),
		MEDIUM_STALAGMITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 0d, 2d, 14d, 6d, 14d), Block.makeCuboidShape(5d, 6d, 5d, 11d, 14d, 11d)),
		MEDIUM_STALACTITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 10d, 2d, 14d, 16d, 14d), Block.makeCuboidShape(5d, 2d, 5d, 11d, 10d, 11d)),
		LARGE_STALAGMITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 0d, 2d, 14d, 6d, 14d), Block.makeCuboidShape(5d, 6d, 5d, 11d, 16d, 11d)),
		LARGE_STALACTITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 10d, 2d, 14d, 16d, 14d), Block.makeCuboidShape(5d, 0d, 5d, 11d, 10d, 11d)),
		COLUMN_SHAPE = Block.makeCuboidShape(6d, 0d, 6d, 10d, 16d, 10d);

	public SpeleothemBlock(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.UP, false).with(BlockStateProperties.DOWN, false).with(SubWildProperties.VERTICAL_FACING, Direction.UP));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.UP).add(BlockStateProperties.DOWN).add(SubWildProperties.VERTICAL_FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		boolean up = state.get(BlockStateProperties.UP);
		boolean down = state.get(BlockStateProperties.DOWN);
		boolean stalagmite = state.get(SubWildProperties.VERTICAL_FACING) == Direction.UP;
		if(!up && down && stalagmite)
			return SMALL_STALAGMITE_SHAPE;
		else if(up && !down && !stalagmite)
			return SMALL_STALACTITE_SHAPE;
		else if(!up && !down && stalagmite)
			return MEDIUM_STALAGMITE_SHAPE;
		else if(!up && !down && !stalagmite)
			return MEDIUM_STALACTITE_SHAPE;
		else if(up && !down && stalagmite)
			return LARGE_STALAGMITE_SHAPE;
		else if(!up && down && !stalagmite)
			return LARGE_STALACTITE_SHAPE;
		else
			return COLUMN_SHAPE;
	}

	// TODO Different damage depending on size
	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
	{
		if(!this.isValidPosition(state, world, pos))
			this.startFalling(world, pos, state);
	}

	public boolean canConnect(IWorldReader world, BlockPos pos, BlockState state, BlockPos toPos, BlockState toState)
	{
		return toState.isIn(SubWildTags.SPELEOTHEMS) && state.getMaterialColor(world, pos) == toState.getMaterialColor(world, toPos);
	}

	public boolean canSupport(IWorldReader world, BlockPos pos, BlockState state, BlockPos supPos, BlockState supState)
	{
		return this.canConnect(world, pos, state, supPos, supState) || supState.isSolidSide(world, supPos, state.get(SubWildProperties.VERTICAL_FACING));
	}

	@Override
	public void updateNeighbors(BlockState state, IWorld world, BlockPos pos, int flags)
	{
		super.updateNeighbors(state, world, pos, flags);
		if(!this.isValidPosition(state, world, pos))
			return;
		for(Direction dir : Direction.Plane.VERTICAL.facingValues)
			state = state.with(SubWildProperties.FACING_LOOKUP.get(dir), this.canConnect(world, pos, state, pos.offset(dir), world.getBlockState(pos.offset(dir))));
		world.setBlockState(pos, state, 3);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		if(side.getAxis().isVertical() && this.canConnect(world, pos, state, adjPos, adjState))
			state = state.with(SubWildProperties.FACING_LOOKUP.get(side), true);
		if(!this.isValidPosition(state, world, pos))
			this.startFalling(world.getWorld(), pos, state);
		return state;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		World world = ctx.getWorld();
		BlockPos pos = ctx.getPos();
		BlockState state = this.getDefaultState();
		Direction face = ctx.getFace();
		if(face.getAxis().isVertical())
			return state.with(SubWildProperties.VERTICAL_FACING, face);
		else
			for(Direction dir : Direction.Plane.VERTICAL.facingValues)
			{
				state = state.with(SubWildProperties.VERTICAL_FACING, dir);
				BlockPos supPos = pos.offset(dir.getOpposite());
				if(this.canSupport(world, pos, state, supPos, world.getBlockState(supPos)))
					return state;
			}
		return state;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockPos supPos = pos.offset(state.get(SubWildProperties.VERTICAL_FACING).getOpposite());
		return this.canSupport(world, pos, state, supPos, world.getBlockState(supPos));
	}

	public void startFalling(World world, BlockPos pos, BlockState state)
	{
		if(!this.falling())
			return;
		FallingBlockEntity falling = new FallingBlockEntity(world, (double) pos.getX() + 0.5d, (double) pos.getY(), (double) pos.getZ() + 0.5d, world.getBlockState(pos));
		falling.dontSetBlock = true;
		this.onStartFalling(falling);
		world.addEntity(falling);
	}

	public boolean falling()
	{
		return true;
	}

	@Override
	protected void onStartFalling(FallingBlockEntity falling)
	{
		falling.setHurtEntities(true);
	}

	@Override
	public void onBroken(World world, BlockPos pos)
	{
		world.playEvent(2001, pos, Block.getStateId(this.getDefaultState()));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {}
}