/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.bingo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDRESCOGI
 */
public class servidor {

    private static final int PUERTO = 12111;
    static int[][] bingo = new int[5][5];

    public static void main(String[] args) {
        ServerSocket servidor;
        Socket conexion;
        DataInputStream entradaDatos;
        DataOutputStream salidaDatos;
        String entrada;
        String salida;
        int valor;
        boolean swF = false;
        boolean swC = false;
        Scanner teclado = new Scanner(System.in);
        llenarBingo();
        boolean sw = false;
        mostrarBingo();
        try {
            servidor = new ServerSocket(PUERTO);
            while (sw == false) {
                conexion = servidor.accept();
                entradaDatos = new DataInputStream(conexion.getInputStream());
                entrada = entradaDatos.readUTF();
                System.out.println("Cliente>>" + entrada);
                valor = Integer.parseInt(entrada);
                validarNumero(valor);
                mostrarBingo();
                swF = validarFila();
                swC = validarColumna();
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                if (swF == true || swC == true) {
                    salida = "Felicitaciones has ganado";
                    salidaDatos.writeUTF(salida);
                    sw = true;
                } else {
                    salida = "ok, Siguiente numero";
                    salidaDatos.writeUTF(salida);
                }
                conexion.close();
                //logica
            }
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void llenarBingo() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = ThreadLocalRandom.current().nextInt(1, 15 + 1);
            for (int x = 0; x < 5; x++) {
                if (bingo[x][0] == valor) {
                    valor = ThreadLocalRandom.current().nextInt(1, 15 + 1);
                    x = -1;
                }
            }
            bingo[i][0] = valor;
        }
        //**********columna 2 **********************
        valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = ThreadLocalRandom.current().nextInt(16, 30 + 1);
            for (int x = 0; x < 5; x++) {
                if (bingo[x][1] == valor) {
                    valor = ThreadLocalRandom.current().nextInt(16, 30 + 1);
                    x = -1;
                }
            }
            bingo[i][1] = valor;
        }
        //**********columna 3 **********************
        valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = ThreadLocalRandom.current().nextInt(31, 45 + 1);
            for (int x = 0; x < 5; x++) {
                if (bingo[x][2] == valor) {
                    valor = ThreadLocalRandom.current().nextInt(31, 45 + 1);
                    x = -1;
                }
            }
            bingo[i][2] = valor;
        }
        //**********columna 4 **********************
        valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = ThreadLocalRandom.current().nextInt(46, 60 + 1);
            for (int x = 0; x < 5; x++) {
                if (bingo[x][3] == valor) {
                    valor = ThreadLocalRandom.current().nextInt(46, 60 + 1);
                    x = -1;
                }
            }
            bingo[i][3] = valor;
        }
        //**********columna 5 **********************
        valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = ThreadLocalRandom.current().nextInt(61, 75 + 1);
            for (int x = 0; x < 5; x++) {
                if (bingo[x][4] == valor) {
                    valor = ThreadLocalRandom.current().nextInt(61, 75 + 1);
                    x = -1;
                }
            }
            bingo[i][4] = valor;
        }

        bingo[2][2] = 0;
    }

    public static void mostrarBingo() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("|" + bingo[i][j] + "|" + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    public static void validarNumero(int valor) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] == valor) {
                    bingo[i][j] = 0;
                }
            }
        }
    }

    public static boolean validarFila() {
        boolean sw = false;
        int ceros = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] == 0) {
                    ceros++;
                }

            }
            if (ceros == 5) {
                sw = true;

            }
            ceros = 0;
        }
        return sw;
    }

    public static boolean validarColumna() {
        boolean sw = false;
        int ceros = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[j][i] == 0) {
                    ceros++;
                }

            }
            if (ceros == 5) {
                sw = true;

            }
            ceros = 0;
        }
        return sw;
    }

}
