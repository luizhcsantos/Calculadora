package client;

import java.io.Serializable;

import compute.Task;

public class Calc implements Task<Integer>, Serializable {

    private static final long serialVersionUID = 227L;

    private final int valor1, valor2; 
    private final String op; 

    public Calc(Integer valor1, String op, Integer valor2) {
        this.valor1 = valor1;
        this.op = op;
        this.valor2 = valor2;
    }

    @Override
    public Integer execute() {
        return computeCal(valor1, op, valor2); 
    }

    public static Integer computeCal(Integer valor1, String op, Integer valor2) {
        int resultado = 0; 

        switch (op) {
            case "+":
                resultado = valor1+valor2; 
                break;
            case "-":
                resultado = valor1-valor2; 
                break;
            case "/":      
                resultado = valor1/valor2; 
                break;
            case "*":
                resultado = valor1*valor2;   
                break;
            default:
                break;
        }
        return resultado;  
    }

    
    
}
