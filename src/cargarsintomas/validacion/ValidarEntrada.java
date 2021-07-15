package cargarsintomas.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEntrada {

    public ValidarEntrada() {}

    public boolean comprEntr(String cad){
        String res = elEspDemas(elEspInFin(cad));
        return compMedExpRegu(res);
    }

    private boolean compMedExpRegu(String cad) {
        Pattern pat = Pattern.compile("^[A-Z?.\\s]{2,}$");
        Matcher mat = pat.matcher(cad);
        return (mat.matches());
    }

    public String elEspInFin(String cad){
        return compMedExpRegu(cad) ? cad.trim() : cad;
    }

    private int compEspDemas(String cad){
        int res = 0;
        for(int i = 0; i < cad.length(); i++){
            if(cad.charAt(i) == ' '){
                res++;
            }
        } return res;
    }

    public String elEspDemas(String cad){
        while(compEspDemas(cad) > 1) {
            for(int i = 0; i < cad.length(); i++) {
                if(cad.charAt(i) == ' '){
                    cad = cad.substring(0,i) + cad.substring(i+1);
                }
            }
        } return cad;
    }
}
