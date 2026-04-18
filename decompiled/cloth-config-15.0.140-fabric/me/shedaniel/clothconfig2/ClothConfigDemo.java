package me.shedaniel.clothconfig2;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import me.shedaniel.autoconfig.util.Utils;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.api.Requirement;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import me.shedaniel.clothconfig2.gui.entries.IntegerSliderEntry;
import me.shedaniel.clothconfig2.gui.entries.MultiElementListEntry;
import me.shedaniel.clothconfig2.gui.entries.NestedListListEntry;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.class_124;
import net.minecraft.class_156;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1893;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_3675;
import net.minecraft.class_7887;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_9334;
import net.minecraft.class_2558.class_2559;
import net.minecraft.class_2568.class_5247;
import net.minecraft.class_2568.class_5249;
import net.minecraft.class_3675.class_307;

public class ClothConfigDemo {
   public static ConfigBuilder getConfigBuilderWithDemo() {
      ConfigBuilder builder = ConfigBuilder.create().setTitle(class_2561.method_43471("title.cloth-config.config"));
      builder.setGlobalized(true);
      builder.setGlobalizedExpanded(false);
      ConfigEntryBuilder entryBuilder = builder.entryBuilder();
      ConfigCategory testing = builder.getOrCreateCategory(class_2561.method_43471("category.cloth-config.testing"));
      testing.addEntry(
         entryBuilder.startKeyCodeField(class_2561.method_43470("Cool Key"), class_3675.field_16237).setDefaultValue(class_3675.field_16237).build()
      );
      testing.addEntry(
         entryBuilder.startModifierKeyCodeField(
               class_2561.method_43470("Cool Modifier Key"), ModifierKeyCode.of(class_307.field_1668.method_1447(79), Modifier.of(false, true, false))
            )
            .setDefaultValue(ModifierKeyCode.of(class_307.field_1668.method_1447(79), Modifier.of(false, true, false)))
            .build()
      );
      testing.addEntry(
         entryBuilder.startDoubleList(class_2561.method_43470("A list of Doubles"), Arrays.asList(1.0, 2.0, 3.0))
            .setDefaultValue(Arrays.asList(1.0, 2.0, 3.0))
            .build()
      );
      testing.addEntry(
         entryBuilder.startLongList(class_2561.method_43470("A list of Longs"), Arrays.asList(1L, 2L, 3L))
            .setDefaultValue(Arrays.asList(1L, 2L, 3L))
            .setInsertButtonEnabled(false)
            .build()
      );
      testing.addEntry(
         entryBuilder.startStrList(class_2561.method_43470("A list of Strings"), Arrays.asList("abc", "xyz"))
            .setTooltip(class_2561.method_43470("Yes this is some beautiful tooltip\nOh and this is the second line!"))
            .setDefaultValue(Arrays.asList("abc", "xyz"))
            .build()
      );
      SubCategoryBuilder colors = entryBuilder.startSubCategory(class_2561.method_43470("Colors")).setExpanded(true);
      colors.add((AbstractConfigListEntry)entryBuilder.startColorField(class_2561.method_43470("A color field"), 65535).setDefaultValue(65535).build());
      colors.add(
         (AbstractConfigListEntry)entryBuilder.startColorField(class_2561.method_43470("An alpha color field"), -16711681)
            .setDefaultValue(-16711681)
            .setAlphaMode(true)
            .build()
      );
      colors.add(
         (AbstractConfigListEntry)entryBuilder.startColorField(class_2561.method_43470("An alpha color field"), -1)
            .setDefaultValue(-65536)
            .setAlphaMode(true)
            .build()
      );

      enum DependencyDemoEnum {
         EXCELLENT,
         GOOD,
         OKAY,
         BAD,
         HORRIBLE;
      }


      class Pair<T, R> {
         final T t;
         final R r;

         public Pair(T t, R r) {
            this.t = t;
            this.r = r;
         }

         public T getLeft() {
            return this.t;
         }

         public R getRight() {
            return this.r;
         }

         @Override
         public boolean equals(Object o) {
            if (this == o) {
               return true;
            } else if (o != null && this.getClass() == o.getClass()) {
               Pair<?, ?> pair = (Pair<?, ?>)o;
               return !Objects.equals(this.t, pair.t) ? false : Objects.equals(this.r, pair.r);
            } else {
               return false;
            }
         }

         @Override
         public int hashCode() {
            int result = this.t != null ? this.t.hashCode() : 0;
            return 31 * result + (this.r != null ? this.r.hashCode() : 0);
         }
      }

      colors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      colors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      colors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      colors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      colors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      SubCategoryBuilder innerColors = entryBuilder.startSubCategory(class_2561.method_43470("Inner Colors")).setExpanded(true);
      innerColors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      innerColors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      innerColors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      SubCategoryBuilder innerInnerColors = entryBuilder.startSubCategory(class_2561.method_43470("Inner Inner Colors")).setExpanded(true);
      innerInnerColors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      innerInnerColors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      innerInnerColors.add(
         (AbstractConfigListEntry)entryBuilder.startDropdownMenu(
               class_2561.method_43470("lol apple"),
               DropdownMenuBuilder.TopCellElementBuilder.ofItemObject(class_1802.field_8279),
               DropdownMenuBuilder.CellCreatorBuilder.ofItemObject()
            )
            .setDefaultValue(class_1802.field_8279)
            .setSelections(
               class_7923.field_41178.method_10220().sorted(Comparator.comparing(class_1792::toString)).collect(Collectors.toCollection(LinkedHashSet::new))
            )
            .setSaveConsumer(item -> System.out.println("save this " + item))
            .build()
      );
      innerColors.add((AbstractConfigListEntry)innerInnerColors.build());
      colors.add((AbstractConfigListEntry)innerColors.build());
      testing.addEntry(colors.build());
      testing.addEntry(entryBuilder.startDropdownMenu(class_2561.method_43470("Suggestion Random Int"), DropdownMenuBuilder.TopCellElementBuilder.of(10, s -> {
         try {
            return Integer.parseInt(s);
         } catch (NumberFormatException var2x) {
            return null;
         }
      })).setDefaultValue(10).setSelections(Lists.newArrayList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})).build());
      testing.addEntry(entryBuilder.startDropdownMenu(class_2561.method_43470("Selection Random Int"), DropdownMenuBuilder.TopCellElementBuilder.of(10, s -> {
         try {
            return Integer.parseInt(s);
         } catch (NumberFormatException var2x) {
            return null;
         }
      })).setDefaultValue(5).setSuggestionMode(false).setSelections(Lists.newArrayList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})).build());
      testing.addEntry(
         new NestedListListEntry<>(
            class_2561.method_43470("Nice"),
            Lists.newArrayList(new Pair[]{new Pair<>(10, 10), new Pair<>(20, 40)}),
            false,
            Optional::empty,
            list -> {},
            () -> Lists.newArrayList(new Pair[]{new Pair<>(10, 10), new Pair<>(20, 40)}),
            entryBuilder.getResetButtonKey(),
            true,
            true,
            (elem, nestedListListEntry) -> {
               if (elem == null) {
                  Pair<Integer, Integer> newDefaultElemValue = new Pair<>(10, 10);
                  return new MultiElementListEntry<>(
                     class_2561.method_43470("Pair"),
                     newDefaultElemValue,
                     Lists.newArrayList(
                        new AbstractConfigListEntry[]{
                           entryBuilder.startIntField(class_2561.method_43470("Left"), newDefaultElemValue.getLeft()).setDefaultValue(10).build(),
                           entryBuilder.startIntField(class_2561.method_43470("Right"), newDefaultElemValue.getRight()).setDefaultValue(10).build()
                        }
                     ),
                     true
                  );
               } else {
                  return new MultiElementListEntry<>(
                     class_2561.method_43470("Pair"),
                     elem,
                     Lists.newArrayList(
                        new AbstractConfigListEntry[]{
                           entryBuilder.startIntField(class_2561.method_43470("Left"), (Integer)elem.getLeft()).setDefaultValue(10).build(),
                           entryBuilder.startIntField(class_2561.method_43470("Right"), (Integer)elem.getRight()).setDefaultValue(10).build()
                        }
                     ),
                     true
                  );
               }
            }
         )
      );
      SubCategoryBuilder depends = entryBuilder.startSubCategory(class_2561.method_43470("Dependencies")).setExpanded(true);
      BooleanListEntry dependency = entryBuilder.startBooleanToggle(class_2561.method_43470("A cool toggle"), false)
         .setTooltip(class_2561.method_43470("Toggle me..."))
         .build();
      depends.add((AbstractConfigListEntry)dependency);
      Collection<BooleanListEntry> toggles = new LinkedList<>();
      toggles.add(
         entryBuilder.startBooleanToggle(class_2561.method_43470("I only work when cool is toggled..."), true)
            .setRequirement(Requirement.isTrue(dependency))
            .build()
      );
      toggles.add(
         entryBuilder.startBooleanToggle(class_2561.method_43470("I only appear when cool is toggled..."), true)
            .setDisplayRequirement(Requirement.isTrue(dependency))
            .build()
      );
      depends.addAll(toggles);
      depends.add(
         (AbstractConfigListEntry)entryBuilder.startBooleanToggle(class_2561.method_43470("I only work when cool matches both of these toggles ^^"), true)
            .setRequirement(Requirement.all(toggles.stream().map(toggle -> Requirement.matches(dependency, toggle)).toArray(Requirement[]::new)))
            .build()
      );
      SubCategoryBuilder dependantSub = entryBuilder.startSubCategory(class_2561.method_43470("Sub-categories can have requirements too..."))
         .setRequirement(Requirement.isTrue(dependency));
      dependantSub.add(
         (AbstractConfigListEntry)entryBuilder.startTextDescription(class_2561.method_43470("This sub category depends on Cool being toggled")).build()
      );
      dependantSub.add((AbstractConfigListEntry)entryBuilder.startBooleanToggle(class_2561.method_43470("Example entry"), true).build());
      dependantSub.add((AbstractConfigListEntry)entryBuilder.startBooleanToggle(class_2561.method_43470("Another example..."), true).build());
      depends.add((AbstractConfigListEntry)dependantSub.build());
      depends.add(
         (AbstractConfigListEntry)entryBuilder.startLongList(class_2561.method_43470("Even lists!"), Arrays.asList(1L, 2L, 3L))
            .setDefaultValue(Arrays.asList(1L, 2L, 3L))
            .setRequirement(Requirement.isTrue(dependency))
            .build()
      );
      EnumListEntry<DependencyDemoEnum> enumDependency = entryBuilder.startEnumSelector(
            class_2561.method_43470("Select a good or bad option"), DependencyDemoEnum.class, DependencyDemoEnum.OKAY
         )
         .build();
      depends.add((AbstractConfigListEntry)enumDependency);
      IntegerSliderEntry intDependency = entryBuilder.startIntSlider(class_2561.method_43470("Select something big or small"), 50, -100, 100).build();
      depends.add((AbstractConfigListEntry)intDependency);
      depends.add(
         (AbstractConfigListEntry)entryBuilder.startBooleanToggle(class_2561.method_43470("I only work when a good option is chosen..."), true)
            .setTooltip(class_2561.method_43470("Select good or better above"))
            .setRequirement(Requirement.isValue(enumDependency, DependencyDemoEnum.EXCELLENT, DependencyDemoEnum.GOOD))
            .build()
      );
      depends.add(
         (AbstractConfigListEntry)entryBuilder.startBooleanToggle(class_2561.method_43470("I need a good option AND a cool toggle!"), true)
            .setTooltip(class_2561.method_43470("Select good or better and also toggle cool"))
            .setRequirement(
               Requirement.all(Requirement.isTrue(dependency), Requirement.isValue(enumDependency, DependencyDemoEnum.EXCELLENT, DependencyDemoEnum.GOOD))
            )
            .build()
      );
      depends.add(
         (AbstractConfigListEntry)entryBuilder.startBooleanToggle(class_2561.method_43470("I only work when numbers are extreme!"), true)
            .setTooltip(class_2561.method_43470("Move the slider..."))
            .setRequirement(Requirement.any(() -> intDependency.getValue() < -70, () -> intDependency.getValue() > 70))
            .build()
      );
      testing.addEntry(depends.build());
      testing.addEntry(
         entryBuilder.startTextDescription(
               class_2561.method_43469(
                  "text.cloth-config.testing.1",
                  new Object[]{
                     class_2561.method_43470("ClothConfig")
                        .method_27694(
                           s -> s.method_10982(true)
                              .method_10949(
                                 new class_2568(
                                    class_5247.field_24343, new class_5249((class_1799)class_156.method_654(new class_1799(class_1802.field_19050), stack -> {
                                       stack.method_57379(class_9334.field_49631, class_2561.method_43470("(・∀・)"));
                                       stack.method_7978(class_7887.method_46817().method_46762(class_7924.field_41265).method_46747(class_1893.field_9131), 10);
                                    }))
                                 )
                              )
                        ),
                     class_2561.method_43471("text.cloth-config.testing.2")
                        .method_27694(
                           s -> s.method_10977(class_124.field_1078)
                              .method_10949(new class_2568(class_5247.field_24342, class_2561.method_43470("https://shedaniel.gitbook.io/cloth-config/")))
                              .method_10958(new class_2558(class_2559.field_11749, "https://shedaniel.gitbook.io/cloth-config/"))
                        ),
                     class_2561.method_43471("text.cloth-config.testing.3")
                        .method_27694(
                           s -> s.method_10977(class_124.field_1060)
                              .method_10958(new class_2558(class_2559.field_11746, Utils.getConfigFolder().getParent().resolve("options.txt").toString()))
                        )
                  }
               )
            )
            .build()
      );
      builder.transparentBackground();
      return builder;
   }
}
