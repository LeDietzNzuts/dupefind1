package malte0811.ferritecore.ducks;

import net.minecraft.class_265;
import org.jetbrains.annotations.Nullable;

public interface BlockStateCacheAccess {
   class_265 getCollisionShape();

   void setCollisionShape(class_265 var1);

   class_265[] getOcclusionShapes();

   void setOcclusionShapes(@Nullable class_265[] var1);

   boolean[] getFaceSturdy();

   void setFaceSturdy(boolean[] var1);
}
