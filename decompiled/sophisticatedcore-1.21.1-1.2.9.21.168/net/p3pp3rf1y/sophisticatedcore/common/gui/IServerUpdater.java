package net.p3pp3rf1y.sophisticatedcore.common.gui;

import java.util.function.Supplier;
import net.minecraft.class_2487;

public interface IServerUpdater {
   void sendBooleanToServer(String var1, boolean var2);

   void sendDataToServer(Supplier<class_2487> var1);
}
