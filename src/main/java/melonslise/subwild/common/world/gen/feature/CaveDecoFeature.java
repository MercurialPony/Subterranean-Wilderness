package melonslise.subwild.common.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import melonslise.subwild.common.capability.INoise;
import melonslise.subwild.common.init.SubWildCapabilities;
import melonslise.subwild.common.world.gen.feature.cavetype.CaveType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class CaveDecoFeature extends Feature<CaveRangeConfig>
{
	public CaveDecoFeature(Codec<CaveRangeConfig> codec)
	{
		super(codec);
	}

	public static boolean yungHack = false;

	@Override
	public boolean place(FeaturePlaceContext<CaveRangeConfig> featurePlaceContext)
	{
		WorldGenLevel world = featurePlaceContext.level();
		Random rand = featurePlaceContext.random();
		BlockPos pos = featurePlaceContext.origin();
		CaveRangeConfig cfg = featurePlaceContext.config();

		final float depth = depthAt(world, pos);
		if(depth < 0f)
			return false;
		CaveType type = cfg.getCaveTypeAt(depth);
		if(type == null)
			return false;
		INoise noise = world.getLevel().getCapability(SubWildCapabilities.NOISE_CAPABILITY).orElse(null);
		BlockPos.MutableBlockPos adjPos = new BlockPos.MutableBlockPos();
		for(int pass = 0; pass < type.getPasses(); ++pass)
		{
			for(Direction dir : type.getGenOrder(pass))
			{
				adjPos.set(pos).move(dir);
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
						if(yungHack) // can be shortened to 1 if but this is more readable
						{
							if(dir == Direction.EAST && adjPos.getX() % 16 == 0)
								break;
							if(dir == Direction.SOUTH && adjPos.getZ() % 16 == 0)
								break;
							if(dir == Direction.WEST && (adjPos.getX() + 1) % 16 == 0)
								break;
							if(dir == Direction.NORTH && (adjPos.getZ() + 1) % 16 == 0)
								break;
						}
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
						if(yungHack) // can be shortened to 1 if but this is more readable
						{
							if(dir == Direction.EAST && adjPos.getX() % 16 == 0)
								break;
							if(dir == Direction.SOUTH && adjPos.getZ() % 16 == 0)
								break;
							if(dir == Direction.WEST && (adjPos.getX() + 1) % 16 == 0)
								break;
							if(dir == Direction.NORTH && (adjPos.getZ() + 1) % 16 == 0)
								break;
						}
						type.genWallExtra(world, noise, pos, dir, depth, pass, rand);
						break;
					}
			}
			if(type.canGenFill(world, pos, world.getBlockState(pos), depth, pass))
				type.genFill(world, noise, pos, depth, pass, rand);
		}
		return false;
	}

	public static float depthAt(LevelAccessor world, BlockPos pos)
	{
		return 1f - (float) pos.getY() / (float) world.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ());
	}
}