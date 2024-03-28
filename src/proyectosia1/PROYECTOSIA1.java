package proyectosia1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import CSV.GestorCSVAlumnos;

/**
 *
 * @author Joaquin Fuenzalida,
 *         Sebastián Cruz,
 *         Maximiliano Bustamante
 */
public class PROYECTOSIA1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 13; i++)
        cargarAlumnosDesdeCSV()
        
        
        menu();
    }
    



    public static void menu() throws IOException {
        int opcion;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {   

            opcion = Integer.parseInt(lector.readLine());

            switch (opcion)
            {
                case 1:
                    //
                    break;
                case 2:
                    //
                    break;
                default:
                    break;
                
            }
            
            break;
        }
        
        
    }

        
        
}