package melonslise.subwild.common.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityProvider<A> implements ICapabilityProvider
{
	protected Capability<A> capability;
	protected A instance;
	protected LazyOptional<A> optional;

	public CapabilityProvider(@Nonnull Capability<A> capability, @Nonnull A instance)
	{
		this.capability = capability;
		this.instance = instance;
		this.optional = LazyOptional.of(() -> instance);
	}

	@Override
	public <B> LazyOptional<B> getCapability(@Nonnull final Capability<B> capability, final @Nullable Direction side)
	{
		return this.capability.orEmpty(capability, this.optional);
	}
}