package melonslise.subwild.common.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.block.AbstractBlock.Properties;

public class IcicleBlock extends SpeleothemBlock
{
	public IcicleBlock(Properties properties)
	{
		super(properties.randomTicks());
	}

	public boolean isHot(World world, BlockPos pos, BlockState state)
	{
		return world.getBrightness(LightType.BLOCK, pos) > 11 - state.getLightBlock(world, pos);
	}

	public void melt(World world, BlockPos pos, BlockState state)
	{
		this.tryToFall(world, pos, state);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		if(this.isHot(world, pos, state))
			this.melt(world, pos, state);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(!this.isHot(world, pos, state) || rand.nextInt(5) != 0)
			return;
		state.getShape(world, pos).forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
		{
			final double x = minX + rand.nextDouble() * (maxX - minX);
			final double y = minY + rand.nextDouble() * (maxY - minY);
			final double z = minZ + rand.nextDouble() * (maxZ - minZ);
			world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + x, pos.getY() + y, pos.getZ() + z, 0d, 0d, 0d);
		});
	}
}