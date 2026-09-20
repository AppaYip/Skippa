package org.appa.skippa.skript.elements.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Example;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import jdk.jfr.Name;
import org.appa.skippa.Skippa;
import org.bukkit.event.Event;
import org.jspecify.annotations.Nullable;
import org.skriptlang.skript.registration.SyntaxInfo;
import org.skriptlang.skript.registration.SyntaxRegistry;

@Name("Distinct")
@Description({
        "Removes all duplicates from a list of objects."
})
@Example("""
        on script load:
            set {_t::*} to (all integers between 0 and 5)
            add 5 to {_t::*}
           \s
            broadcast {_t::*} # 0,1,2,3,4,5,5
           \s
            make {_t::*} distinct
            broadcast {_t::*} # 0,1,2,3,4,5
       """)
@Since("1.0")
public class EffDistinct extends Effect {

    public static void register(SyntaxRegistry registry) {
        registry.register(SyntaxRegistry.EFFECT, SyntaxInfo.builder(EffDistinct.class)
                .supplier(EffDistinct::new)
                .addPatterns(
                        "make %objects% distinct",
                        "remove [all ][the ]duplicates[ from| out of] %objects%"
                )
                .build());
    }

    private Expression<Object> objects;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        
        return true;
    }


    @Override
    protected void execute(Event event) {
        Skippa.getInstance().getComponentLogger().info("I hate this so much.");
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Fuck you.";
    }
}
