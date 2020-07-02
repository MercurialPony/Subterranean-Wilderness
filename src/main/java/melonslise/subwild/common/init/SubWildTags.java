package melonslise.subwild.common.init;

import melonslise.subwild.SubWild;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public final class SubWildTags
{
	private SubWildTags() {}

	public static final Tag<Block>
		// Expedition
		FOXFIRE = wrapBlock("foxfire"),
		SPELEOTHEMS = wrapBlock("speleothems"),

		// Forge
		TERRACOTTA = wrapBlock("forge", "terracotta");

	public static final Tag<Item>
		COAL_ORES = wrapItem("coal_ores"),
		IRON_ORES = wrapItem("iron_ores"),
		GOLD_ORES = wrapItem("gold_ores"),
		LAPIS_ORES = wrapItem("lapis_ores"),
		REDSTONE_ORES = wrapItem("redstone_ores"),
		DIAMOND_ORES = wrapItem("diamond_ores"),
		EMERALD_ORES = wrapItem("emerald_ores");

	public static BlockTags.Wrapper wrapBlock(String name)
	{
		return wrapBlock(SubWild.ID, name);
	}

	public static BlockTags.Wrapper wrapBlock(String id, String name)
	{
		return new BlockTags.Wrapper(new ResourceLocation(id, name));
	}

	public static ItemTags.Wrapper wrapItem(String name)
	{
		return wrapItem(SubWild.ID, name);
	}

	public static ItemTags.Wrapper wrapItem(String id, String name)
	{
		return new ItemTags.Wrapper(new ResourceLocation(id, name));
	}
}