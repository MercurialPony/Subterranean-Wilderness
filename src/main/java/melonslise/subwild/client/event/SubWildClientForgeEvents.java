package melonslise.subwild.client.event;

import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;

import melonslise.subwild.SubWild;
import melonslise.subwild.common.init.SubWildFeatures;
import melonslise.subwild.common.world.gen.feature.CaveDecoFeature;
import melonslise.subwild.common.world.gen.feature.CaveRangeConfig;
import melonslise.subwild.common.world.gen.feature.cavetype.CaveType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SubWild.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class SubWildClientForgeEvents
{
	@SubscribeEvent
	public static void onRenderText(final RenderGameOverlayEvent.Text event)
	{
		Minecraft mc = Minecraft.getInstance();
		if(!mc.gameSettings.showDebugInfo || mc.isReducedDebug())
			return;

		try {
			BlockPos pos = mc.player.getPosition();
			CaveType cave = null;
			List<List<Supplier<ConfiguredFeature<?, ?>>>> stageToFeature = mc.world.getBiome(pos).getGenerationSettings().getFeatures();
			final int stage = GenerationStage.Decoration.UNDERGROUND_DECORATION.ordinal();
			if (stageToFeature.size() >= stage)
				for (Supplier<ConfiguredFeature<?, ?>> supp : stageToFeature.get(stage)) {
					ConfiguredFeature cf = supp.get();
					if (cf.config instanceof DecoratedFeatureConfig) {
						ConfiguredFeature cf1 = ((DecoratedFeatureConfig) cf.config).feature.get();
						if (cf1.feature == SubWildFeatures.CAVE_DECO.get()) {
							cave = ((CaveRangeConfig) cf1.config).getCaveTypeAt(CaveDecoFeature.depthAt(mc.world, pos));
							break;
						}
					}
				}
			ListIterator<String> it = event.getLeft().listIterator();
			while (it.hasNext())
				if (it.next().startsWith("Biome:")) {
					it.add("Cave: " + (cave != null ? cave.name : "none"));
					break;
				}
		} catch (final IndexOutOfBoundsException e) {
			SubWild.LOGGER.fatal("### Something has broken core Minecraft behaviour that SubWild relies on! ###");
			SubWild.LOGGER.error("");
			SubWild.LOGGER.error("Help fix it");
			SubWild.LOGGER.error("-----------");
			SubWild.LOGGER.error("1) Check for updates to your mods - this might've already been fixed in newer versions.");
			SubWild.LOGGER.error("2) Make a backup of your world and remove mods one by one until this stops happening.");
			SubWild.LOGGER.error("3) Contact the author of the last mod you removed and give them this crash report.");
			SubWild.LOGGER.error("4) File an issue with SubWild with the name of the mod from step 2 that was causing the problem");
			SubWild.LOGGER.error("   so that we can look into fixing it on our side if necessary.");
			SubWild.LOGGER.error("   You can file an issue here: https://github.com/Melonslise/Subterranean-Wilderness/issues");
			SubWild.LOGGER.error("5) Add back in all the other mods that weren't causing problems and restore your backup from step 1.");
			SubWild.LOGGER.error("");
			SubWild.LOGGER.error("Happening with only SubWild installed?");
			SubWild.LOGGER.error("--------------------------------------");
			SubWild.LOGGER.error("If this crash is happening and the only mod installed is SubWild:");
			SubWild.LOGGER.error("1) Check for updates - this might've already been fixed in newer versions.");
			SubWild.LOGGER.error("   https://curseforge.com/minecraft/mc-mods/subterranean-wilderness/files/all?filter-game-version=1738749986%3A70886");
			SubWild.LOGGER.error("2) File an issue with this crash report if you're on the latest version so that we can look into fixing it.");
			SubWild.LOGGER.error("   You can file an issue here: https://github.com/Melonslise/Subterranean-Wilderness/issues");
			SubWild.LOGGER.error("");
			SubWild.LOGGER.error("Mod dev?");
			SubWild.LOGGER.error("--------");
			SubWild.LOGGER.error("You can find the source code here (assuming v1.2.1):");
			SubWild.LOGGER.error("https://github.com/Melonslise/Subterranean-Wilderness/blob/master/src/main/java/melonslise/subwild/client/event/SubWildClientForgeEvents.java");
			SubWild.LOGGER.error("");
			SubWild.LOGGER.error("Crash stacktrace is:");
			e.printStackTrace();
			SubWild.LOGGER.error("");
			throw e;
		}
	}
}