package cargarregistros.utilitariosreg;

import monitor.Sintoma;
import monitor.Sintomas;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Convertidor {

    public static  Map<String, Boolean> aDiccEstado (List<List<String>> lsint) {
        Map<String, Boolean> dest = new HashMap<>();
        if(lsint.size() >= 0) {
            for(List<String> lst : lsint) {
                dest.put(lst.get(lst.size()-1), false);
            }
        }
        return dest;
    }

    public static Map<String,String> diccList(List<List<String>> nlist) {
        Map<String,String> ls = new HashMap<>();
        for(List<String> l: nlist) {
            ls.put(l.get(l.size()-1),l.get(0));
        } return ls;
    }

    public static  Map<Integer, String> aDiccOpciones (List<List<String>> lSint) {
        Map<Integer, String> dsint = new HashMap<>();
        int index = 0;
        for(List<String> ls: lSint) {
            dsint.put(index, ls.get(ls.size()-1));
            index++;
        }
        return dsint;
    }

    public static List<List<String>> convertirSintAList(Sintomas sintomas) {
        List<List<String>> lsn = new ArrayList<>();
        for(Sintoma sint: sintomas){
            List<String> lsint = new ArrayList<>();
            lsint.add(obtExt(String.valueOf(sint.getClass())));
            //lsint.add(sint.getNombre());
            lsint.add(sint.toString());
            lsn.add(lsint);
        }
        return lsn;
    }

    public static Sintomas devolverSints (String paquete, List<List<String>> lsint) {
        Sintomas sintomas = new Sintomas();
        for(List<String> ls: lsint) {
            sintomas = devSintomas(paquete,ls.get(0),ls.get(1),sintomas);
        }
        return sintomas;
    }

    private static Sintomas devSintomas(String paquete, String tipo, String nombreSintoma, Sintomas sintomas){
        Sintoma sintoma = null;
        try {
            Class<?> cl = Class.forName(paquete + "." + tipo);
            Constructor<?> constructor = cl.getConstructor(String.class);
            sintoma = (Sintoma)constructor.newInstance(nombreSintoma);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        sintomas.add(sintoma);
        return sintomas;
    }

    public static String obtExt(String rutaAr){
        String[] arrCad = null;
        if(caracRep(rutaAr) > 0) {
            rutaAr = rutaAr.replace('.',',');
            arrCad = rutaAr.split(",");
        }
        assert arrCad != null;
        return arrCad[1];
    }

    public static int caracRep(String cad){
        int resI = 0;
        for (int i = 0; i < cad.length(); i++) {
            resI = (cad.charAt(i) == '.') ? resI+1: resI;
        }
        return resI;
    }

    public static String convDateString(Date date){
        SimpleDateFormat sdatef = new SimpleDateFormat("yyyy/MM/dd");
        return sdatef.format(date);
    }

    public static Date convStringDate(String fecha){
        SimpleDateFormat sdatef = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try{
            date = sdatef.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
