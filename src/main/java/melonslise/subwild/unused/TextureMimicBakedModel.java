/*
package melonslise.expedition.unused;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.mojang.blaze3d.matrix.MatrixStack;

import melonslise.expedition.common.init.ExpeditionProperties;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;

@OnlyIn(Dist.CLIENT)
public class TextureMimicBakedModel extends BakedModelWrapper
{
	public final OverrideList list;
	public TextureAtlasSprite mimic;

	public TextureMimicBakedModel(IBakedModel model)
	{
		super(model);
		this.list = new OverrideList(model.getOverrides());
	}

	public TextureMimicBakedModel(IBakedModel model, TextureAtlasSprite mimic)
	{
		this(model);
		this.mimic = mimic;
	}

	// FIXME Optimize and impr
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand)
	{
		List<BakedQuad> quads = super.getQuads(state, side, rand);
		if(this.mimic != null)
			for(BakedQuad quad : quads)
			{
				if(quad.func_187508_a().getName().equals(this.mimic.getName()))
					break;
				remap(quad, this.mimic);
			}
		return quads;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData data)
	{
		return this.getQuads(state, side, rand);
	}

	@Override
	public IModelData getModelData(ILightReader world, BlockPos pos, BlockState state, IModelData data)
	{
		this.setMimic(data);
		return super.getModelData(world, pos, state, data);
	}

	public void setMimic(IModelData data)
	{
		this.mimic = ExpeditionClientUtils.particleFromState(data.getData(ExpeditionProperties.MIMIC_PROPERTY));
	}

	@Override
	public ItemOverrideList getOverrides()
	{
		return this.list;
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return this.mimic;
	}

	@Override
	public TextureAtlasSprite getParticleTexture(IModelData data)
	{
		this.setMimic(data);
		return this.getParticleTexture();
	}

	@Override
	public IBakedModel handlePerspective(TransformType camera, MatrixStack matrix)
	{
		IBakedModel model = super.handlePerspective(camera, matrix);
		return model instanceof TextureMimicBakedModel ? model : new TextureMimicBakedModel(model, this.mimic);
	}



	public static void remap(BakedQuad quad, TextureAtlasSprite sprite)
	{
		int[] vertexData = quad.getVertexData();
		for (int vertex = 0; vertex < 4; ++vertex)
		{
			int shift = DefaultVertexFormats.BLOCK.getIntegerSize() * vertex;
			vertexData[shift + 4] = Float.floatToRawIntBits(sprite.getInterpolatedU(getUnInterpolatedU(quad.func_187508_a(), Float.intBitsToFloat(vertexData[shift + 4]))));
			vertexData[shift + 5] = Float.floatToRawIntBits(sprite.getInterpolatedV(getUnInterpolatedV(quad.func_187508_a(), Float.intBitsToFloat(vertexData[shift + 5]))));
		}
		quad.sprite = sprite;
	}

	public static float getUnInterpolatedU(TextureAtlasSprite sprite, float u)
	{
		return (u - sprite.getMinU()) / (sprite.getMaxU() - sprite.getMinU()) * 16f;
	}

	public static float getUnInterpolatedV(TextureAtlasSprite sprite, float v)
	{
		return (v - sprite.getMinV()) / (sprite.getMaxV() - sprite.getMinV()) * 16f;
	}

	public static class OverrideList extends ItemOverrideList
	{
		public final ItemOverrideList list;

		public OverrideList(ItemOverrideList list)
		{
			this.list = list;
		}

		@Override
		public IBakedModel getModelWithOverrides(IBakedModel model, ItemStack stack, World world, LivingEntity entity)
		{
			IBakedModel model1 = super.getModelWithOverrides(model, stack, world, entity);
			TextureAtlasSprite sprite = ExpeditionClientUtils.particleFromState(MimicTileEntity.stateFromStack(stack));
			if(model1 instanceof TextureMimicBakedModel)
				((TextureMimicBakedModel) model1).mimic = sprite;
			else
				model1 = new TextureMimicBakedModel(model1, sprite);
			return model1;
		}
	}
}
*/