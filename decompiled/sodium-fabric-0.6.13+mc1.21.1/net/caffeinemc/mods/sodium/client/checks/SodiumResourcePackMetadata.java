package net.caffeinemc.mods.sodium.client.checks;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.class_7677;

public record SodiumResourcePackMetadata(List<String> ignoredShaders) {
   public static final Codec<SodiumResourcePackMetadata> CODEC = RecordCodecBuilder.create(
      instance -> instance.group(Codec.STRING.listOf().fieldOf("ignored_shaders").forGetter(SodiumResourcePackMetadata::ignoredShaders))
         .apply(instance, SodiumResourcePackMetadata::new)
   );
   public static final class_7677<SodiumResourcePackMetadata> SERIALIZER = class_7677.method_45252("sodium", CODEC);
}
