package For;

import java.util.Scanner;

public class questão4 { // Nome da classe corrigido para seguir convenções de Java
    public static void main(String[] args) { // Adicionado o método main
        // Cria o scanner para leitura de entrada
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário o tamanho do quadrado
        System.out.print("Digite o tamanho do quadrado (n): ");
        int n = scanner.nextInt();

        // Gera o quadrado de asteriscos
        for (int i = 0; i < n; i++) { // Controla as linhas
            for (int j = 0; j < n; j++) { // Controla as colunas
                System.out.print("* ");
            }
            System.out.println(); // Quebra de linha após cada linha de asteriscos
        }

        scanner.close(); // Fecha o scanner
    }
}
