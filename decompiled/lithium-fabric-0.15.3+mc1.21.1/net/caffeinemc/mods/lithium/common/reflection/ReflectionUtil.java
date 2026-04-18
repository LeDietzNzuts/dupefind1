package net.caffeinemc.mods.lithium.common.reflection;

import java.util.WeakHashMap;
import net.caffeinemc.mods.lithium.common.services.PlatformMappingInformation;
import net.minecraft.class_128;
import net.minecraft.class_129;
import net.minecraft.class_1297;
import net.minecraft.class_148;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_4970;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReflectionUtil {
   private static final String REMAPPED_ON_ENTITY_COLLISION = PlatformMappingInformation.INSTANCE
      .mapMethodName(
         "intermediary",
         "net.minecraft.class_4970",
         "method_9548",
         "(Lnet/minecraft/class_2680;Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_1297;)V",
         "entityInside"
      );
   private static final WeakHashMap<Class<?>, Boolean> CACHED_IS_ENTITY_TOUCHABLE = new WeakHashMap<>();

   public static boolean hasMethodOverride(Class<?> clazz, Class<?> superclass, boolean fallbackResult, String methodName, Class<?>... methodArgs) {
      while (clazz != null && clazz != superclass && superclass.isAssignableFrom(clazz)) {
         try {
            clazz.getDeclaredMethod(methodName, methodArgs);
            return true;
         } catch (NoSuchMethodException var9) {
            clazz = clazz.getSuperclass();
         } catch (NoClassDefFoundError var10) {
            Logger logger = LogManager.getLogger("Lithium Class Analysis");
            logger.warn(
               "Lithium Class Analysis Error: Class "
                  + clazz.getName()
                  + " cannot be analysed, because getting declared methods crashes with NoClassDefFoundError: "
                  + var10.getMessage()
                  + ". This is usually caused by modded entities declaring methods that have a return type or parameter type that is annotated with @Environment(value=EnvType.CLIENT). Loading the type is not possible, because it only exists in the CLIENT environment. The recommended fix is to annotate the method with this argument or return type with the same annotation. Lithium handles this error by assuming the class cannot be included in some optimizations."
            );
            return fallbackResult;
         } catch (Throwable var11) {
            String crashedClass = clazz.getName();
            class_128 crashReport = class_128.method_560(var11, "Lithium Class Analysis");
            class_129 crashReportSection = crashReport.method_562(var11.getClass().toString() + " when getting declared methods.");
            crashReportSection.method_578("Analyzed class", crashedClass);
            crashReportSection.method_578("Analyzed method name", methodName);
            crashReportSection.method_578("Analyzed method args", methodArgs);
            throw new class_148(crashReport);
         }
      }

      return false;
   }

   public static boolean isBlockStateEntityTouchable(class_2680 operand) {
      Class<? extends class_2248> blockClazz = (Class<? extends class_2248>)operand.method_26204().getClass();
      Boolean result = CACHED_IS_ENTITY_TOUCHABLE.get(blockClazz);
      if (result != null) {
         return result;
      } else {
         boolean res = hasMethodOverride(
            blockClazz, class_4970.class, true, REMAPPED_ON_ENTITY_COLLISION, class_2680.class, class_1937.class, class_2338.class, class_1297.class
         );
         CACHED_IS_ENTITY_TOUCHABLE.put(blockClazz, res);
         return res;
      }
   }
}
