package app;

import java.util.Scanner;

public class EsperaEnter {

    // Método que espera a que el usuario presione Enter
    public void esperarPorEnter() {
        System.out.println("Presiona Enter para continuar...");

        Scanner scanner = new Scanner(System.in);
        
        // Espera hasta que el usuario presione Enter
        scanner.nextLine();
        

        // No es necesario cerrar el Scanner si está vinculado a System.in en un contexto simple
    }
}
