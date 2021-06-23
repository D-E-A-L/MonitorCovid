package cargarregistros.guiregistros;

import monitor.Sintomas;

import java.util.*;

import static cargarregistros.guiregistros.Convertidor.*;


public class OperacionRegisro {

    private final Map<Integer,String> ORD_EST;
    private final Map<String,Boolean> EST_SINT;
    private final Map<String, String> GET_LIST;

    public OperacionRegisro(Sintomas sintomas) {
        List<List<String>>  listSint = convertirSintAList(sintomas);
        ORD_EST = aDiccOpciones(listSint);
        EST_SINT = aDiccEstado(listSint);
        GET_LIST = diccList(listSint);
    }

    public String mostOpc(){
        StringBuilder cad = new StringBuilder();
        for(int i = 0; i < ORD_EST.size(); i++) {
            if(!EST_SINT.get(ORD_EST.get(i))) {
                cad.append(i).append(".- ").append(ORD_EST.get(i)).append("; ");
                if(i % 5 == 0) {
                    cad.append("\n");
                }
            }
        } return cad.toString() + EST_SINT.size() + ".- SALIR Y GUARDAR CAMBIOS";
    }

    public void actDicEst(String cad) {
        EST_SINT.put(cad,true);
    }

    public int tamSintomas() {return ORD_EST.size();}

    public List<String> obtTipoSint(int opc) {
        List<String> lsint = new ArrayList<>();
        lsint.add(GET_LIST.get(obtSint(opc)));
        lsint.add(obtSint(opc));
        actDicEst(obtSint(opc));
        return lsint;
    }

    private String obtSint(int opc) {
        return ORD_EST.get(opc);
    }

}