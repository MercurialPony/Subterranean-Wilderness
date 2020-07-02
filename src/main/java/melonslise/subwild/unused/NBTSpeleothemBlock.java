/*
package melonslise.expedition.unused;

import java.util.List;

import melonslise.expedition.common.config.ExpeditionConfig;
import melonslise.expedition.common.init.ExpeditionProperties;
import melonslise.expedition.common.init.ExpeditionTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// FIXME No sync on place (light, place sound, etc)
public class SpeleothemBlockNBT extends Block
{
	public static final VoxelShape
		SMALL_STALAGMITE_SHAPE = Block.makeCuboidShape(7d, 0d, 7d, 9d, 6d, 9d),
		SMALL_STALACTITE_SHAPE = Block.makeCuboidShape(7d, 10d, 7d, 9d, 16d, 9d),
		MEDIUM_STALAGMITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 0d, 2d, 14d, 6d, 14d), Block.makeCuboidShape(5d, 6d, 5d, 11d, 14d, 11d)),
		MEDIUM_STALACTITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 10d, 2d, 14d, 16d, 14d), Block.makeCuboidShape(5d, 2d, 5d, 11d, 10d, 11d)),
		LARGE_STALAGMITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 0d, 2d, 14d, 6d, 14d), Block.makeCuboidShape(5d, 6d, 5d, 11d, 16d, 11d)),
		LARGE_STALACTITE_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2d, 10d, 2d, 14d, 16d, 14d), Block.makeCuboidShape(5d, 0d, 5d, 11d, 10d, 11d)),
		COLUMN_SHAPE = Block.makeCuboidShape(6d, 0d, 6d, 10d, 16d, 10d);

	public SpeleothemBlockNBT(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.UP, false).with(BlockStateProperties.DOWN, false).with(ExpeditionProperties.VERTICAL_FACING, Direction.UP));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.UP).add(BlockStateProperties.DOWN).add(ExpeditionProperties.VERTICAL_FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
	{
		boolean up = state.get(BlockStateProperties.UP);
		boolean down = state.get(BlockStateProperties.DOWN);
		boolean stalagmite = state.get(ExpeditionProperties.VERTICAL_FACING) == Direction.UP;
		if(!up && down && stalagmite)
			return SMALL_STALAGMITE_SHAPE;
		else if(!up && down && !stalagmite)
			return SMALL_STALACTITE_SHAPE;
		else if(!up && !down && stalagmite)
			return MEDIUM_STALAGMITE_SHAPE;
		else if(!up && !down && !stalagmite)
			return MEDIUM_STALACTITE_SHAPE;
		else if(up && !down && stalagmite)
			return LARGE_STALAGMITE_SHAPE;
		else if(up && !down && !stalagmite)
			return LARGE_STALACTITE_SHAPE;
		else
			return COLUMN_SHAPE;
	}

	public BlockState connect(IWorldReader world, BlockPos pos, BlockState state)
	{
		BlockPos up = pos.up();
		BlockPos down = pos.down();
		Block mimic = this.getMimic(world, pos).getBlock();
		return state
			.with(BlockStateProperties.UP,world.getBlockState(up).isIn(ExpeditionTags.SPELEOTHEM) && this.getMimic(world, up).getBlock() == mimic)
			.with(BlockStateProperties.DOWN, world.getBlockState(down).isIn(ExpeditionTags.SPELEOTHEM) && this.getMimic(world, down).getBlock() == mimic);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction side, BlockState adjState, IWorld world, BlockPos pos, BlockPos adjPos)
	{
		return this.isValidPosition(state, world, pos) ? this.connect(world, pos, state) : Blocks.AIR.getDefaultState();
	}

	// FIXME check mimic block
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockPos supPos = pos.offset(state.get(ExpeditionProperties.VERTICAL_FACING).getOpposite());
		BlockState supState = world.getBlockState(supPos);
		//Block mimic = this.getMimic(world, pos).getBlock();
		return supState.isIn(ExpeditionTags.SPELEOTHEM) || supState.isOpaqueCube(world, supPos);
	}

	public MimicTileEntity getTileEntity(IBlockReader world, BlockPos pos)
	{
		return (MimicTileEntity) world.getTileEntity(pos);
	}

	public BlockState getMimic(IBlockReader world, BlockPos pos)
	{
		return this.getTileEntity(world, pos).mimic;
	}

	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return MimicTileEntity.stackFromState(this, this.getMimic(world, pos));
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new MimicTileEntity();
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items)
	{
		super.fillItemGroup(group, items);
		for(BlockState state : ExpeditionConfig.SPELEOTHEM_STATES)
			items.add(MimicTileEntity.stackFromState(this, state));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag)
	{
		super.addInformation(stack, world, tooltip, flag);
		tooltip.add(MimicTileEntity.stateFromStack(stack).getBlock().getNameTextComponent().applyTextStyle(TextFormatting.GRAY));
	}

	@Override
	public MaterialColor getMaterialColor(BlockState state, IBlockReader world, BlockPos pos)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getMaterialColor(state, world, pos) : te.mimic.getMaterialColor(world, pos);
	}

	@Override
	public float getBlockHardness(BlockState state, IBlockReader world, BlockPos pos)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getBlockHardness(state, world, pos) : te.mimic.getBlockHardness(world, pos);
	}

	@Override
	public float getExplosionResistance(BlockState state, IWorldReader world, BlockPos pos, Entity exploder, Explosion explosion)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getExplosionResistance(state, world, pos, exploder, explosion) : te.mimic.getExplosionResistance(world, pos, exploder, explosion);
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getLightValue(state, world, pos) : te.mimic.getLightValue(world, pos);
	}

	@Override
	public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, Entity entity)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getSoundType(state, world, pos, entity) : te.mimic.getSoundType(world, pos, entity);
	}

	@Override
	public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, Entity entity)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getSlipperiness(state, world, pos, entity) : te.mimic.getSlipperiness(world, pos, entity);
	}

	// FIXME IsOpaque ?

	// FIXME IsOpaqueCube ?

	// FIXME propogatesSkylight ?

	@Override
	public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, IBlockReader world, BlockPos pos)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getPlayerRelativeBlockHardness(state, player, world, pos) : te.mimic.getPlayerRelativeBlockHardness(player, world, pos);
	}

	@Override
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.canHarvestBlock(state, world, pos, player) : te.mimic.canHarvestBlock(world, pos, player);
	}

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getFlammability(state, world, pos, face) : te.mimic.getFlammability(world, pos, face);
	}

	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.isFlammable(state, world, pos, face) : te.mimic.isFlammable(world, pos, face);
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.getFireSpreadSpeed(state, world, pos, face) : te.mimic.getFireSpreadSpeed(world, pos, face);
	}

	@Override
	public boolean isFireSource(BlockState state, IBlockReader world, BlockPos pos, Direction side)
	{
		MimicTileEntity te = this.getTileEntity(world, pos);
		return te == null ? super.isFireSource(state, world, pos, side) : te.mimic.isFireSource(world, pos, side);
	}
}
*/