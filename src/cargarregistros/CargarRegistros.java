package cargarregistros;

import cargarregistros.guiregistros.ConsolaRegistros;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.util.Date;

public class CargarRegistros {

    private final String RUTA_REG;

    private final Registros REGISTROS;
    private final Sintomas SINTOMAS;

    public CargarRegistros(Sintomas sints) {
        SINTOMAS = sints;
        RUTA_REG = "registros.dat";
        GestorRegistros gestor = new GestorRegistros(RUTA_REG);
        REGISTROS = gestor.getRegistrosArchivo();
        cargarRegistro();
    }

    //region private methods
    private void cargarRegistro() {
            new ConsolaRegistros(RUTA_REG, SINTOMAS);
    }
    //endregion

    //region public methods used in Monitor
    public Registro getRegistro() {
        return (REGISTROS.isEmpty())? new Registro(new Date(), new Sintomas()) : REGISTROS.peek();
    }

    public Registros getRegistros() {
        return REGISTROS;
    }
    //endregion
}
