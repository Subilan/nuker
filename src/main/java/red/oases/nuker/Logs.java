package red.oases.nuker;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class Logs {

    public static void send(CommandSender sender, Component comp) {
        sender.sendMessage(comp);
    }

    public static void send(CommandSender sender, String text) {
        send(sender, Component.text(text));
    }

    public static Component error(String text) {
        return Component.text(text).color(NamedTextColor.RED);
    }
}
