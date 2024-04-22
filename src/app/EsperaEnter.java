package app;

import java.util.Scanner;

public class EsperaEnter {

    public void esperarPorEnter() {
        System.out.println("Presiona Enter para continuar...");

        Scanner scanner = new Scanner(System.in);

        // Espera hasta que el usuario presione Enter
        scanner.nextLine();

    }
}
