package melonslise.subwild.common.world.gen.feature;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildCapabilities;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class CavePlacement extends Placement<NoPlacementConfig>
{
	public CavePlacement(Codec<NoPlacementConfig> codec)
	{
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, NoPlacementConfig cfg, BlockPos pos)
	{
		World world = helper.level.getLevel();
		if(!SubWildConfig.isAllowed(world) || !world.getCapability(SubWildCapabilities.NOISE_CAPABILITY).isPresent())
			return Stream.empty();
		Set<BlockPos> set = new HashSet<>(1024);
		IChunk chunk = helper.level.getChunk(pos);
		ChunkPos chPos = chunk.getPos();
		if(SubWildConfig.EXPENSIVE_SCAN.get())
		{
			BlockPos.Mutable mut = new BlockPos.Mutable();
			for(int x = 0; x < 16; ++x)
				for(int z = 0; z < 16; ++z)
					for(int y = 0, yMax = chunk.getHeight(Heightmap.Type.OCEAN_FLOOR, x, z); y < yMax; ++y)
						if(chunk.getBlockState(mut.set(x, y, z)).getBlock() == Blocks.CAVE_AIR)
							set.add(mut.offset(chPos.getMinBlockX(), 0, chPos.getMinBlockZ()).immutable());
		}
		BitSet bits = ((ChunkPrimer) chunk).getOrCreateCarvingMask(GenerationStage.Carving.AIR);
			for(int a = 0; a < bits.length(); ++a)
				if(bits.get(a))
					set.add(new BlockPos(chPos.getMinBlockX() + (a & 15), a >> 8, chPos.getMinBlockZ() + (a >> 4 & 15)));
		return set.stream();
	}
}