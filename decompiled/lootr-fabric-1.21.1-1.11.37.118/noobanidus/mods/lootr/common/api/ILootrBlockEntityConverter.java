package noobanidus.mods.lootr.common.api;

import java.util.function.Function;
import net.minecraft.class_2591;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;

public interface ILootrBlockEntityConverter<T> extends Function<T, ILootrBlockEntity> {
   ILootrBlockEntity apply(T var1);

   class_2591<?> getBlockEntityType();
}
