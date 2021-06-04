package melonslise.subwild.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MagmaBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoltenBlock extends MagmaBlock
{
	public MoltenBlock(Properties properties)
	{
		super(properties.setAllowsSpawn((state, world, pos, type) -> type.isImmuneToFire()));
	}

	@Override
	public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack)
	{
		super.harvestBlock(world, player, pos, state, te, stack);
		if(!EnchantmentHelper.getEnchantments(stack).containsKey(Enchantments.SILK_TOUCH))
			world.setBlockState(pos, Blocks.LAVA.getDefaultState());
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		if(rand.nextInt(10) != 0)
			return;
		for(Direction dir : Direction.Plane.VERTICAL)
		{
			BlockPos adjPos = pos.offset(dir);
			BlockState adjState = world.getBlockState(adjPos);
			if(!adjState.isSolid() || !adjState.isSolidSide(world, adjPos, dir.getOpposite()))
			{
				if(dir == Direction.UP)
					world.addParticle(ParticleTypes.SMOKE, (double) adjPos.getX() + rand.nextDouble(), (double) adjPos.getY() + 0.1, (double) adjPos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
				else
					world.addParticle(ParticleTypes.DRIPPING_LAVA, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() - 0.1d, (double) pos.getZ() + rand.nextDouble(), 0d, 0d, 0d);
			}
		}
	}
}