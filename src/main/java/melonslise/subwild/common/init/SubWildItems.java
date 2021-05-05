package melonslise.subwild.common.init;

import melonslise.subwild.SubWild;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class SubWildItems
{
	public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SubWild.ID);

	public static final ItemGroup TAB = new ItemGroup(SubWild.ID)
	{
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(SubWildBlocks.LONG_FOXFIRE.get());
		}
	};

	public static void register()
	{
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static RegistryObject<Item> add(String name, Item item)
	{
		return ITEMS.register(name, () -> item);
	}
}