package melonslise.subwild.common.event;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.init.SubWildCapabilities;
import melonslise.subwild.common.init.SubWildLookups;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

@Mod.EventBusSubscriber(modid = SubWild.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class SubWildForgeEvents
{
	private SubWildForgeEvents() {}

	@SubscribeEvent
	public static void onServerLoad(FMLServerAboutToStartEvent event)
	{
		event.getServer().getResourceManager().addReloadListener(new ReloadListener()
		{
			@Override
			protected Object prepare(IResourceManager manager, IProfiler profiler)
			{
				return null;
			}

			@Override
			protected void apply(Object obj, IResourceManager manager, IProfiler profiler)
			{
				SubWildLookups.init();
			}
		});
	}

	@SubscribeEvent
	public static void attachCapabilitiesToWorld(AttachCapabilitiesEvent<World> event)
	{
		SubWildCapabilities.attachToWorld(event);
	}
}