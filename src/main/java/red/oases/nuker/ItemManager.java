package red.oases.nuker;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemManager {
    // TODO: colorize by RGB gradient
    public static final Component immunityText = Component.text("核爆免疫").color(NamedTextColor.GREEN);
    public static final Component nuclearSeeking = Component.text("核能窥探").color(NamedTextColor.GREEN);

    public static NamespacedKey property(@NotNull String key) {
        return new NamespacedKey(Nuker.plugin, "oasis-nuker-item-property-" + key);
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
        var meta = input.getItemMeta();
        var data = meta.getPersistentDataContainer();
        data.set(property("effect-proof"), PersistentDataType.BOOLEAN, flag);
        input.setItemMeta(meta);

        if (flag) {
            setLore(input, immunityText);
        } else {
            removeLore(input, immunityText);
        }

        return input;
    }

    public static ItemStack setDiggingTool(ItemStack input, boolean flag) {
        var meta = input.getItemMeta();
        var data = meta.getPersistentDataContainer();
        data.set(property("digging-tool"), PersistentDataType.BOOLEAN, flag);
        input.setItemMeta(meta);

        if (flag) {
            setLore(input, nuclearSeeking);
        } else {
            removeLore(input, nuclearSeeking);
        }

        return input;
    }

    public static boolean isTrueProperty(ItemStack input, NamespacedKey key) {
        var meta = input.getItemMeta();
        if (meta == null) return false;
        var container = meta.getPersistentDataContainer();
        return Boolean.TRUE.equals(container.get(key, PersistentDataType.BOOLEAN));

    }

    public static boolean isEffectProof(ItemStack input) {
        return isTrueProperty(input, property("effect-proof"));
    }

    public static boolean isDiggingTool(ItemStack input) {
       return isTrueProperty(input, property("digging-tool"));
    }
}
