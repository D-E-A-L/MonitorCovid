package cargarsintomas.guisintomas;

import static extra.OperacionSimple.caracRep;
import static extra.OperacionSimple.obtPor;

public class ComprobarLetras {

    private final ValidarEntrada VALE;

    public ComprobarLetras(){
        VALE = new ValidarEntrada();
    }

    public double porcFinal (String pCompr, String palDatos) {
        pCompr = VALE.elEspInFin(VALE.elEspDemas(pCompr));
        String ncad = eliCarDup(pCompr.replace(" ",""));
        palDatos = VALE.elEspInFin(VALE.elEspDemas(palDatos)).replace(" ","");
        return  obtPor(coencidentes(ncad,palDatos),palDatos.length()) - obtPor(noCoencidentes(ncad,palDatos),palDatos.length()) ;
    }

    private int coencidentes(String cb, String cbd) {
        int scnd = 0, nc = 0;
        for(char nca : cb.toCharArray()) {
            for( char nch : cbd.toCharArray()) {
                if(nca == nch) {
                    nc++;
                }
            } scnd = scnd + nc; nc = 0;
        }
        return scnd;
    }

    private int noCoencidentes(String cb, String cbd) {
        int nc = 0;
        for(char c: cb.toCharArray()) {
            if(caracRep(cbd,c) == 0) {
                nc++;
            }
        }
        return nc;
    }

    private String cadConf(String ec){
        ec =  VALE.elEspDemas(VALE.elEspInFin(ec));
        ec = ec.split(" ").length > 1 ? ec.replace(" ",""): ec;
        return ec;
    }

    public String eliCarDup(String cad) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cad.length(); i++) {
            if(cad.indexOf(cad.charAt(i)) == i) {
                sb.append(cad.charAt(i));
            }
        } return sb.toString();
    }

    public String comparar(String ori, String fn) { //*
        return porcFinal(ori, fn) > 75 ? fn: ori;
    }

}
