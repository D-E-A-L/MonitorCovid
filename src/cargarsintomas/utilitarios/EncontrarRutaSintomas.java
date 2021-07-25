package cargarsintomas.utilitarios;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public final  class EncontrarRutaSintomas {

    private static final String SEPARADOR = System.getProperty("file.separator");

    //region private methods
    private static boolean esRutaProduc(String ruta) {
        return ruta.contains("out");
    }

    private static String aniadirDev(String ruta){
        return ruta + SEPARADOR + "src" + SEPARADOR;
    }
    //endregion

    //region public methods used in LeerSubClases
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
    //endregion
}
