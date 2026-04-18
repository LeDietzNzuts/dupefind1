package io.github.suel_ki.beautify.compat.every_compat;

import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Trellis;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet.Builder;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.class_2248;
import net.minecraft.class_2498;
import net.minecraft.class_2960;
import net.minecraft.class_3481;
import net.minecraft.class_7924;

public class BeautifyModule extends EveryCompatModule {
   public final SimpleEntrySet<WoodType, class_2248> tellis;
   public final SimpleEntrySet<WoodType, class_2248> blinds;
   public final SimpleEntrySet<WoodType, class_2248> picture_frames;

   public BeautifyModule(String modId) {
      super(modId, "bd");
      class_2960 tab = this.modRes("group");
      this.tellis = ((Builder)((Builder)((Builder)((Builder)((Builder)SimpleEntrySet.builder(
                           WoodType.class,
                           "trellis",
                           this.getModBlock("oak_trellis"),
                           () -> VanillaWoodTypes.OAK,
                           woodType -> new Trellis(
                              Utils.copyPropertySafe(woodType.planks).method_9629(0.3F, 0.3F).method_9626(class_2498.field_11542).method_22488()
                           )
                        )
                        .addTag(class_3481.field_33713, class_7924.field_41254))
                     .addTag(class_3481.field_22414, class_7924.field_41254))
                  .setTabKey(tab))
               .defaultRecipe()
               .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)"))
            .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)"))
         .build();
      this.addEntry(this.tellis);
      this.blinds = ((Builder)((Builder)((Builder)SimpleEntrySet.builder(
                     WoodType.class,
                     "blinds",
                     this.getModBlock("oak_blinds"),
                     () -> VanillaWoodTypes.OAK,
                     woodType -> new Blinds(Utils.copyPropertySafe(woodType.planks).method_22488().method_9629(0.4F, 0.4F).method_9626(class_2498.field_11547))
                  )
                  .requiresChildren(new String[]{"slab"}))
               .addTag(class_3481.field_33713, class_7924.field_41254))
            .setTabKey(tab))
         .defaultRecipe()
         .build();
      this.addEntry(this.blinds);
      this.picture_frames = ((Builder)((Builder)((Builder)SimpleEntrySet.builder(
                     WoodType.class,
                     "picture_frame",
                     this.getModBlock("oak_picture_frame"),
                     () -> VanillaWoodTypes.OAK,
                     woodType -> new PictureFrame(
                        Utils.copyPropertySafe(woodType.planks).method_22488().method_9629(0.1F, 0.1F).method_9626(class_2498.field_11547).method_22488()
                     )
                  )
                  .requiresChildren(new String[]{"slab"}))
               .addTexture(this.modRes("block/oak_frame_texture")))
            .setTabKey(tab))
         .defaultRecipe()
         .build();
      this.addEntry(this.picture_frames);
   }
}
