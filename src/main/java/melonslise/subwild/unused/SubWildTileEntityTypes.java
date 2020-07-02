/*
package melonslise.expedition.unused;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

import melonslise.expedition.Expedition;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;

public final class ExpeditionTileEntityTypes
{
	private ExpeditionTileEntityTypes() {}

	public static final List<TileEntityType> TILE_ENTITY_TYPES = Lists.newArrayList();

	//public static final TileEntityType MIMIC = add("mimic", MimicTileEntity::new, ExpeditionBlocks.SPELEOTHEM);

	public static void register(RegistryEvent.Register<TileEntityType<?>> event)
	{
		for(TileEntityType type : TILE_ENTITY_TYPES) event.getRegistry().register(type);
	}

	public static TileEntityType add(String name, Supplier<? extends TileEntity> factory, Block... blocks)
	{
		TileEntityType type = TileEntityType.Builder.create(factory, blocks).build(null).setRegistryName(Expedition.ID, name);
		TILE_ENTITY_TYPES.add(type);
		return type;
	}
}
*/