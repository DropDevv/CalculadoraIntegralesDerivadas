/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package derivada;

/**
 *
 * @author COCOL2
 */
public class MathEngine {

    /**
     * @param args the command line arguments
     */
    private final Differentiator differentiator;
    
    public MathEngine()
    {
        //iniciar el iferenciador
        this.differentiator = new Differentiator();
    }
    
    /**
     * Método para derivar una función respecto a una variable.
     * @param expression La función a derivar (por ejemplo "x^3 + sin(x)").
     * @param variable La variable respecto a la cual se deriva (x o y).
     * @return La derivada simbólica de la función.
     */
    
    public String derive(String expression, String variable)
    {
        if(expression ==null || expression.isEmpty())
        {
            return "!Error: no se ingresó ninguna función";
        }
        
        if(!variable.equals("x")&& !variable.equals("y"))
        {
            return "!Error: variable no válida. Use 'x' o 'y'.";
        }
        try
        {
            //usa la clase differentiator para derivar
            String result= differentiator.derive(expression, variable);
            return "d/d" + variable + "(" + expression + ") = "+ result; 
        }catch(Exception e)
        {
            return "!Error al derivar: " + e.getMessage();
        }
    }
    
    public String calculate(String type, String expression, String variable)
    {
        if(type.equalsIgnoreCase("derivada"))
        {
            return derive(expression, variable);
        }
        // en el futuro: else if para integral
        
        else
        {
            return "!Operación no soportada todavía.";
        }
    }
}
