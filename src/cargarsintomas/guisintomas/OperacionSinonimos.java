package cargarsintomas.guisintomas;

import bd.LeerBD;

import java.util.List;

public class OperacionSinonimos {

    private final List<List<String>> SINONIMOS;

    private final ComprobarLetras COMPL;
    private final ComprobarSinonimos COMPS;
    private final String RUTA_SINONIMO;

    public OperacionSinonimos(String ruta){
        RUTA_SINONIMO = ruta;
        LeerBD LEERBD = new LeerBD();
        SINONIMOS = LEERBD.Leer(ruta);
        COMPL = new ComprobarLetras();
        COMPS =new ComprobarSinonimos(ruta);
    }


    public boolean empate(String sintoma, List<List<String>> lisd)  {
        boolean rband = false;
        List<String> lr = COMPS.obtListaSino(prediccionPalabra(sintoma));
        if(concuerda(lisd,lr)){
            rband = true;
        } return rband;
    }

    private boolean concuerda (List<List<String>> dl, List<String> sinonimos) {
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

    public String prediccionPalabra(String orig) {
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
}