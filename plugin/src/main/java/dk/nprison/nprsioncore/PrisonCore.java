package dk.nprison.nprsioncore;


import dk.nprison.nprsioncore.api.enums.Hook;
import dk.nprison.nprsioncore.api.hooks.Actionbar;
import dk.nprison.nprsioncore.api.hooks.PlaceholderAPIHook;
import dk.nprison.nprsioncore.api.hooks.VaultHook;
import dk.nprison.nprsioncore.api.interfaces.IHook;
import dk.nprison.nprsioncore.core.config.ConfigUtils;
import dk.nprison.nprsioncore.core.config.ConfigWrapper;
import dk.nprison.nprsioncore.core.listeners.PlayerListener;
import dk.nprison.nprsioncore.core.listeners.WeatherListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class PrisonCore extends JavaPlugin {
    private static final HashMap<String, Plugin> DEPENDANTS = new HashMap<>();
    private static final HashMap<Hook, Boolean> HOOKS = new HashMap<>();
    private static PrisonCore instance;

    public static ConfigWrapper configYMLWrapper;
    public static FileConfiguration configYML;
    private static ConfigUtils loader;
    @Override
    public void onEnable() {
        instance = this;
        //loader = new Loader(this);


        Bukkit.getLogger().info("Loading dependant plugins.");
        for(Plugin dependant : getServer().getPluginManager().getPlugins()){
            PluginDescriptionFile pdf = dependant.getDescription();
            if(pdf.getDepend().contains(getName()) || pdf.getSoftDepend().contains(getName()))
                DEPENDANTS.put(dependant.getName(), dependant);
        }
        Bukkit.getLogger().info(String.format("Loaded dependants (%d): %s", DEPENDANTS.size(), DEPENDANTS.values()));

        Bukkit.getLogger().info("[PrisonCore] - Initialising hooks...");
        initialiseHooks();


        if(!(new File(getDataFolder(), "config.yml").exists())) {
            saveResource("config.yml", false);
        }
        configYMLWrapper = new ConfigWrapper(this, null, "config.yml");
        configYML = configYMLWrapper.getConfig();
        loader = new ConfigUtils(instance);

        registerListeners(loader);
    }

    @Override
    public void onDisable() {

    }


    private void registerListeners(ConfigUtils loader){
        Bukkit.getLogger().info("Registering listeners...");


        new PlayerListener(this, loader);
        new WeatherListener(this, loader);
    }

    private void initialiseHooks(){
        IHook[] hooks = new IHook[]{
                new VaultHook(),
                new PlaceholderAPIHook(),
                new Actionbar(),
        };
        for(IHook hook : hooks)
            HOOKS.put(hook.getEnum(), hook.init(this));
    }

    public static boolean isHookInitialised(Hook paramHook) {
        return HOOKS.getOrDefault(paramHook, false);
    }



    public static PrisonCore getInstance() {
        return instance;
    }
}
