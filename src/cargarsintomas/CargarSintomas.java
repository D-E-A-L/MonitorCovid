package cargarsintomas;

import cargarsintomas.guisintomas.ConsolaSintomas;
import monitor.Sintomas;

public class CargarSintomas {

    private final GestorSintomas GESTOR;

    private final String RUTA_SINTOMAS;
    private final String RUTA_SINONIMOS;
    private final String RUTA_HIJOS;

    public CargarSintomas() {
        RUTA_SINTOMAS = "sintomas.dat";
        RUTA_SINONIMOS = "./cargarsintomas/sinonimos.csv";
        RUTA_HIJOS = "sintomas";
        GESTOR = new GestorSintomas(RUTA_SINTOMAS,RUTA_HIJOS);
        registrarSintomas();
    }

    //region private methods
    private void registrarSintomas() {
        new ConsolaSintomas(RUTA_SINTOMAS,RUTA_SINONIMOS,RUTA_HIJOS);
    }
    //endregion

    //region public methods used in Monitor
    public Sintomas getSintomas() {
        return GESTOR.getSintomasArchivo();
    }
    //endregion

}
