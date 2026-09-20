package org.appa.skippa.skript.elements.effects;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Example;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.skriptlang.skript.registration.SyntaxInfo;
import org.skriptlang.skript.registration.SyntaxRegistry;

import java.util.Arrays;

@Name("Distinct")
@Description({"Removes the duplicate values out of a variable."})
@Example("""
        on script load:
            set {_t::*} to 1, 2, 2, 3, 3
            make {_t::*} distinct
            broadcast {_t::*} # 1,2,3
       """)
@Since("1.0")
public class EffDistinct extends Effect {

    public static void register(SyntaxRegistry registry) {
        registry.register(
                SyntaxRegistry.EFFECT,
                SyntaxInfo.builder(EffDistinct.class)
                    .supplier(EffDistinct::new)
                    .addPatterns(
                        "make %objects% distinct",
                        "make %objects% unique"
                    )
                    .build()
        );
    }

    private Expression<?> objects;


    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        objects = expressions[0];

        return objects.acceptChange(Changer.ChangeMode.SET) != null;
    }


    @Override
    protected void execute(Event event) {
        Object[] values = objects.getArray(event);

        Object[] distinct = Arrays.stream(values)
                .distinct()
                .toArray();

        objects.change(event, distinct, Changer.ChangeMode.SET);
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "make " + objects.toString(event, debug) + " distinct";
    }
}
