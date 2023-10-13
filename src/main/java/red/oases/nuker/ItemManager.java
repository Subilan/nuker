package red.oases.nuker;

import de.tr7zw.changeme.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemManager {
    // TODO: colorize by RGB gradient
    public static final Component immunityText = Component.text("核爆免疫").color(NamedTextColor.GREEN);
    public static final Component nuclearSeeking = Component.text("核能窥探").color(NamedTextColor.GREEN);

    public static String property(@NotNull String key) {
        return "oasis-nuker-item-property-" + key;
    }

    public static void setLore(ItemStack input, Component comp) {
        var meta = input.getItemMeta();
        if (!meta.hasLore()) return;
        var lore = meta.lore(); assert lore != null;
        lore.add(comp);
        input.setItemMeta(meta);
    }

    public static void removeLore(ItemStack input, Component comp) {
        var meta = input.getItemMeta();
        if (!meta.hasLore()) return;
        var lore = meta.lore(); assert lore != null;
        if (!lore.remove(comp)) {
            Nuker.logger.warning("Operation failed. Cannot remove lore from item.");
        }
        input.setItemMeta(meta);
    }

    public static ItemStack setEffectProof(ItemStack input, boolean flag) {
        NBT.modify(input, n -> {
            n.setBoolean(property("effect-proof"), flag);
        });

        if (flag) {
            setLore(input, immunityText);
        } else {
            removeLore(input, immunityText);
        }

        return input;
    }

    public static ItemStack setDiggingTool(ItemStack input, boolean flag) {
        NBT.modify(input, n -> {
            n.setBoolean(property("digging-tool"), flag);
        });

        if (flag) {
            setLore(input, nuclearSeeking);
        } else {
            removeLore(input, nuclearSeeking);
        }

        return input;
    }

    public static boolean isEffectProof(ItemStack input) {
        return NBT.get(input, t -> t.getBoolean(property("effect-proof")));
    }

    public static boolean isDiggingTool(ItemStack input) {
        return NBT.get(input, t -> t.getBoolean(property("digging-tool")));
    }
}
