package melonslise.subwild.common.init;

import java.util.Map;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public final class SubWildProperties
{
	public static final Map<Direction, BooleanProperty> FACING_LOOKUP = PipeBlock.PROPERTY_BY_DIRECTION;

	public static final BooleanProperty GLOWING = BooleanProperty.create("glowing");
	public static final DirectionProperty VERTICAL_FACING = DirectionProperty.create("facing", Direction.Axis.Y);

	// public static final ModelProperty<BlockState> MIMIC_PROPERTY = new ModelProperty<>();
}