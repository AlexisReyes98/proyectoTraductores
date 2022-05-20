import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visitador  extends RuBaseVisitor<Value>{
    public String salidaConsola = "";

    private Map<String, Value> memory = new HashMap<String, Value>();
    public static final double SMALL_VALUE = 0.00000000001;

    @Override public Value visitPrograma(RuParser.ProgramaContext ctx) { return visitChildren(ctx); }

    @Override public Value visitBloque(RuParser.BloqueContext ctx) { return visitChildren(ctx); }

    @Override public Value visitSentencia(RuParser.SentenciaContext ctx) { return visitChildren(ctx); }

    @Override public Value visitAsignacion(RuParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        Value value = this.visit(ctx.expr());
        return memory.put(id, value);
    }

    @Override public Value visitSentencia_if(RuParser.Sentencia_ifContext ctx) {
        List<RuParser.Bloque_condicionalContext> condiciones = ctx.bloque_condicional();

        boolean bloque = false;

        for (RuParser.Bloque_condicionalContext condicion : condiciones){
            Value eval = this.visit(condicion.expr());

            if (eval.asBoolean()){
                bloque = true;
                this.visit(condicion.bloque_de_sentencia());
                break;
            }
        }

        if (!bloque && ctx.bloque_de_sentencia() != null){
            this.visit(ctx.bloque_de_sentencia());
        }

        return Value.VOID;
    }

    @Override public Value visitBloque_condicional(RuParser.Bloque_condicionalContext ctx) { return visitChildren(ctx); }

    @Override public Value visitBloque_de_sentencia(RuParser.Bloque_de_sentenciaContext ctx) { return visitChildren(ctx); }

    @Override public Value visitSentencia_while(RuParser.Sentencia_whileContext ctx) {
        Value value = this.visit(ctx.expr());
        while (value.asBoolean()){
            this.visit(ctx.bloque_de_sentencia());
            value = this.visit(ctx.expr());
        }
        return Value.VOID;
    }

    @Override public Value visitLog(RuParser.LogContext ctx) {
        Value value = this.visit(ctx.expr());
        System.out.println(value);
        salidaConsola += value.asString() + "\n";
        return value;
    }

    @Override public Value visitImprimir(RuParser.ImprimirContext ctx) {
        Value value = this.visit(ctx.expr());
        System.out.println(value);
        return value;
    }

    @Override public Value visitMenosUnarioExpr(RuParser.MenosUnarioExprContext ctx) {
        Value value = this.visit(ctx.expr());
        return new Value(-value.asDouble());
    }

    @Override public Value visitNotExpr(RuParser.NotExprContext ctx) {
        Value value = this.visit(ctx.expr());
        return new Value(!value.asBoolean());
    }

    @Override public Value visitMultiplicacionExpr(RuParser.MultiplicacionExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        switch (ctx.op.getType()){
            case RuParser.MULT:
                return new Value(left.asDouble() * right.asDouble());
            case RuParser.DIV:
                return new Value(left.asDouble() / right.asDouble());
            case RuParser.MOD:
                return new Value(left.asDouble() % right.asDouble());
            default:
                throw new RuntimeException("No se identifica el operador: " + RuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override public Value visitAtomExpr(RuParser.AtomExprContext ctx) { return visitChildren(ctx); }

    @Override public Value visitOrExpr(RuParser.OrExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(left.asBoolean() || right.asBoolean());
    }

    @Override public Value visitPowExpr(RuParser.PowExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(Math.pow(left.asDouble(), right.asDouble()));
    }

    @Override public Value visitIgualdadExpr(RuParser.IgualdadExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));

        switch (ctx.op.getType()){
            case RuParser.IGUAL:
                return left.isDouble() && right.isDouble() ?
                        new Value(Math.abs(left.asDouble() - right.asDouble()) < SMALL_VALUE) :
                        new Value(left.equals(right));
            case RuParser.DIFQ:
                return left.isDouble() && right.isDouble() ?
                        new Value(Math.abs(left.asDouble() - right.asDouble()) >= SMALL_VALUE) :
                        new Value(!left.equals(right));
            default:
                throw new RuntimeException("No se identifica el operador" + RuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override public Value visitRelacionalExpr(RuParser.RelacionalExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        switch (ctx.op.getType()){
            case RuParser.MENORQ:
                return new Value(left.asDouble() < right.asDouble());
            case RuParser.MENIG:
                return new Value(left.asDouble() <= right.asDouble());
            case RuParser.MAYORQ:
                return new Value(left.asDouble() > right.asDouble());
            case RuParser.MAYIG:
                return new Value(left.asDouble() >= right.asDouble());
            default:
                throw new RuntimeException("No se identifica el operador: " + RuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override public Value visitAditivaExpr(RuParser.AditivaExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        switch (ctx.op.getType()){
            case RuParser.MAS:
                return left.isDouble() && right.isDouble()?
                        new Value(left.asDouble() + right.asDouble()):
                        new Value(left.asString() + right.asString());
            case RuParser.MENOS:
                return new Value(left.asDouble() - right.asDouble());
            default:
                throw new RuntimeException("No se identifica el operador: " + RuParser.tokenNames[ctx.op.getType()]);
        }
    }

    @Override public Value visitAndExpr(RuParser.AndExprContext ctx) {
        Value left = this.visit(ctx.expr(0));
        Value right = this.visit(ctx.expr(1));
        return new Value(left.asBoolean() && right.asBoolean());
    }

    @Override public Value visitParExpr(RuParser.ParExprContext ctx) { return visitChildren(ctx); }

    @Override public Value visitNumberAtom(RuParser.NumberAtomContext ctx) {
        return new Value(Double.valueOf(ctx.getText()));
    }

    @Override public Value visitBooleanAtom(RuParser.BooleanAtomContext ctx) {
        return new Value(Boolean.valueOf(ctx.getText()));
    }

    @Override public Value visitIdAtom(RuParser.IdAtomContext ctx) {
        String id = ctx.getText();
        Value value = memory.get(id);
        if (value == null){
            throw new RuntimeException("ID ATOM - Error: " + id);
        }
        return value;
    }

    @Override public Value visitStringAtom(RuParser.StringAtomContext ctx) {
        String string = ctx.getText();
        // strip quotes
        string = string.substring(1, string.length() - 1).replace("\"\"", "\"");
        return new Value(string);
    }

    @Override public Value visitNilAtom(RuParser.NilAtomContext ctx) { return new Value(null);}
}