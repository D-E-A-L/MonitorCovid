package cargarregistros;

import cargarregistros.guiregistros.ConsolaRegistros;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import java.util.Date;

import static cargarregistros.EncontrarRuta.encRuta;

public class CargarRegistros {

    private final String RUTA_REG;

    private final GestorRegistros GESTOR;
    private Registros registros;
    private final Sintomas SINTOMAS;

    public CargarRegistros(Sintomas sints) {
        SINTOMAS = sints;
        RUTA_REG = encRuta("cargarregistros","registros.dat");
        GESTOR = new GestorRegistros(RUTA_REG);
        registros = GESTOR.getRegistrosArchivo();
        cargarRegistro();
    }

    private void cargarRegistro() {
            new ConsolaRegistros(RUTA_REG, SINTOMAS);
    }

    public Registro getRegistro() {
        if(registros.isEmpty()){
            return new Registro(new Date(), new Sintomas());
        }
        return registros.peek();
    }
}
