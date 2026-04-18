package net.raphimc.immediatelyfast.feature.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Collections;
import java.util.List;
import net.minecraft.class_7677;

public record ImmediatelyFastResourcePackMetadata(List<String> compatibleFeatures) {
   public static final ImmediatelyFastResourcePackMetadata DEFAULT = new ImmediatelyFastResourcePackMetadata(Collections.emptyList());
   public static final Codec<ImmediatelyFastResourcePackMetadata> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(Codec.STRING.listOf().fieldOf("compatible_features").forGetter(ImmediatelyFastResourcePackMetadata::compatibleFeatures))
         .apply(instance, ImmediatelyFastResourcePackMetadata::new)
   );
   public static final class_7677<ImmediatelyFastResourcePackMetadata> SERIALIZER = class_7677.method_45252("immediatelyfast", CODEC);
}
