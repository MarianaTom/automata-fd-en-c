package intento2;

import java.util.ArrayList;
import java.util.Scanner;
public class AutomataV2 {

    int matrizTrans[][];
    String cadena;
    ArrayList<Integer> valoresM = new ArrayList<>();
    String[] simbolos;
    ArrayList<Integer> estadosF = new ArrayList<>();
    private int edoini;
    

    public void crearMatriz(int m, int n) {
        matrizTrans = new int[m][n];
    }

    public void llenarVector(int valor) {
        valoresM.add(valor);
    }

    public void llenarEstadosF(int valor) {
        estadosF.add(valor);
    }

    public void llenarSimbolos(String[] valores) {
        simbolos = valores;
    }

    private void llenarMatriz() {
        int i = 0;
        int j = 0;
        //convertir el ArrayList a una matriz de transicion[m][n]
        for (int v : valoresM) {
            if (j == matrizTrans[i].length) {
                i++;
                j = 0;
            }
            matrizTrans[i][j] = v;
            j++;
        }
    }

    public void imprimirMatriz() {
        llenarMatriz();
        System.out.println("matriz de transicion");
        for (int[] matrizTran : matrizTrans) {
            for (int k : matrizTran) {
                System.out.print(k + ",");
            }
            System.out.println("");
        }

    }

    public void leerCadena() {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresa la cadena a evaluar");
        cadena = teclado.nextLine();
        procesarCadena(cadena);
    }

    private void procesarCadena(String cadena) {
        //para validar la palabra
        boolean flag = false;
        //posiciones de la amtriz
        int m = 0;
        int n = 0;

        //convertir los simbolos en letras para comparar la cadena
        char[] letras = new char[simbolos.length];
        for (int i = 0; i < simbolos.length; i++) {
            letras[i] = simbolos[i].charAt(0);
        }
        
        System.out.print("("+m+","+cadena+")");
        
        //comenzar a validar cada letra de la palabra
        for (int i = 0; i < cadena.length(); i++) {
            flag = false;
            for (int j = 0; j < letras.length; j++) {
             if (cadena.charAt(i) == letras[j]) {
                    //leer la posicion de la letra
                    n = j;
                    m = (matrizTrans[m][n]) - 1;//como el arreglo comienza en 0 le restamos uno
                   // System.out.print("("+m+","+cadena+")");
                    System.out.print("("+(m + 1)+","+cadena.charAt(i)+")|- ");
//                    System.out.println("con letra " + cadena.charAt(i) + " paso al estado->" + (m + 1));
                    flag = true;
                }

            }
            //si no encontro la letra en los simbolos la bandera nunca cambio a falso
            if (flag == false) {
                System.out.println(cadena.charAt(i) + " no esta en el alfabeto");
            }
        }
        //si llegamos al estado 5 y la bandera quedo en verdadero la palabra fue aceptada
        if ((m + 1) == 5 && flag == true) {
            System.out.println(" : Palabra aceptada");
        } else {
            if (flag == false) {
                System.out.println(" : Palabra no aceptada");
            }
            System.out.println(": Palabra no aceptada");
        }

    }
}
