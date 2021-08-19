package melonslise.subwild.common.block;

import java.util.Random;

import melonslise.subwild.common.init.SubWildProperties;
import melonslise.subwild.common.init.SubWildTags;
import melonslise.subwild.common.util.SubWildUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpeleothemBlock extends FallingBlock implements SimpleWaterloggedBlock
{
	public static final VoxelShape
		SMALL_STALAGMITE_SHAPE = Block.box(7d, 0d, 7d, 9d, 6d, 9d),
		SMALL_STALACTITE_SHAPE = Block.box(7d, 10d, 7d, 9d, 16d, 9d),
		MEDIUM_STALAGMITE_SHAPE = Shapes.or(Block.box(2d, 0d, 2d, 14d, 6d, 14d), Block.box(5d, 6d, 5d, 11d, 14d, 11d)),
		MEDIUM_STALACTITE_SHAPE = Shapes.or(Block.box(2d, 10d, 2d, 14d, 16d, 14d), Block.box(5d, 2d, 5d, 11d, 10d, 11d)),
		LARGE_STALAGMITE_SHAPE = Shapes.or(Block.box(2d, 0d, 2d, 14d, 6d, 14d), Block.box(5d, 6d, 5d, 11d, 16d, 11d)),
		LARGE_STALACTITE_SHAPE = Shapes.or(Block.box(2d, 10d, 2d, 14d, 16d, 14d), Block.box(5d, 0d, 5d, 11d, 10d, 11d)),
		COLUMN_SHAPE = Block.box(6d, 0d, 6d, 10d, 16d, 10d);

	public SpeleothemBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.UP, false).setValue(BlockStateProperties.DOWN, false).setValue(SubWildProperties.VERTICAL_FACING, Direction.UP).setValue(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.UP).add(BlockStateProperties.DOWN).add(SubWildProperties.VERTICAL_FACING).add(BlockStateProperties.WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx)
	{
		final boolean up = state.getValue(BlockStateProperties.UP);
		final boolean down = state.getValue(BlockStateProperties.DOWN);
		final boolean stalagmite = state.getValue(SubWildProperties.VERTICAL_FACING) == Direction.UP;
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

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand)
	{
		this.tryToFall(world, pos, state);
	}

	public boolean canConnect(LevelReader world, BlockPos pos, BlockState state, BlockPos toPos, BlockState toState)
	{
		return toState.is(SubWildTags.SPELEOTHEMS) && state.getMapColor(world, pos) == toState.getMapColor(world, toPos);
	}

	public boolean canSupport(LevelReader world, BlockPos pos, BlockState state, BlockPos supPos, BlockState supState)
	{
		return this.canConnect(world, pos, state, supPos, supState) || supState.isFaceSturdy(world, supPos, state.getValue(SubWildProperties.VERTICAL_FACING));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction side, BlockState adjState, LevelAccessor world, BlockPos pos, BlockPos adjPos)
	{
		if(side.getAxis().isVertical() && this.canConnect(world, pos, state, adjPos, adjState))
			state = state.setValue(SubWildProperties.FACING_LOOKUP.get(side), true);
		world.getBlockTicks().scheduleTick(pos, this, 1);
		return state;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		Level world = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();
		BlockState state = this.defaultBlockState();
		Direction face = ctx.getClickedFace();
		if(face.getAxis().isVertical())
			state = state.setValue(SubWildProperties.VERTICAL_FACING, face);
		for(Direction dir : Direction.Plane.VERTICAL)
		{
			BlockPos adjPos = pos.relative(dir);
			BlockState adjState = world.getBlockState(adjPos);
			state = state.setValue(SubWildProperties.FACING_LOOKUP.get(dir), this.canConnect(world, pos, state, adjPos, adjState));
			if(this.canSupport(world, pos, state, adjPos, adjState))
				state = state.setValue(SubWildProperties.VERTICAL_FACING, dir.getOpposite());
		}
		return SubWildUtil.waterlog(state, world, pos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		BlockPos supPos = pos.relative(state.getValue(SubWildProperties.VERTICAL_FACING).getOpposite());
		return this.canSupport(world, pos, state, supPos, world.getBlockState(supPos));
	}

	public void tryToFall(Level world, BlockPos pos, BlockState state)
	{
		if(!this.falling() || this.canSurvive(state, world, pos))
			return;
		FallingBlockEntity falling = new FallingBlockEntity(world, (double) pos.getX() + 0.5d, (double) pos.getY(), (double) pos.getZ() + 0.5d, world.getBlockState(pos));
		falling.cancelDrop = true;
		this.falling(falling);
		world.addFreshEntity(falling);
	}

	public boolean falling()
	{
		return true;
	}

	@Override
	protected void falling(FallingBlockEntity falling)
	{
		falling.setHurtsEntities(2.0F, 40); /** Values taken from {@link net.minecraft.world.level.block.AnvilBlock#falling} */
	}

	/* Method no longer exists
	@Override
	public void onBroken(Level world, BlockPos pos, FallingBlockEntity entity)
	{
		world.levelEvent(2001, pos, Block.getId(this.defaultBlockState()));
	}*/

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
}