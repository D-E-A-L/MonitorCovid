package cargarsintomas.guisintomas;

import monitor.Sintoma;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;

import static cargarsintomas.extra.OperacionMedia.actDobleListDatos;
import static cargarsintomas.extra.OperacionSimple.obtExt;

public class OperacionSintomas {

    public OperacionSintomas() {
    }

    public List<List<String>> aDobleListaString(Sintomas sintomas) {
        List<List<String>> dList = new ArrayList<>();
        for(Sintoma sintoma: sintomas) {
            List<String> nList = new ArrayList<>();
            nList.add(obtExt(String.valueOf(sintoma.getClass())));
            nList.add(sintoma.getNombre());
            dList.add(nList);
        }
        return dList;
    }

    public List<List<String>> regDobListaSintoma(String tipoSin, String sintoma, List<List<String>> dlist) {
        List<List<String>> dobleListSint = actDobleListDatos(sintoma,dlist);
        List<String> lsa = new ArrayList<>();
        lsa.add(tipoSin);
        lsa.add(sintoma);
        dobleListSint.add(lsa);
        return dobleListSint;
    }

}
