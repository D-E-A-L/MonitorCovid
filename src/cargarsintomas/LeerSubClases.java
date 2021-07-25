package cargarsintomas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static cargarsintomas.utilitarios.EncontrarRutaSintomas.obtRutaPath;
import static cargarsintomas.utilitarios.OperacionSimple.*;

public class LeerSubClases {

    private final String SEPARADOR;

    public LeerSubClases(){
        SEPARADOR = System.getProperty("file.separator");
    }

    //region private methods
    private List<String> obtHijos (String ncl, Class<?>  nuevaC) {
        List<String> resL = new ArrayList<>();
        for (String rs : listJavaClass(ncl) != null? listJavaClass(ncl): new ArrayList<String>()) {
            try {
                Class<?> nClass = Class.forName(rs);
                var nc = nClass.getSuperclass();
                if(nc.equals(nuevaC)) {
                    resL.add(rs);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return resL;
    }

    private List<String> listJavaClass(String cl) {
        List<String> resL = new ArrayList<>();
        List<String> listArch = listArchCarp(cl);
        if( listArch != null) {
            for (String scad : listArch) {
                resL.add(cl+"." + obtNom(scad));
            }
        }
        return resL;
    }

    private List<String> listArchCarp (String archCarp) {
        File miArch = new File(obtRutaPath(archCarp));
        String[] ee = miArch.list();
        List<String> listRes = new ArrayList<>();
        if(ee !=null ) {
            for(String cad : ee) {
                if(obtExt(cad).equals("class") || (obtExt(cad).equals("java"))) {
                    listRes.add(cad);
                }
            }
        }else {
            listRes = pathJar(archCarp);
        }
        return listRes;
    }

    private List<String>  pathJar(String ruta) {
       return pathJarOrigin("home.jar",ruta);
    }

    private List<String> pathJarOrigin(String ruta, String vruta) {
        List<String> classNames = new ArrayList<>();
        ZipInputStream Zip;
        try {
            Zip = new ZipInputStream(new FileInputStream(ruta));
            boolean res;
            for (ZipEntry entry = Zip.getNextEntry(); entry != null; entry = Zip.getNextEntry()) {
                res = entry.getName().contains(vruta);
                if (!entry.isDirectory() && res && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace(SEPARADOR.charAt(0), '.');
                    classNames.add(className.substring("sintomas.".length()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } return classNames;
    }
    //endregion

    //region public methods used in ConsolaRegistros
    public List<String> listarHijos (String ncl, Class<?>  nuevaC) {
        List<String> lisc = new ArrayList<>();
        if(obtHijos(ncl,nuevaC) != null) {
            for (String ncad: obtHijos(ncl,nuevaC)) {
                lisc.add(obtExt(ncad));
            }
        }
        return  lisc;
    }
    //endregion

}
