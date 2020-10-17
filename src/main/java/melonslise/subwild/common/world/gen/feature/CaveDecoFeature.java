package melonslise.subwild.common.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildCapabilities;
import melonslise.subwild.common.world.gen.feature.cavetype.CaveType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

public class CaveDecoFeature extends Feature<CaveRangeConfig>
{
	public CaveDecoFeature(Codec<CaveRangeConfig> codec)
	{
		super(codec);
	}

	@Override
	public boolean func_241855_a(final ISeedReader world, final ChunkGenerator gen, final Random rand, final BlockPos pos, final CaveRangeConfig cfg)
	{
		final INoise noise = world.getWorld().getCapability(SubWildCapabilities.NOISE_CAPABILITY).orElse(null);
		if(noise == null)
			return false;
		final float depth = depthAt(world, pos);
		if(depth < 0f)
			return false;
		final CaveType type = cfg.getCaveTypeAt(depth);
		if(type == null)
			return false;
		BlockPos.Mutable adjPos = new BlockPos.Mutable();
		for(int pass = 0; pass < type.getPasses(); ++pass)
		{
			for(Direction dir : type.getGenOrder(pass))
			{
				adjPos.setPos(pos).move(dir);
				if(type.canGenSide(world, adjPos, world.getBlockState(adjPos), depth, pass, dir))
					switch (dir)
					{
					case UP:
						type.genCeil(world, noise, adjPos, depth, pass, rand);
						break;
					case DOWN:
						type.genFloor(world, noise, adjPos, depth, pass, rand);
						break;
					default:
						type.genWall(world, noise, adjPos, depth, pass, rand);
						break;
					}
				if(type.canGenExtra(world, pos, world.getBlockState(pos), adjPos, world.getBlockState(adjPos), depth, pass, dir))
					switch(dir)
					{
					case UP:
						type.genCeilExtra(world, noise, pos, depth, pass, rand);
						break;
					case DOWN:
						type.genFloorExtra(world, noise, pos, depth, pass, rand);
						break;
					default:
						type.genWallExtra(world, noise, pos, dir, depth, pass, rand);
						break;
					}
			}
			if(type.canGenFill(world, pos, world.getBlockState(pos), depth, pass))
				type.genFill(world, noise, pos, depth, pass, rand);
		}
		return false;
	}

	public static float depthAt(final IWorld world, final BlockPos pos)
	{
		return 1f - (float) pos.getY() / (float) world.getHeight(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ());
	}
}