package io.github.AppaYip.skippa;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.SyntaxElement;
import org.bukkit.plugin.java.JavaPlugin;
import org.skriptlang.skript.addon.AddonModule;
import org.skriptlang.skript.addon.SkriptAddon;
import org.skriptlang.skript.registration.SyntaxRegistry;
import org.skriptlang.skript.util.ClassLoader;

import java.lang.reflect.InvocationTargetException;

public final class Skippa extends JavaPlugin implements AddonModule {
    static Skippa plugin;

    public static Skippa getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        SkriptAddon addon = Skript.instance().registerAddon(Skippa.class, "Skippa");
        addon.localizer().setSourceDirectories("lang", null);
        addon.loadModules(this);

        getComponentLogger().info("Skippa has loaded successfully!");
    }


    @Override
    public void load(SkriptAddon addon) {
        ClassLoader.builder()
                .basePackage("io.github.AppaYip.skippa.skript.elements")
                .deep(true)
                .initialize(true)
                .forEachClass(clazz -> {
                    if (SyntaxElement.class.isAssignableFrom(clazz)) {
                        try {
                            clazz.getMethod("register", SyntaxRegistry.class).invoke(null, addon.syntaxRegistry());
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            getComponentLogger().error("Failed to load syntax class: ", e);
                        }
                    }
                })
                .build()
                .loadClasses(Skippa.class);
    }

    @Override
    public String name() {
        return "Skippa";
    }
}
