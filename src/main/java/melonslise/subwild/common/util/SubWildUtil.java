package melonslise.subwild.common.util;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.Property;
import net.minecraft.state.StateHolder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public final class SubWildUtil
{
	/*
	public static String stringFromState(BlockState state)
	{
		if(state == null)
			return "";
		String str = state.getBlock().getRegistryName().toString();
		if (!state.getValues().isEmpty())
		{
			str += "[";
			str += state.getValues().entrySet().stream()
					.map(entry -> entry.getKey().getName() + "=" + Util.getValueName(entry.getKey(), entry.getValue()))
					.collect(Collectors.joining(","));
			str += "]";
		}
		return str;
	}

	public static BlockState stateFromString(String str)
	{
		try
		{
			BlockState state = new BlockStateArgument().parse(new StringReader(str)).getState();
			return state;
		}
		catch (CommandSyntaxException e)
		{
			return Blocks.AIR.getDefaultState();
		}
	}
	*/

	public static BlockState waterlog(BlockState state, IWorldReader world, BlockPos pos)
	{
		FluidState fluidState = world.getFluidState(pos);
		return state.setValue(BlockStateProperties.WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8);
	}

	public static BlockState copyStateProps(BlockState from, BlockState to)
	{
		for(Property prop : from.getProperties()) // getProperties
			to = to.setValue(prop, from.getValue(prop));
		return to;
	}

	// TODO Test performance vs manual creation of map and iteration
	/*
	 * T must implement hashCode or equals or be a singleton
	 */
	public static <T> T mostFrequent(Stream<T> stream)
	{
		return stream
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
			.entrySet()
			.stream()
			.max(Map.Entry.comparingByValue())
			.map(Map.Entry::getKey)
			.orElse(null);
	}
}