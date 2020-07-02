package melonslise.subwild.common.event;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.init.SubWildBlocks;
import melonslise.subwild.common.init.SubWildCapabilities;
import melonslise.subwild.common.init.SubWildFeatures;
import melonslise.subwild.common.init.SubWildItems;
import melonslise.subwild.common.init.SubWildPlacements;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = SubWild.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SubWildModEvents
{
	private SubWildModEvents() {}

	// FIXME Also event for updating the config when the gui for it is added
	@SubscribeEvent
	public static void onSetup(FMLCommonSetupEvent event)
	{
		SubWildFeatures.addFeatures();
		SubWildCapabilities.register();
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		SubWildBlocks.register(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		SubWildItems.register(event);
	}

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
	{
		SubWildFeatures.register(event);
	}

	@SubscribeEvent
	public static void registerPlacements(RegistryEvent.Register<Placement<?>> event)
	{
		SubWildPlacements.register(event);
	}
}