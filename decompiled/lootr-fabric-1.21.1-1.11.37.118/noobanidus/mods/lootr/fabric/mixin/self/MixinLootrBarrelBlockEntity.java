package noobanidus.mods.lootr.fabric.mixin.self;

import net.fabricmc.fabric.api.blockview.v2.RenderDataBlockEntity;
import net.minecraft.class_1657;
import noobanidus.mods.lootr.common.block.entity.LootrBarrelBlockEntity;
import noobanidus.mods.lootr.common.client.ClientHooks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LootrBarrelBlockEntity.class)
public class MixinLootrBarrelBlockEntity implements RenderDataBlockEntity {
   @Nullable
   public Object getRenderData() {
      class_1657 player = ClientHooks.getPlayer();
      return player == null ? null : ((LootrBarrelBlockEntity)this).hasClientOpened(player.method_5667());
   }
}
