package melonslise.subwild.common.init;

import melonslise.subwild.SubWild;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class SubWildItems
{
	public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SubWild.ID);

	public static final CreativeModeTab TAB = new CreativeModeTab(SubWild.ID)
	{
		@OnlyIn(Dist.CLIENT)
		@Override
		public ItemStack makeIcon()
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