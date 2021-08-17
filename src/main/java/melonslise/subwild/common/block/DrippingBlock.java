package melonslise.subwild.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.block.AbstractBlock.Properties;

public class DrippingBlock extends Block
{
	public final IParticleData particle;

	public DrippingBlock(Properties properties, IParticleData particle)
	{
		super(properties);
		this.particle = particle;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(rand.nextInt(10) != 1)
			return;
		BlockPos down = pos.below();
		BlockState downState = world.getBlockState(down);
		if(!downState.canOcclude() || !downState.isFaceSturdy(world, down, Direction.UP))
			world.addParticle(this.particle, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() - 0.1d, (double) pos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
	}
}