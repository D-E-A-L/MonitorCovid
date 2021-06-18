package cargarsintomas.guisintomas;

import bd.LeerBD;

import java.util.ArrayList;
import java.util.List;

public class ComprobarSinonimos {

    private final List<List<String>> SINONIMOS;

    private final LeerBD LEER;
    private final ComprobarLetras COMPL;

    public ComprobarSinonimos(String ruta){
        LEER = new LeerBD();
        COMPL = new ComprobarLetras();
        SINONIMOS = LEER.Leer(ruta);
    }

    public boolean empatarSin(String sintoma) {
        boolean rBool = false;
        for(List<String> lSin: SINONIMOS) {
            for(String cadSin: lSin) {
                if(cadSin.equals(sintoma)) {
                    rBool = true;
                    break;
                }
            } if(rBool) {
                break;
            }
        } return rBool;
    }

    public List<String> obtListaSino(String sintoma) {
        boolean rbl = false;
        List<String> rlist = new ArrayList<>();
        for(List<String> lsn : SINONIMOS) {
            for(String csin : lsn) {
                if(csin.equals(sintoma)) {
                    rlist = lsn;
                    rbl = true;
                    break;
                } if(rbl) {
                    break;
                }
            }
        }
        return rlist;
    }
}
