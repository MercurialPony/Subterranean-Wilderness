package melonslise.subwild.common.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoltenBlock extends MagmaBlock
{
	public MoltenBlock(Properties properties)
	{
		super(properties.isValidSpawn((state, world, pos, type) -> type.fireImmune()));
	}

	@Override
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, BlockEntity te, ItemStack stack)
	{
		super.playerDestroy(world, player, pos, state, te, stack);
		if(!EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.SILK_TOUCH))
			world.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		if(rand.nextInt(10) != 0)
			return;
		for(Direction dir : Direction.Plane.VERTICAL)
		{
			BlockPos adjPos = pos.relative(dir);
			BlockState adjState = world.getBlockState(adjPos);
			if(!adjState.canOcclude() || !adjState.isFaceSturdy(world, adjPos, dir.getOpposite()))
			{
				if(dir == Direction.UP)
					world.addParticle(ParticleTypes.SMOKE, (double) adjPos.getX() + rand.nextDouble(), (double) adjPos.getY() + 0.1, (double) adjPos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
				else
					world.addParticle(ParticleTypes.DRIPPING_LAVA, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() - 0.1d, (double) pos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
			}
		}
	}
}