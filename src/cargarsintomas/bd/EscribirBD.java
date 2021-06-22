package cargarsintomas.bd;

import java.util.List;

import static cargarsintomas.extra.OperacionSimple.obtExt;

public class EscribirBD {

    private EscribirCSV escrSV;

    public EscribirBD(String ruta, boolean append) {
        escrSV = new EscribirCSV(ruta, append);
        //escrJASON = new EscribirJASON(ruta, append);
    }

    public void escribir (String rArchivo, List<List<String>> datos) {
        switch (obtExt(rArchivo)) {
            case "csv" -> escrSV.escribir(datos);
            //case "json" -> escrJASON.escribir(datos);
            //case "dat" -> escrDAT.escribir(rArchivo);
        }
    }
}