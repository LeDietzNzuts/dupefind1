package vectorwing.farmersdelight.refabricated;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1799;
import net.minecraft.class_47;
import net.minecraft.class_5341;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class LootModifier {
   public LootModifier(@Nullable class_5341[] conditionsIn) {
   }

   @NotNull
   protected abstract ObjectArrayList<class_1799> doApply(ObjectArrayList<class_1799> var1, class_47 var2);
}
