package melonslise.subwild.client.model;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;

@OnlyIn(Dist.CLIENT)
public class BrightnessBakedModel extends BakedModelWrapper
{
	public final Predicate<ResourceLocation> filter;

	public BrightnessBakedModel(IBakedModel model, Predicate<ResourceLocation> filter)
	{
		super(model);
		this.filter = filter;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand, IModelData data)
	{
		List<BakedQuad> quads = this.originalModel.getQuads(state, side, rand, data);
		for(BakedQuad quad : quads)
		{
			if(quad.getVertexData()[6] == 0x00F00F0)
				break;
			if(this.filter.test(quad.func_187508_a().getName()))
				transformQuad(quad);
		}
		return quads;
	}

	public static void transformQuad(BakedQuad quad)
	{
		int[] vertexData = quad.getVertexData();
		vertexData[6] = 0x00F000F0;
		vertexData[6 + 8] = 0x00F000F0;
		vertexData[6 + 8 + 8] = 0x00F000F0;
		vertexData[6 + 8 + 8 + 8] = 0x00F000F0;
	}
}