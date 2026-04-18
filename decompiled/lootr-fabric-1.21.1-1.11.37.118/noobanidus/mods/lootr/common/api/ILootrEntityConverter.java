package noobanidus.mods.lootr.common.api;

import java.util.function.Function;
import net.minecraft.class_1299;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;

public interface ILootrEntityConverter<T> extends Function<T, ILootrEntity> {
   ILootrEntity apply(T var1);

   class_1299<?> getEntityType();
}
