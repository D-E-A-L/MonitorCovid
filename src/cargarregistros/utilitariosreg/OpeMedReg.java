package cargarregistros.utilitariosreg;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import static cargarregistros.utilitariosreg.OpeSimpReg.*;


public class OpeMedReg {

    private final Map<Integer,String> ORD_EST;
    private final Map<String, String> GET_LIST;
    private final List<List<String>> LIST_SINT;

    private  Map<String,Boolean> est_sint;

    private Sintomas sintomas;

    public OpeMedReg(Sintomas sint) {
        sintomas = sint;
        LIST_SINT = convertirSintAList(sintomas);
        ORD_EST = aDiccOpciones(LIST_SINT);
        est_sint = aDiccEstado(LIST_SINT);
        GET_LIST = diccList(LIST_SINT);
    }

    //region private methods
    public void modificarSintomasOrd(Sintomas sins) {
        sintomas = sins;
    }

    private String obtSint(int opc) {
        return  ORD_EST.get(opc);
    }

    private String limpiar(String cad, String carac) {
        return cad.contains(carac) ? cad.split(carac)[1] : cad;
    }

    private void actDicEst(String cad) {
        est_sint.put(cad,true);
    }

    private String converStringSintomas(Sintomas sintomas) {
        StringBuilder res = new StringBuilder();
        for(Sintoma sintoma: sintomas) {
            if(!res.toString().equals("")){
                res.append("; ").append(sintoma.toString());
            } else {
                res.append(sintoma.toString());
            }
        } res.append("\n");
        return res.toString();
    }
    //endregion

    //region public methods used in ConsolaRegistros
    public int tamSintomas() {
        return ORD_EST.size();
    }

    public void restablecer(){
        est_sint = aDiccEstado(LIST_SINT);
    }

    public String mostrarRegistros(Registros registros){
        StringBuilder res = new StringBuilder();
        for(Registro registro: registros) {
            res.append(registro.getFecha()).append("-->").append(converStringSintomas(registro.getSintomas()));
        }
        return res.toString();
    }

    public String mostOpc(){
        StringBuilder cad = new StringBuilder();
        if(ORD_EST != null) {
            for(int i = 0; i < ORD_EST.size(); i++) {
                if(!est_sint.get(ORD_EST.get(i))) {
                    cad.append(i).append(".- ").append(ORD_EST.get(i)).append("; ");
                    if(i % 3 == 0 && i > 0) {
                        cad.append("\n");
                    }
                }
            }
            cad.append("\n").append(est_sint.size()).append(".- REGISTRAR");
        } return cad.toString();
    }

    public String mostOpc1(){
        StringBuilder cad = new StringBuilder();
        if(ORD_EST != null) {
            for(int i = 0; i < ORD_EST.size(); i++) {
                if(!est_sint.get(ORD_EST.get(i))) {
                    cad.append(i).append(".- ").append(ORD_EST.get(i)).append("; ");
                    if(i % 3 == 0 && i > 0) {
                        cad.append("\n");
                    }
                }
            }
        } return cad.toString();
    }

    public List<String> obtTipoSint(int opc) {
        List<String> lsint = new ArrayList<>();
        lsint.add(GET_LIST.get(limpiar(obtSint(opc),"-->")));
        lsint.add(limpiar(obtSint(opc),"-->"));
        actDicEst(obtSint(opc));
        return lsint;
    }
    //endregion

}