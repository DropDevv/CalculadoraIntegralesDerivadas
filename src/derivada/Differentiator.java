/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package derivada;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

/**
 * Clase para calcular derivadas simbólicas simples o complejas.
 * Primero intenta resolver derivadas simples manualmente,
 * y si no puede, usa Symja como respaldo.
 * 
 * @author COCOL2
 */
public class Differentiator {
    
    private final ExprEvaluator evaluator;

    public Differentiator() {
        this.evaluator = new ExprEvaluator();
    }

    /**
     * Deriva una expresión matemática con respecto a una variable.
     * @param expr Expresión (por ejemplo "x^3 + 2x^2 + 5")
     * @param variable Variable de derivación ("x" o "y")
     * @return Cadena con la derivada simbólica
     */
    public String derive(String expr, String variable) {
        expr = expr.replace(" ", "");

        try {
            // === PRIMER INTENTO: derivadas simples (hechas por ti) ===
            if (expr.matches("^[0-9]+$")) return "0";  // constante
            if (expr.equals(variable)) return "1";      // variable simple

            if (expr.matches("^" + variable + "\\^[0-9]+$")) {
                String[] parts = expr.split("\\^");
                int n = Integer.parseInt(parts[1]);
                return n + "*" + variable + "^" + (n - 1);
            }

            if (expr.matches("^[0-9]+" + variable + "\\^[0-9]+$")) {
                String[] parts = expr.split(variable + "\\^");
                int a = Integer.parseInt(parts[0]);
                int n = Integer.parseInt(parts[1]);
                return (a * n) + "*" + variable + "^" + (n - 1);
            }

            if (expr.matches("^[0-9]+" + variable + "$")) {
                String a = expr.replace(variable, "");
                return a;
            }

            if (expr.startsWith("sin(") && expr.endsWith(")")) {
                return "cos" + expr.substring(3);
            }

            if (expr.startsWith("cos(") && expr.endsWith(")")) {
                return "-sin" + expr.substring(3);
            }

            if (expr.equals("e^" + variable)) {
                return "e^" + variable;
            }

            //SEGUNDO INTENTO: derivadas compuestas usando Symja
            IExpr result = evaluator.eval("D(" + expr + ", " + variable + ")");
            return result.toString();

        } catch (Exception e) {
            return "!Error al derivar: " + e.getMessage();
        }
    }
}
