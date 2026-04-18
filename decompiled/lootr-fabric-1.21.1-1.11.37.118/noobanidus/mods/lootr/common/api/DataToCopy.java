package noobanidus.mods.lootr.common.api;

import net.minecraft.class_1273;
import net.minecraft.class_2487;

public record DataToCopy(class_2487 data, class_1273 lockCode) {
   public static final DataToCopy EMPTY = new DataToCopy(new class_2487(), class_1273.field_5817);
}
