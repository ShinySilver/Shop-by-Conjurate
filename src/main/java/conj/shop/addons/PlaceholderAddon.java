package conj.shop.addons;

import conj.shop.Initiate;
import conj.shop.commands.control.Manager;
import conj.shop.data.enums.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class PlaceholderAddon {
    public static List<String> placehold(final Player player, List<String> messages) {
        messages = (List<String>) PlaceholderAPI.setPlaceholders(player, (List) messages);
        return messages;
    }

    public static String placehold(final Player player, String message) {
        message = PlaceholderAPI.setPlaceholders(player, message);
        return message;
    }

    public static void register(final Plugin plugin) {
        final boolean hooked = PlaceholderAPI.registerPlaceholderHook(plugin, new PlaceholderHook() {
            public String onPlaceholderRequest(final Player player, final String identify) {
                if (identify.equals("previous")) {
                    return String.valueOf(new Manager().getPreviousPage(player));
                }
                if (identify.equals("current")) {
                    return String.valueOf(new Manager().getOpenPage(player));
                }
                if (identify.equals("main")) {
                    return String.valueOf(Config.MAIN_PAGE.toString());
                }
                return null;
            }
        });
        if (hooked) {
            plugin.getLogger().info("Successfully hooked into PlaceholderAPI.");
            Initiate.placeholderapi = true;
        }
    }
}
