import app.*;
import java.io.*;
import CSV.*;

public class Main {
    public static void main(String[] args) {
        LeerCSV lectorCSV = new LeerCSV();
        try {
            lectorCSV.leerCSV();
        } catch (Exception e) { System.out.println("error"); }

    }
}