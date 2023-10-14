package red.oases.nuker;

import de.tr7zw.changeme.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemManager {
    // TODO: colorize by RGB gradient
    public static final Component immunityText = Component.text("核爆免疫").color(NamedTextColor.GREEN);
    public static final Component nuclearSeeking = Component.text("核能窥探").color(NamedTextColor.GREEN);

    public static String property(@NotNull String key) {
        return "oasis-nuker-item-property-" + key;
    }

    public static void setLore(ItemStack input, Component comp) {
        var meta = input.getItemMeta();
        var originalLore = meta.lore();
        var currentLore = originalLore == null ? new ArrayList<Component>() : new ArrayList<>(originalLore);
        currentLore.add(comp);
        meta.lore(currentLore);
        input.setItemMeta(meta);
    }

    public static void removeLore(ItemStack input, Component comp) {
        var meta = input.getItemMeta();
        var originalLore = meta.lore();
        var currentLore = originalLore == null ? new ArrayList<Component>() : new ArrayList<>(originalLore);
        currentLore.remove(comp);
        meta.lore(currentLore);
        input.setItemMeta(meta);
    }

    public static ItemStack setEffectProof(ItemStack input, boolean flag) {
        NBT.itemStackToNBT(input).setBoolean(property("effect-proof"), flag);

        if (flag) {
            setLore(input, immunityText);
        } else {
            removeLore(input, immunityText);
        }

        return input;
    }

    public static ItemStack setDiggingTool(ItemStack input, boolean flag) {
        var nbt = NBT.itemStackToNBT(input);
        nbt.setBoolean(property("digging-tool"), flag);
        var result = NBT.itemStackFromNBT(nbt);

        if (flag) {
            setLore(result, nuclearSeeking);
        } else {
            removeLore(result, nuclearSeeking);
        }

        return result;
    }

    public static boolean isEffectProof(ItemStack input) {
        return NBT.itemStackToNBT(input).getBoolean(property("effect-proof"));
    }

    public static boolean isDiggingTool(ItemStack input) {
        return NBT.itemStackToNBT(input).getBoolean(property("digging-tool"));
    }
}
