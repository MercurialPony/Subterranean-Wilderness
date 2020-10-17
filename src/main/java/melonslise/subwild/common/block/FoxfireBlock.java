package melonslise.subwild.common.block;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import melonslise.subwild.common.init.SubWildProperties;
import melonslise.subwild.common.init.SubWildTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class FoxfireBlock extends Block implements IPlantable
{
	public static final VoxelShape
		SHAPE_NORTH = Block.makeCuboidShape(5d, 5d, 10d, 11d, 11d, 16d),
		SHAPE_EAST = Block.makeCuboidShape(0d, 5d, 5d, 6d, 11d, 11d),
		SHAPE_SOUTH = Block.makeCuboidShape(5d, 5d, 0d, 11d, 11d, 6d),
		SHAPE_WEST = Block.makeCuboidShape(10d, 5d, 5d, 16d, 11d, 11d),
		SHAPE_UP = Block.makeCuboidShape(5d, 0d, 5d, 11d, 6d, 11d),
		SHAPE_DOWN = Block.makeCuboidShape(5d, 10d, 5d, 11d, 16d, 11d);

	public static final int GLOWING_THRESHOLD = 7;

	public FoxfireBlock(Properties properties)
	{
		super(properties.tickRandomly().setLightLevel(state -> state.get(SubWildProperties.GLOWING) ? 4 : 0));
		this.setDefaultState(this.stateContainer.getBaseState().with(SubWildProperties.GLOWING, false).with(BlockStateProperties.FACING, Direction.NORTH));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(SubWildProperties.GLOWING, BlockStateProperties.FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		switch(state.get(BlockStateProperties.FACING))
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
			return VoxelShapes.empty();
		}
	}

	/*
	 *	Block#randomTick receives random ticks. By default also calls Block#tick
	 *	Block#tick receives scheduled ticks
	 */
	@Override
	public void tick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random rand)
	{
		final boolean glowing = state.get(SubWildProperties.GLOWING);
		if(world.getLightSubtracted(pos, 0) < GLOWING_THRESHOLD != glowing)
			world.setBlockState(pos, state.with(SubWildProperties.GLOWING, !glowing));
		if(rand.nextInt(250) == 0)
		{
			List<Pair<BlockPos, Direction>> orients = Lists.newArrayList();
			int shrooms = 5;
			for(BlockPos pos1 : BlockPos.getAllInBoxMutable(pos.getX() - 3, pos.getY() - 3, pos.getZ() - 3, pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3))
			{
				BlockState state1 = world.getBlockState(pos1);
				if(SubWildTags.FOXFIRE.contains(state1.getBlock()))
					if(--shrooms <= 0)
						return;
				if(this.isValidGround(state1, world, pos1))
					for(Direction side : Direction.values())
						if(world.isAirBlock(pos1.offset(side)))
							orients.add(Pair.of(pos1.offset(side), side));
			}
			if(orients.size() <= 0)
				return;
			final Pair<BlockPos, Direction> orient = orients.get(rand.nextInt(orients.size()));
			world.setBlockState(orient.getLeft(), SubWildTags.FOXFIRE.getRandomElement(rand).getDefaultState().with(BlockStateProperties.FACING, orient.getRight()), 2);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(state.get(SubWildProperties.GLOWING) && rand.nextInt(15) == 0)
			world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + 0.5f, (double) pos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) 
	{
		return this.getDefaultState().with(SubWildProperties.GLOWING, ctx.getWorld().getLightSubtracted(ctx.getPos(), 0) < GLOWING_THRESHOLD).with(BlockStateProperties.FACING, ctx.getFace());
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		return state.isValidPosition(world, pos) ? super.updatePostPlacement(state, side, adjState, world, pos, adjPos) : Blocks.AIR.getDefaultState();
	}

	// TODO Something better than a material check (e.g. tags)
	// TODO Add forge thing support
	public boolean isValidGround(BlockState state, IWorldReader world, BlockPos pos)
	{
		return state.getMaterial() == Material.WOOD && state.isOpaqueCube(world, pos); //  && state.canSustainPlant(world, pos, side, this)
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockPos adjPos = pos.offset(state.get(BlockStateProperties.FACING).getOpposite());
		return this.isValidGround(world.getBlockState(adjPos), world, adjPos);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader world, BlockPos pos, PathType type)
	{
		return type == PathType.AIR && !this.canCollide ? true : super.allowsMovement(state, world, pos, type);
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos)
	{
		return PlantType.CAVE;
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos)
	{
		BlockState state = world.getBlockState(pos);
		return state.getBlock() != this ? this.getDefaultState() : state;
	}
}