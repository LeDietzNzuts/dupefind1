package net.p3pp3rf1y.sophisticatedbackpacks.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_5250;
import net.minecraft.class_5251;
import net.minecraft.class_2558.class_2559;
import net.minecraft.class_2568.class_5247;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.AccessLogRecord;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackAccessLogger;

public class ListCommand {
   private ListCommand() {
   }

   static ArgumentBuilder<class_2168, ?> register() {
      return ((LiteralArgumentBuilder)class_2170.method_9247("list")
            .executes(context -> printBackpackList(new ArrayList<>(BackpackAccessLogger.getAllBackpackLogs()), (class_2168)context.getSource())))
         .then(
            class_2170.method_9244("playerName", BackpackPlayerArgumentType.playerName())
               .executes(
                  context -> printBackpackList(
                     new ArrayList<>(BackpackAccessLogger.getBackpackLogsForPlayer((String)context.getArgument("playerName", String.class))),
                     (class_2168)context.getSource()
                  )
               )
         );
   }

   private static int printBackpackList(List<AccessLogRecord> allLogs, class_2168 source) {
      SimpleDateFormat dateFormat = new SimpleDateFormat();
      allLogs.sort(Comparator.comparing(AccessLogRecord::getAccessTime).reversed());
      source.method_9226(() -> class_2561.method_43471("commands.sophisticatedbackpacks.list.header"), false);
      allLogs.forEach(
         alr -> {
            class_5250 message = class_2561.method_43470("");
            message.method_10852(
               class_2561.method_43470(alr.getBackpackName())
                  .method_27694(
                     s -> s.method_10977(class_124.field_1060)
                        .method_10958(new class_2558(class_2559.field_11745, "/sophisticatedbackpacks give @p " + alr.getBackpackUuid()))
                        .method_10949(
                           new class_2568(
                              class_5247.field_24342,
                              class_2561.method_43469("chat.sophisticatedbackpacks.backpack_uuid.tooltip", new Object[]{alr.getBackpackUuid().toString()})
                           )
                        )
                  )
            );
            message.method_10852(class_2561.method_43470(", "));
            class_5250 clothColor = class_2561.method_43471("commands.sophisticatedbackpacks.list.cloth_color");
            clothColor.method_27696(clothColor.method_10866().method_27703(class_5251.method_27717(alr.getClothColor())));
            message.method_10852(clothColor);
            message.method_10852(class_2561.method_43470(" "));
            class_5250 trimColor = class_2561.method_43471("commands.sophisticatedbackpacks.list.trim_color");
            trimColor.method_27696(trimColor.method_10866().method_27703(class_5251.method_27717(alr.getTrimColor())));
            message.method_10852(trimColor);
            message.method_10852(class_2561.method_43470(", "));
            message.method_10852(class_2561.method_43470(alr.getPlayerName()));
            message.method_10852(class_2561.method_43470(", "));
            message.method_10852(class_2561.method_43470(dateFormat.format(new Date(alr.getAccessTime()))));
            source.method_9226(() -> message, false);
         }
      );
      return 0;
   }
}
