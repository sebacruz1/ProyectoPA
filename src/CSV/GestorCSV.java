package CSV;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import app.Curso;

public class GestorCSV {

    public void guardarAsistenciasEnCSV(Curso curso, String rutaArchivo) {
        try (FileWriter csvWriter = new FileWriter(rutaArchivo)) {
            csvWriter.append("Fecha,Id Alumno,Nombre Alumno,Asistencia\n");
            
            for (Map.Entry<LocalDate, RegistroAsistencia> entrada : curso.getAsistenciasPorFecha().entrySet()) {
                LocalDate fecha = entrada.getKey();
                RegistroAsistencia registro = entrada.getValue();
                
                for (Map.Entry<Alumno, Boolean> asistencia : registro.getAsistencias().entrySet()) {
                    Alumno alumno = asistencia.getKey();
                    Boolean asistio = asistencia.getValue();

                    csvWriter.append(fecha.toString())
                             .append(",")
                             .append(alumno.getId())
                             .append(",")
                             .append(alumno.getNombre())
                             .append(",")
                             .append(asistio ? "Presente" : "Ausente")
                             .append("\n");
                }
            }

            System.out.println("CSV generado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo CSV: " + e.getMessage());
        }
    }
}
