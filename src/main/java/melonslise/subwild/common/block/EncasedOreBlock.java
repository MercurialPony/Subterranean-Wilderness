package melonslise.subwild.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EncasedOreBlock extends OreBlock
{
	public EncasedOreBlock(Properties properties)
	{
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjState, Direction side)
	{
		return adjState.getBlock() == this ? true : super.isSideInvisible(state, adjState, side);
	}
}