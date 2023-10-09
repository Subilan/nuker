package red.oases.nuker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Files {
    public static FileConfiguration config;

    public static void load(JavaPlugin plugin) {
        config = plugin.getConfig();
    }
}
