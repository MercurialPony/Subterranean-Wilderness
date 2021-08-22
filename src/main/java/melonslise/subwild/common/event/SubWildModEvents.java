package melonslise.subwild.common.event;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.world.gen.feature.CaveDecoFeature;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = SubWild.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SubWildModEvents
{
	// FIXME Also event for updating the config when the gui for it is added
	@SubscribeEvent
	public static void onSetup(FMLCommonSetupEvent event)
	{
		CaveDecoFeature.yungHack = ModList.get().isLoaded("bettercaves");
		if(CaveDecoFeature.yungHack)
		{
			SubWild.LOGGER.warn("");
			SubWild.LOGGER.warn("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			SubWild.LOGGER.warn("Yung's Better Caves Present! Enabling workaround for wall feature placements!");
			SubWild.LOGGER.warn("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			SubWild.LOGGER.warn("");
		}
	}

	@SubscribeEvent
	public static void registerCapability(RegisterCapabilitiesEvent event)
	{
		event.register(INoise.class);
	}
}