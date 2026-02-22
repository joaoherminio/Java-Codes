package For;
import java.util.Scanner;

public class questão3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		//Usuário imputa um n°
		System.out.println("Por favor escolha um número");
		int n = scanner.nextInt();
		
		//Fatorial em si
		long fatorial = 1;
		for(int i = 1; i <= n; i++) {
			fatorial *= i;
		}
		
		//Resultado
		System.out.println("O fatorial de " + n + " é " + fatorial);
		
		scanner.close();

	}

}
