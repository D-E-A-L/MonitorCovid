package cargarregistros.guiregistros;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.*;

import static cargarregistros.guiregistros.Convertidor.*;


public class OperacionRegisro {

    private final Map<Integer,String> ORD_EST;
    private final Map<String, String> GET_LIST;
    private final List<List<String>> LIST_SINT;

    private  Map<String,Boolean> est_sint;

    public OperacionRegisro(Sintomas sintomas) {
        LIST_SINT = convertirSintAList(sintomas);
        ORD_EST = aDiccOpciones(LIST_SINT);
        est_sint = aDiccEstado(LIST_SINT);
        GET_LIST = diccList(LIST_SINT);
    }

    public String mostrarRegistros(Registros registros){
        String res = "";
        for(Registro registro: registros) {
            if(res.equals("") || res.equals(null) ) {
                res = registro.getFecha() +"-->"+ converStringSintomas(registro.getSintomas());
            } else {
                res = res + registro.getFecha() +"-->"+ converStringSintomas(registro.getSintomas());
            }
        }
        return res;
    }

    private String converStringSintomas(Sintomas sintomas) {
        String res = "";
        for(Sintoma sintoma: sintomas) {
            if(!res.equals("")){
                res = res + "; "+sintoma.toString();
            } else {
                res = res + sintoma.toString();
            }
        } res = res + "\n";
        return res;
    }

    public String mostOpc(){
        StringBuilder cad = new StringBuilder();
        if(ORD_EST != null) {
            for(int i = 0; i < ORD_EST.size(); i++) {
                if(!est_sint.get(ORD_EST.get(i))) {
                    cad.append(i).append(".- ").append(ORD_EST.get(i)).append("; ");
                    if(i % 5 == 0 && i > 0) {
                        cad.append("\n");
                    }
                }
            }
            cad.append("\n").append(est_sint.size()).append(".- REGISTRAR");
        } return cad.toString();
    }


    public void actDicEst(String cad) {
        est_sint.put(cad,true);
    }

    public int tamSintomas() {return ORD_EST.size();}

    public List<String> obtTipoSint(int opc) {
        List<String> lsint = new ArrayList<>();
        lsint.add(GET_LIST.get(obtSint(opc)));
        lsint.add(obtSint(opc));
        actDicEst(obtSint(opc));
        return lsint;
    }

    public void restablecer(){
        est_sint = aDiccEstado(LIST_SINT);
    }

    private String obtSint(int opc) {
        return ORD_EST.get(opc);
    }

}