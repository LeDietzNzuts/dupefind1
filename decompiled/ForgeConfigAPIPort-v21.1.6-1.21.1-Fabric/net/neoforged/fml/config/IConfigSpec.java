package net.neoforged.fml.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import org.jetbrains.annotations.Nullable;

public interface IConfigSpec {
   boolean isEmpty();

   boolean isCorrect(UnmodifiableCommentedConfig var1);

   void correct(CommentedConfig var1);

   void acceptConfig(@Nullable IConfigSpec.ILoadedConfig var1);

   public interface ILoadedConfig {
      CommentedConfig config();

      void save();
   }
}
