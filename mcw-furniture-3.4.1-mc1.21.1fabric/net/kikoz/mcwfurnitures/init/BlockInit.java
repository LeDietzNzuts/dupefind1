package net.kikoz.mcwfurnitures.init;

import net.kikoz.mcwfurnitures.objects.Chair;
import net.kikoz.mcwfurnitures.objects.Chaise;
import net.kikoz.mcwfurnitures.objects.Couch;
import net.kikoz.mcwfurnitures.objects.Desk;
import net.kikoz.mcwfurnitures.objects.Table;
import net.kikoz.mcwfurnitures.objects.TableHitbox;
import net.kikoz.mcwfurnitures.objects.TallFurniture;
import net.kikoz.mcwfurnitures.objects.TallFurnitureHinge;
import net.kikoz.mcwfurnitures.objects.WideFurniture;
import net.kikoz.mcwfurnitures.objects.cabinets.Cabinet;
import net.kikoz.mcwfurnitures.objects.cabinets.CabinetHinge;
import net.kikoz.mcwfurnitures.objects.chairs.ModernChair;
import net.kikoz.mcwfurnitures.objects.chairs.StripedChair;
import net.kikoz.mcwfurnitures.objects.counters.Counter;
import net.kikoz.mcwfurnitures.objects.counters.CupboardCounter;
import net.kikoz.mcwfurnitures.objects.counters.SinkCounter;
import net.kikoz.mcwfurnitures.objects.counters.StorageCounter;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2498;
import net.minecraft.class_2960;
import net.minecraft.class_3620;
import net.minecraft.class_7923;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_4970.class_2251;

public class BlockInit {
   public static final class_2248 OAK_WARDROBE = registerBlock(
      "oak_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_MODERN_WARDROBE = registerBlock(
      "oak_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_DOUBLE_WARDROBE = registerBlock(
      "oak_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_BOOKSHELF = registerBlock(
      "oak_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_BOOKSHELF_CUPBOARD = registerBlock(
      "oak_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_DRAWER = registerBlock(
      "oak_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_DOUBLE_DRAWER = registerBlock(
      "oak_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_BOOKSHELF_DRAWER = registerBlock(
      "oak_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "oak_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_LARGE_DRAWER = registerBlock(
      "oak_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_LOWER_TRIPLE_DRAWER = registerBlock(
      "oak_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_TRIPLE_DRAWER = registerBlock(
      "oak_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_DESK = registerBlock(
      "oak_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_COVERED_DESK = registerBlock(
      "oak_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_MODERN_DESK = registerBlock(
      "oak_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_TABLE = registerBlock(
      "oak_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_END_TABLE = registerBlock(
      "oak_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_COFFEE_TABLE = registerBlock(
      "oak_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_GLASS_TABLE = registerBlock(
      "oak_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_CHAIR = registerBlock(
      "oak_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_MODERN_CHAIR = registerBlock(
      "oak_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_STRIPED_CHAIR = registerBlock(
      "oak_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_STOOL_CHAIR = registerBlock(
      "oak_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_COUNTER = registerBlock(
      "oak_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_DRAWER_COUNTER = registerBlock(
      "oak_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_DOUBLE_DRAWER_COUNTER = registerBlock(
      "oak_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_CUPBOARD_COUNTER = registerBlock(
      "oak_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_WARDROBE = registerBlock(
      "birch_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_MODERN_WARDROBE = registerBlock(
      "birch_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_DOUBLE_WARDROBE = registerBlock(
      "birch_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_BOOKSHELF = registerBlock(
      "birch_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_BOOKSHELF_CUPBOARD = registerBlock(
      "birch_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_DRAWER = registerBlock(
      "birch_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_DOUBLE_DRAWER = registerBlock(
      "birch_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_BOOKSHELF_DRAWER = registerBlock(
      "birch_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "birch_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_LARGE_DRAWER = registerBlock(
      "birch_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_LOWER_TRIPLE_DRAWER = registerBlock(
      "birch_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_TRIPLE_DRAWER = registerBlock(
      "birch_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_DESK = registerBlock(
      "birch_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_COVERED_DESK = registerBlock(
      "birch_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_MODERN_DESK = registerBlock(
      "birch_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_TABLE = registerBlock(
      "birch_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_END_TABLE = registerBlock(
      "birch_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_COFFEE_TABLE = registerBlock(
      "birch_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_GLASS_TABLE = registerBlock(
      "birch_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_CHAIR = registerBlock(
      "birch_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_MODERN_CHAIR = registerBlock(
      "birch_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_STRIPED_CHAIR = registerBlock(
      "birch_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_STOOL_CHAIR = registerBlock(
      "birch_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_COUNTER = registerBlock(
      "birch_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_DRAWER_COUNTER = registerBlock(
      "birch_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_DOUBLE_DRAWER_COUNTER = registerBlock(
      "birch_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_CUPBOARD_COUNTER = registerBlock(
      "birch_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_WARDROBE = registerBlock(
      "spruce_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_MODERN_WARDROBE = registerBlock(
      "spruce_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_DOUBLE_WARDROBE = registerBlock(
      "spruce_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_BOOKSHELF = registerBlock(
      "spruce_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_BOOKSHELF_CUPBOARD = registerBlock(
      "spruce_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_DRAWER = registerBlock(
      "spruce_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_DOUBLE_DRAWER = registerBlock(
      "spruce_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_BOOKSHELF_DRAWER = registerBlock(
      "spruce_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "spruce_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_LARGE_DRAWER = registerBlock(
      "spruce_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_LOWER_TRIPLE_DRAWER = registerBlock(
      "spruce_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_TRIPLE_DRAWER = registerBlock(
      "spruce_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_DESK = registerBlock(
      "spruce_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_COVERED_DESK = registerBlock(
      "spruce_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_MODERN_DESK = registerBlock(
      "spruce_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_TABLE = registerBlock(
      "spruce_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_END_TABLE = registerBlock(
      "spruce_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_COFFEE_TABLE = registerBlock(
      "spruce_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_GLASS_TABLE = registerBlock(
      "spruce_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_CHAIR = registerBlock(
      "spruce_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_MODERN_CHAIR = registerBlock(
      "spruce_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_STRIPED_CHAIR = registerBlock(
      "spruce_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_STOOL_CHAIR = registerBlock(
      "spruce_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_COUNTER = registerBlock(
      "spruce_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_DRAWER_COUNTER = registerBlock(
      "spruce_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_DOUBLE_DRAWER_COUNTER = registerBlock(
      "spruce_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_CUPBOARD_COUNTER = registerBlock(
      "spruce_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_WARDROBE = registerBlock(
      "jungle_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_MODERN_WARDROBE = registerBlock(
      "jungle_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_DOUBLE_WARDROBE = registerBlock(
      "jungle_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_BOOKSHELF = registerBlock(
      "jungle_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_BOOKSHELF_CUPBOARD = registerBlock(
      "jungle_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_DRAWER = registerBlock(
      "jungle_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_DOUBLE_DRAWER = registerBlock(
      "jungle_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_BOOKSHELF_DRAWER = registerBlock(
      "jungle_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "jungle_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_LARGE_DRAWER = registerBlock(
      "jungle_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_LOWER_TRIPLE_DRAWER = registerBlock(
      "jungle_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_TRIPLE_DRAWER = registerBlock(
      "jungle_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_DESK = registerBlock(
      "jungle_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_COVERED_DESK = registerBlock(
      "jungle_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_MODERN_DESK = registerBlock(
      "jungle_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_TABLE = registerBlock(
      "jungle_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_END_TABLE = registerBlock(
      "jungle_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_COFFEE_TABLE = registerBlock(
      "jungle_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_GLASS_TABLE = registerBlock(
      "jungle_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_CHAIR = registerBlock(
      "jungle_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_MODERN_CHAIR = registerBlock(
      "jungle_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_STRIPED_CHAIR = registerBlock(
      "jungle_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_STOOL_CHAIR = registerBlock(
      "jungle_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_COUNTER = registerBlock(
      "jungle_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_DRAWER_COUNTER = registerBlock(
      "jungle_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_DOUBLE_DRAWER_COUNTER = registerBlock(
      "jungle_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_CUPBOARD_COUNTER = registerBlock(
      "jungle_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_WARDROBE = registerBlock(
      "acacia_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_MODERN_WARDROBE = registerBlock(
      "acacia_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_DOUBLE_WARDROBE = registerBlock(
      "acacia_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_BOOKSHELF = registerBlock(
      "acacia_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_BOOKSHELF_CUPBOARD = registerBlock(
      "acacia_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_DRAWER = registerBlock(
      "acacia_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_DOUBLE_DRAWER = registerBlock(
      "acacia_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_BOOKSHELF_DRAWER = registerBlock(
      "acacia_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "acacia_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_LARGE_DRAWER = registerBlock(
      "acacia_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_LOWER_TRIPLE_DRAWER = registerBlock(
      "acacia_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_TRIPLE_DRAWER = registerBlock(
      "acacia_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_DESK = registerBlock(
      "acacia_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_COVERED_DESK = registerBlock(
      "acacia_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_MODERN_DESK = registerBlock(
      "acacia_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_TABLE = registerBlock(
      "acacia_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_END_TABLE = registerBlock(
      "acacia_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_COFFEE_TABLE = registerBlock(
      "acacia_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_GLASS_TABLE = registerBlock(
      "acacia_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_CHAIR = registerBlock(
      "acacia_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_MODERN_CHAIR = registerBlock(
      "acacia_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_STRIPED_CHAIR = registerBlock(
      "acacia_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_STOOL_CHAIR = registerBlock(
      "acacia_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_COUNTER = registerBlock(
      "acacia_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_DRAWER_COUNTER = registerBlock(
      "acacia_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_DOUBLE_DRAWER_COUNTER = registerBlock(
      "acacia_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_CUPBOARD_COUNTER = registerBlock(
      "acacia_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_WARDROBE = registerBlock(
      "dark_oak_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_MODERN_WARDROBE = registerBlock(
      "dark_oak_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_DOUBLE_WARDROBE = registerBlock(
      "dark_oak_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_BOOKSHELF = registerBlock(
      "dark_oak_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_BOOKSHELF_CUPBOARD = registerBlock(
      "dark_oak_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_DRAWER = registerBlock(
      "dark_oak_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_DOUBLE_DRAWER = registerBlock(
      "dark_oak_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_BOOKSHELF_DRAWER = registerBlock(
      "dark_oak_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "dark_oak_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_LARGE_DRAWER = registerBlock(
      "dark_oak_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_LOWER_TRIPLE_DRAWER = registerBlock(
      "dark_oak_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_TRIPLE_DRAWER = registerBlock(
      "dark_oak_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_DESK = registerBlock(
      "dark_oak_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_COVERED_DESK = registerBlock(
      "dark_oak_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_MODERN_DESK = registerBlock(
      "dark_oak_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_TABLE = registerBlock(
      "dark_oak_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_END_TABLE = registerBlock(
      "dark_oak_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_COFFEE_TABLE = registerBlock(
      "dark_oak_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_GLASS_TABLE = registerBlock(
      "dark_oak_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_CHAIR = registerBlock(
      "dark_oak_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_MODERN_CHAIR = registerBlock(
      "dark_oak_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_STRIPED_CHAIR = registerBlock(
      "dark_oak_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_STOOL_CHAIR = registerBlock(
      "dark_oak_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_COUNTER = registerBlock(
      "dark_oak_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_DRAWER_COUNTER = registerBlock(
      "dark_oak_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_DOUBLE_DRAWER_COUNTER = registerBlock(
      "dark_oak_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_CUPBOARD_COUNTER = registerBlock(
      "dark_oak_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_WARDROBE = registerBlock(
      "crimson_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_MODERN_WARDROBE = registerBlock(
      "crimson_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_DOUBLE_WARDROBE = registerBlock(
      "crimson_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_BOOKSHELF = registerBlock(
      "crimson_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_BOOKSHELF_CUPBOARD = registerBlock(
      "crimson_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_DRAWER = registerBlock(
      "crimson_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_DOUBLE_DRAWER = registerBlock(
      "crimson_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_BOOKSHELF_DRAWER = registerBlock(
      "crimson_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "crimson_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_LARGE_DRAWER = registerBlock(
      "crimson_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_LOWER_TRIPLE_DRAWER = registerBlock(
      "crimson_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_TRIPLE_DRAWER = registerBlock(
      "crimson_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_DESK = registerBlock(
      "crimson_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_COVERED_DESK = registerBlock(
      "crimson_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_MODERN_DESK = registerBlock(
      "crimson_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_TABLE = registerBlock(
      "crimson_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_END_TABLE = registerBlock(
      "crimson_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_COFFEE_TABLE = registerBlock(
      "crimson_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_GLASS_TABLE = registerBlock(
      "crimson_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_CHAIR = registerBlock(
      "crimson_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_MODERN_CHAIR = registerBlock(
      "crimson_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_STRIPED_CHAIR = registerBlock(
      "crimson_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_STOOL_CHAIR = registerBlock(
      "crimson_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_COUNTER = registerBlock(
      "crimson_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_DRAWER_COUNTER = registerBlock(
      "crimson_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_DOUBLE_DRAWER_COUNTER = registerBlock(
      "crimson_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_CUPBOARD_COUNTER = registerBlock(
      "crimson_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_WARDROBE = registerBlock(
      "warped_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_MODERN_WARDROBE = registerBlock(
      "warped_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_DOUBLE_WARDROBE = registerBlock(
      "warped_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_BOOKSHELF = registerBlock(
      "warped_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_BOOKSHELF_CUPBOARD = registerBlock(
      "warped_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_DRAWER = registerBlock(
      "warped_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_DOUBLE_DRAWER = registerBlock(
      "warped_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_BOOKSHELF_DRAWER = registerBlock(
      "warped_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "warped_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_LARGE_DRAWER = registerBlock(
      "warped_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_LOWER_TRIPLE_DRAWER = registerBlock(
      "warped_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_TRIPLE_DRAWER = registerBlock(
      "warped_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_DESK = registerBlock(
      "warped_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_COVERED_DESK = registerBlock(
      "warped_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_MODERN_DESK = registerBlock(
      "warped_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_TABLE = registerBlock(
      "warped_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_END_TABLE = registerBlock(
      "warped_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_COFFEE_TABLE = registerBlock(
      "warped_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_GLASS_TABLE = registerBlock(
      "warped_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_CHAIR = registerBlock(
      "warped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_MODERN_CHAIR = registerBlock(
      "warped_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_STRIPED_CHAIR = registerBlock(
      "warped_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_STOOL_CHAIR = registerBlock(
      "warped_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_COUNTER = registerBlock(
      "warped_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_DRAWER_COUNTER = registerBlock(
      "warped_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_DOUBLE_DRAWER_COUNTER = registerBlock(
      "warped_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_CUPBOARD_COUNTER = registerBlock(
      "warped_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_WARDROBE = registerBlock(
      "mangrove_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_MODERN_WARDROBE = registerBlock(
      "mangrove_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_DOUBLE_WARDROBE = registerBlock(
      "mangrove_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_BOOKSHELF = registerBlock(
      "mangrove_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_BOOKSHELF_CUPBOARD = registerBlock(
      "mangrove_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_DRAWER = registerBlock(
      "mangrove_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_DOUBLE_DRAWER = registerBlock(
      "mangrove_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_BOOKSHELF_DRAWER = registerBlock(
      "mangrove_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "mangrove_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_LARGE_DRAWER = registerBlock(
      "mangrove_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_LOWER_TRIPLE_DRAWER = registerBlock(
      "mangrove_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_TRIPLE_DRAWER = registerBlock(
      "mangrove_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_DESK = registerBlock(
      "mangrove_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_COVERED_DESK = registerBlock(
      "mangrove_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_MODERN_DESK = registerBlock(
      "mangrove_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_TABLE = registerBlock(
      "mangrove_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_END_TABLE = registerBlock(
      "mangrove_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_COFFEE_TABLE = registerBlock(
      "mangrove_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_GLASS_TABLE = registerBlock(
      "mangrove_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_CHAIR = registerBlock(
      "mangrove_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_MODERN_CHAIR = registerBlock(
      "mangrove_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_STRIPED_CHAIR = registerBlock(
      "mangrove_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_STOOL_CHAIR = registerBlock(
      "mangrove_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_COUNTER = registerBlock(
      "mangrove_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_DRAWER_COUNTER = registerBlock(
      "mangrove_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_DOUBLE_DRAWER_COUNTER = registerBlock(
      "mangrove_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_CUPBOARD_COUNTER = registerBlock(
      "mangrove_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_WARDROBE = registerBlock(
      "stripped_oak_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_MODERN_WARDROBE = registerBlock(
      "stripped_oak_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_DOUBLE_WARDROBE = registerBlock(
      "stripped_oak_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_BOOKSHELF = registerBlock(
      "stripped_oak_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_oak_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_DRAWER = registerBlock(
      "stripped_oak_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_DOUBLE_DRAWER = registerBlock(
      "stripped_oak_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_BOOKSHELF_DRAWER = registerBlock(
      "stripped_oak_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_oak_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_LARGE_DRAWER = registerBlock(
      "stripped_oak_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_oak_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_TRIPLE_DRAWER = registerBlock(
      "stripped_oak_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_DESK = registerBlock(
      "stripped_oak_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_COVERED_DESK = registerBlock(
      "stripped_oak_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_MODERN_DESK = registerBlock(
      "stripped_oak_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_TABLE = registerBlock(
      "stripped_oak_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_END_TABLE = registerBlock(
      "stripped_oak_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_COFFEE_TABLE = registerBlock(
      "stripped_oak_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_GLASS_TABLE = registerBlock(
      "stripped_oak_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_CHAIR = registerBlock(
      "stripped_oak_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_MODERN_CHAIR = registerBlock(
      "stripped_oak_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_STRIPED_CHAIR = registerBlock(
      "stripped_oak_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_STOOL_CHAIR = registerBlock(
      "stripped_oak_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_COUNTER = registerBlock(
      "stripped_oak_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_DRAWER_COUNTER = registerBlock(
      "stripped_oak_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_oak_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_CUPBOARD_COUNTER = registerBlock(
      "stripped_oak_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_WARDROBE = registerBlock(
      "stripped_birch_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_MODERN_WARDROBE = registerBlock(
      "stripped_birch_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_DOUBLE_WARDROBE = registerBlock(
      "stripped_birch_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_BOOKSHELF = registerBlock(
      "stripped_birch_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_birch_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_DRAWER = registerBlock(
      "stripped_birch_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_DOUBLE_DRAWER = registerBlock(
      "stripped_birch_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_BOOKSHELF_DRAWER = registerBlock(
      "stripped_birch_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_birch_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_LARGE_DRAWER = registerBlock(
      "stripped_birch_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_birch_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_TRIPLE_DRAWER = registerBlock(
      "stripped_birch_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_DESK = registerBlock(
      "stripped_birch_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_COVERED_DESK = registerBlock(
      "stripped_birch_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_MODERN_DESK = registerBlock(
      "stripped_birch_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_TABLE = registerBlock(
      "stripped_birch_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_END_TABLE = registerBlock(
      "stripped_birch_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_COFFEE_TABLE = registerBlock(
      "stripped_birch_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_GLASS_TABLE = registerBlock(
      "stripped_birch_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_CHAIR = registerBlock(
      "stripped_birch_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_MODERN_CHAIR = registerBlock(
      "stripped_birch_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_STRIPED_CHAIR = registerBlock(
      "stripped_birch_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_STOOL_CHAIR = registerBlock(
      "stripped_birch_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_COUNTER = registerBlock(
      "stripped_birch_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_DRAWER_COUNTER = registerBlock(
      "stripped_birch_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_birch_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_CUPBOARD_COUNTER = registerBlock(
      "stripped_birch_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_WARDROBE = registerBlock(
      "stripped_spruce_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_MODERN_WARDROBE = registerBlock(
      "stripped_spruce_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_DOUBLE_WARDROBE = registerBlock(
      "stripped_spruce_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_BOOKSHELF = registerBlock(
      "stripped_spruce_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_spruce_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_DRAWER = registerBlock(
      "stripped_spruce_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_DOUBLE_DRAWER = registerBlock(
      "stripped_spruce_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_BOOKSHELF_DRAWER = registerBlock(
      "stripped_spruce_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_spruce_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_LARGE_DRAWER = registerBlock(
      "stripped_spruce_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_spruce_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_TRIPLE_DRAWER = registerBlock(
      "stripped_spruce_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_DESK = registerBlock(
      "stripped_spruce_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_COVERED_DESK = registerBlock(
      "stripped_spruce_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_MODERN_DESK = registerBlock(
      "stripped_spruce_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_TABLE = registerBlock(
      "stripped_spruce_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_END_TABLE = registerBlock(
      "stripped_spruce_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_COFFEE_TABLE = registerBlock(
      "stripped_spruce_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_GLASS_TABLE = registerBlock(
      "stripped_spruce_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_CHAIR = registerBlock(
      "stripped_spruce_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_MODERN_CHAIR = registerBlock(
      "stripped_spruce_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_STRIPED_CHAIR = registerBlock(
      "stripped_spruce_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_STOOL_CHAIR = registerBlock(
      "stripped_spruce_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_COUNTER = registerBlock(
      "stripped_spruce_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_DRAWER_COUNTER = registerBlock(
      "stripped_spruce_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_spruce_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_CUPBOARD_COUNTER = registerBlock(
      "stripped_spruce_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_WARDROBE = registerBlock(
      "stripped_jungle_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_MODERN_WARDROBE = registerBlock(
      "stripped_jungle_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_DOUBLE_WARDROBE = registerBlock(
      "stripped_jungle_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_BOOKSHELF = registerBlock(
      "stripped_jungle_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_jungle_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_DRAWER = registerBlock(
      "stripped_jungle_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_DOUBLE_DRAWER = registerBlock(
      "stripped_jungle_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_BOOKSHELF_DRAWER = registerBlock(
      "stripped_jungle_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_jungle_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_LARGE_DRAWER = registerBlock(
      "stripped_jungle_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_jungle_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_TRIPLE_DRAWER = registerBlock(
      "stripped_jungle_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_DESK = registerBlock(
      "stripped_jungle_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_COVERED_DESK = registerBlock(
      "stripped_jungle_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_MODERN_DESK = registerBlock(
      "stripped_jungle_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_TABLE = registerBlock(
      "stripped_jungle_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_END_TABLE = registerBlock(
      "stripped_jungle_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_COFFEE_TABLE = registerBlock(
      "stripped_jungle_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_GLASS_TABLE = registerBlock(
      "stripped_jungle_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_CHAIR = registerBlock(
      "stripped_jungle_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_MODERN_CHAIR = registerBlock(
      "stripped_jungle_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_STRIPED_CHAIR = registerBlock(
      "stripped_jungle_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_STOOL_CHAIR = registerBlock(
      "stripped_jungle_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_COUNTER = registerBlock(
      "stripped_jungle_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_DRAWER_COUNTER = registerBlock(
      "stripped_jungle_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_jungle_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_CUPBOARD_COUNTER = registerBlock(
      "stripped_jungle_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_WARDROBE = registerBlock(
      "stripped_acacia_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_MODERN_WARDROBE = registerBlock(
      "stripped_acacia_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_DOUBLE_WARDROBE = registerBlock(
      "stripped_acacia_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_BOOKSHELF = registerBlock(
      "stripped_acacia_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_acacia_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_DRAWER = registerBlock(
      "stripped_acacia_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_DOUBLE_DRAWER = registerBlock(
      "stripped_acacia_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_BOOKSHELF_DRAWER = registerBlock(
      "stripped_acacia_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_acacia_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_LARGE_DRAWER = registerBlock(
      "stripped_acacia_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_acacia_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_TRIPLE_DRAWER = registerBlock(
      "stripped_acacia_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_DESK = registerBlock(
      "stripped_acacia_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_COVERED_DESK = registerBlock(
      "stripped_acacia_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_MODERN_DESK = registerBlock(
      "stripped_acacia_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_TABLE = registerBlock(
      "stripped_acacia_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_END_TABLE = registerBlock(
      "stripped_acacia_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_COFFEE_TABLE = registerBlock(
      "stripped_acacia_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_GLASS_TABLE = registerBlock(
      "stripped_acacia_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_CHAIR = registerBlock(
      "stripped_acacia_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_MODERN_CHAIR = registerBlock(
      "stripped_acacia_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_STRIPED_CHAIR = registerBlock(
      "stripped_acacia_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_STOOL_CHAIR = registerBlock(
      "stripped_acacia_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_COUNTER = registerBlock(
      "stripped_acacia_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_DRAWER_COUNTER = registerBlock(
      "stripped_acacia_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_acacia_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_CUPBOARD_COUNTER = registerBlock(
      "stripped_acacia_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_WARDROBE = registerBlock(
      "stripped_dark_oak_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_MODERN_WARDROBE = registerBlock(
      "stripped_dark_oak_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_DOUBLE_WARDROBE = registerBlock(
      "stripped_dark_oak_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_BOOKSHELF = registerBlock(
      "stripped_dark_oak_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_dark_oak_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_DRAWER = registerBlock(
      "stripped_dark_oak_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_DOUBLE_DRAWER = registerBlock(
      "stripped_dark_oak_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_BOOKSHELF_DRAWER = registerBlock(
      "stripped_dark_oak_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_dark_oak_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_LARGE_DRAWER = registerBlock(
      "stripped_dark_oak_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_dark_oak_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_TRIPLE_DRAWER = registerBlock(
      "stripped_dark_oak_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_DESK = registerBlock(
      "stripped_dark_oak_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_COVERED_DESK = registerBlock(
      "stripped_dark_oak_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_MODERN_DESK = registerBlock(
      "stripped_dark_oak_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_TABLE = registerBlock(
      "stripped_dark_oak_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_END_TABLE = registerBlock(
      "stripped_dark_oak_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_COFFEE_TABLE = registerBlock(
      "stripped_dark_oak_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_GLASS_TABLE = registerBlock(
      "stripped_dark_oak_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_CHAIR = registerBlock(
      "stripped_dark_oak_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_MODERN_CHAIR = registerBlock(
      "stripped_dark_oak_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_STRIPED_CHAIR = registerBlock(
      "stripped_dark_oak_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_STOOL_CHAIR = registerBlock(
      "stripped_dark_oak_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_COUNTER = registerBlock(
      "stripped_dark_oak_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_DRAWER_COUNTER = registerBlock(
      "stripped_dark_oak_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_dark_oak_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_CUPBOARD_COUNTER = registerBlock(
      "stripped_dark_oak_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_WARDROBE = registerBlock(
      "stripped_crimson_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_MODERN_WARDROBE = registerBlock(
      "stripped_crimson_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_DOUBLE_WARDROBE = registerBlock(
      "stripped_crimson_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_BOOKSHELF = registerBlock(
      "stripped_crimson_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_crimson_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_DRAWER = registerBlock(
      "stripped_crimson_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_DOUBLE_DRAWER = registerBlock(
      "stripped_crimson_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_BOOKSHELF_DRAWER = registerBlock(
      "stripped_crimson_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_crimson_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_LARGE_DRAWER = registerBlock(
      "stripped_crimson_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_crimson_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_TRIPLE_DRAWER = registerBlock(
      "stripped_crimson_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_DESK = registerBlock(
      "stripped_crimson_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_COVERED_DESK = registerBlock(
      "stripped_crimson_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_MODERN_DESK = registerBlock(
      "stripped_crimson_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_TABLE = registerBlock(
      "stripped_crimson_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_END_TABLE = registerBlock(
      "stripped_crimson_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_COFFEE_TABLE = registerBlock(
      "stripped_crimson_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_GLASS_TABLE = registerBlock(
      "stripped_crimson_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_CHAIR = registerBlock(
      "stripped_crimson_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_MODERN_CHAIR = registerBlock(
      "stripped_crimson_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_STRIPED_CHAIR = registerBlock(
      "stripped_crimson_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_STOOL_CHAIR = registerBlock(
      "stripped_crimson_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_COUNTER = registerBlock(
      "stripped_crimson_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_DRAWER_COUNTER = registerBlock(
      "stripped_crimson_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_crimson_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_CUPBOARD_COUNTER = registerBlock(
      "stripped_crimson_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_WARDROBE = registerBlock(
      "stripped_warped_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_MODERN_WARDROBE = registerBlock(
      "stripped_warped_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_DOUBLE_WARDROBE = registerBlock(
      "stripped_warped_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_BOOKSHELF = registerBlock(
      "stripped_warped_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_warped_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_DRAWER = registerBlock(
      "stripped_warped_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_DOUBLE_DRAWER = registerBlock(
      "stripped_warped_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_BOOKSHELF_DRAWER = registerBlock(
      "stripped_warped_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_warped_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_LARGE_DRAWER = registerBlock(
      "stripped_warped_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_warped_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_TRIPLE_DRAWER = registerBlock(
      "stripped_warped_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_DESK = registerBlock(
      "stripped_warped_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_COVERED_DESK = registerBlock(
      "stripped_warped_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_MODERN_DESK = registerBlock(
      "stripped_warped_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_TABLE = registerBlock(
      "stripped_warped_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_END_TABLE = registerBlock(
      "stripped_warped_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_COFFEE_TABLE = registerBlock(
      "stripped_warped_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_GLASS_TABLE = registerBlock(
      "stripped_warped_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_CHAIR = registerBlock(
      "stripped_warped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_MODERN_CHAIR = registerBlock(
      "stripped_warped_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_STRIPED_CHAIR = registerBlock(
      "stripped_warped_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_STOOL_CHAIR = registerBlock(
      "stripped_warped_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_COUNTER = registerBlock(
      "stripped_warped_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_DRAWER_COUNTER = registerBlock(
      "stripped_warped_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_warped_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_CUPBOARD_COUNTER = registerBlock(
      "stripped_warped_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_WARDROBE = registerBlock(
      "stripped_mangrove_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_MODERN_WARDROBE = registerBlock(
      "stripped_mangrove_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_DOUBLE_WARDROBE = registerBlock(
      "stripped_mangrove_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_BOOKSHELF = registerBlock(
      "stripped_mangrove_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_mangrove_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_DRAWER = registerBlock(
      "stripped_mangrove_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_DOUBLE_DRAWER = registerBlock(
      "stripped_mangrove_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_BOOKSHELF_DRAWER = registerBlock(
      "stripped_mangrove_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_mangrove_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_LARGE_DRAWER = registerBlock(
      "stripped_mangrove_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_mangrove_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_TRIPLE_DRAWER = registerBlock(
      "stripped_mangrove_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_DESK = registerBlock(
      "stripped_mangrove_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_COVERED_DESK = registerBlock(
      "stripped_mangrove_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_MODERN_DESK = registerBlock(
      "stripped_mangrove_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_TABLE = registerBlock(
      "stripped_mangrove_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_END_TABLE = registerBlock(
      "stripped_mangrove_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_COFFEE_TABLE = registerBlock(
      "stripped_mangrove_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_GLASS_TABLE = registerBlock(
      "stripped_mangrove_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_CHAIR = registerBlock(
      "stripped_mangrove_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_MODERN_CHAIR = registerBlock(
      "stripped_mangrove_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_STRIPED_CHAIR = registerBlock(
      "stripped_mangrove_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_STOOL_CHAIR = registerBlock(
      "stripped_mangrove_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_COUNTER = registerBlock(
      "stripped_mangrove_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_DRAWER_COUNTER = registerBlock(
      "stripped_mangrove_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_mangrove_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_CUPBOARD_COUNTER = registerBlock(
      "stripped_mangrove_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_WARDROBE = registerBlock(
      "cherry_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_MODERN_WARDROBE = registerBlock(
      "cherry_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_DOUBLE_WARDROBE = registerBlock(
      "cherry_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_BOOKSHELF = registerBlock(
      "cherry_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_BOOKSHELF_CUPBOARD = registerBlock(
      "cherry_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_DRAWER = registerBlock(
      "cherry_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_DOUBLE_DRAWER = registerBlock(
      "cherry_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_BOOKSHELF_DRAWER = registerBlock(
      "cherry_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "cherry_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_LARGE_DRAWER = registerBlock(
      "cherry_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_LOWER_TRIPLE_DRAWER = registerBlock(
      "cherry_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_TRIPLE_DRAWER = registerBlock(
      "cherry_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_DESK = registerBlock(
      "cherry_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_COVERED_DESK = registerBlock(
      "cherry_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_MODERN_DESK = registerBlock(
      "cherry_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_TABLE = registerBlock(
      "cherry_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_END_TABLE = registerBlock(
      "cherry_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_COFFEE_TABLE = registerBlock(
      "cherry_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_GLASS_TABLE = registerBlock(
      "cherry_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_CHAIR = registerBlock(
      "cherry_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_MODERN_CHAIR = registerBlock(
      "cherry_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_STRIPED_CHAIR = registerBlock(
      "cherry_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_STOOL_CHAIR = registerBlock(
      "cherry_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_COUNTER = registerBlock(
      "cherry_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_DRAWER_COUNTER = registerBlock(
      "cherry_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_DOUBLE_DRAWER_COUNTER = registerBlock(
      "cherry_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_CUPBOARD_COUNTER = registerBlock(
      "cherry_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_WARDROBE = registerBlock(
      "stripped_cherry_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_MODERN_WARDROBE = registerBlock(
      "stripped_cherry_modern_wardrobe", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_DOUBLE_WARDROBE = registerBlock(
      "stripped_cherry_double_wardrobe", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_BOOKSHELF = registerBlock(
      "stripped_cherry_bookshelf", new TallFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_BOOKSHELF_CUPBOARD = registerBlock(
      "stripped_cherry_bookshelf_cupboard", new TallFurnitureHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_DRAWER = registerBlock(
      "stripped_cherry_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_DOUBLE_DRAWER = registerBlock(
      "stripped_cherry_double_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_BOOKSHELF_DRAWER = registerBlock(
      "stripped_cherry_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_LOWER_BOOKSHELF_DRAWER = registerBlock(
      "stripped_cherry_lower_bookshelf_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_LARGE_DRAWER = registerBlock(
      "stripped_cherry_large_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_LOWER_TRIPLE_DRAWER = registerBlock(
      "stripped_cherry_lower_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_TRIPLE_DRAWER = registerBlock(
      "stripped_cherry_triple_drawer", new WideFurniture(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_DESK = registerBlock(
      "stripped_cherry_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_COVERED_DESK = registerBlock(
      "stripped_cherry_covered_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_MODERN_DESK = registerBlock(
      "stripped_cherry_modern_desk", new Desk(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_TABLE = registerBlock(
      "stripped_cherry_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_END_TABLE = registerBlock(
      "stripped_cherry_end_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_COFFEE_TABLE = registerBlock(
      "stripped_cherry_coffee_table", new Table(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_GLASS_TABLE = registerBlock(
      "stripped_cherry_glass_table", new TableHitbox(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_CHAIR = registerBlock(
      "stripped_cherry_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_MODERN_CHAIR = registerBlock(
      "stripped_cherry_modern_chair", new ModernChair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_STRIPED_CHAIR = registerBlock(
      "stripped_cherry_striped_chair", new StripedChair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_STOOL_CHAIR = registerBlock(
      "stripped_cherry_stool_chair", new Chair(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_COUNTER = registerBlock(
      "stripped_cherry_counter", new Counter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_DRAWER_COUNTER = registerBlock(
      "stripped_cherry_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_DOUBLE_DRAWER_COUNTER = registerBlock(
      "stripped_cherry_double_drawer_counter", new StorageCounter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_CUPBOARD_COUNTER = registerBlock(
      "stripped_cherry_cupboard_counter", new CupboardCounter(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_KITCHEN_CABINET = registerBlock(
      "oak_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_DOUBLE_KITCHEN_CABINET = registerBlock(
      "oak_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_GLASS_KITCHEN_CABINET = registerBlock(
      "oak_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_KITCHEN_CABINET = registerBlock(
      "stripped_oak_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_oak_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_oak_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_KITCHEN_CABINET = registerBlock(
      "birch_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_DOUBLE_KITCHEN_CABINET = registerBlock(
      "birch_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_GLASS_KITCHEN_CABINET = registerBlock(
      "birch_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_KITCHEN_CABINET = registerBlock(
      "stripped_birch_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_birch_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_birch_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_KITCHEN_CABINET = registerBlock(
      "spruce_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_DOUBLE_KITCHEN_CABINET = registerBlock(
      "spruce_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_GLASS_KITCHEN_CABINET = registerBlock(
      "spruce_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_KITCHEN_CABINET = registerBlock(
      "stripped_spruce_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_spruce_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_spruce_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_KITCHEN_CABINET = registerBlock(
      "jungle_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_DOUBLE_KITCHEN_CABINET = registerBlock(
      "jungle_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_GLASS_KITCHEN_CABINET = registerBlock(
      "jungle_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_KITCHEN_CABINET = registerBlock(
      "stripped_jungle_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_jungle_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_jungle_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_KITCHEN_CABINET = registerBlock(
      "acacia_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_DOUBLE_KITCHEN_CABINET = registerBlock(
      "acacia_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_GLASS_KITCHEN_CABINET = registerBlock(
      "acacia_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_KITCHEN_CABINET = registerBlock(
      "stripped_acacia_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_acacia_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_acacia_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_KITCHEN_CABINET = registerBlock(
      "dark_oak_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_DOUBLE_KITCHEN_CABINET = registerBlock(
      "dark_oak_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_GLASS_KITCHEN_CABINET = registerBlock(
      "dark_oak_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_KITCHEN_CABINET = registerBlock(
      "stripped_dark_oak_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_dark_oak_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_dark_oak_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_KITCHEN_CABINET = registerBlock(
      "warped_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_DOUBLE_KITCHEN_CABINET = registerBlock(
      "warped_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_GLASS_KITCHEN_CABINET = registerBlock(
      "warped_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_KITCHEN_CABINET = registerBlock(
      "stripped_warped_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_warped_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_warped_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_KITCHEN_CABINET = registerBlock(
      "crimson_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_DOUBLE_KITCHEN_CABINET = registerBlock(
      "crimson_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_GLASS_KITCHEN_CABINET = registerBlock(
      "crimson_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_KITCHEN_CABINET = registerBlock(
      "stripped_crimson_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_crimson_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_crimson_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_KITCHEN_CABINET = registerBlock(
      "mangrove_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15982).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_DOUBLE_KITCHEN_CABINET = registerBlock(
      "mangrove_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15982).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_GLASS_KITCHEN_CABINET = registerBlock(
      "mangrove_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15982).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_KITCHEN_CABINET = registerBlock(
      "stripped_mangrove_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_15982).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_mangrove_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15982).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_mangrove_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_15982).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_KITCHEN_CABINET = registerBlock(
      "cherry_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_DOUBLE_KITCHEN_CABINET = registerBlock(
      "cherry_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_GLASS_KITCHEN_CABINET = registerBlock(
      "cherry_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_KITCHEN_CABINET = registerBlock(
      "stripped_cherry_kitchen_cabinet", new CabinetHinge(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_DOUBLE_KITCHEN_CABINET = registerBlock(
      "stripped_cherry_double_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_GLASS_KITCHEN_CABINET = registerBlock(
      "stripped_cherry_glass_kitchen_cabinet", new Cabinet(class_2251.method_9637().method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 OAK_KITCHEN_SINK = registerBlock(
      "oak_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_OAK_KITCHEN_SINK = registerBlock(
      "stripped_oak_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BIRCH_KITCHEN_SINK = registerBlock(
      "birch_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_BIRCH_KITCHEN_SINK = registerBlock(
      "stripped_birch_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 SPRUCE_KITCHEN_SINK = registerBlock(
      "spruce_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_SPRUCE_KITCHEN_SINK = registerBlock(
      "stripped_spruce_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 JUNGLE_KITCHEN_SINK = registerBlock(
      "jungle_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_JUNGLE_KITCHEN_SINK = registerBlock(
      "stripped_jungle_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ACACIA_KITCHEN_SINK = registerBlock(
      "acacia_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_ACACIA_KITCHEN_SINK = registerBlock(
      "stripped_acacia_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 DARK_OAK_KITCHEN_SINK = registerBlock(
      "dark_oak_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_DARK_OAK_KITCHEN_SINK = registerBlock(
      "stripped_dark_oak_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CRIMSON_KITCHEN_SINK = registerBlock(
      "crimson_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CRIMSON_KITCHEN_SINK = registerBlock(
      "stripped_crimson_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_25704).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WARPED_KITCHEN_SINK = registerBlock(
      "warped_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_WARPED_KITCHEN_SINK = registerBlock(
      "stripped_warped_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MANGROVE_KITCHEN_SINK = registerBlock(
      "mangrove_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_MANGROVE_KITCHEN_SINK = registerBlock(
      "stripped_mangrove_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15996).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CHERRY_KITCHEN_SINK = registerBlock(
      "cherry_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15989).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 STRIPPED_CHERRY_KITCHEN_SINK = registerBlock(
      "stripped_cherry_kitchen_sink", new SinkCounter(class_2251.method_9637().method_31710(class_3620.field_15989).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WHITE_COUCH = registerBlock(
      "white_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16022).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 WHITE_CHAISE = registerBlock(
      "white_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16022).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 LIGHT_GRAY_COUCH = registerBlock(
      "light_gray_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15993).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 LIGHT_GRAY_CHAISE = registerBlock(
      "light_gray_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15993).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 GRAY_COUCH = registerBlock(
      "gray_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15978).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 GRAY_CHAISE = registerBlock(
      "gray_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15978).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BLACK_COUCH = registerBlock(
      "black_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16009).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BLACK_CHAISE = registerBlock(
      "black_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16009).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BROWN_COUCH = registerBlock(
      "brown_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15977).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BROWN_CHAISE = registerBlock(
      "brown_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15977).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 RED_COUCH = registerBlock(
      "red_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 RED_CHAISE = registerBlock(
      "red_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16020).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ORANGE_COUCH = registerBlock(
      "orange_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15987).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 ORANGE_CHAISE = registerBlock(
      "orange_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15987).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 YELLOW_COUCH = registerBlock(
      "yellow_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16010).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 YELLOW_CHAISE = registerBlock(
      "yellow_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16010).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 LIME_COUCH = registerBlock(
      "lime_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15997).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 LIME_CHAISE = registerBlock(
      "lime_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15997).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 GREEN_COUCH = registerBlock(
      "green_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15995).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 GREEN_CHAISE = registerBlock(
      "green_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15995).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CYAN_COUCH = registerBlock(
      "cyan_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 CYAN_CHAISE = registerBlock(
      "cyan_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16026).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 LIGHT_BLUE_COUCH = registerBlock(
      "light_blue_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16024).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 LIGHT_BLUE_CHAISE = registerBlock(
      "light_blue_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16024).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BLUE_COUCH = registerBlock(
      "blue_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15984).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 BLUE_CHAISE = registerBlock(
      "blue_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15984).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 PURPLE_COUCH = registerBlock(
      "purple_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16014).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 PURPLE_CHAISE = registerBlock(
      "purple_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16014).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MAGENTA_COUCH = registerBlock(
      "magenta_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15998).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 MAGENTA_CHAISE = registerBlock(
      "magenta_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_15998).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 PINK_COUCH = registerBlock(
      "pink_couch",
      new Couch(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );
   public static final class_2248 PINK_CHAISE = registerBlock(
      "pink_chaise",
      new Chaise(class_2251.method_9637().method_22488().method_9626(class_2498.field_11543).method_31710(class_3620.field_16030).method_9629(2.0F, 2.3F))
   );

   private static class_2248 registerBlock(String name, class_2248 block) {
      registerBlockItem(name, block);
      return (class_2248)class_2378.method_10230(class_7923.field_41175, class_2960.method_60655("mcwfurnitures", name), block);
   }

   private static class_1792 registerBlockItem(String name, class_2248 block) {
      return (class_1792)class_2378.method_10230(
         class_7923.field_41178, class_2960.method_60655("mcwfurnitures", name), new class_1747(block, new class_1793())
      );
   }

   public static void registerModBlocks() {
   }
}
