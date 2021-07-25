package cargarsintomas.utilitarios;

import monitor.Sintoma;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static cargarsintomas.utilitarios.OperacionMedia.converSintListD;
import static cargarsintomas.utilitarios.OperacionSimple.obtExt;

public final class Ordenar {

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

    private static List<String> convrStackList(Stack<String> st){
        List<String> list = new ArrayList<>();
        if(!st.isEmpty()) {
            list.addAll(st);
        } return list;
    }

    private static Sintomas convrStackSint(Stack<Sintoma> st){
        Sintomas sint = new Sintomas();
        if(!st.isEmpty()) {
            for(Sintoma re : st) {
                sint.add(re);
            }
        } return sint;
    }
    //endregion

    //region public methods used in ConsolaSintomas
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
        }
        return convrStackSint(sins);
    }
    //endregion
}
