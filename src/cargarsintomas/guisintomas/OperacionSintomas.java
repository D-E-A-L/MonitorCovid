package cargarsintomas.guisintomas;

import monitor.Sintoma;
import monitor.Sintomas;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static extra.OperacionMedia.actDobleListDatos;
import static extra.OperacionSimple.obtExt;

public class OperacionSintomas {

    public OperacionSintomas() {
    }

    public Sintomas regSintomas(List<List<String>> ac) {
        Sintomas sintomas = new Sintomas();
        for (List<String> lc : ac) {
            for(int i = 0; i < ac.size(); i++) {
                try {
                    Class<?> nClass = Class.forName("sintomas."+ lc.get(0));
                    Constructor<?> constructor = nClass.getConstructor(String.class);
                    Sintoma sintoma = (Sintoma) (constructor.newInstance(new Object[]{lc.get(1)}));
                    sintomas.add(sintoma);
                } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sintomas;
    }

    public List<List<String>> aDobleListaString(Sintomas sintomas) {
        List<List<String>> dList = new ArrayList<>();
        for(Sintoma sintoma: sintomas) {
            List<String> nList = new ArrayList<>();
            nList.add(obtExt(String.valueOf(sintoma.getClass())));
            nList.add(sintoma.getNombre());
            dList.add(nList);
        }
        return dList;
    }

    public List<List<String>> regDobListaSintoma(String tipoSin, String sintoma, List<List<String>> dlist) {
        List<List<String>> dobleListSint = actDobleListDatos(sintoma,dlist);
        List<String> lsa = new ArrayList<>();
        lsa.add(tipoSin);
        lsa.add(sintoma);
        dobleListSint.add(lsa);
        return dobleListSint;
    }

}
