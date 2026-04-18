package noobanidus.mods.lootr.common.api;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_6880;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class LootrOptional {
   private static final Map<Class<? extends class_2586>, MethodHandle> blockEntityMap = new HashMap<>();
   private static final Set<Class<? extends class_2586>> invalidBlockEntities = new HashSet<>();
   private static final String GET_LOOTR_OBJECT = "getLootrObject";

   @Nullable
   public static ILootrBlockEntity getBlockEntity(class_2586 blockEntity) {
      class_6880<class_2591<?>> holder = blockEntity.method_11017().method_53254();
      if (holder != null && holder.method_40220(LootrTags.BlockEntity.LOOTR_OBJECT)) {
         if (invalidBlockEntities.contains(blockEntity.getClass())) {
            return null;
         } else {
            MethodHandle handle = blockEntityMap.get(blockEntity.getClass());
            if (handle == null) {
               MethodType type = MethodType.methodType(Object.class);

               try {
                  handle = MethodHandles.lookup().findVirtual(blockEntity.getClass(), "getLootrObject", type);
                  blockEntityMap.put((Class<? extends class_2586>)blockEntity.getClass(), handle);
               } catch (IllegalAccessException | NoSuchMethodException var6) {
                  return null;
               }
            }

            try {
               Object result = (Object)handle.invoke((class_2586)blockEntity);
               if (result instanceof ILootrBlockEntity) {
                  return (ILootrBlockEntity)result;
               } else {
                  invalidBlockEntities.add((Class<? extends class_2586>)blockEntity.getClass());
                  return null;
               }
            } catch (Throwable var5) {
               throw new RuntimeException(var5);
            }
         }
      } else {
         return null;
      }
   }
}
