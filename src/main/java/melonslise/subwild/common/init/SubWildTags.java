package melonslise.subwild.common.init;

import melonslise.subwild.SubWild;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public final class SubWildTags
{
	public static final ITag.INamedTag<Block>
		// Expedition
		FOXFIRE = wrapBlock("foxfire"),
		SPELEOTHEMS = wrapBlock("speleothems"),

		// Forge
		TERRACOTTA = wrapBlock("forge", "terracotta");

	public static final ITag.INamedTag<Item>
		COAL_ORES = wrapItem("coal_ores"),
		IRON_ORES = wrapItem("iron_ores"),
		GOLD_ORES = wrapItem("gold_ores"),
		LAPIS_ORES = wrapItem("lapis_ores"),
		REDSTONE_ORES = wrapItem("redstone_ores"),
		DIAMOND_ORES = wrapItem("diamond_ores"),
		EMERALD_ORES = wrapItem("emerald_ores");

	public static ITag.INamedTag<Block> wrapBlock(String name)
	{
		return wrapBlock(SubWild.ID, name);
	}

	public static ITag.INamedTag<Block> wrapBlock(String id, String name)
	{
		return BlockTags.bind(id + ":" + name);
	}

	public static ITag.INamedTag<Item> wrapItem(String name)
	{
		return wrapItem(SubWild.ID, name);
	}

	public static ITag.INamedTag<Item> wrapItem(String id, String name)
	{
		return ItemTags.bind(id + ":" + name);
	}
}