package red.oases.nuker.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import red.oases.nuker.Commands.Annotations.DisableConsole;
import red.oases.nuker.ItemManager;
import red.oases.nuker.Logs;

@DisableConsole
public class CommandNukerItem extends Command {
    public CommandNukerItem(String[] args, CommandSender sender) {
        super(args, sender);
    }

    @Override
    protected boolean execute() {
        if (args.length < 3) {
            Logs.send(sender, Logs.error("错误：参数不足"));
            return true;
        }

        var player = (Player) sender;

        var action = args[1];
        switch (action) {
            case "set" -> {
                var key = args[2];
                var flag = args.length == 3 || Boolean.parseBoolean(args[3]);

                var item = player.getInventory().getItemInMainHand();
                if (item.isEmpty()) {
                    Logs.send(sender, "请手持所要操作的物品");
                    return true;
                }

                ItemStack modified;

                try {
                    modified = switch (key) {
                        case "effect-proof" -> {
                            if ((flag && ItemManager.isEffectProof(item)) || (!flag && !ItemManager.isEffectProof(item)))
                                throw new IllegalStateException();
                            yield ItemManager.setEffectProof(item, flag);
                        }
                        case "digging-tool" -> {
                            if ((flag && ItemManager.isDiggingTool(item)) || (!flag && !ItemManager.isDiggingTool(item)))
                                throw new IllegalStateException();
                            yield ItemManager.setDiggingTool(item, flag);
                        }
                        default -> throw new IllegalArgumentException();
                    };
                } catch (IllegalArgumentException e) {
                    Logs.send(sender, "无效的物品属性：" + key);
                    return true;
                } catch (IllegalStateException e) {
                    Logs.send(sender, "无效的设置操作：请勿重复设置为同一状态");
                    return true;
                }

                player.getInventory().setItemInMainHand(modified);
                Logs.send(sender, "成功：已设置手持物品的 " + key + " 属性为 " + flag);
                return true;
            }
        }

        return true;
    }
}
