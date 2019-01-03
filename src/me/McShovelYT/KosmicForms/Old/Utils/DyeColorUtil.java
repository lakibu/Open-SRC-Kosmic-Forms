package me.McShovelYT.KosmicForms.Old.Utils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.DyeColor;

public class DyeColorUtil {
    private static final BiMap<DyeColor, Integer> map = HashBiMap.create();

    static {
        map.put(DyeColor.WHITE, 15);
        map.put(DyeColor.ORANGE, 14);
        map.put(DyeColor.MAGENTA, 13);
        map.put(DyeColor.LIGHT_BLUE, 12);
        map.put(DyeColor.YELLOW, 11);
        map.put(DyeColor.LIME, 10);
        map.put(DyeColor.PINK, 9);
        map.put(DyeColor.GRAY, 8);
        map.put(DyeColor.SILVER, 7);
        map.put(DyeColor.CYAN, 6);
        map.put(DyeColor.PURPLE, 5);
        map.put(DyeColor.BLUE, 4);
        map.put(DyeColor.BROWN, 3);
        map.put(DyeColor.GREEN, 2);
        map.put(DyeColor.RED, 1);
        map.put(DyeColor.BLACK, 0);
    }

    public DyeColorUtil() {
    }

    public static short toShort(DyeColor dyeColor) {
        return ((Integer)map.get(dyeColor)).shortValue();
    }

    public static DyeColor fromInt(int number) {
        return (DyeColor)map.inverse().get(number);
    }
}
