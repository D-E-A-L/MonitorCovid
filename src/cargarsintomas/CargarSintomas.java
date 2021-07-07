package cargarsintomas;

import cargarsintomas.guisintomas.ConsolaSintomas;
import monitor.Sintomas;

import static cargarsintomas.utilitarios.EncontrarRutaSintomas.encRuta;

public class CargarSintomas {

    private final GestorSintomas GESTOR;

    private final String RUTA_SINTOMAS;
    private final String RUTA_SINONIMOS;
    private final String RUTA_HIJOS;

    public CargarSintomas() {
        RUTA_SINTOMAS = encRuta("cargarsintomas","sintomas.dat");
        RUTA_SINONIMOS = encRuta("cargarsintomas","sinonimos.csv");
        RUTA_HIJOS = "sintomas";
        GESTOR = new GestorSintomas(RUTA_SINTOMAS,RUTA_HIJOS);
        registrarSintomas();
    }

    private void registrarSintomas() {
        new ConsolaSintomas(RUTA_SINTOMAS,RUTA_SINONIMOS,RUTA_HIJOS);
    }

    public Sintomas getSintomas() {
        return GESTOR.getSintomasArchivo();
    }

}
