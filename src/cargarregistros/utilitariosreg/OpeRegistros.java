package cargarregistros.utilitariosreg;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;

import static cargarregistros.utilitariosreg.Convertidor.convDateString;
import static cargarregistros.utilitariosreg.ConvertidorReg.listaRegistros;

public class OpeRegistros {

    public OpeRegistros(){}

    public Registros combFReg(Registros registros){//arreglar
        Registros rReg = new Registros();
        List<Registro> rs = listaRegistros(registros);
        Registro aux;
        int incr = 0;
        if( rs.size() >= 1) {
            aux = rs.get(incr);
            for(int i = 1 ; i < rs.size(); i ++) {
                if(incr < rs.size()-1) {
                    if(!convDateString(aux.getFecha()).equals(convDateString(rs.get(i).getFecha()))) {
                        rReg.push(rs.get(incr));
                        rReg.push(rs.get(i));
                    } else {
                        if(!rReg.isEmpty()) {
                            Registro aux1 = rReg.peek();
                            if(convDateString(aux1.getFecha()).equals(convDateString(rs.get(i).getFecha()))) {
                                rReg.push(combFinReg(rs,convDateString(aux1.getFecha())));
                            }
                        }
                    } incr++;
                }
            }
        } if (rs.size() == 1){
            rReg.push(rs.get(0));
        }
        return elimRegDup(rReg);
    }

    private Registro combFinReg(List<Registro> lReg, String fecha) {
        Registro reg = null;
        List<Registro> agrReg;
        Registro aux;
        int incr = 0;
        if(!lReg.isEmpty()) {
            agrReg = agrReg(lReg, fecha);
            if(agrReg.size() > 1) {
                aux = agrReg.get(incr);
                for(int i =1; i < agrReg.size(); i++) {
                    if( incr < agrReg.size()-1) {
                        reg = combReg(aux,agrReg.get(i));
                    } incr++;
                }
            } else if(agrReg.size() == 1){
                reg = agrReg.get(0);
            }
        }
        return reg;
    }

    private List<Registro> agrReg(List<Registro> registros, String fecha){
        List<Registro> reg = new ArrayList<>();
        for(Registro r: registros) {
            if(convDateString(r.getFecha()).equals(fecha)) {
                reg.add(r);
            }
        } return reg;
    }

    private Registro combReg(Registro r1, Registro r2) {
        Registro regr = null;
        if(r1 != null && r2 != null) {
            regr = r1;
            if(r1 != r2) {
                regr = new Registro(r1.getFecha(),combSints(r1.getSintomas(), r2.getSintomas()));
            }
        }
        return regr;
    }

    private Sintomas combSints(Sintomas s1, Sintomas s2) {
        Sintomas sRes = new Sintomas();
        if(s1 != null || s2 != null) {
            sRes = s1;
            for(Sintoma si1 : s1){
                for(Sintoma si2: s2) {
                    if(!si1.equals(si2)) {
                        sRes.add(si1);
                    }
                }
            }
        }
        return  sRes;
    }

     private Registros elimRegDup(Registros registros){
        Registros nreg = new Registros();
        List<Registro> lreg = listaRegistros(registros);
        int incr = 0;
        Registro aux;
        for(int i = 1; i < lreg.size();i++) {
            aux = lreg.get(incr);
            if(incr < lreg.size() -1) {
                if(!aux.equals(lreg.get(i))){
                    nreg.push(aux);
                    if (listaRegistros(nreg).size() == lreg.size()-1) {
                        nreg.push(lreg.get(lreg.size()-1));
                    }
                }
                incr++;
            }
        }
        return nreg;
     }
}
