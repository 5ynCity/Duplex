package me.syn.duplex.compat;

import org.bukkit.plugin.java.JavaPlugin;

public class InternalsInitializer {
    private final String version;
    private final JavaPlugin plugin;

    public InternalsInitializer(JavaPlugin plugin)
    {
        this.plugin = plugin;
        String packageName = plugin.getServer().getClass().getPackage().getName();
        this.version = packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public Internals newInternals()
    {
        try
        {
            final Class<?> handlerClass = Class.forName("me.syn.duplexanticheat.internals.versions." + version);

            if (Internals.class.isAssignableFrom(handlerClass))
            {
                return (Internals) handlerClass.getConstructor().newInstance();
            }
        }
        catch (Exception e)
        {
            if (e instanceof ClassNotFoundException) //from failure setting handlerClass
            {
                plugin.getLogger().severe("Server version not compatible. Stopping plugin...");
            }
            else //from failure getting or instantiating internals
            {
                plugin.getLogger().severe("Unknown Initialization Error. Stopping plugin...");
                e.printStackTrace();
            }
        }
        return null;
    }

}
