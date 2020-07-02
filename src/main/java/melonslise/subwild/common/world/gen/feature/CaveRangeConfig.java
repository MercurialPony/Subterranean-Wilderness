package melonslise.subwild.common.world.gen.feature;

import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import melonslise.subwild.common.init.SubWildFeatures;
import melonslise.subwild.common.world.gen.feature.cavetype.CaveType;
import net.minecraft.util.IDynamicSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class CaveRangeConfig implements IFeatureConfig
{
	public static class DepthRange<A extends IDynamicSerializable> implements IDynamicSerializable
	{
		public final A inst;
		public final double min, max;

		public DepthRange(A inst, double min, double max)
		{
			this.inst = inst;
			this.min = Math.min(min, max);
			this.max = Math.max(min, max);
		}

		public boolean contains(double depth)
		{
			return this.min <= depth && depth < this.max;
		}

		@Override
		public <B> B serialize(DynamicOps<B> ops)
		{
			return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(
				ops.createString("inst"), this.inst.serialize(ops),
				ops.createString("min"), ops.createDouble(this.min),
				ops.createString("max"), ops.createDouble(this.max)))).getValue();
		}
	}

	public final List<DepthRange<CaveType>> caveRanges;

	public CaveRangeConfig(List<DepthRange<CaveType>> caveRanges)
	{
		this.caveRanges = caveRanges;
	}

	public CaveType getCaveTypeAt(double depth)
	{
		for(DepthRange<CaveType> caveDepth : this.caveRanges)
			if(caveDepth.contains(depth))
				return caveDepth.inst;
		return null;
	}

	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> ops)
	{
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(
			ops.createString("caveRanges"), ops.createList(this.caveRanges.stream().map(depthRange -> depthRange.serialize(ops))))));
	}

	public static <T> CaveRangeConfig deserialize(Dynamic<T> dyn)
	{
		dyn.get("caveRanges").asList(dyn1 -> new DepthRange(SubWildFeatures.CAVE_TYPES.get(new ResourceLocation(dyn1.get("name").asString(SubWildFeatures.BASIC_CAVE.name.toString()))), dyn1.get("min").asDouble(0d), dyn1.get("max").asDouble(1d)));
		return null;
	}

	public static Builder builder()
	{
		return new Builder();
	}

	public static class Builder
	{
		public final List<DepthRange<CaveType>> caveRanges = Lists.newArrayList();

		public Builder addCaveType(CaveType type, double min, double max)
		{
			this.caveRanges.add(new DepthRange(type, min, max));
			return this;
		}

		public CaveRangeConfig build()
		{
			return new CaveRangeConfig(this.caveRanges);
		}
	}
}