package melonslise.subwild.common.world.gen.feature;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import melonslise.subwild.common.world.gen.feature.cavetype.CaveType;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class CaveRangeConfig implements IFeatureConfig
{
	public static final Codec<CaveRangeConfig> CODEC = RecordCodecBuilder.create(record -> record
		.group(
			CaveRange.CODEC.listOf().fieldOf("cave_ranges").forGetter(inst -> inst.caveRanges))
		.apply(record, CaveRangeConfig::new));

	public static class CaveRange
	{
		public static final Codec<CaveRange> CODEC = RecordCodecBuilder.create(record -> record
			.group(
				CaveType.CODEC.fieldOf("cave_type").forGetter(inst -> inst.type),
				Codec.FLOAT.fieldOf("min").forGetter(inst -> (float) inst.min),
				Codec.FLOAT.fieldOf("max").forGetter(inst -> (float) inst.max))
			.apply(record, CaveRange::new));

		public final CaveType type;
		public final double min, max;

		public CaveRange(final CaveType inst, final double min, final double max)
		{
			this.type = inst;
			this.min = Math.min(min, max);
			this.max = Math.max(min, max);
		}

		public boolean contains(final double depth)
		{
			return this.min <= depth && depth < this.max;
		}
	}

	public final List<CaveRange> caveRanges;

	public CaveRangeConfig(final List<CaveRange> caveRanges)
	{
		this.caveRanges = caveRanges;
	}

	public CaveType getCaveTypeAt(final double depth)
	{
		for(final CaveRange caveDepth : this.caveRanges)
			if(caveDepth.contains(depth))
				return caveDepth.type;
		return null;
	}

	public static Builder builder()
	{
		return new Builder();
	}

	public static class Builder
	{
		public final List<CaveRange> caveRanges = Lists.newArrayList();

		public Builder addCaveType(final CaveType type, final double min, final double max)
		{
			this.caveRanges.add(new CaveRange(type, min, max));
			return this;
		}

		public CaveRangeConfig build()
		{
			return new CaveRangeConfig(this.caveRanges);
		}
	}
}