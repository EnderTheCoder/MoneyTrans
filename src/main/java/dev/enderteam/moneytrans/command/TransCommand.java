package dev.enderteam.moneytrans.command;

import dev.enderteam.moneytrans.MoneyTrans;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class TransCommand implements CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OfflinePlayer[] players = Bukkit.getOfflinePlayers();
        for (OfflinePlayer player : players) {
            double money = MoneyTrans.ECON.getBalance(player);
            sender.sendMessage(String.format("玩家金钱：%s. 转换为点券后: %s", money, ((int)(money / 100000))));
            Player admin = (Player) sender;
            admin.performCommand("points give " + player.getName() + " " + ((int)(money / 100000)));
            MoneyTrans.ECON.withdrawPlayer(player, money - 1);
        }
        return true;
    }
}
