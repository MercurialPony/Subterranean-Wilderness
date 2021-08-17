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

import net.minecraft.block.AbstractBlock.Properties;

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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(SubWildProperties.GLOWING, BlockStateProperties.FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
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
			return VoxelShapes.empty();
		}
	}

	/*
	 *	Block#randomTick receives random ticks. By default also calls Block#tick
	 *	Block#tick receives scheduled ticks
	 */
	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
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
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(state.getValue(SubWildProperties.GLOWING) && rand.nextInt(15) == 0)
			world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + 0.5f, (double) pos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) 
	{
		return this.defaultBlockState().setValue(SubWildProperties.GLOWING, ctx.getLevel().getRawBrightness(ctx.getClickedPos(), 0) < GLOWING_THRESHOLD).setValue(BlockStateProperties.FACING, ctx.getClickedFace());
	}

	@Override
	public BlockState updateShape(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		return state.canSurvive(world, pos) ? super.updateShape(state, side, adjState, world, pos, adjPos) : Blocks.AIR.defaultBlockState();
	}

	// TODO Something better than a material check (e.g. tags)
	// TODO Add forge thing support
	public boolean isValidGround(BlockState state, IWorldReader world, BlockPos pos)
	{
		return state.getMaterial() == Material.WOOD && state.isSolidRender(world, pos); //  && state.canSustainPlant(world, pos, side, this)
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockPos adjPos = pos.relative(state.getValue(BlockStateProperties.FACING).getOpposite());
		return this.isValidGround(world.getBlockState(adjPos), world, adjPos);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader world, BlockPos pos, PathType type)
	{
		return type == PathType.AIR && !this.hasCollision || super.isPathfindable(state, world, pos, type);
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
		return state.getBlock() != this ? this.defaultBlockState() : state;
	}
}