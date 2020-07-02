package melonslise.subwild;

import melonslise.subwild.common.config.SubWildConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(SubWild.ID)
public class SubWild
{
	public static final String ID = "subwild";

	public SubWild()
	{
		ModLoadingContext.get().registerConfig(Type.COMMON, SubWildConfig.SPEC);
	}
}