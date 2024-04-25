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

            // Cria um StringTokenizer para dividir a expressao em tokens usando espaços como delimitadores padrão
            StringTokenizer tokenizer = new StringTokenizer(expressao);

            // Cria um array para armazenar os tokens
            String[] tokens = new String[tokenizer.countTokens()]; // Tamanho do array é o número de tokens

            // Preenche o array com os tokens obtidos do StringTokenizer
            int index = 0;
            while (tokenizer.hasMoreTokens()) {
                tokens[index++] = tokenizer.nextToken(); // Obter próximo token e armazená-lo no array
            }

            // Verifica se o número de tokens é diferente de 3
            if (tokens.length != 3) {
                // Lança uma exceção IllegalArgumentException se a expressao não tiver o formato esperado
                throw new IllegalArgumentException("Expressao invalida. Deve ser no formato: 'valor1 operador valor2'");
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