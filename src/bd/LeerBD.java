package bd;

import java.util.ArrayList;
import java.util.List;

import static bd.LeerJASON.leerJASON;
import static bd.LeerSCV.leerCSV;
import static extra.OperacionSimple.obtExt;

public class LeerBD{

    public LeerBD () {
    }

    public List<List<String>> Leer (String rArchivo) {
        List<List<String>> list = new ArrayList<>();
        switch (obtExt(rArchivo)) {
            case "csv" -> list = leerCSV(rArchivo);
            case "json" -> list = leerJASON(rArchivo);
            //case "dat" -> leerDat(rArchivo);
        }
        return list;
    }
}