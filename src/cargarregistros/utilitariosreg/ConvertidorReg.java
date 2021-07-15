package cargarregistros.utilitariosreg;

import monitor.Registro;
import monitor.Registros;

import java.util.ArrayList;
import java.util.List;

public final class ConvertidorReg {

    public static List<Registro> listaRegistros(Registros registros) {
        List<Registro> lreg = new ArrayList<>();
        if(!registros.isEmpty()) {
            for(Registro re: registros) {
                lreg.add(re);
            }
        }
        return lreg;
    }
}
