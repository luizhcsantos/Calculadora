package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

import compute.Compute;

public class computeCalc {

    public static void main(String args[]) {

        if (args.length < 3) {
            System.err.println("Uso: java client.computeCalc <host> <porta> <expressao>");
            System.exit(1);
        }

        try {
            String host = args[0];
            int porta = Integer.parseInt(args[1]);
            String expressao = args[2];

            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(host, porta);
            Compute comp = (Compute) registry.lookup(name);

            StringTokenizer tokenizer = new StringTokenizer(expressao); 

            String[] tokens = new String[tokenizer.countTokens()];
            int index = 0;
            while (tokenizer.hasMoreTokens()) {
                tokens[index++] = tokenizer.nextToken();
            }
            
            if (tokens.length != 3) {
                throw new IllegalArgumentException("Expressao invalida. Deve ser no formato: valor1 operador valor2");
            }

            Integer valor1 = Integer.parseInt(tokens[0]);
            String op = tokens[1]; 
            Integer valor2 = Integer.parseInt(tokens[2]); 

            Calc task = new Calc(valor1, op, valor2);
            Integer result = comp.executeTask(task);
            System.out.println("Resultado: " + result);
        } catch (Exception e) {
            System.err.println("ComputeCalc exception:");
            e.printStackTrace();
        } 
    }

    
}