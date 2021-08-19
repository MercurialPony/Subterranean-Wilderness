package melonslise.subwild.common.world.gen.feature;

import java.util.BitSet;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

public class LiquidCavePlacement extends FeatureDecorator<NoneDecoratorConfiguration>
{
	public LiquidCavePlacement(Codec<NoneDecoratorConfiguration> codec)
	{
		super(codec);
	}

	public Stream<BlockPos> getPositions(DecorationContext helper, Random rand, NoneDecoratorConfiguration cfg, BlockPos pos)
	{
		Level world = helper.getLevel().getLevel();
		if(!SubWildConfig.isAllowed(world) || !world.getCapability(SubWildCapabilities.NOISE_CAPABILITY).isPresent())
			return Stream.empty();
		ChunkPos chunkpos = new ChunkPos(pos);
		BitSet bitset = helper.getCarvingMask(chunkpos, GenerationStep.Carving.LIQUID);
		return IntStream.range(0, bitset.length()).filter(bitset::get).mapToObj((bit) -> new BlockPos(chunkpos.getMinBlockX() + (bit & 15), bit >> 8, chunkpos.getMinBlockZ() + (bit >> 4 & 15)));
	}
}
