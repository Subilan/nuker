package red.oases.nuker.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import red.oases.nuker.Logs;

public class Executor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!label.equalsIgnoreCase("nuker")) return true;

        if (args.length == 0) {
            Logs.send(sender, "错误：参数不足");
            return true;
        }

        return switch (args[0]) {
            case "item" -> new CommandNukerItem(args, sender).collect();
            default -> {
                Logs.send(sender, "错误：未知指令 /nuker " + args[0]);
                yield true;
            }
        };
    }
}
