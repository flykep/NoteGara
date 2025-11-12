package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner keyboard = new Scanner(System.in);

            // 1️ Messaggio di benvenuto
            System.out.println(in.readLine());

            while (true) {
                System.out.print("Inserisci numero lista (1-3) oppure '!' per uscire: ");
                String lista = keyboard.nextLine();
             
                out.println(lista);
                if (lista.equals("!")) break;

                String risposta = in.readLine();
                System.out.println("SERVER: " + risposta);

                if (risposta.equals("OK")) {
                    System.out.print("Inserisci numero nominativo: ");
                    String nominativo = keyboard.nextLine();
                    out.println(nominativo);

                    String risposta2 = in.readLine();
                    System.out.println("SERVER: " + risposta2);

                    if (risposta2.equals("OK")) {
                        String nome = in.readLine();
                        System.out.println("Nominativo: " + nome);
                    }
                }
            }

            clientSocket.close();
            System.out.println("Connessione terminata.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
