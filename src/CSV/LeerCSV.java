package CSV;
import java.io.*;
import java.util.Scanner;
public class LeerCSV  {
    public void leerCSV() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/CSV/ejemplocsv.csv"));
        String value;
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            value = sc.next();
            System.out.print(value + " ");
        }
        sc.close();

    }
}
