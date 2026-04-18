package noobanidus.mods.lootr.fabric.mixin.data_fixer;

import net.fabricmc.fabric.api.blockview.v2.RenderDataBlockEntity;
import net.minecraft.class_1657;
import noobanidus.mods.lootr.common.block.entity.LootrBrushableBlockEntity;
import noobanidus.mods.lootr.common.client.ClientHooks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LootrBrushableBlockEntity.class)
public class MixinLootrBrushableBlockEntity implements RenderDataBlockEntity {
   @Nullable
   public Object getRenderData() {
      class_1657 player = ClientHooks.getPlayer();
      return player == null ? null : ((LootrBrushableBlockEntity)this).hasClientOpened(player.method_5667());
   }
}
