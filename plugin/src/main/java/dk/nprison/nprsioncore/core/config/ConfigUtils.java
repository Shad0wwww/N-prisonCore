package dk.nprison.nprsioncore.core.config;

import dk.nprison.nprsioncore.PrisonCore;

public class ConfigUtils {
    private final PrisonCore plugin;




    public ConfigUtils(PrisonCore plugin) {
        this.plugin = plugin;
    }

    public String getJoinMessage() {
        return String.valueOf(plugin.getConfig().get("server-messages.join"));
    }

    public String getLeaveMessage() {
        return (String) plugin.getConfig().get("server-messages.leave");
    }

    public Boolean AllowWeatherChanges() {
        return (Boolean) plugin.getConfig().get("canceller-toggling.allow-weather-changes");
    }


}
