package cargarregistros;

import monitor.Registros;

public class CargarRegistros {

    private final String RUTA_REGISTROS;

    private GestorRegistros gestorRegistros;
    private Registros registros;

    public CargarRegistros() {
        RUTA_REGISTROS = "cargarregistros/registros.dat";
        gestorRegistros = new GestorRegistros(RUTA_REGISTROS);
        registros = gestorRegistros.getRegistrosArchivo();
    }

    public Registros getRegistros() {
        return registros;
    }
}
