package com.talhanation.smallships.client.model.sail;

import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5601;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;

public class GalleySailModel extends SailModel {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "galley_sail_model"), "main");
   private final class_630 GalleySail;

   public GalleySailModel() {
      class_630 root = createBodyLayer().method_32109();
      this.GalleySail = root.method_32086("GalleySail");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 GalleySail = partdefinition.method_32117("GalleySail", class_5606.method_32108(), class_5603.method_32090(-2.1F, 26.2F, 0.0F));
      class_5610 Sail_4 = GalleySail.method_32117("Sail_4", class_5606.method_32108(), class_5603.method_32091(-32.0F, -31.0F, 2.6F, 0.2443F, -0.2793F, 0.0F));
      class_5610 Base1 = Sail_4.method_32117(
         "Base1",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(2.4311F, -0.7772F, 2.0974F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(0.4311F, 0.2228F, 2.0974F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(-0.5689F, 2.2228F, 2.0974F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(8.4311F, -0.7772F, 2.0974F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -0.7772F, 2.0974F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -0.7772F, 2.0974F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -0.7772F, 2.0974F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -0.7772F, 2.0974F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(5.9F, -5.1F, -2.9F, -0.0175F, -0.1571F, 0.0F)
      );
      class_5610 Base2 = Base1.method_32117(
         "Base2",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(6.4311F, -4.7383F, 2.0773F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(2.4311F, -2.7383F, 2.0773F, 4.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(4.4311F, -4.7383F, 2.0773F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(8.4311F, -4.7383F, 2.0773F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(20.4311F, -4.7383F, 2.0773F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.7383F, 2.0773F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.7383F, 2.0773F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.7383F, 2.0773F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.2F, 0.0F, 0.0873F, 0.0F, 0.0F)
      );
      class_5610 Base3 = Base2.method_32117(
         "Base3",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(9.4311F, -4.6322F, 2.0715F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(6.4311F, -2.6322F, 2.0715F, 3.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(7.4311F, -4.6322F, 2.0715F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(11.4311F, -4.6322F, 2.0715F, 9.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(20.4311F, -4.6322F, 2.0715F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.6322F, 2.0715F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(44.4311F, -4.6322F, 2.0715F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.6322F, 2.0715F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F)
      );
      class_5610 Base4 = Base3.method_32117(
         "Base4",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(12.4311F, -4.6337F, 2.073F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(9.4311F, -2.6337F, 2.073F, 3.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(11.4311F, -4.6337F, 2.073F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(14.4311F, -4.6337F, 2.073F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -4.6337F, 2.073F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(32.4311F, -4.6337F, 2.073F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.6337F, 2.073F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.6337F, 2.073F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base5 = Base4.method_32117(
         "Base5",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(14.4311F, -3.6352F, 2.0744F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(15.4311F, -4.6352F, 2.0744F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(13.4311F, -2.6352F, 2.0744F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(16.4311F, -4.6352F, 2.0744F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -4.6352F, 2.0744F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.6352F, 2.0744F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(44.4311F, -4.6352F, 2.0744F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.6352F, 2.0744F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base6 = Base5.method_32117(
         "Base6",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(18.4311F, -3.5292F, 2.0686F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(16.4311F, -1.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(19.4311F, -4.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(18.4311F, -4.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(19.4311F, -5.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(17.4311F, -2.5292F, 2.0686F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -4.5292F, 2.0686F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(24.4311F, -4.5292F, 2.0686F, 8.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(32.4311F, -4.5292F, 2.0686F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.5292F, 2.0686F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.5292F, 2.0686F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F)
      );
      class_5610 Base7 = Base6.method_32117(
         "Base7",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(21.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(22.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(23.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(27.4311F, -4.4249F, 2.064F, 5.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0262F, 0.0F, 0.0F)
      );
      class_5610 Base8 = Base7.method_32117(
         "Base8",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(24.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(25.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(23.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(26.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -4.4249F, 2.064F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 5)
            .method_32098(32.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base9 = Base8.method_32117(
         "Base9",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(27.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(28.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(26.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(29.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -4.4249F, 2.064F, 11.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base10 = Base9.method_32117(
         "Base10",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(31.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -1.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(29.4311F, -1.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -2.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -4.4249F, 2.064F, 11.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base11 = Base10.method_32117(
         "Base11",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(35.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(36.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(35.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(36.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -1.4249F, 2.064F, 3.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -2.4249F, 2.064F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -3.4249F, 2.064F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(37.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base12 = Base11.method_32117(
         "Base12",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(38.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(38.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(37.4311F, -3.4249F, 2.064F, 1.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base13 = Base12.method_32117(
         "Base13",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(41.4311F, -3.4292F, 2.066F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(41.4311F, -4.4292F, 2.066F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -2.4292F, 2.066F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(43.4311F, -4.4292F, 2.066F, 5.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.4292F, 2.066F, 8.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4292F, 2.066F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base14 = Base13.method_32117(
         "Base14",
         class_5606.method_32108().method_32101(91, 9).method_32098(56.4311F, -4.4264F, 2.0629F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0175F, 0.0F, 0.0F)
      );
      class_5610 Base15 = Base14.method_32117(
         "Base15",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(42.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.425F, 2.0614F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -3.9F, 0.0F, 0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base16 = Base15.method_32117(
         "Base16",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(48.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(46.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(50.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base17 = Base16.method_32117(
         "Base17",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(51.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(51.4311F, -5.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(49.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(53.4311F, -4.425F, 2.0614F, 3.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base18 = Base17.method_32117(
         "Base18",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(53.4311F, -3.425F, 2.0614F, 3.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(54.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base19 = Base18.method_32117(
         "Base19",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(55.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(57.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(61.4311F, -8.425F, 2.0614F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(59.4311F, -7.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(58.4311F, -5.425F, 2.0614F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Sail_3 = GalleySail.method_32117("Sail_3", class_5606.method_32108(), class_5603.method_32091(-32.0F, -31.0F, 2.6F, 0.2443F, -0.2793F, 0.0F));
      class_5610 Base20 = Sail_3.method_32117("Base20", class_5606.method_32108(), class_5603.method_32091(5.9F, -5.1F, -2.9F, -0.0175F, -0.1571F, 0.0F));
      class_5610 Base21 = Base20.method_32117("Base21", class_5606.method_32108(), class_5603.method_32091(0.0F, 0.2F, 0.0F, 0.0873F, 0.0F, 0.0F));
      class_5610 Base22 = Base21.method_32117("Base22", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F));
      class_5610 Base23 = Base22.method_32117(
         "Base23",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(12.4311F, -4.6337F, 2.073F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(9.4311F, -2.6337F, 2.073F, 3.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(11.4311F, -4.6337F, 2.073F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(14.4311F, -4.6337F, 2.073F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -4.6337F, 2.073F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(32.4311F, -4.6337F, 2.073F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.6337F, 2.073F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.6337F, 2.073F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base24 = Base23.method_32117(
         "Base24",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(14.4311F, -3.6352F, 2.0744F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(15.4311F, -4.6352F, 2.0744F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(13.4311F, -2.6352F, 2.0744F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(16.4311F, -4.6352F, 2.0744F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -4.6352F, 2.0744F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.6352F, 2.0744F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(44.4311F, -4.6352F, 2.0744F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.6352F, 2.0744F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base25 = Base24.method_32117(
         "Base25",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(18.4311F, -3.5292F, 2.0686F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(16.4311F, -1.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(19.4311F, -4.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(18.4311F, -4.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(19.4311F, -5.5292F, 2.0686F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(17.4311F, -2.5292F, 2.0686F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -4.5292F, 2.0686F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(24.4311F, -4.5292F, 2.0686F, 8.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(32.4311F, -4.5292F, 2.0686F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.5292F, 2.0686F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.5292F, 2.0686F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F)
      );
      class_5610 Base26 = Base25.method_32117(
         "Base26",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(21.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(22.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(23.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(27.4311F, -4.4249F, 2.064F, 5.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0262F, 0.0F, 0.0F)
      );
      class_5610 Base27 = Base26.method_32117(
         "Base27",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(24.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(25.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(23.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(26.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -4.4249F, 2.064F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 5)
            .method_32098(32.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base28 = Base27.method_32117(
         "Base28",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(27.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(28.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(26.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(29.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -4.4249F, 2.064F, 11.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base29 = Base28.method_32117(
         "Base29",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(31.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -1.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(29.4311F, -1.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -2.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -4.4249F, 2.064F, 11.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base30 = Base29.method_32117(
         "Base30",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(35.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(36.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(35.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(36.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -1.4249F, 2.064F, 3.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -2.4249F, 2.064F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -3.4249F, 2.064F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(37.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base31 = Base30.method_32117(
         "Base31",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(38.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(38.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(37.4311F, -3.4249F, 2.064F, 1.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base32 = Base31.method_32117(
         "Base32",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(41.4311F, -3.4292F, 2.066F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(41.4311F, -4.4292F, 2.066F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -2.4292F, 2.066F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(43.4311F, -4.4292F, 2.066F, 5.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.4292F, 2.066F, 8.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4292F, 2.066F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base33 = Base32.method_32117(
         "Base33",
         class_5606.method_32108().method_32101(91, 9).method_32098(56.4311F, -4.4264F, 2.0629F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0175F, 0.0F, 0.0F)
      );
      class_5610 Base34 = Base33.method_32117(
         "Base34",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(42.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.425F, 2.0614F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -3.9F, 0.0F, 0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base35 = Base34.method_32117(
         "Base35",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(48.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(46.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(50.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base36 = Base35.method_32117(
         "Base36",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(51.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(51.4311F, -5.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(49.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(53.4311F, -4.425F, 2.0614F, 3.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base37 = Base36.method_32117(
         "Base37",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(53.4311F, -3.425F, 2.0614F, 3.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(54.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base38 = Base37.method_32117(
         "Base38",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(55.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(57.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(61.4311F, -8.425F, 2.0614F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(59.4311F, -7.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(58.4311F, -5.425F, 2.0614F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 bone = Sail_3.method_32117("bone", class_5606.method_32108(), class_5603.method_32091(14.4632F, -13.7947F, 0.1047F, 0.0F, -0.0436F, 0.0F));
      class_5610 cube_r1 = bone.method_32117(
         "cube_r1",
         class_5606.method_32108()
            .method_32101(87, 7)
            .method_32098(70.9385F, -11.676F, -3.1522F, 3.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(60.9385F, -11.676F, -3.1522F, 10.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(50.9385F, -11.676F, -3.1522F, 10.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(40.9385F, -11.676F, -3.1522F, 10.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(30.9385F, -11.676F, -3.1522F, 10.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(20.9385F, -11.676F, -3.1522F, 10.0F, 4.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(-18.7509F, 9.8947F, -2.2001F, 0.0F, -0.1309F, 0.0F)
      );
      class_5610 Sail_2 = GalleySail.method_32117("Sail_2", class_5606.method_32108(), class_5603.method_32091(-32.0F, -31.0F, 2.6F, 0.2443F, -0.2793F, 0.0F));
      class_5610 Base39 = Sail_2.method_32117("Base39", class_5606.method_32108(), class_5603.method_32091(5.9F, -5.1F, -2.9F, -0.0175F, -0.1571F, 0.0F));
      class_5610 Base40 = Base39.method_32117("Base40", class_5606.method_32108(), class_5603.method_32091(0.0F, 0.2F, 0.0F, 0.0873F, 0.0F, 0.0F));
      class_5610 Base41 = Base40.method_32117("Base41", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F));
      class_5610 Base42 = Base41.method_32117("Base42", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F));
      class_5610 Base43 = Base42.method_32117("Base43", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F));
      class_5610 Base44 = Base43.method_32117("Base44", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F));
      class_5610 Base45 = Base44.method_32117(
         "Base45",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(21.4311F, -3.4249F, 2.064F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(22.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(20.4311F, -2.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(23.4311F, -4.4249F, 2.064F, 4.0F, 3.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0262F, 0.0F, 0.0F)
      );
      class_5610 Base46 = Base45.method_32117(
         "Base46",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(24.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(25.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(23.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(26.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -4.4249F, 2.064F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 5)
            .method_32098(32.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 8)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base47 = Base46.method_32117(
         "Base47",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(27.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(28.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(26.4311F, -2.4249F, 2.064F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(29.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -4.4249F, 2.064F, 11.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base48 = Base47.method_32117(
         "Base48",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(31.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -1.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(29.4311F, -1.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(30.4311F, -2.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -4.4249F, 2.064F, 11.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base49 = Base48.method_32117(
         "Base49",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(35.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(36.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(35.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(36.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(32.4311F, -1.4249F, 2.064F, 3.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -2.4249F, 2.064F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(33.4311F, -3.4249F, 2.064F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(37.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base50 = Base49.method_32117(
         "Base50",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(38.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(38.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(37.4311F, -3.4249F, 2.064F, 1.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base51 = Base50.method_32117(
         "Base51",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(41.4311F, -3.4292F, 2.066F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(41.4311F, -4.4292F, 2.066F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -2.4292F, 2.066F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(43.4311F, -4.4292F, 2.066F, 5.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.4292F, 2.066F, 8.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4292F, 2.066F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base52 = Base51.method_32117(
         "Base52",
         class_5606.method_32108().method_32101(91, 9).method_32098(56.4311F, -4.4264F, 2.0629F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0175F, 0.0F, 0.0F)
      );
      class_5610 Base53 = Base52.method_32117(
         "Base53",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(42.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.425F, 2.0614F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -3.9F, 0.0F, 0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base54 = Base53.method_32117(
         "Base54",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(48.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(46.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(50.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base55 = Base54.method_32117(
         "Base55",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(51.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(51.4311F, -5.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(49.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(53.4311F, -4.425F, 2.0614F, 3.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base56 = Base55.method_32117(
         "Base56",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(53.4311F, -3.425F, 2.0614F, 3.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(54.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base57 = Base56.method_32117(
         "Base57",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(55.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(57.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(61.4311F, -8.425F, 2.0614F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(59.4311F, -7.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(58.4311F, -5.425F, 2.0614F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 bone2 = Sail_2.method_32117("bone2", class_5606.method_32108(), class_5603.method_32091(15.2632F, -24.8947F, -1.1953F, 0.0F, -0.0436F, 0.0F));
      class_5610 cube_r2 = bone2.method_32117(
         "cube_r2",
         class_5606.method_32108()
            .method_32101(87, 7)
            .method_32098(63.9385F, -16.676F, -3.1522F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(53.9385F, -16.676F, -3.1522F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(43.9385F, -16.676F, -3.1522F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(32.9385F, -16.676F, -3.1522F, 11.0F, 5.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(-18.7509F, 9.8947F, -2.2001F, 0.0F, -0.1309F, 0.0F)
      );
      class_5610 Sail_1 = GalleySail.method_32117("Sail_1", class_5606.method_32108(), class_5603.method_32091(-32.0F, -31.0F, 2.6F, 0.2443F, -0.2793F, 0.0F));
      class_5610 Base58 = Sail_1.method_32117("Base58", class_5606.method_32108(), class_5603.method_32091(5.9F, -5.1F, -2.9F, -0.0175F, -0.1571F, 0.0F));
      class_5610 Base59 = Base58.method_32117("Base59", class_5606.method_32108(), class_5603.method_32091(0.0F, 0.2F, 0.0F, 0.0873F, 0.0F, 0.0F));
      class_5610 Base60 = Base59.method_32117("Base60", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F));
      class_5610 Base61 = Base60.method_32117("Base61", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F));
      class_5610 Base62 = Base61.method_32117("Base62", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, -0.0087F, 0.0F, 0.0F));
      class_5610 Base63 = Base62.method_32117("Base63", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0349F, 0.0F, 0.0F));
      class_5610 Base64 = Base63.method_32117("Base64", class_5606.method_32108(), class_5603.method_32091(0.0F, -4.0F, 0.0F, 0.0262F, 0.0F, 0.0F));
      class_5610 Base65 = Base64.method_32117("Base65", class_5606.method_32108(), class_5603.method_32090(0.0F, -4.0F, 0.0F));
      class_5610 Base66 = Base65.method_32117("Base66", class_5606.method_32108(), class_5603.method_32090(0.0F, -4.0F, 0.0F));
      class_5610 Base67 = Base66.method_32117("Base67", class_5606.method_32108(), class_5603.method_32090(0.0F, -4.0F, 0.0F));
      class_5610 Base68 = Base67.method_32117(
         "Base68",
         class_5606.method_32108().method_32101(91, 9).method_32098(36.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base69 = Base68.method_32117(
         "Base69",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(38.4311F, -3.4249F, 2.064F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(38.4311F, -4.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(39.4311F, -5.4249F, 2.064F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(37.4311F, -3.4249F, 2.064F, 1.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -4.4249F, 2.064F, 4.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.4249F, 2.064F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(94, 9)
            .method_32098(56.4311F, -4.4249F, 2.064F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base70 = Base69.method_32117(
         "Base70",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(41.4311F, -3.4292F, 2.066F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(41.4311F, -4.4292F, 2.066F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(40.4311F, -2.4292F, 2.066F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(43.4311F, -4.4292F, 2.066F, 5.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.4292F, 2.066F, 8.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.4292F, 2.066F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base71 = Base70.method_32117(
         "Base71",
         class_5606.method_32108().method_32101(91, 9).method_32098(56.4311F, -4.4264F, 2.0629F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0175F, 0.0F, 0.0F)
      );
      class_5610 Base72 = Base71.method_32117(
         "Base72",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(42.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(44.4311F, -4.425F, 2.0614F, 12.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -3.9F, 0.0F, 0.0087F, 0.0F, 0.0F)
      );
      class_5610 Base73 = Base72.method_32117(
         "Base73",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(48.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(48.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(46.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(50.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base74 = Base73.method_32117(
         "Base74",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(51.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(51.4311F, -5.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(49.4311F, -3.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(53.4311F, -4.425F, 2.0614F, 3.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base75 = Base74.method_32117(
         "Base75",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(53.4311F, -3.425F, 2.0614F, 3.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(54.4311F, -4.425F, 2.0614F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(56.4311F, -4.425F, 2.0614F, 7.0F, 4.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 Base76 = Base75.method_32117(
         "Base76",
         class_5606.method_32108()
            .method_32101(91, 9)
            .method_32098(55.4311F, -2.425F, 2.0614F, 2.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(57.4311F, -4.425F, 2.0614F, 6.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(61.4311F, -8.425F, 2.0614F, 2.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(59.4311F, -7.425F, 2.0614F, 2.0F, 3.0F, 1.0F, new class_5605(0.0F))
            .method_32101(91, 9)
            .method_32098(58.4311F, -5.425F, 2.0614F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -4.0F, 0.0F)
      );
      class_5610 bone3 = Sail_1.method_32117("bone3", class_5606.method_32108(), class_5603.method_32091(22.2632F, -32.8947F, -3.1953F, 0.0F, -0.0436F, 0.0F));
      class_5610 cube_r3 = bone3.method_32117(
         "cube_r3",
         class_5606.method_32108()
            .method_32101(87, 7)
            .method_32098(59.9385F, -23.676F, -3.1522F, 8.0F, 6.0F, 6.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(49.9385F, -23.676F, -3.1522F, 10.0F, 6.0F, 6.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(39.9385F, -23.676F, -3.1522F, 10.0F, 6.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(-18.7509F, 9.8947F, -2.2001F, 0.0F, -0.1309F, 0.0F)
      );
      class_5610 Sail_0 = GalleySail.method_32117("Sail_0", class_5606.method_32108(), class_5603.method_32090(-25.9F, -32.2F, 3.0F));
      class_5610 bone4 = Sail_0.method_32117("bone4", class_5606.method_32108(), class_5603.method_32091(4.3333F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7418F));
      class_5610 cube_r4 = bone4.method_32117(
         "cube_r4",
         class_5606.method_32108()
            .method_32101(87, 7)
            .method_32098(-20.0F, -2.0F, -1.0F, 8.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(-4.0F, -2.0F, -1.0F, 8.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(-12.0F, -2.0F, -1.0F, 8.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(-28.0F, -2.0F, -1.0F, 8.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(44.0F, -2.0F, -1.0F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(34.0F, -2.0F, -1.0F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(24.0F, -2.0F, -1.0F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(14.0F, -2.0F, -1.0F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(4.0F, -2.0F, -1.0F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(-48.0F, -2.0F, -1.0F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(87, 7)
            .method_32098(-38.0F, -2.0F, -1.0F, 10.0F, 5.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(45.6667F, -7.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      class_5610 ropes = GalleySail.method_32117("ropes", class_5606.method_32108(), class_5603.method_32090(56.6F, -29.2F, 0.0F));
      class_5610 rope_13 = ropes.method_32117("rope_13", class_5606.method_32108(), class_5603.method_32091(8.0F, 8.0F, 0.0F, 0.0F, -1.5708F, -0.7854F));
      class_5610 cube_r81 = rope_13.method_32117(
         "cube_r81",
         class_5606.method_32108()
            .method_32101(86, 5)
            .method_32098(34.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(64.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(74.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(84.5F, -0.5F, -17.5F, 8.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(89.5F, -0.5F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(49.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(22.5F, 0.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(22.5F, -1.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(11.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 11.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r82 = rope_13.method_32117(
         "cube_r82",
         class_5606.method_32108().method_32101(8, 20).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -22.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r83 = rope_13.method_32117(
         "cube_r83",
         class_5606.method_32108().method_32101(13, 28).method_32098(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -10.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 rope_2 = ropes.method_32117("rope_2", class_5606.method_32108(), class_5603.method_32091(-118.0F, 21.0F, 0.0F, 0.0F, -1.5708F, 0.7418F));
      class_5610 cube_r19 = rope_2.method_32117(
         "cube_r19",
         class_5606.method_32108()
            .method_32101(86, 5)
            .method_32098(34.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(64.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(74.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(84.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(94.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(104.5F, -0.5F, -17.5F, 5.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(49.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(22.5F, 0.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(22.5F, -1.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 5)
            .method_32098(11.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 11.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r20 = rope_2.method_32117(
         "cube_r20",
         class_5606.method_32108().method_32101(8, 20).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -22.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r21 = rope_2.method_32117(
         "cube_r21",
         class_5606.method_32108().method_32101(13, 28).method_32098(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -10.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      return class_5607.method_32110(meshdefinition, 128, 64);
   }

   public void setupAnim(Ship briggEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      switch (briggEntity.<Byte>getData(Ship.SAIL_STATE)) {
         case 0:
            this.GalleySail.method_32086("Sail_0").field_3665 = true;
            this.GalleySail.method_32086("Sail_1").field_3665 = false;
            this.GalleySail.method_32086("Sail_2").field_3665 = false;
            this.GalleySail.method_32086("Sail_3").field_3665 = false;
            this.GalleySail.method_32086("Sail_4").field_3665 = false;
            break;
         case 1:
            this.GalleySail.method_32086("Sail_0").field_3665 = false;
            this.GalleySail.method_32086("Sail_1").field_3665 = true;
            this.GalleySail.method_32086("Sail_2").field_3665 = false;
            this.GalleySail.method_32086("Sail_3").field_3665 = false;
            this.GalleySail.method_32086("Sail_4").field_3665 = false;
            break;
         case 2:
            this.GalleySail.method_32086("Sail_0").field_3665 = false;
            this.GalleySail.method_32086("Sail_1").field_3665 = false;
            this.GalleySail.method_32086("Sail_2").field_3665 = true;
            this.GalleySail.method_32086("Sail_3").field_3665 = false;
            this.GalleySail.method_32086("Sail_4").field_3665 = false;
            break;
         case 3:
            this.GalleySail.method_32086("Sail_0").field_3665 = false;
            this.GalleySail.method_32086("Sail_1").field_3665 = false;
            this.GalleySail.method_32086("Sail_2").field_3665 = false;
            this.GalleySail.method_32086("Sail_3").field_3665 = true;
            this.GalleySail.method_32086("Sail_4").field_3665 = false;
            break;
         case 4:
            this.GalleySail.method_32086("Sail_0").field_3665 = false;
            this.GalleySail.method_32086("Sail_1").field_3665 = false;
            this.GalleySail.method_32086("Sail_2").field_3665 = false;
            this.GalleySail.method_32086("Sail_3").field_3665 = false;
            this.GalleySail.method_32086("Sail_4").field_3665 = true;
      }
   }

   public void method_2828(class_4587 poseStack, class_4588 buffer, int packedLight, int packedOverlay, int color) {
      this.GalleySail.method_22699(poseStack, buffer, packedLight, packedOverlay, color);
   }
}
