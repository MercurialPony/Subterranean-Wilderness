package melonslise.subwild.common.block;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import melonslise.subwild.common.init.SubWildProperties;
import melonslise.subwild.common.init.SubWildTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class FoxfireBlock extends Block implements IPlantable
{
	public static final VoxelShape
		SHAPE_NORTH = Block.box(5d, 5d, 10d, 11d, 11d, 16d),
		SHAPE_EAST = Block.box(0d, 5d, 5d, 6d, 11d, 11d),
		SHAPE_SOUTH = Block.box(5d, 5d, 0d, 11d, 11d, 6d),
		SHAPE_WEST = Block.box(10d, 5d, 5d, 16d, 11d, 11d),
		SHAPE_UP = Block.box(5d, 0d, 5d, 11d, 6d, 11d),
		SHAPE_DOWN = Block.box(5d, 10d, 5d, 11d, 16d, 11d);

	public static final int GLOWING_THRESHOLD = 7;

	public FoxfireBlock(Properties properties)
	{
		super(properties.randomTicks().lightLevel(state -> state.getValue(SubWildProperties.GLOWING) ? 4 : 0));
		this.registerDefaultState(this.stateDefinition.any().setValue(SubWildProperties.GLOWING, false).setValue(BlockStateProperties.FACING, Direction.NORTH));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(SubWildProperties.GLOWING, BlockStateProperties.FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx)
	{
		switch(state.getValue(BlockStateProperties.FACING))
		{
		case NORTH:
			return SHAPE_NORTH;
		case EAST:
			return SHAPE_EAST;
		case SOUTH:
			return SHAPE_SOUTH;
		case WEST:
			return SHAPE_WEST;
		case UP:
			return SHAPE_UP;
		case DOWN:
			return SHAPE_DOWN;
		default:
			return Shapes.empty();
		}
	}

	/*
	 *	Block#randomTick receives random ticks. By default also calls Block#tick
	 *	Block#tick receives scheduled ticks
	 */
	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, Random rand)
	{
		boolean glowing = state.getValue(SubWildProperties.GLOWING);
		if(world.getRawBrightness(pos, 0) < GLOWING_THRESHOLD != glowing)
			world.setBlockAndUpdate(pos, state.setValue(SubWildProperties.GLOWING, !glowing));
		if(rand.nextInt(250) == 0)
		{
			List<Pair<BlockPos, Direction>> orients = Lists.newArrayList();
			int shrooms = 5;
			for(BlockPos pos1 : BlockPos.betweenClosed(pos.getX() - 3, pos.getY() - 3, pos.getZ() - 3, pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3))
			{
				BlockState state1 = world.getBlockState(pos1);
				if(SubWildTags.FOXFIRE.contains(state1.getBlock()))
					if(--shrooms <= 0)
						return;
				if(this.isValidGround(state1, world, pos1))
					for(Direction side : Direction.values())
						if(world.isEmptyBlock(pos1.relative(side)))
							orients.add(Pair.of(pos1.relative(side), side));
			}
			if(orients.size() <= 0)
				return;
			Pair<BlockPos, Direction> orient = orients.get(rand.nextInt(orients.size()));
			world.setBlock(orient.getLeft(), SubWildTags.FOXFIRE.getRandomElement(rand).defaultBlockState().setValue(BlockStateProperties.FACING, orient.getRight()), 2);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		if(state.getValue(SubWildProperties.GLOWING) && rand.nextInt(15) == 0)
			world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + 0.5f, (double) pos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		return this.defaultBlockState().setValue(SubWildProperties.GLOWING, ctx.getLevel().getRawBrightness(ctx.getClickedPos(), 0) < GLOWING_THRESHOLD).setValue(BlockStateProperties.FACING, ctx.getClickedFace());
	}

	@Override
	public BlockState updateShape(BlockState state, Direction side, BlockState adjState, LevelAccessor world, BlockPos pos, BlockPos adjPos)
	{
		return state.canSurvive(world, pos) ? super.updateShape(state, side, adjState, world, pos, adjPos) : Blocks.AIR.defaultBlockState();
	}

	// TODO Something better than a material check (e.g. tags)
	// TODO Add forge thing support
	public boolean isValidGround(BlockState state, LevelReader world, BlockPos pos)
	{
		return state.getMaterial() == Material.WOOD && state.isSolidRender(world, pos); //  && state.canSustainPlant(world, pos, side, this)
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		BlockPos adjPos = pos.relative(state.getValue(BlockStateProperties.FACING).getOpposite());
		return this.isValidGround(world.getBlockState(adjPos), world, adjPos);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type)
	{
		return type == PathComputationType.AIR && !this.hasCollision || super.isPathfindable(state, world, pos, type);
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos)
	{
		return PlantType.CAVE;
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos)
	{
		BlockState state = world.getBlockState(pos);
		return state.getBlock() != this ? this.defaultBlockState() : state;
	}
}