package cargarsintomas.utilitarios;

import cargarsintomas.bd.LeerBD;
import cargarsintomas.validacion.ComprobarLetras;
import cargarsintomas.validacion.ComprobarSinonimos;

import java.util.List;

public class OperacionSinonimos {

    private final List<List<String>> SINONIMOS;

    private final ComprobarLetras COMPL;
    private final ComprobarSinonimos COMPS;

    public OperacionSinonimos(String ruta){
        LeerBD LEERBD = new LeerBD();
        SINONIMOS = LEERBD.Leer(ruta);
        COMPL = new ComprobarLetras();
        COMPS = new ComprobarSinonimos(ruta);
    }

    //region private methods
    private boolean concuerda(List<List<String>> dl, List<String> sinonimos) {
        boolean rbool = false;
        for(String sc: sinonimos) {
            for(List<String> lsim : dl) {
                for(String csim: lsim) {
                    if(sc.equals(csim)) {
                        rbool = true;
                        break;
                    }
                } if(rbool) {
                    break;
                }
            } if(rbool) {
                break;
            }
        }
        return  rbool;
    }

    private String prediccionPalabra(String orig) {
        String rcad ="";
        boolean bandera = false;
        for(List<String> ls : SINONIMOS) {
            for(String cs : ls) {
                rcad = COMPL.comparar(orig,cs);
                bandera = true;
                break;
            } if(bandera) {
                break;
            }
        } return rcad;
    }
    //endregion

    //region public methods ConsolaSintomas
    public boolean empate(String sintoma, List<List<String>> lisd)  {
        boolean rband = false;
        List<String> lr = COMPS.obtListaSino(prediccionPalabra(sintoma));
        if(concuerda(lisd,lr)){
            rband = true;
        } return rband;
    }
    //endregion

}