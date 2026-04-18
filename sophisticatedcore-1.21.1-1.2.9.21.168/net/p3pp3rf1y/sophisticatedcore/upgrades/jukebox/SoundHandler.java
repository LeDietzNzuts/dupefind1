package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import java.util.UUID;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_6880;
import net.minecraft.class_9793;

public interface SoundHandler {
   boolean play(class_1937 var1, class_2338 var2, UUID var3, class_1799 var4, class_6880<class_9793> var5);

   boolean play(class_1937 var1, class_243 var2, UUID var3, int var4, class_1799 var5, class_6880<class_9793> var6);

   void stop(class_1937 var1, class_243 var2, UUID var3);

   void update(UUID var1, class_243 var2);
}
