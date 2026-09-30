package io.github.AppaYip.skippa.skript.elements.functions;

import ch.njol.skript.lang.function.Functions;
import org.skriptlang.skript.addon.SkriptAddon;
import org.skriptlang.skript.common.function.DefaultFunction;

public class Test {
    public Test(SkriptAddon addon) {
        registerFunctions(addon);
    }

    public void registerFunctions(SkriptAddon addon) {
        Functions.register(
                DefaultFunction.<String>builder(
                        addon, "test", String.class
                )
                .parameter("a", String.class)
                .parameter("b", String.class)

                .build(args -> {
                    String a = args.get("a");
                    String b = args.get("b");

                    return a+b;
                })
        );
    }
}
