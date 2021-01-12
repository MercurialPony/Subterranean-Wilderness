package melonslise.subwild.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public interface ITranslucent
{
	default boolean isIce(BlockState state)
	{
		return true;
	}

	public static boolean isAdjacentIce(BlockState state)
	{
		Block block = state.getBlock();
		return block instanceof ITranslucent && ((ITranslucent) block).isIce(state);
	}
}