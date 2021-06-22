package cargarregistros.guiregistros;

import monitor.Sintomas;

import java.util.*;

import static cargarregistros.guiregistros.Convertidor.*;


public class OperacionRegisro {

    private final Map<Integer,String> EST_SINT;
    private final Map<String,Boolean> ORD_SINT;
    private final Map<String, String> GET_LIST;

    public OperacionRegisro(Sintomas sintomas) {
        List<List<String>>  listSint = convertirSintAList(sintomas);
        EST_SINT = aDiccOpciones(listSint);
        ORD_SINT = aDiccEstado(listSint);
        GET_LIST = diccList(listSint);
    }

    public String mostOpc(){
        StringBuilder cad = new StringBuilder();
        for(int i = 0; i < EST_SINT.size(); i++) {
            if(!ORD_SINT.get(EST_SINT.get(i))) {
                cad.append(i).append(".- ").append(EST_SINT.get(i)).append("; ");
                if(i % 5 == 0) {
                    cad.append("\n");
                }
            }
        } return cad.toString() + ORD_SINT.size() + ".- SALIR Y GUARDAR CAMBIOS";
    }

    public void actDicEst(String cad) {
        ORD_SINT.put(cad,true);
    }

    public int tamSintomas() {return EST_SINT.size();}

    public List<String> obtTipoSint(int opc) {
        List<String> lsint = new ArrayList<>();
        lsint.add(GET_LIST.get(obtSint(opc)));
        lsint.add(obtSint(opc));
        actDicEst(obtSint(opc));
        return lsint;
    }

    private String obtSint(int opc) {
        return EST_SINT.get(opc);
    }

}