package cargarsintomas.utilitarios;

import monitor.Sintoma;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;

import static cargarsintomas.utilitarios.OperacionMedia.actDobleListDatos;
import static cargarsintomas.utilitarios.OperacionSimple.obtExt;

public class OperacionSintomas {

    public OperacionSintomas() {
    }

    //region public methods used in ConsolaSintoma
    public List<List<String>> aDobleListaString(Sintomas sintomas) {
        List<List<String>> dList = new ArrayList<>();
        for(Sintoma sintoma: sintomas) {
            List<String> nList = new ArrayList<>();
            nList.add(obtExt(String.valueOf(sintoma.getClass())));
            nList.add(sintoma.toString());
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
    //endregion
}
