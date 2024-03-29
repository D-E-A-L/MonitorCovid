package cargarsintomas.utilitarios;

import monitor.Sintoma;
import monitor.Sintomas;

import java.util.*;

import static cargarsintomas.utilitarios.OperacionSimple.obtExt;

public final class OperacionMedia {

    //region private methods
    private static int existeIgualInt(String sintoma, List<List<String>> lSintomas) {
        int i = 0;
        if (lSintomas.size() > 0 ) {
            for(List<String> lint: lSintomas) {
                for(String nc : lint) {
                    if(nc.equals(sintoma)) {
                        i++;
                    }
                }
            }
        }
        return i;
    }
    //endregion

    //region public methods used in ConsolaSintomas
    public static String mostOpLista(List<?> lista) {
        StringBuilder rcad = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            rcad.append(i).append(".- ").append(lista.get(i)).append("; ");
        } rcad.append(lista.size()).append(".- Salir");
        return rcad.toString();
    }

    public static Map<Integer,String> dicIntString(List<?> nLista) {
        Map<Integer, String> diccLista = new HashMap<>();
        for (int i = 0; i < nLista.size(); i++) {
            diccLista.put(i,(String) nLista.get(i));
        }
        return diccLista;
    }

    public static List<List<String>> noRepetido(List<List<String>> lsAux, List<List<String>> lSintomas) {
        for (List<String> aux : lsAux) {
            for (int b = 0; b < lSintomas.size(); b++) {
                String ae = aux.get(1);
                if (existeIgualInt(ae,lSintomas) > 0) {
                    lSintomas.remove(b);
                }
            }
        }
        return lSintomas;
    }
    //endregion

    //region public methods used in OperacionSintomas
    public static List<List<String>> actDobleListDatos(String acSintoma, List<List<String>> dList) {
        List<List<String>> ndList = new ArrayList<>();
        if(existeIgualInt(acSintoma,dList) == 0) {
            ndList = dList;
        }
        return ndList;
    }
    //endregion

    //region public methods used in OrdenarSintomasReg,OperacionSimple, Ordenar
    public static List<List<String>> converSintListD(Sintomas sintomas) {
        List<List<String>> listRes = new ArrayList<>();
        if(!sintomas.equals(null)) {
            for(Sintoma sintoma : sintomas) {
                List<String> nlist = new  ArrayList<>();
                nlist.add(obtExt(String.valueOf(sintoma.getClass())));
                nlist.add(sintoma.toString());
                listRes.add(nlist);
            }
        } else {
            listRes = null;
        }
        return listRes;
    }
    //endregion

}