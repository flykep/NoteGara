package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerThread extends Thread {
    private Socket socket;

    // Liste di nominativi
    private static final ArrayList<String> lista1 = new ArrayList<>(Arrays.asList(
            "Marco Rossi", "Ivan Bruno", "Giulia Neri", "Luca Bianchi", "Sara Galli"));

    private static final ArrayList<String> lista2 = new ArrayList<>(Arrays.asList(
            "Ciccio Bello", "Francesca Pini", "Giorgio Verdi", "Marta Lodi", "Claudio Benvenuti", "Pippo Baudo"));

    private static final ArrayList<String> lista3 = new ArrayList<>(Arrays.asList(
            "Anna Rosa", "Paolo Conti", "Davide Leone", "Chiara Valli", "Elisa Greco"));

    private static final ArrayList<ArrayList<String>> liste = new ArrayList<>(
            Arrays.asList(lista1, lista2, lista3));

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 1️⃣ Messaggio di benvenuto
            out.println("ASER Server v1.2");

            while (true) {
                // 2️⃣ Riceve la richiesta della lista
                String richiestaLista = in.readLine();
                if (richiestaLista == null || richiestaLista.equals("!")) {
                    break; // termina la connessione
                }

                int indiceLista;
                indiceLista = Integer.parseInt(richiestaLista);

                if (indiceLista >= 1 && indiceLista <= liste.size()) {
                    out.println("OK");
                    String richiestaNominativo = in.readLine();

                  int indiceNominativo = Integer.parseInt(richiestaNominativo);

                    ArrayList<String> listaScelta = liste.get(indiceLista - 1);

                    if (indiceNominativo >= 1 && indiceNominativo <= listaScelta.size()) {
                        out.println("OK");
                        out.println(listaScelta.get(indiceNominativo - 1));
                    } else {
                        out.println("KO");
                    }
                } else {
                    out.println("KO");
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
