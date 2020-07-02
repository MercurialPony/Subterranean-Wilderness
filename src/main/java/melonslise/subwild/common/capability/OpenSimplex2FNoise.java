package melonslise.subwild.common.capability;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.util.OpenSimplex2F;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class OpenSimplex2FNoise implements INoise
{
	public static final ResourceLocation ID = new ResourceLocation(SubWild.ID, "osn");

	public final OpenSimplex2F os2f;

	public OpenSimplex2FNoise()
	{
		this.os2f = new OpenSimplex2F(0);
	}

	public OpenSimplex2FNoise(World world)
	{
		this.os2f = new OpenSimplex2F(world.getSeed());
	}

	@Override
	public double sample(double x, double y, double z)
	{
		return this.os2f.noise3_XZBeforeY(x, y, z);
	}
}