package net.raphimc.immediatelyfast.feature.sign_text_buffering;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1921;
import net.minecraft.class_3300;
import net.minecraft.class_4013;
import net.minecraft.class_8242;

public class SignTextCache implements class_4013 {
   public final SignAtlasFramebuffer signAtlasFramebuffer = new SignAtlasFramebuffer();
   public final Cache<class_8242, SignAtlasFramebuffer.Slot> slotCache = CacheBuilder.newBuilder()
      .expireAfterAccess(5L, TimeUnit.SECONDS)
      .removalListener(notification -> {
         if (!notification.getCause().equals(RemovalCause.EXPLICIT)) {
            SignAtlasFramebuffer.Slot slot = (SignAtlasFramebuffer.Slot)notification.getValue();
            if (slot != null) {
               slot.markFree();
            }
         }
      })
      .build();
   public final class_1921 renderLayer = class_1921.method_23028(this.signAtlasFramebuffer.getTextureId());

   public void clearCache() {
      this.slotCache.invalidateAll();
      this.signAtlasFramebuffer.clear();
   }

   public void method_14491(class_3300 manager) {
      this.clearCache();
   }
}
