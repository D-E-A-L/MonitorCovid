package cargarsintomas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static cargarsintomas.extra.OperacionSimple.*;

public class LeerSubClases {

    public LeerSubClases(){
    }

    public List<String> listarHijos (String ncl, Class<?>  nuevaC) {
        List<String> lisc = new ArrayList<>();
        for (String ncad: obtHijos(ncl,nuevaC)) {
            lisc.add(obtExt(ncad));
        }
        return  lisc;
    }

    private List<String> obtHijos (String ncl, Class<?>  nuevaC) {
        List<String> resL = new ArrayList<>();
        for (String rs : listJavaClass(ncl)) {
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
        for (String scad : listArchCarp(cl)) {
            resL.add(cl/*obtPaquete(cl)*/ + "." + obtNom(scad));
        }
        return resL;
    }

    private String obtPaquete (String nrut) {
        String nCad = "";
        String[] arr;
        if(caracRep(nrut,'/') > 0) {
            nCad = nrut.replace('/',',');
            arr = nCad.split(",");
            /*if (arr.length > 2) {
                for(int i = 1; i < arr.length; i++) {
                    nCad = arr[i];
                }
            } else */if(arr.length > 2) {
                nCad = arr[arr.length -2];
            }
        }
        return nCad;
    }

    private List<String> listArchCarp (String archCarp) {
        File miArch = new File(obtRutaPath(archCarp));
        String[] ee = miArch.list();
        List<String> listRes = new ArrayList<>();
        for(String cad : ee != null ? ee : new String[0]) {
            if(obtExt(cad).equals("class") || (obtExt(cad).equals("java"))) {
                listRes.add(cad);
            }
        }
        return listRes;
    }
}
