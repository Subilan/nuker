package red.oases.nuker.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import red.oases.nuker.Commands.Annotations.DisableConsole;
import red.oases.nuker.Logs;

public abstract class Command {
    public String[] args;
    public CommandSender sender;
    public boolean disableConsole;

    public Command(String[] args, CommandSender sender) {
        this.args = args;
        this.sender = sender;
        this.disableConsole = this.getClass().isAnnotationPresent(DisableConsole.class);
    }

    protected abstract boolean execute();

    public boolean collect() {
        if (disableConsole && !(sender instanceof Player)) {
            Logs.send(sender, "此指令只能由玩家执行。");
            return true;
        }

        return this.execute();
    }
}