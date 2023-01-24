package dk.nprison.nprsioncore.core.listeners;

import dk.nprison.nprsioncore.PrisonCore;
import dk.nprison.nprsioncore.api.utils.ColorUtils;
import dk.nprison.nprsioncore.core.config.ConfigUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    PrisonCore plugin;
    ConfigUtils loader;
    public PlayerListener(PrisonCore plugin, ConfigUtils loader) {
        this.plugin = plugin;
        this.loader = loader;
    }

    /**
     * @param event The event that was fired.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ColorUtils.getColored(loader.getJoinMessage().replace("%player%", event.getPlayer().getName())));
    }

    /**
     * @param event The event that was fired.
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        event.setQuitMessage(
                ColorUtils.getColored(loader.getLeaveMessage().replace("%player%", event.getPlayer().getName()))
        );

    }

}
