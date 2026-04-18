package net.p3pp3rf1y.sophisticatedcore.crafting;

import com.mojang.serialization.MapCodec;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.class_1860;
import net.minecraft.class_1865;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public class RecipeWrapperSerializer<T extends class_1860<?>, R extends class_1860<?> & IWrapperRecipe<T>> implements class_1865<R> {
   @Nullable
   private MapCodec<R> codec;
   @Nullable
   private class_9139<class_9129, R> streamCodec;
   private final Function<T, R> initialize;
   private final class_1865<T> recipeSerializer;

   public RecipeWrapperSerializer(Function<T, R> initialize, class_1865<T> recipeSerializer) {
      this.initialize = initialize;
      this.recipeSerializer = recipeSerializer;
   }

   public MapCodec<R> method_53736() {
      if (this.codec == null) {
         this.codec = this.recipeSerializer.method_53736().xmap(this.initialize, rec$ -> ((IWrapperRecipe)rec$).getCompose());
      }

      return this.codec;
   }

   public class_9139<class_9129, R> method_56104() {
      if (this.streamCodec == null) {
         this.streamCodec = new class_9139<class_9129, R>() {
            public R decode(class_9129 buffer) {
               T compose = (T)RecipeWrapperSerializer.this.recipeSerializer.method_56104().decode(buffer);
               return compose == null ? null : RecipeWrapperSerializer.this.initialize.apply(compose);
            }

            public void encode(class_9129 pBuffer, R pValue) {
               RecipeWrapperSerializer.this.recipeSerializer.method_56104().encode(pBuffer, pValue.getCompose());
            }
         };
      }

      return this.streamCodec;
   }
}
