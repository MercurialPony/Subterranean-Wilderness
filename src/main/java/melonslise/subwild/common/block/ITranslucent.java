package melonslise.subwild.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface ITranslucent
{
	default boolean isIce(BlockState state)
	{
		return true;
	}

	static boolean isAdjacentIce(BlockState state)
	{
		Block block = state.getBlock();
		return block instanceof ITranslucent iTranslucent && iTranslucent.isIce(state);
	}
}