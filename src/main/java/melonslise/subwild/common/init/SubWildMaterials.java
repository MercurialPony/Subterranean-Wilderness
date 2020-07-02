package melonslise.subwild.common.init;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public final class SubWildMaterials
{
	private SubWildMaterials() {}

	public static final Material ICE_ORE = new Material.Builder(MaterialColor.ICE).notOpaque().requiresTool().build();
}