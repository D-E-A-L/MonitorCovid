package cargarregistros;

import monitor.Registro;
import monitor.Registros;

import static bd.LeerDat.leerDat;

public class GestorRegistros {

    private Registros registros;

    public GestorRegistros(String rutaReg){
        registros = obtenerRegistros(rutaReg);
    }

    private Registros obtenerRegistros(String ruta){
        Registros reg = new Registros();
        for(Object ob: leerDat(ruta)) {
            Registro registro = (Registro) ob;
            reg.push(registro);
        } return reg;
    }

    public Registros getRegistrosArchivo() {
        return registros;
    }
}
