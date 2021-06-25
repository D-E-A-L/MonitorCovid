package cargarsintomas.extra;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public final class OperacionSimple {
    
    public static int seRepite(List<List<String>> listd, List<String> listBuscar) {
        int res = 0;
        for(List<String> lsint: listd) {
            if(lsint.size() == listBuscar.size()) {
                for(int i = 0; i < lsint.size(); i++) {
                    if(lsint.get(i).equals(listBuscar.get(i))) {
                        res++;
                    }
                }
            }
        } return res;
    }

    public static double obtPor(int var, int tot) {
        DecimalFormat dfrmt = new DecimalFormat("#.##");
        return Double.parseDouble(dfrmt.format((100 * var) / tot));
    }

    public static String obtExt(String rutaAr){
        String[] arrCad = null;
        if(caracRep(rutaAr,'.') > 0) {
            rutaAr = rutaAr.replace('.',',');
            arrCad = rutaAr.split(",");
        }
        assert arrCad != null;
        return arrCad[1];
    }

    public static String obtNom(String rutaAr){
        String[] arrCad = null;
        if(caracRep(rutaAr,'.') > 0) {
            rutaAr = rutaAr.replace('.',',');
            arrCad = rutaAr.split(",");
        }
        assert arrCad != null;
        return arrCad[0];
    }

    public static int caracRep(String cad, char car){
        int resI = 0;
        for (int i = 0; i < cad.length(); i++) {
            resI = (cad.charAt(i) == car) ? resI+1: resI;
        }
        return resI;
    }

    public static String mostListDatos (List<List<String>> datos) {
        StringBuilder rdatos = new StringBuilder();
        for (List<String> d: datos) {
            for(int i = 0; i < d.size(); i++) {
                if(i < d.size()) {
                    if( i < d.size() -1 ) {
                        rdatos.append(d.get(i)+"-->");
                    }
                    else if(i == d.size() -1) {
                        rdatos.append(d.get(i)+"\n");
                    }
                }
            }
        }
        return rdatos.toString();
    }

    public static String eliUltimo (String s) {
        StringBuilder sb = new StringBuilder(s);
        return String.valueOf(sb.deleteCharAt(sb.length()-1));
    }

    public static List<String> deArrayAList (String[] arStr) {
        List<String> nList = new ArrayList<>();
        Collections.addAll(nList, arStr);
        return nList;
    }

    public static String encRuta (String ruta, String aBuscar) {
        String res = "";
        if(listArchCarp(ruta).size()> 0) {
            for(String cad: listArchCarp(ruta)) {
                if(cad.contains(aBuscar)) {
                    res = cad;
                } else {
                    res = obtRutaPath(ruta) + "/" + aBuscar;
                }
            }
        } else {
            res = obtRutaPath(ruta) + "/" + aBuscar;
        }
        return res.replaceAll(" ","%20");
    }

    private static List<String> listArchCarp (String archCarp) {
        File miArch = new File(obtRutaPath(archCarp));
        String[] ee = miArch.list();
        List<String> listRes = new ArrayList<>();
        for(String cad : separarExt(ee) != null ? separarExt(ee) : new String[0]) {
            if(obtExt(cad).equals("csv") || (obtExt(cad).equals("dat"))) {
                listRes.add(obtRutaPath(archCarp+"/"+cad));
            }
        }
        return listRes;
    }

    private static String[] separarExt(String[] aux) {
        List<String> lres = new ArrayList<>();
        for (String c: aux) {
            if(caracRep(c,'.')==1) {
                lres.add(c);
            }
        }
        return convertirList(lres);
    }

    private static String[] convertirList(List<String> list) {
        String[] res = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static String obtRutaPath (String ruta) {
        String nruta = "";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        try {
            Enumeration resources = classLoader.getResources(ruta);
            URL resource = (URL) resources.nextElement();
            nruta = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nruta;
    }
}
