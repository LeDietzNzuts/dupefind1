package noobanidus.mods.lootr.common.api.processor;

import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

sealed interface ILootrProcessor<T> permits ILootrProcessor.Post, ILootrProcessor.Pre {
   void process(class_3218 var1, @Nullable class_2338 var2, T var3, @Nullable class_2680 var4, @NotNull class_5321<class_52> var5, long var6);

   public sealed interface Post<T> extends ILootrProcessor<T> permits ILootrEntityProcessor.Post, ILootrBlockEntityProcessor.Post {
   }

   public sealed interface Pre<T> extends ILootrProcessor<T> permits ILootrBlockEntityProcessor.Pre, ILootrEntityProcessor.Pre {
   }
}
