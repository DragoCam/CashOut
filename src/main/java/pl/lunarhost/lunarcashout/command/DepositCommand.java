/*
 * Copyright 2024 Klaudiusz Wojtyczka <drago.klaudiusz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.lunarhost.lunarcashout.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.lunarhost.lunarcashout.LunarCashOutPlugin;

public class DepositCommand implements CommandExecutor {

    /*
     * The plugin instance
     */
    private LunarCashOutPlugin plugin;

    /**
     * Creates the "/deposit" command handler
     */
    public DepositCommand(LunarCashOutPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can deposit bank notes");
        } else if (!sender.hasPermission("banknotes.deposit")) {
            sender.sendMessage(plugin.getMessage("messages.insufficient-permissions"));
        } else {
            Player player = (Player) sender;
            ItemStack item = player.getItemInHand();

            if (item != null && plugin.isBanknote(item)) {
                double amount = plugin.getBanknoteAmount(item);

                if (Double.compare(amount, 0) > 0) {
                    // Double check the response
                    plugin.getEconomy().depositPlayer(player, amount);
                    player.sendMessage(plugin.getMessage("messages.note-redeemed")
                            .replace("[money]", plugin.formatDouble(amount)));
                } else {
                    player.sendMessage(plugin.getMessage("messages.invalid-note"));
                }

                // Remove the slip
                if (item.getAmount() <= 1) {
                    player.getInventory().removeItem(item);
                } else {
                    item.setAmount(item.getAmount() - 1);
                }
            } else {
                player.sendMessage(plugin.getMessage("messages.nothing-in-hand"));
            }
        }
        return true;
    }
}
