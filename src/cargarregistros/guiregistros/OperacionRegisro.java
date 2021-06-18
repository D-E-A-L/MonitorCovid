package cargarregistros.guiregistros;

import cargarsintomas.CargarSintomas;
import cargarsintomas.GestorSintomas;
import cargarsintomas.guisintomas.OperacionSintomas;
import monitor.Sintomas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OperacionRegisro {

    private Sintomas sintomas;

    private final GestorSintomas GES_SINT;
    private final CargarSintomas CES_SINT;
    private final OperacionSintomas OPSINT;

    private List<List<String>> lSintomas;
    private Map<Integer, String> ordSint;
    private Map<String, Boolean> estSint;

    public OperacionRegisro(String rutaSin) {
        GES_SINT = new GestorSintomas(rutaSin);
        CES_SINT = new CargarSintomas();
        OPSINT = new OperacionSintomas();
        sintomas = CES_SINT.getSintomas();
        ordSint = aDiccOpciones(OPSINT.aDobleListaString(sintomas));
        estSint = aDiccEstado(OPSINT.aDobleListaString(sintomas));
    }

    private Map<Integer, String> aDiccOpciones (List<List<String>> lSint) {
        Map<Integer, String> dsint = new HashMap<>();
        int index = 0;
        for(List<String> ls: lSint) {
            dsint.put(index, ls.get(ls.size()-1));
            index++;
        }
        return dsint;
    }

    private Map<String, Boolean> aDiccEstado (List<List<String>> lsint) {
        Map<String, Boolean> dest = new HashMap<>();
        for(List<String> lst : lsint) {
            dest.put(lst.get(lsint.size()-1), false);
        }
        return dest;
    }

    public int tamDic() {
        return ordSint.size();
    }

    public String mostOpc(){
        String cad = "";
        for(int i = 0; i < estSint.size(); i++) {
            if(estSint.get(ordSint.get(i)) == false) {
                cad = cad + i + ".- " + ordSint.get(i)+ "; ";
                if(i % 5 == 0 && i > 0) {
                    cad = cad + "\n";
                }
            }
        } cad = cad + ordSint.size() + ".- SALIR Y GUARDAR CAMBIOS";
        return cad;
    }

}