package net.p3pp3rf1y.sophisticatedcore.fluid;

import io.github.fabricators_of_create.porting_lib.transfer.MutableContainerItemContext;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemHandlerHelper;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.impl.transfer.DebugMessages;
import net.minecraft.class_1268;
import net.minecraft.class_128;
import net.minecraft.class_148;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2398;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3486;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_5712;
import net.p3pp3rf1y.sophisticatedcore.inventory.IInventoryHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class FluidUtil {
   public static final long BUCKET_VOLUME_IN_MILLIBUCKETS = 81L;

   public static int toBuckets(long amount) {
      return (int)(amount / 81L);
   }

   public static boolean isFluidStorage(class_1799 stack) {
      return ContainerItemContext.withConstant(stack).find(FluidStorage.ITEM) != null;
   }

   public static boolean placeFluid(
      @Nullable class_1657 player, @NotNull class_1937 level, @NotNull class_2338 pos, Storage<FluidVariant> source, FluidVariant resource, long maxAmount
   ) {
      if (!level.method_8477(pos)) {
         return false;
      } else {
         class_3611 fluid = resource.getFluid();
         if (fluid == class_3612.field_15906) {
            return false;
         } else {
            class_2680 state = level.method_8320(pos);
            boolean waterlog = state.method_28498(class_2741.field_12508);
            if (!waterlog && !state.method_45474()) {
               return false;
            } else if (waterlog && fluid != class_3612.field_15910) {
               return false;
            } else {
               class_3610 fluidState = state.method_26227();
               if (!fluidState.method_15769() && fluidState.method_15772() != fluid) {
                  return false;
               } else {
                  Transaction extraction = Transaction.openOuter();

                  boolean var14;
                  label102: {
                     try {
                        long result = source.extract(resource, maxAmount, extraction);
                        if (result == 0L) {
                           var14 = false;
                           break label102;
                        }

                        extraction.commit();
                     } catch (Throwable var16) {
                        if (extraction != null) {
                           try {
                              extraction.close();
                           } catch (Throwable var15) {
                              var16.addSuppressed(var15);
                           }
                        }

                        throw var16;
                     }

                     if (extraction != null) {
                        extraction.close();
                     }

                     if (level.method_8597().comp_644() && fluid.method_15785().method_15767(class_3486.field_15517)) {
                        level.method_8396(
                           player,
                           pos,
                           class_3417.field_15102,
                           class_3419.field_15245,
                           0.5F,
                           2.6F + (level.field_9229.method_43057() - level.field_9229.method_43057()) * 0.8F
                        );

                        for (int i = 0; i < 8; i++) {
                           level.method_8406(
                              class_2398.field_11237,
                              pos.method_10263() + Math.random(),
                              pos.method_10264() + Math.random(),
                              pos.method_10260() + Math.random(),
                              0.0,
                              0.0,
                              0.0
                           );
                        }

                        return true;
                     }

                     if (waterlog) {
                        level.method_8652(pos, (class_2680)state.method_11657(class_2741.field_12508, true), 3);
                        level.method_39281(pos, class_3612.field_15910, 1);
                        return true;
                     }

                     if (!level.field_9236 && state.method_26188(fluid) && !state.method_51176()) {
                        level.method_22352(pos, true);
                     }

                     if (!level.method_8652(pos, fluid.method_15785().method_15759(), 3) && !fluidState.method_15771()) {
                        return false;
                     }

                     playFluidSound(player, level, pos, resource, false);
                     return true;
                  }

                  if (extraction != null) {
                     extraction.close();
                  }

                  return var14;
               }
            }
         }
      }
   }

   public static void playFluidSound(@Nullable class_1657 player, class_1936 level, class_2338 pos, FluidVariant resource, boolean fill) {
      class_3414 sound = fill ? FluidVariantAttributes.getFillSound(resource) : FluidVariantAttributes.getEmptySound(resource);
      level.method_8396(player, pos, sound, class_3419.field_15245, 1.0F, 1.0F);
      level.method_33596(player, class_5712.field_28166, pos);
   }

   public static boolean interactWithFluidStorage(Storage<FluidVariant> storage, class_1657 player, class_1268 hand, boolean fill) {
      Storage<FluidVariant> handStorage = (Storage<FluidVariant>)ContainerItemContext.ofPlayerHand(player, hand).find(FluidStorage.ITEM);
      if (handStorage == null) {
         return false;
      } else {
         class_1792 handItem = player.method_5998(hand).method_7909();

         try {
            return fill
               ? moveWithSound(storage, handStorage, Long.MAX_VALUE, player, true, handItem, null)
               : moveWithSound(handStorage, storage, Long.MAX_VALUE, player, false, handItem, null);
         } catch (Exception var8) {
            class_128 report = class_128.method_560(var8, "Interacting with fluid storage");
            report.method_562("Interaction details")
               .method_577("Player", () -> DebugMessages.forPlayer(player))
               .method_578("Hand", hand)
               .method_577("Hand item", handItem::toString)
               .method_577("Fluid storage", () -> Objects.toString(storage, null));
            throw new class_148(report);
         }
      }
   }

   private static boolean moveWithSound(
      Storage<FluidVariant> from,
      Storage<FluidVariant> to,
      long maxAmount,
      @Nullable class_1657 player,
      boolean fill,
      class_1792 handItem,
      @Nullable Transaction ctx
   ) {
      Iterator var8 = from.iterator();

      boolean var17;
      Transaction transferTransaction;
      while (true) {
         if (!var8.hasNext()) {
            return false;
         }

         StorageView<FluidVariant> view = (StorageView<FluidVariant>)var8.next();
         if (!view.isResourceBlank()) {
            FluidVariant resource = (FluidVariant)view.getResource();
            transferTransaction = Transaction.openNested(ctx);

            long maxExtracted;
            try {
               maxExtracted = view.extract(resource, maxAmount, transferTransaction);
            } catch (Throwable var20) {
               if (transferTransaction != null) {
                  try {
                     transferTransaction.close();
                  } catch (Throwable var19) {
                     var20.addSuppressed(var19);
                  }
               }

               throw var20;
            }

            if (transferTransaction != null) {
               transferTransaction.close();
            }

            transferTransaction = Transaction.openNested(ctx);

            try {
               long accepted = to.insert(resource, maxExtracted, transferTransaction);
               if (accepted > 0L && view.extract(resource, accepted, transferTransaction) == accepted) {
                  transferTransaction.commit();
                  class_3414 sound = fill ? FluidVariantAttributes.getFillSound(resource) : FluidVariantAttributes.getEmptySound(resource);
                  if (resource.isOf(class_3612.field_15910)) {
                     if (fill && handItem == class_1802.field_8469) {
                        sound = class_3417.field_14779;
                     }

                     if (!fill && handItem == class_1802.field_8574) {
                        sound = class_3417.field_14826;
                     }
                  }

                  if (player != null) {
                     player.method_17356(sound, class_3419.field_15245, 1.0F, 1.0F);
                  }

                  var17 = true;
                  break;
               }
            } catch (Throwable var21) {
               if (transferTransaction != null) {
                  try {
                     transferTransaction.close();
                  } catch (Throwable var18) {
                     var21.addSuppressed(var18);
                  }
               }

               throw var21;
            }

            if (transferTransaction != null) {
               transferTransaction.close();
            }
         }
      }

      if (transferTransaction != null) {
         transferTransaction.close();
      }

      return var17;
   }

   public static FluidActionResult tryFillContainer(
      class_1799 container, Storage<FluidVariant> fluidSource, long maxAmount, @Nullable class_1657 player, boolean doFill
   ) {
      class_1799 containerCopy = container.method_46651(1);
      ContainerItemContext context = new MutableContainerItemContext(containerCopy);
      Storage<FluidVariant> containerFluidHandler = (Storage<FluidVariant>)context.find(FluidStorage.ITEM);
      if (containerFluidHandler == null) {
         return FluidActionResult.FAILURE;
      } else {
         Transaction ctx = Transaction.openOuter();

         FluidActionResult var11;
         label48: {
            try {
               boolean success = moveWithSound(fluidSource, containerFluidHandler, maxAmount, player, true, container.method_7909(), ctx);
               if (success) {
                  if (doFill) {
                     ctx.commit();
                  }

                  var11 = new FluidActionResult(context.getItemVariant().toStack((int)context.getAmount()));
                  break label48;
               }
            } catch (Throwable var13) {
               if (ctx != null) {
                  try {
                     ctx.close();
                  } catch (Throwable var12) {
                     var13.addSuppressed(var12);
                  }
               }

               throw var13;
            }

            if (ctx != null) {
               ctx.close();
            }

            return FluidActionResult.FAILURE;
         }

         if (ctx != null) {
            ctx.close();
         }

         return var11;
      }
   }

   public static FluidActionResult tryEmptyContainer(
      class_1799 container, Storage<FluidVariant> fluidDestination, long maxAmount, @Nullable class_1657 player, boolean doDrain
   ) {
      class_1799 containerCopy = container.method_46651(1);
      ContainerItemContext context = new MutableContainerItemContext(containerCopy);
      Storage<FluidVariant> containerFluidHandler = (Storage<FluidVariant>)context.find(FluidStorage.ITEM);
      if (containerFluidHandler == null) {
         return FluidActionResult.FAILURE;
      } else {
         Transaction ctx = Transaction.openOuter();

         FluidActionResult var11;
         label48: {
            try {
               boolean success = moveWithSound(containerFluidHandler, fluidDestination, maxAmount, player, false, container.method_7909(), ctx);
               if (success) {
                  if (doDrain) {
                     ctx.commit();
                  }

                  var11 = new FluidActionResult(context.getItemVariant().toStack((int)context.getAmount()));
                  break label48;
               }
            } catch (Throwable var13) {
               if (ctx != null) {
                  try {
                     ctx.close();
                  } catch (Throwable var12) {
                     var13.addSuppressed(var12);
                  }
               }

               throw var13;
            }

            if (ctx != null) {
               ctx.close();
            }

            return FluidActionResult.FAILURE;
         }

         if (ctx != null) {
            ctx.close();
         }

         return var11;
      }
   }

   public static FluidActionResult tryFillContainerAndStow(
      class_1799 container, Storage<FluidVariant> fluidSource, IInventoryHandlerHelper inventory, long maxAmount, @Nullable class_1657 player, boolean doFill
   ) {
      if (container.method_7960()) {
         return FluidActionResult.FAILURE;
      } else {
         if (player != null && player.method_31549().field_7477) {
            FluidActionResult filledReal = tryFillContainer(container, fluidSource, maxAmount, player, doFill);
            if (filledReal.isSuccess()) {
               return new FluidActionResult(container);
            }
         } else if (container.method_7947() == 1) {
            FluidActionResult filledReal = tryFillContainer(container, fluidSource, maxAmount, player, doFill);
            if (filledReal.isSuccess()) {
               return filledReal;
            }
         } else {
            FluidActionResult filledSimulated = tryFillContainer(container, fluidSource, maxAmount, player, false);
            if (filledSimulated.isSuccess()) {
               class_1799 remainder = inventory.insertItem(filledSimulated.getResult(), true);
               if (remainder.method_7960() || player != null) {
                  FluidActionResult filledReal = tryFillContainer(container, fluidSource, maxAmount, player, doFill);
                  remainder = inventory.insertItem(filledReal.getResult(), !doFill);
                  if (!remainder.method_7960() && player != null && doFill) {
                     ItemHandlerHelper.giveItemToPlayer(player, remainder);
                  }

                  class_1799 containerCopy = container.method_7972();
                  containerCopy.method_7934(1);
                  return new FluidActionResult(containerCopy);
               }
            }
         }

         return FluidActionResult.FAILURE;
      }
   }

   public static FluidActionResult tryEmptyContainerAndStow(
      class_1799 container,
      Storage<FluidVariant> fluidDestination,
      IInventoryHandlerHelper inventory,
      long maxAmount,
      @Nullable class_1657 player,
      boolean doDrain
   ) {
      if (container.method_7960()) {
         return FluidActionResult.FAILURE;
      } else {
         if (player != null && player.method_31549().field_7477) {
            FluidActionResult emptiedReal = tryEmptyContainer(container, fluidDestination, maxAmount, player, doDrain);
            if (emptiedReal.isSuccess()) {
               return new FluidActionResult(container);
            }
         } else if (container.method_7947() == 1) {
            FluidActionResult emptiedReal = tryEmptyContainer(container, fluidDestination, maxAmount, player, doDrain);
            if (emptiedReal.isSuccess()) {
               return emptiedReal;
            }
         } else {
            FluidActionResult emptiedSimulated = tryEmptyContainer(container, fluidDestination, maxAmount, player, false);
            if (emptiedSimulated.isSuccess()) {
               class_1799 remainder = inventory.insertItem(emptiedSimulated.getResult(), true);
               if (remainder.method_7960() || player != null) {
                  FluidActionResult emptiedReal = tryEmptyContainer(container, fluidDestination, maxAmount, player, doDrain);
                  remainder = inventory.insertItem(emptiedReal.getResult(), !doDrain);
                  if (!remainder.method_7960() && player != null && doDrain) {
                     ItemHandlerHelper.giveItemToPlayer(player, remainder);
                  }

                  class_1799 containerCopy = container.method_7972();
                  containerCopy.method_7934(1);
                  return new FluidActionResult(containerCopy);
               }
            }
         }

         return FluidActionResult.FAILURE;
      }
   }

   public static Optional<ResourceAmount<FluidVariant>> getFluidContained(class_1799 container) {
      if (!container.method_7960()) {
         container = container.method_46651(1);
         Optional<ResourceAmount<FluidVariant>> fluidContained = Optional.ofNullable(
               (Storage)ContainerItemContext.withConstant(container).find(FluidStorage.ITEM)
            )
            .map(handler -> StorageUtil.findExtractableContent((Storage)handler, null));
         return fluidContained.filter(f -> !((FluidVariant)f.resource()).isBlank());
      } else {
         return Optional.empty();
      }
   }
}
