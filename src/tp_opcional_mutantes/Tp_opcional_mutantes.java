package tp_opcional_mutantes;

import java.util.Scanner;

public class Tp_opcional_mutantes {

    public static void main(String[] args) {
        String[] matriz = cargarMatriz();
        mostrarMatriz(matriz);
        
        if (isMutant(matriz)) {
            System.out.println("ES MUTANTE");
        } else {
            System.out.println("NO ES MUTANTE");
        }
    }

    //Muestra la matirz ingresada por el usuario
    public static void mostrarMatriz(String matriz[]) {
        System.out.println("------------------MATRIZ INGRESADA------------------");
        for (String string : matriz) {
            for (int i = 0; i < string.length(); i++) {
                System.out.print(string.charAt(i) + " ");
            }
            System.out.println("");
        }
    }

    //Pide el ingreso de las 6 filas y retorna un array
    public static String[] cargarMatriz() {
        Scanner leer = new Scanner(System.in);

        String[] array = new String[6];
        System.out.println("Ingresa los valores para cada fila");
        System.out.println("Tiene que ser en el formato ABCDEF donde cada letra puede ser A,T,C o G");
        String valorFila = "";
        for (int i = 0; i < 6; i++) {
            System.out.println("-------Ingresa el valor " + (i + 1) + "-----------");
            valorFila = corroborarFila();
            array[i] = valorFila;
        }
        return array;
    }

    //Es llamada de la funcion que pide las filas, esta funcion se encarga de corroborar que se ingresen los caracteres y cantidad correcta
    public static String corroborarFila() {

        Scanner leer = new Scanner(System.in);
        boolean repetir = false;
        String fila;

        do {
            System.out.print("-> ");
            fila = leer.nextLine().toLowerCase();
            if (fila.length() != 6) {
                System.out.println("Ingrese un valor valido");
            }
        } while (fila.length() != 6);

        for (int i = 0; i < fila.length(); i++) {

            char caracter = fila.charAt(i);
            if (caracter != 'a' && caracter != 't' && caracter != 'c' && caracter != 'g') {
                repetir = true;
                break;
            }
        }
        if (repetir) {
            System.out.println("Alguno de los caracteres de la cadena es invalido\nIngrese uno valido");
            return corroborarFila();
        } else {
            return fila;
        }
    }

    //Esta se va a encaragar de retornar verdadero si se encuentra mas de una secuencia de 4 lineas
    public static boolean isMutant(String[] dna) {
        int filas = dna.length;
        int columnas = dna[0].length();
        int contadorSecuencias = 0;

        // Verificar horizontalmente
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j <= columnas - 4; j++) {
                char caracterActual = dna[i].charAt(j);
                if (dna[i].charAt(j + 1) == caracterActual && dna[i].charAt(j + 2) == caracterActual && dna[i].charAt(j + 3) == caracterActual) {
                    contadorSecuencias++;
                }
            }
        }

        // Verificar verticalmente
        for (int i = 0; i <= filas - 4; i++) {
            for (int j = 0; j < columnas; j++) {
                char caracterActual = dna[i].charAt(j);
                if (dna[i + 1].charAt(j) == caracterActual && dna[i + 2].charAt(j) == caracterActual && dna[i + 3].charAt(j) == caracterActual) {
                    contadorSecuencias++;
                }
            }
        }

        // Verificar diagonalmente (de izquierda a derecha y de derecha a izquierda)
        for (int i = 0; i <= filas - 4; i++) {
            for (int j = 0; j <= columnas - 4; j++) {
                char caracterActual = dna[i].charAt(j);
                if (dna[i + 1].charAt(j + 1) == caracterActual && dna[i + 2].charAt(j + 2) == caracterActual && dna[i + 3].charAt(j + 3) == caracterActual) {
                    contadorSecuencias++;
                }
                if (dna[i + 3].charAt(j) == caracterActual && dna[i + 2].charAt(j + 1) == caracterActual && dna[i + 1].charAt(j + 2) == caracterActual && dna[i].charAt(j + 3) == caracterActual) {
                    contadorSecuencias++;
                }
            }
        }

        return contadorSecuencias > 1;
    }
}
