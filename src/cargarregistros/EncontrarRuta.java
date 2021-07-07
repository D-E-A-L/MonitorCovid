package cargarregistros;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static cargarregistros.guiregistros.Convertidor.obtExt;
import static cargarregistros.guiregistros.Convertidor.caracRep;

public final class EncontrarRuta {

    private static final String SEPARADOR = System.getProperty("file.separator");

    public static String encRuta (String ruta, String aBuscar) {
        String res = "";
        if(listArchCarp(ruta).size()> 0) {
            for(String cad: listArchCarp(ruta)) {
                if(cad.contains(aBuscar)) {
                    res = cad;
                } else {
                    res = obtRutaPath(ruta) + SEPARADOR + aBuscar;
                }
            }
        } else {
            res = obtRutaPath(ruta) + SEPARADOR + aBuscar;
        }
        //return res.replaceAll(" ","%20");
        return res;
    }

    private static List<String> listArchCarp (String archCarp) {
        File miArch = new File(obtRutaPath(archCarp));
        String[] ee = miArch.list();
        List<String> listRes = new ArrayList<>();
        assert ee != null;
        separarExt(ee);
        for(String cad : separarExt(ee)) {
            if(obtExt(cad).equals("csv") || (obtExt(cad).equals("dat"))) {
                listRes.add(obtRutaPath(archCarp+SEPARADOR+cad));
            }
        }
        return listRes;
    }

    private static String[] separarExt(String[] aux) {
        List<String> lres = new ArrayList<>();
        for (String c: aux) {
            if(caracRep(c)==1) {
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
        File nfile = new File("");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        try {
            if(esRutaProduc(nfile.getCanonicalPath())) {
                Enumeration<URL> resources = classLoader.getResources(ruta);
                URL resource = resources.nextElement();
                nruta = resource.getFile();
            } else {
                nruta =  aniadirDev(nfile.getCanonicalPath()) + ruta;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nruta;
    }

    private static boolean esRutaProduc(String ruta) {
        return ruta.contains("out");
    }

    private static String aniadirDev(String ruta){
        return ruta + SEPARADOR + "src" + SEPARADOR;
    }
}
