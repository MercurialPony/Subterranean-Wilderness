package melonslise.subwild.common.world.gen.feature;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class PlacementAtCave extends Placement<NoPlacementConfig>
{
	public PlacementAtCave(Codec<NoPlacementConfig> codec)
	{
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator gen, Random rand, NoPlacementConfig cfg, BlockPos pos)
	{
		Set<BlockPos> set = new HashSet<>(1024);
		IChunk chunk = world.getChunk(pos);
		ChunkPos chPos = chunk.getPos();
		BlockPos.Mutable mut = new BlockPos.Mutable();
		for(int x = 0; x < 16; ++x)
			for(int z = 0; z < 16; ++z)
				for(int y = 0, yMax = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, x, z); y < yMax; ++y)
					if(chunk.getBlockState(mut.setPos(x, y, z)).getBlock() == Blocks.CAVE_AIR)
						set.add(mut.add(chPos.getXStart(), 0, chPos.getZStart()).toImmutable());
		BitSet bits = ((ChunkPrimer) chunk).getCarvingMask(GenerationStage.Carving.AIR);
		if(bits != null)
			for(int a = 0; a < bits.length(); ++a)
				if(bits.get(a))
					set.add(new BlockPos(chPos.getXStart() + (a & 15), a >> 8, chPos.getZStart() + (a >> 4 & 15)));
		return set.stream();
	}
}