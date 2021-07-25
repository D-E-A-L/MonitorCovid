package cargarregistros.utilitariosreg;

import monitor.Sintoma;
import monitor.Sintomas;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public final class OpeSimpReg {

    //region private methods
    private static String limpiar(String cad){
        return cad.contains("-->")? cad.split("-->")[1]:cad;
    }

    private static int caracRep(String cad, char carBuscar){
        int resI = 0;
        for (int i = 0; i < cad.length(); i++) {
            resI = (cad.charAt(i) == carBuscar) ? resI+1: resI;
        }
        return resI;
    }
    //endregion

    //region public methods used in OpeMedReg
    public static  Map<String, Boolean> aDiccEstado (List<List<String>> lsint) {
        Map<String, Boolean> dest = new HashMap<>();
        if(lsint != null) {
            for(List<String> list : lsint) {
                dest.put(list.get(0)+"-->"+list.get(list.size()-1), false);
            }
        }
        return dest;
    }

    public static  Map<Integer, String> aDiccOpciones (List<List<String>> lSint) {
        Map<Integer, String> dsint = new HashMap<>();
        int index = 0;
        for(List<String> ls: lSint) {
            dsint.put(index, ls.get(0) +"-->"+ls.get(ls.size()-1));
            index++;
        }
        return dsint;
    }

    public static List<List<String>> convertirSintAList(Sintomas sintomas) {
        List<List<String>> lsn = new ArrayList<>();
        for(Sintoma sint: sintomas){
            List<String> lsint = new ArrayList<>();
            lsint.add(obtExt(String.valueOf(sint.getClass())));
            lsint.add(sint.toString());
            lsn.add(lsint);
        }
        return lsn;
    }
    //endregion

    //region public methods used in ConsolaRegistros
    public static Map<String,String> diccList(List<List<String>> nlist) {
        Map<String,String> ls = new HashMap<>();
        for(List<String> l: nlist) {
            ls.put(limpiar(l.get(l.size()-1)),l.get(0));
        } return ls;
    }
    //endregion

    //region public methods OpeSinpReg, OrdenarSintomasReg
    public static String obtExt(String rutaAr){
        String[] arrCad = null;
        if(caracRep(rutaAr,'.') > 0) {
            rutaAr = rutaAr.replace('.',',');
            arrCad = rutaAr.split(",");
        }
        assert arrCad != null;
        return arrCad[1];
    }
    //endregion

}
