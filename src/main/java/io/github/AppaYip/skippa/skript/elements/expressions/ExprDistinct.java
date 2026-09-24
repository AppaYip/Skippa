package io.github.AppaYip.skippa.skript.elements.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Example;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jspecify.annotations.Nullable;
import org.skriptlang.skript.registration.SyntaxInfo;
import org.skriptlang.skript.registration.SyntaxRegistry;

import java.util.Arrays;

@Name("Distinct")
@Description({
        "Returns the values of an expression with duplicate values removed.",
        "This does not modify the original list."
})
@Example("""
       on script load:
           set {_t::*} to (all integers between 0 and 5)
           add 5 to {_t::*}
           broadcast {_t::*} without duplicates # 0,1,2,3,4,5
       """)
@Since("1.0")
public class ExprDistinct extends SimpleExpression<Object> {

    public static void register(SyntaxRegistry registry) {
        registry.register(SyntaxRegistry.EXPRESSION, SyntaxInfo.Expression.builder(ExprDistinct.class, Object.class)
                .supplier(ExprDistinct::new)
                .addPatterns(
                        "%objects% without duplicate[s] [values]",
                        "[the] unique values of %objects%"
                )
                .build());
    }


    private Expression<?> objects;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        Expression<Object> expression = LiteralUtils.defendExpression(expressions[0]);

        if (LiteralUtils.hasUnparsedLiteral(expression)) {
            return false;
        }

        this.objects = expression;
        return true;
    }


    @Override
    protected Object @Nullable [] get(Event event) {
        return Arrays.stream(objects.getArray(event))
                .distinct()
                .toArray();
    }

    @Override
    public boolean isSingle() {
        return this.objects.isSingle();
    }


    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "unique values of " + objects.toString(event, debug);
    }


    @Override
    public Class<?> getReturnType() {
        return Object.class;
    }
}
