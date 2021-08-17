package melonslise.subwild.common.init;

import java.util.Map;

import net.minecraft.block.SixWayBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Direction;

public final class SubWildProperties
{
	public static final Map<Direction, BooleanProperty> FACING_LOOKUP = SixWayBlock.PROPERTY_BY_DIRECTION;

	public static final BooleanProperty GLOWING = BooleanProperty.create("glowing");
	public static final DirectionProperty VERTICAL_FACING = DirectionProperty.create("facing", Direction.Axis.Y);

	// public static final ModelProperty<BlockState> MIMIC_PROPERTY = new ModelProperty<>();
}