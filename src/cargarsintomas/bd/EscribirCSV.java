package cargarsintomas.bd;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import static cargarsintomas.extra.OperacionSimple.eliUltimo;

public class EscribirCSV {

    private final char SEPARADOR;
    private FileWriter fr;
    private PrintWriter pw;

    public EscribirCSV(String nRut, boolean append) {
        SEPARADOR = ';';
        try{
            fr = new FileWriter(nRut, append);
            pw = new PrintWriter(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escribir(List<List<String>> datos) {
        try{
            for(List<String> ndat : datos) {
                StringBuilder nDatos = new StringBuilder();
                for(String nd : ndat) {
                    nDatos.append(nd).append(SEPARADOR);
                }
                pw.println(eliUltimo(nDatos.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

}
