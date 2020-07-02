package melonslise.subwild.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class XpBlock extends OreBlock
{
	public final int xpMin, xpMax;

	public XpBlock(Properties properties, int xpMin, int xpMax)
	{
		super(properties);
		this.xpMin = xpMin;
		this.xpMax = xpMax;
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos position, int fortune, int silk)
	{
		return silk != 0 ? 0 : MathHelper.nextInt(this.RANDOM, this.xpMin, this.xpMax);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjState, Direction side)
	{
		return !this.isSolid(state) && adjState.getBlock() == this ? true : super.isSideInvisible(state, adjState, side);
	}
}