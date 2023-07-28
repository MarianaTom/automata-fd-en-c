/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package intento2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Mariana
 */
public class Intento2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear un objeto JFileChooser
        AutomataV2 automata = new AutomataV2();

        JFileChooser fileChooser = new JFileChooser();

        // Abrir el explorador de archivos
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado por el usuario
            File file = fileChooser.getSelectedFile();
            int m = 0, n = 0, nfin = 0, edoini, edotota;

  
            try {
                // Crear un lector de archivos
                FileReader fileReader = new FileReader(file);
                try ( BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    String line;
                    // Leer el archivo línea por línea
                    int contLinea = 0;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                        //La primera linea es de los estados
                        if (contLinea == 0) {
                            char c = line.charAt(line.length() - 1);
                            m = Integer.parseInt(Character.toString(c));
                            System.out.println(m);
                        }
                        //La segunda linea es el numero de simbolos
                        if (contLinea == 1) {
                            char c = line.charAt(line.length() - 1);
                            n = Integer.parseInt(Character.toString(c));
                            System.out.println(n);
                        }
                        automata.crearMatriz(m, n);
                        
                        //La tercera linea son los simbolos 
                        if (contLinea == 2) {
                            automata.llenarSimbolos(line.split(","));
                        }

                        //contar m lineas para las transiciones
                        if (contLinea > 2 && contLinea <= (m + 2)) {
                            String estadosTrans[] = line.split(",");
                            int auxMat[] = new int[estadosTrans.length];
                            for (int i = 0; i < estadosTrans.length; i++) {
                                auxMat[i] = Integer.parseInt(estadosTrans[i]);

                                automata.llenarVector(auxMat[i]);
                            }

                            System.out.println("");
                        }
                        //leer estado inicial
                        if (contLinea == 3 + m) {
                            char c = line.charAt(line.length() - 1);
                            edoini = Integer.parseInt(Character.toString(c));
                            System.out.println("Estado incial: "+ edoini);
                        }
                        if (contLinea == 4 + m) {
                            char c = line.charAt(line.length() - 1);
                            edotota = Integer.parseInt(Character.toString(c));
                            System.out.println("Total de estados finales: "+ edotota);
                        }
                        
                         if (contLinea == 5 + m ) {
                            char c = line.charAt(line.length() - 1);
                            nfin = Integer.parseInt(Character.toString(c));
                            System.out.println("Estado final:"+ nfin);
                        }
                         
                        //despues de conta, leer los estados finals
                        if (contLinea > (m + 2)) {
                            automata.llenarEstadosF(Integer.parseInt(line));
                        }
                        //contador de lienas
                        contLinea++;
                    }

                    // Cerrar el lector de archivos
                }
            } catch (IOException e) {
            }
            
            //acciones del automata
            //automata.imprimirSimbolos();
            automata.imprimirMatriz();
            //automata.imprimirEstadosF();
            automata.leerCadena();

        }

    }

}
