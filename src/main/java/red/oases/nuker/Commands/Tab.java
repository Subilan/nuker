package red.oases.nuker.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Tab implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return switch (args.length) {
            case 1 -> List.of("item");

            case 2 -> switch (args[0]) {
                case "item" -> List.of("set");
                default -> null;
            };

            case 3 -> switch (args[0]) {
                case "item" -> List.of("digging-tool", "effect-proof");
                default -> null;
            };

            case 4 -> switch (args[0]) {
                case "item" -> List.of("true", "false");
                default -> null;
            };

            default -> null;
        };
    }
}
