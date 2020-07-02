package melonslise.subwild.common.init;

import java.util.ArrayList;
import java.util.List;

import melonslise.subwild.SubWild;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;

public final class SubWildItems
{
	private SubWildItems( ) {}

	public static final List<Item> ITEMS = new ArrayList<>(142);

	public static final ItemGroup TAB = new ItemGroup(SubWild.ID)
	{
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(SubWildBlocks.LONG_FOXFIRE);
		}
	};

	public static void register(RegistryEvent.Register<Item> event)
	{
		for(Item item : ITEMS)
			event.getRegistry().register(item);
	}

	public static Item add(String name, Item item)
	{
		ITEMS.add(item.setRegistryName(SubWild.ID, name));
		return item;
	}

	public static Item add(BlockItem item)
	{
		ITEMS.add(item.setRegistryName(item.getBlock().getRegistryName()));
		return item;
	}
}