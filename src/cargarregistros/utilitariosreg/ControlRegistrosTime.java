package cargarregistros.utilitariosreg;

import monitor.Registro;
import monitor.Registros;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static cargarregistros.utilitariosreg.Convertidor.*;
import static cargarregistros.utilitariosreg.ConvertidorReg.listaRegistros;

public class ControlRegistrosTime {

    private final OpeRegistros OPREG;
    private final Registros REGISTROS;

    public ControlRegistrosTime(Registros registros) {
        OPREG = new OpeRegistros();
        REGISTROS = registros;
    }

    public void imprimirTes() {
        List<Registro> listReg = listaRegistros(REGISTROS);
        int decr = listReg.size() - 2;
        for(int i = listReg.size() - 1; i>=1; i--) {
            if(decr >=0 ) {
                System.out.println(i + " = " + decr +" ?: " + esDiaSegAnterior(listReg.get(i).getFecha(),listReg.get(decr).getFecha()));
                decr--;
            }
        }
    }

    public boolean esDiaSegAnterior(Date actual, Date anterior) {
        return convDateString(diaAnterior(actual)).equals(convDateString(anterior));
    }

    private Date diaAnterior(Date date) {
        Calendar calen = Calendar.getInstance();
        calen.setTime(date);
        calen.add(Calendar.DAY_OF_YEAR, -1);
        return calen.getTime();
    }
    
}
