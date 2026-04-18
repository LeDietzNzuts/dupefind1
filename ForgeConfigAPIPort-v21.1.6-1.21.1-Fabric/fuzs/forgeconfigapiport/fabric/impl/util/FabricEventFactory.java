package fuzs.forgeconfigapiport.fabric.impl.util;

import com.google.common.reflect.AbstractInvocationHandler;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.class_1269;
import org.jetbrains.annotations.Nullable;

public final class FabricEventFactory {
   public static <T> Event<T> create(Class<? super T> type) {
      return EventFactory.createArrayBacked(
         type, events -> Proxy.newProxyInstance(EventFactory.class.getClassLoader(), new Class[]{type}, new AbstractInvocationHandler() {
            protected Object handleInvocation(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
               for (Object event : events) {
                  FabricEventFactory.invokeFast(event, method, args);
               }

               return null;
            }
         })
      );
   }

   public static <T> Event<T> createSimpleResult(Class<? super T> type, boolean inverted) {
      return EventFactory.createArrayBacked(
         type, events -> Proxy.newProxyInstance(EventFactory.class.getClassLoader(), new Class[]{type}, new AbstractInvocationHandler() {
            protected Object handleInvocation(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
               for (Object event : events) {
                  boolean result = FabricEventFactory.<Boolean>invokeFast(event, method, args);
                  if (result == inverted) {
                     return result;
                  }
               }

               return !inverted;
            }
         })
      );
   }

   public static <T> Event<T> createVanillaResult(Class<? super T> type) {
      return EventFactory.createArrayBacked(
         type, events -> Proxy.newProxyInstance(EventFactory.class.getClassLoader(), new Class[]{type}, new AbstractInvocationHandler() {
            protected Object handleInvocation(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
               for (Object event : events) {
                  class_1269 result = FabricEventFactory.invokeFast(event, method, args);
                  if (result != class_1269.field_5811) {
                     return result;
                  }
               }

               return class_1269.field_5811;
            }
         })
      );
   }

   private static <T> T invokeFast(Object object, Method method, Object[] args) throws Throwable {
      return (T)MethodHandles.lookup().unreflect(method).bindTo(object).invokeWithArguments(args);
   }
}
