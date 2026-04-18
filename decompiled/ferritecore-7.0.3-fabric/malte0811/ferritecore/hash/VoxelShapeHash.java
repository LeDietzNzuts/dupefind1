package malte0811.ferritecore.hash;

import it.unimi.dsi.fastutil.Hash.Strategy;
import malte0811.ferritecore.mixin.accessors.ArrayVSAccess;
import malte0811.ferritecore.mixin.accessors.SliceShapeAccess;
import malte0811.ferritecore.mixin.accessors.VoxelShapeAccess;
import net.minecraft.class_249;
import net.minecraft.class_265;

public class VoxelShapeHash implements Strategy<class_265> {
   public static final VoxelShapeHash INSTANCE = new VoxelShapeHash();

   public int hashCode(class_265 o) {
      return this.hashCode((VoxelShapeAccess)o);
   }

   public int hashCode(VoxelShapeAccess o) {
      if (o instanceof SliceShapeAccess access) {
         return SliceShapeHash.INSTANCE.hashCode(access);
      } else if (o instanceof ArrayVSAccess access) {
         return ArrayVoxelShapeHash.INSTANCE.hashCode(access);
      } else {
         return this.isCubeShape(o) ? DiscreteVSHash.INSTANCE.hashCode(o.getShape()) : o.hashCode();
      }
   }

   public boolean equals(class_265 a, class_265 b) {
      return this.equals((VoxelShapeAccess)a, (VoxelShapeAccess)b);
   }

   public boolean equals(VoxelShapeAccess a, VoxelShapeAccess b) {
      if (a == b) {
         return true;
      } else if (a != null && b != null) {
         if (a.getClass() != b.getClass()) {
            return false;
         } else if (a instanceof SliceShapeAccess accessA) {
            return SliceShapeHash.INSTANCE.equals(accessA, (SliceShapeAccess)b);
         } else if (a instanceof ArrayVSAccess accessA) {
            return ArrayVoxelShapeHash.INSTANCE.equals(accessA, (ArrayVSAccess)b);
         } else {
            return this.isCubeShape(a) ? DiscreteVSHash.INSTANCE.equals(a.getShape(), b.getShape()) : a.equals(b);
         }
      } else {
         return false;
      }
   }

   private boolean isCubeShape(Object o) {
      return o instanceof class_249;
   }
}
