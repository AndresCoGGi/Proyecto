/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.bingo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDRESCOGI
 */
public class cliente {

    private static final int PUERTO = 12111;
    private static final String IP = "localhost";

    public static void main(String[] args) {
        Socket conexion;
        DataInputStream entradaDatos;
        DataOutputStream salidaDatos;
        String aleatorio;
        Scanner teclado = new Scanner(System.in);
        boolean sw = false;
        try {
            System.out.println("Iniciando cliente....");
            while (true) {

                conexion = new Socket(IP, PUERTO);
                //logica
                aleatorio = String.valueOf(ThreadLocalRandom.current().nextInt(1, 75 + 1));
                salidaDatos = new DataOutputStream(conexion.getOutputStream());
                System.out.println(aleatorio);
                salidaDatos.writeUTF(aleatorio);
                entradaDatos = new DataInputStream(conexion.getInputStream());
                System.out.print("Server>> " + entradaDatos.readUTF() + "\n");
                conexion.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
