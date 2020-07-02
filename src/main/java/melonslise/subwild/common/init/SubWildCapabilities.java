package melonslise.subwild.common.init;

import melonslise.subwild.common.capability.CapabilityProvider;
import melonslise.subwild.common.capability.EmptyCapabilityStorage;
import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.capability.OpenSimplex2FNoise;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public final class SubWildCapabilities
{
	private SubWildCapabilities() {}

	@CapabilityInject(INoise.class)
	public static final Capability<INoise> NOISE_CAPABILITY = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(INoise.class, new EmptyCapabilityStorage(), OpenSimplex2FNoise::new);
	}

	public static void attachToWorld(AttachCapabilitiesEvent<World> event)
	{
		event.addCapability(OpenSimplex2FNoise.ID, new CapabilityProvider(NOISE_CAPABILITY, new OpenSimplex2FNoise(event.getObject())));
	}
}