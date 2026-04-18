package fuzs.forgeconfigapiport.fabric.impl.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.Codec;
import fuzs.forgeconfigapiport.fabric.impl.client.commands.arguments.ModIdArgument;
import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_3542;
import net.minecraft.class_7485;
import net.minecraft.class_2558.class_2559;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfigs;

public class FabricConfigCommand {
   private static final Dynamic2CommandExceptionType ERROR_NO_CONFIG = new Dynamic2CommandExceptionType(
      (modId, type) -> class_2561.method_43469("commands.config.noconfig", new Object[]{modId, type})
   );

   public static <T extends Enum<T> & class_3542, P extends class_2172> void register(CommandDispatcher<P> dispatcher, BiConsumer<P, class_2561> feedbackSender) {
      dispatcher.register(
         (LiteralArgumentBuilder)LiteralArgumentBuilder.literal("config")
            .then(
               RequiredArgumentBuilder.argument(
                     "mod",
                     new ModIdArgument() {
                        @Override
                        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
                           return class_2172.method_9264(
                              FabricLoader.getInstance()
                                 .getAllMods()
                                 .stream()
                                 .map(container -> container.getMetadata().getId())
                                 .filter(FabricConfigCommand::anyModConfigsExist),
                              builder
                           );
                        }
                     }
                  )
                  .then(
                     RequiredArgumentBuilder.argument("type", enumConstant(ModConfig.Type.class))
                        .executes(
                           commandContext -> showFile(
                              component -> feedbackSender.accept((T)((class_2172)commandContext.getSource()), component),
                              (String)commandContext.getArgument("mod", String.class),
                              (ModConfig.Type)commandContext.getArgument("type", ModConfig.Type.class)
                           )
                        )
                  )
            )
      );
   }

   public static <T extends Enum<T> & class_3542> class_7485<T> enumConstant(Class<? extends T> enumClazz) {
      return new class_7485<T>(class_3542.method_28140(enumClazz::getEnumConstants), enumClazz::getEnumConstants) {};
   }

   private static boolean anyModConfigsExist(String modId) {
      return Stream.of(ModConfig.Type.values()).flatMap(type -> ModConfigs.getConfigFileNames(modId, type).stream()).findAny().isPresent();
   }

   private static <T extends Enum<T> & class_3542> int showFile(Consumer<class_2561> feedbackSender, String modId, ModConfig.Type type) throws CommandSyntaxException {
      List<String> configFileNames = ModConfigs.getConfigFileNames(modId, type);
      if (configFileNames.isEmpty()) {
         throw ERROR_NO_CONFIG.create(modId, type.method_15434());
      } else {
         configFileNames.stream()
            .map(File::new)
            .map(FabricConfigCommand::fileComponent)
            .forEach(
               component -> feedbackSender.accept(class_2561.method_43469("commands.config.getwithtype", new Object[]{modId, type.method_15434(), component}))
            );
         return configFileNames.size();
      }
   }

   private static class_2561 fileComponent(File file) {
      return class_2561.method_43470(file.getName())
         .method_27692(class_124.field_1073)
         .method_27694(style -> style.method_10958(new class_2558(class_2559.field_11746, file.getAbsolutePath())));
   }

   public interface ConfigCommandContext<T extends Enum<T> & class_3542> {
      String name();

      Class<T> getType();

      List<String> getConfigFileNames(String var1, T var2);
   }
}
