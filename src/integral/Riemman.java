/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integral;

import org.nfunk.jep.JEP;

/**
 *
 * @author ACER NITRO 5
 */

public class Riemman {

    public String metodoRiemman(String a, String b, String fx, String particion, String variable) {
        String mensaje;

        try {
            JEP objJEP = new JEP();
            objJEP.addStandardFunctions();   // Funciones matemáticas (sin, cos, log, etc.)
            objJEP.addStandardConstants();   // Constantes (e, pi)
            objJEP.setImplicitMul(true);     // Permite 2x → 2*x

            // --- Evaluar límites ---
            objJEP.parseExpression(a);
            double a1 = objJEP.getValue();

            objJEP.parseExpression(b);
            double b1 = objJEP.getValue();

            int n = Integer.parseInt(particion);
            double h = (b1 - a1) / n;
            double suma = 0.0;

            // --- Agregar la variable antes de parsear la función ---
            objJEP.addVariable(variable, 0); // Define la variable usada (x o y)

            // --- Parsear la expresión una vez ---
            objJEP.parseExpression(fx);

            for (int i = 0; i < n; i++) {
                double puntoMedio = a1 + (i + 0.5) * h;

                // Asignar nuevo valor a la variable
                objJEP.setVarValue(variable, puntoMedio);

                double valor = objJEP.getValue();
                suma += valor;
            }

            double resultado = h * suma;
            mensaje = Double.toString(resultado);

        } catch (Exception e) {
            mensaje = "Error de datos: " + e.getMessage();
        }

        return mensaje;
    }
}
