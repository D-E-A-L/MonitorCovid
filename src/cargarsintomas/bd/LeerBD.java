package cargarsintomas.bd;

import java.util.ArrayList;
import java.util.List;

import static cargarsintomas.bd.LeerJASON.leerJASON;
import static cargarsintomas.bd.LeerSCV.leerCSV;
import static cargarsintomas.extra.OperacionSimple.obtExt;

public class LeerBD{

    public LeerBD () {
    }

    public List<List<String>> Leer (String rArchivo) {
        List<List<String>> list = new ArrayList<>();
        switch (obtExt(rArchivo)) {
            case "csv" -> list = leerCSV(rArchivo);
            case "json" -> list = leerJASON();
        }
        return list;
    }
}