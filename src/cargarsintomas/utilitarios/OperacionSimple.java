package cargarsintomas.utilitarios;

import monitor.Sintomas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cargarsintomas.utilitarios.OperacionMedia.converSintListD;

public final class OperacionSimple {

    public static double obtPor(int var, int tot) {
        DecimalFormat dfrmt = new DecimalFormat("#.##");
        return Double.parseDouble(dfrmt.format((100L * var) / tot));
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
                if( i < d.size() -1 ) {
                    rdatos.append(d.get(i)).append("-->");
                }
                else if(i == d.size() -1) {
                    rdatos.append(d.get(i)).append("\n");
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

    public static String mostSintReg(Sintomas sintomas) {
        List<List<String>> dlSint = converSintListD(sintomas);
        StringBuilder strres = new StringBuilder();
        if(!dlSint.equals(null)) {
            for(int i = 0; i < dlSint.size(); i++) {
                List<String> lsint = dlSint.get(i);
                strres.append(lsint.get(0)).append(": ").append(lsint.get(1)).append("; ");
                if( (i+1) % 3 == 0 && i > 0) {
                    strres.append("\n");
                }
            }
        } else {
            strres = new StringBuilder();
        }
        return strres.toString();
    }
}