/*
package melonslise.expedition.unused;

import javax.annotation.Nonnull;

import melonslise.expedition.common.init.ExpeditionProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;

public class MimicTileEntity extends TileEntity
{
	@Nonnull
	public BlockState mimic = Blocks.AIR.getDefaultState();

	public MimicTileEntity()
	{
		super(null);
		//super(ExpeditionTileEntityTypes.MIMIC);
	}

	// TODO World !isRemote?
	public void sync()
	{
		BlockState state = this.world.getBlockState(this.pos);
		this.world.notifyBlockUpdate(this.pos, state, state, 2);
		this.markDirty();
	}

	@Override
	public IModelData getModelData()
	{
		return new ModelDataMap.Builder().withInitial(ExpeditionProperties.MIMIC_PROPERTY, this.mimic).build();
	}

	public static final String
		MIMIC_KEY = "Mimic",
		TILE_ENTITY_KEY = "BlockEntityTag";

	@Override
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		return new SUpdateTileEntityPacket(this.pos, 1, this.writeData(new CompoundNBT()));
	}

	@Override
	public void onDataPacket(NetworkManager manager, SUpdateTileEntityPacket packet)
	{
		this.readData(packet.getNbtCompound());
		this.requestModelDataUpdate();
		// Rebuild the chunk mesh
		BlockState state = this.world.getBlockState(this.pos);
		this.world.notifyBlockUpdate(this.pos, state, state, 0);
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		return this.write(new CompoundNBT());
	}

	@Override
	public void handleUpdateTag(CompoundNBT nbt)
	{
		this.read(nbt);
	}

	public void readData(CompoundNBT nbt)
	{
		this.mimic = ExpeditionUtils.stateFromString(nbt.getString(MIMIC_KEY));
	}

	public CompoundNBT writeData(CompoundNBT nbt)
	{
		nbt.putString(MIMIC_KEY, ExpeditionUtils.stringFromState(this.mimic));
		return nbt;
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);
		this.readData(nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		super.write(nbt);
		this.writeData(nbt);
		return nbt;
	}

	public static ItemStack stackFromState(IItemProvider item, BlockState state)
	{
		ItemStack stack = new ItemStack(item);
		stack.getOrCreateChildTag(TILE_ENTITY_KEY).putString(MIMIC_KEY, ExpeditionUtils.stringFromState(state));
		return stack;
	}

	public static BlockState stateFromStack(ItemStack stack)
	{
		if(stack.hasTag())
			if(stack.getTag().contains(TILE_ENTITY_KEY))
				return ExpeditionUtils.stateFromString(stack.getTag().getCompound(TILE_ENTITY_KEY).getString(MIMIC_KEY));
		return Blocks.AIR.getDefaultState();
	}
}
*/