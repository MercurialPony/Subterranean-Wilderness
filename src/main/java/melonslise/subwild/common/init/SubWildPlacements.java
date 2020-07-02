package melonslise.subwild.common.init;

import java.util.ArrayList;
import java.util.List;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.world.gen.feature.PlacementAtCave;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;

public final class SubWildPlacements
{
	private SubWildPlacements() {}

	public static final List<Placement> PLACEMENTS = new ArrayList<>(1);

	public static final Placement
		CAVE = add("cave", new PlacementAtCave(NoPlacementConfig::deserialize));

	public static void register(RegistryEvent.Register<Placement<?>> event)
	{
		for(Placement<?> pl : PLACEMENTS)
			event.getRegistry().register(pl);
	}

	public static Placement add(String name, Placement pl)
	{
		PLACEMENTS.add((Placement) pl.setRegistryName(SubWild.ID, name));
		return pl;
	}
}