package cargarregistros.utilitariosreg;

import monitor.Sintoma;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static cargarregistros.utilitariosreg.ConvertidorReg.*;
import static cargarregistros.utilitariosreg.OpeSimpReg.obtExt;
import static cargarsintomas.utilitarios.OperacionMedia.converSintListD;

public final class OrdenarSintomasReg {

    //region private methods
    private static List<String> obtCateg(Sintomas sintomas) {
        List<List<String>> sints = converSintListD(sintomas);
        int incr = 0;
        String aux;
        List<String> lisRes = new ArrayList<>();
        if(sints.size() > 1) {
            lisRes.add(sints.get(0).get(0));
            for(int i= 1; i < sints.size(); i++){
                if(incr < sints.size()-1) {
                    aux = sints.get(incr).get(0);
                    if(!aux.equals(sints.get(i).get(0))){
                        lisRes.add(sints.get(i).get(0));
                        incr++;
                    }
                }
            }
        } return sinRepetir(lisRes);
    }

    private static List<String> sinRepetir(List<String> nls) {
        Stack<String> aux = new Stack<>();
        int icr = 0;
        if(!nls.isEmpty()) {
            if(nls.size() == 1) {
                aux.push(nls.get(icr));
            } else {
                for(int i = 1; i < nls.size(); i++){
                    if(icr < nls.size() -1) {
                        if(!nls.get(icr).equals(nls.get(i))) {
                            aux.push(nls.get(icr));
                            aux.push(nls.get(i));
                        } else if(!aux.isEmpty()){
                            if(!aux.peek().equals(nls.get(i))){
                                aux.push(nls.get(icr));
                                aux.push(nls.get(i));
                            }
                        } icr++;
                    }
                }
            }
        }
        return convrStackList(aux);
    }
    //endregion


    //region public methods used in ConsolaReg
    public static Sintomas ordPorCat(Sintomas sintomas) {
        Stack<Sintoma> sint = new Stack<>();
        if(sintomas != null){
            for(String s: obtCateg(sintomas)){
                for(Sintoma sin : sintomas) {
                    if(s.equals(obtExt(String.valueOf(sin.getClass())))) {
                        sint.push(sin);
                    }
                }
            }
        } return convrStackSint(sint);
    }

    public static Sintomas ordAlf(Sintomas sintomas) {
        char[] abecedario = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        Stack<Sintoma> sins = new Stack<>();
        if(sintomas != null) {
            for(char abc: abecedario) {
                for(Sintoma s: sintomas){
                    String ee = s.toString();
                    char b = Character.toLowerCase(ee.charAt(0));
                    if(abc == b) {
                        sins.push(s);
                    }
                }
            }
        } else {
            return new Sintomas();
        }
        return convrStackSint(sins);
    }
    //endregion

}
