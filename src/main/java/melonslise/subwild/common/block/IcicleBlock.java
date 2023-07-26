package melonslise.subwild.common.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IcicleBlock extends SpeleothemBlock
{
	public IcicleBlock(Properties properties)
	{
		super(properties.randomTicks());
	}

	public boolean isHot(Level world, BlockPos pos, BlockState state)
	{
		return world.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(world, pos);
	}

	public void melt(Level world, BlockPos pos, BlockState state)
	{
		this.tryToFall(world, pos, state);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
	{
		if(this.isHot(world, pos, state))
			this.melt(world, pos, state);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
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