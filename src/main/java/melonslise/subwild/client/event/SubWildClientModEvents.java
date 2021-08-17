package melonslise.subwild.client.event;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import melonslise.subwild.SubWild;
import melonslise.subwild.client.model.BrightnessBakedModel;
import melonslise.subwild.common.init.SubWildBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SubWild.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SubWildClientModEvents
{
	public static final List<Pair<ModelResourceLocation, Function<IBakedModel, IBakedModel>>> MODEL_OVERRIDES = Lists.newArrayList();

	@SubscribeEvent
	public static void onSetup(FMLClientSetupEvent event)
	{
		RenderTypeLookup.setRenderLayer(SubWildBlocks.SHORT_FOXFIRE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.LONG_FOXFIRE.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_PATCH.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.WATER_PUDDLE.get(), RenderType.translucent());

		RenderTypeLookup.setRenderLayer(SubWildBlocks.FROZEN_STONE_SPELEOTHEM.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.FROZEN_GRANITE_SPELEOTHEM.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.FROZEN_DIORITE_SPELEOTHEM.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.FROZEN_ANDESITE_SPELEOTHEM.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.FROZEN_SANDSTONE_SPELEOTHEM.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.FROZEN_RED_SANDSTONE_SPELEOTHEM.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.FROZEN_OBSIDIAN_SPELEOTHEM.get(), RenderType.translucent());

		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICICLE.get(), RenderType.translucent());

		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_STONE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_GRANITE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_DIORITE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_ANDESITE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_SANDSTONE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_SMOOTH_SANDSTONE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_RED_SANDSTONE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_SMOOTH_RED_SANDSTONE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_OBSIDIAN.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_BLACKSTONE.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.MOLTEN_BASALT.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_COAL_ORE.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_IRON_ORE.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_GOLD_ORE.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_LAPIS_ORE.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_REDSTONE_ORE.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_DIAMOND_ORE.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(SubWildBlocks.ICE_EMERALD_ORE.get(), RenderType.translucent());

		addBlockOverride(SubWildBlocks.SHORT_FOXFIRE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("glowing")));
		addBlockOverride(SubWildBlocks.LONG_FOXFIRE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("glowing")));

		addFullOverride(SubWildBlocks.MOLTEN_STONE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_GRANITE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_DIORITE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_ANDESITE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_SANDSTONE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_SMOOTH_SANDSTONE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_RED_SANDSTONE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_SMOOTH_RED_SANDSTONE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_OBSIDIAN.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_BLACKSTONE.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
		addFullOverride(SubWildBlocks.MOLTEN_BASALT.get(), model -> new BrightnessBakedModel(model, tex -> tex.getPath().contains("lava")));
	}

	@SubscribeEvent
	public static void registerBlockColors(ColorHandlerEvent.Block event)
	{
		event.getBlockColors().register((state, world, pos, layer) -> BiomeColors.getAverageWaterColor(world, pos), SubWildBlocks.WATER_PUDDLE.get());
	}

	@SubscribeEvent
	public static void registerItemColors(ColorHandlerEvent.Item event)
	{
		event.getItemColors().register((stack, layer) -> Fluids.WATER.getAttributes().getColor(), SubWildBlocks.WATER_PUDDLE.get());
	}

	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event)
	{
		Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
		for(Pair<ModelResourceLocation, Function<IBakedModel, IBakedModel>> override : MODEL_OVERRIDES)
		{
			IBakedModel model = registry.get(override.getLeft());
			if(model != null)
				registry.put(override.getLeft(), override.getRight().apply(model));
		}
	}

	public static void addBlockOverride(Block block, Function<IBakedModel, IBakedModel> wrap)
	{
		for(BlockState state : block.getStateDefinition().getPossibleStates())
			MODEL_OVERRIDES.add(Pair.of(BlockModelShapes.stateToModelLocation(state), wrap));
	}

	// TODO Better way to get model
	public static void addItemOverride(IItemProvider item, Function<IBakedModel, IBakedModel> wrap)
	{
		MODEL_OVERRIDES.add(Pair.of(new ModelResourceLocation(item.asItem().getRegistryName(), "inventory"), wrap));
	}

	public static void addFullOverride(Block block, Function<IBakedModel, IBakedModel> wrap)
	{
		addBlockOverride(block, wrap);
		addItemOverride(block, wrap);
	}
}