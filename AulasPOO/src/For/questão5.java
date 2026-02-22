package For;

public class questão5 {
    // Programa que imprime os números primos até 100
    public static void main(String[] args) {
        System.out.println("Números primos de 1 a 100:");
        for (int i = 2; i <= 100; i++) { // Itera de 2 a 100
            if (isPrimo(i)) { // Verifica se o número é primo
                System.out.print(i + " ");
            }
        }
    }

    // Método para verificar se um número é primo
    public static boolean isPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) { // Testa divisores até a raiz quadrada
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
