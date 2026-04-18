package com.magistuarmory.block;

import com.magistuarmory.item.MedievalShieldItem;
import com.magistuarmory.item.ModItems;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1767;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2185;
import net.minecraft.class_2215;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3468;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_7718;
import net.minecraft.class_7833;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class PaviseBlock extends class_2185 {
   public static final class_2758 ROTATION = class_2741.field_12532;
   static final class_238 COLLISION_AABB = new class_238(0.0, 0.0, 0.46875, 1.0, 1.0, 0.53125);
   static final Vector3d CENTER = new Vector3d(0.5, 0.5, 0.5);
   static final Vector3d BOXMIN = new Vector3d(0.0, 0.0, 0.0);
   static final Vector3d BOXMAX = new Vector3d(1.0, 1.0, 1.0);
   public final MapCodec<PaviseBlock> codec;
   private final Supplier<class_2591<PaviseBlockEntity>> entityType;
   protected String shieldId;

   public PaviseBlock(class_1767 color, class_2251 prop, String shieldId, Supplier<class_2591<PaviseBlockEntity>> entityType) {
      super(color, prop);
      this.shieldId = shieldId;
      this.entityType = entityType;
      this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(ROTATION, 0));
      this.codec = RecordCodecBuilder.mapCodec(
         instance -> instance.group(class_1767.field_41600.fieldOf("color").forGetter(class_2185::method_9303), method_54096())
            .apply(instance, (a, b) -> new PaviseBlock(a, b, shieldId, entityType))
      );
   }

   public class_2591<PaviseBlockEntity> getEntityType() {
      return this.entityType.get();
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{ROTATION});
   }

   protected MapCodec<? extends class_2185> method_53969() {
      return this.codec;
   }

   public class_2586 method_10123(class_2338 blockpos, class_2680 blockstate) {
      return new PaviseBlockEntity(this.entityType, blockpos, blockstate);
   }

   @NotNull
   public class_1792 method_8389() {
      RegistrySupplier<MedievalShieldItem> item = ModItems.PAVISES.wood;
      return item != null ? (class_1792)item.get() : class_1802.field_8162;
   }

   public class_2680 method_9605(class_1750 blockPlaceContext) {
      return (class_2680)this.method_9564().method_11657(ROTATION, class_7718.method_45479(blockPlaceContext.method_8044()));
   }

   @NotNull
   public class_1799 method_9574(class_4538 reader, class_2338 blockpos, class_2680 blockstate) {
      return reader.method_8321(blockpos) instanceof PaviseBlockEntity pavise ? pavise.getStack() : class_1799.field_8037;
   }

   public class_265 method_9530(class_2680 blockstate, class_1922 blockgetter, class_2338 blockpos, class_3726 context) {
      class_238 aabb = COLLISION_AABB;
      float yrot = -class_7718.method_45482((Integer)blockstate.method_11654(class_2215.field_9924));
      aabb = rotateAABB(aabb, class_7833.field_40716.rotationDegrees(yrot));
      return class_259.method_1078(aabb);
   }

   public static class_238 rotateAABB(class_238 axisAlignedBB, Quaternionf quaternion) {
      Vector3d mincoords = new Vector3d(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1321);
      Vector3d maxcoords = new Vector3d(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1324);
      mincoords.sub(CENTER);
      maxcoords.sub(CENTER);
      quaternion.transform(mincoords);
      quaternion.transform(maxcoords);
      mincoords.add(CENTER).max(BOXMIN);
      maxcoords.add(CENTER).min(BOXMAX);
      return new class_238(mincoords.x(), mincoords.y(), mincoords.z(), maxcoords.x(), maxcoords.y(), maxcoords.z());
   }

   public void method_9556(class_1937 level, class_1657 player, class_2338 blockpos, class_2680 blockstate, @Nullable class_2586 blockentity, class_1799 stack) {
      player.method_7259(class_3468.field_15427.method_14956(this));
      player.method_7322(0.005F);
      method_9511(blockstate, level, blockpos, blockentity, player, stack);
      if (blockentity instanceof PaviseBlockEntity pavise) {
         level.method_8649(
            new class_1542(level, blockpos.method_10263() + 0.5, blockpos.method_10264() + 0.5, blockpos.method_10260() + 0.5, pavise.getStack())
         );
      }
   }

   public void method_9585(class_1936 accessor, class_2338 blockpos, class_2680 blockstate) {
      if (accessor.method_8320(blockpos.method_10084()).method_26204() == ModBlocks.PAVISE_UPPER_COLLISION.get()) {
         accessor.method_22352(blockpos.method_10084(), false);
      }

      super.method_9585(accessor, blockpos, blockstate);
   }
}
