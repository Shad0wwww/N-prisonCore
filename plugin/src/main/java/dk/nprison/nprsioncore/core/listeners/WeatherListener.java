package dk.nprison.nprsioncore.core.listeners;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import dk.nprison.nprsioncore.PrisonCore;
import dk.nprison.nprsioncore.core.config.ConfigUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {
    private final boolean allowWeatherChanges;
    PrisonCore plugin;
    public WeatherListener(PrisonCore plugin, ConfigUtils loader) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        this.allowWeatherChanges = loader.AllowWeatherChanges();
    }

    /**
     * Disable changing weather.
     *
     * @param event {@link WeatherChangeEvent}
     */
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (!allowWeatherChanges) {
            event.setCancelled(true);
        }
    }

}
