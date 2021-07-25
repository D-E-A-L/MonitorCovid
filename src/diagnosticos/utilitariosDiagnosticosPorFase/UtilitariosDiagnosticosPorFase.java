package diagnosticos.utilitariosDiagnosticosPorFase;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;


public final class UtilitariosDiagnosticosPorFase{

    //region private methods
    private static Date diaAnterior(Date date) {
        Calendar calen = Calendar.getInstance();
        calen.setTime(date);
        calen.add(Calendar.DAY_OF_YEAR, -1);
        return calen.getTime();
    }

    private static boolean esHoy(Date actual, Date actReg){
        return convDateString(actual).equals(convDateString(actReg));
    }

    private static boolean esDiaSegAnterior(Date actual, Date anterior) {
        return convDateString(diaAnterior(actual)).equals(convDateString(anterior));
    }

    private static List<Registro> obtNReg(Registros registros, int n){
        Stack<Registro> lreg = convRegStack(registros);
        List<Registro> lisr = new ArrayList<>();
        int incr = 0;
        if(!lreg.isEmpty()) {
            if(lreg.size() >= n -1) {
                while(incr <= n-1) {
                    lisr.add(lreg.pop());
                    incr++;
                }
            }
        } return lisr;
    }

    private static String convDateString(Date date){
        SimpleDateFormat sdatef = new SimpleDateFormat("yyy-MM-dd");
        return sdatef.format(date);
    }
    //endregion

    //region public methods used in diagnosticoPorFase
    public static Stack<Registro> convRegStack(Registros registros){
        Stack<Registro> pr = new Stack<>();
        if(!registros.isEmpty()) {
            for(Registro r: registros) {
                pr.push(r);
            }
        }
        return pr;
    }

    public static boolean controlReg(Registros registros){
        boolean res = false;
        Stack<Registro> regs = convRegStack(registros);
        if (!regs.isEmpty()) {
            if(regs.size() == 1) {
                if(!esHoy(regs.pop().getFecha(),new Date())) {
                    res = esDiaSegAnterior(new Date(),regs.pop().getFecha());
                }
            } else if(regs.size() > 1) {
                res = esDiaSegAnterior(regs.pop().getFecha(),regs.pop().getFecha());
            }
        }
        return res;
    }

    public static int calcPesoSint(Sintomas sintomas) {
        int res = 0;
        if(sintomas != null) {
            for(Sintoma sint: sintomas) {
                res = res + sint.peso();
            }
        } return res;
    }

    public static boolean controlFase(Registros registros, int n) {
        boolean res = false;
        List<Registro> regs = obtNReg(registros, n);
        int incr = 0;
        if(!regs.isEmpty()) {
            for(int i=1; i < regs.size(); i++) {
                if(incr < regs.size()-1) {
                    if(!esHoy(regs.get(0).getFecha(),new Date())) {
                        res = res == esDiaSegAnterior(regs.get(incr).getFecha(),regs.get(i).getFecha());
                    }
                }
            }
        }
        return res;
    }
    //endregion
}
