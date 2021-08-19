package melonslise.subwild.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class XpBlock extends OreBlock implements ITranslucent
{
	public final int xpMin, xpMax;

	public XpBlock(Properties properties, int xpMin, int xpMax)
	{
		super(properties);
		this.xpMin = xpMin;
		this.xpMax = xpMax;
	}

	@Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidState)
	{
		return true;
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader world, BlockPos position, int fortune, int silk)
	{
		return silk != 0 ? 0 : Mth.nextInt(this.RANDOM, this.xpMin, this.xpMax);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean skipRendering(BlockState state, BlockState adjState, Direction side)
	{
		return this.isIce(state) && ITranslucent.isAdjacentIce(adjState) || super.skipRendering(state, adjState, side);
	}

	@Override
	public boolean isIce(BlockState state)
	{
		return !state.canOcclude();
	}
}