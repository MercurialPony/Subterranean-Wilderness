package melonslise.subwild.common.world.gen.feature;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

public class CavePlacement extends FeatureDecorator<NoneDecoratorConfiguration>
{
	public CavePlacement(Codec<NoneDecoratorConfiguration> codec)
	{
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(DecorationContext helper, Random rand, NoneDecoratorConfiguration cfg, BlockPos pos)
	{
		Level world = helper.getLevel().getLevel();
		if(!SubWildConfig.isAllowed(world) || !world.getCapability(SubWildCapabilities.NOISE_CAPABILITY).isPresent())
			return Stream.empty();
		Set<BlockPos> set = new HashSet<>(1024);
		ChunkAccess chunk = helper.getLevel().getChunk(pos);
		ChunkPos chPos = chunk.getPos();
		if(SubWildConfig.EXPENSIVE_SCAN.get())
		{
			BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos();
			for(int x = 0; x < 16; ++x)
				for(int z = 0; z < 16; ++z)
					for(int y = 0, yMax = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z); y < yMax; ++y)
						if(chunk.getBlockState(mut.set(x, y, z)).getBlock() == Blocks.CAVE_AIR)
							set.add(mut.offset(chPos.getMinBlockX(), 0, chPos.getMinBlockZ()).immutable());
		}
		BitSet bits = ((ProtoChunk) chunk).getOrCreateCarvingMask(GenerationStep.Carving.AIR);
			for(int a = 0; a < bits.length(); ++a)
				if(bits.get(a))
					set.add(new BlockPos(chPos.getMinBlockX() + (a & 15), a >> 8, chPos.getMinBlockZ() + (a >> 4 & 15)));
		return set.stream();
	}
}