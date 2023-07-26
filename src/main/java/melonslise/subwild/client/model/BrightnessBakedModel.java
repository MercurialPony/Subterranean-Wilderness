package melonslise.subwild.client.model;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;

@OnlyIn(Dist.CLIENT)
public class BrightnessBakedModel extends BakedModelWrapper
{
	public final Predicate<ResourceLocation> filter;

	public BrightnessBakedModel(BakedModel model, Predicate<ResourceLocation> filter)
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
			if(quad.getVertices()[6] == 0x00F00F0)
				break;
			if(this.filter.test(quad.getSprite().getName()))
				transformQuad(quad);
		}
		return quads;
	}

	public static void transformQuad(BakedQuad quad)
	{
		int[] vertexData = quad.getVertices();
		final int step = vertexData.length / 4;
		vertexData[6] = 0x00F000F0;
		vertexData[6 + step] = 0x00F000F0;
		vertexData[6 + 2 * step] = 0x00F000F0;
		vertexData[6 + 3 * step] = 0x00F000F0;
	}
}