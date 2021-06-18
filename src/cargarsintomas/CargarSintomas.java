package cargarsintomas;

import cargarsintomas.guisintomas.ConsolaSintomas;
import monitor.Sintomas;

public class CargarSintomas {

    private GestorSintomas gestor;
    private ConsolaSintomas consintoma;

    private final String RUTA_SINTOMAS;
    private final String RUTA_SINONIMOS;
    private final String RUTA_HIJOS;

    public CargarSintomas() {
        RUTA_SINTOMAS = "cargarsintomas/sintomas.dat";
        RUTA_SINONIMOS = "cargarsintomas/sinonimos.csv";
        RUTA_HIJOS = "sintomas";
        gestor = new GestorSintomas(RUTA_SINTOMAS);
        consintoma = new ConsolaSintomas(RUTA_SINTOMAS,RUTA_SINONIMOS,RUTA_HIJOS);
        registrarSintomas();
    }

    private void registrarSintomas() {
        consintoma.mostrarTipoSint();
    }

    public Sintomas getSintomas() {
        return gestor.getSintomasArchivo();
    }

}
