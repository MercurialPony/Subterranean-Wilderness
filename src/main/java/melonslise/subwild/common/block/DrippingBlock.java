package melonslise.subwild.common.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DrippingBlock extends Block
{
	public final ParticleOptions particle;

	public DrippingBlock(Properties properties, ParticleOptions particle)
	{
		super(properties);
		this.particle = particle;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		if(rand.nextInt(10) != 1)
			return;
		BlockPos down = pos.below();
		BlockState downState = world.getBlockState(down);
		if(!downState.canOcclude() || !downState.isFaceSturdy(world, down, Direction.UP))
			world.addParticle(this.particle, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() - 0.1d, (double) pos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
	}
}