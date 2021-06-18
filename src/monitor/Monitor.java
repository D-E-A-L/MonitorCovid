package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;

public class Monitor {
    private Sintomas sintomas;
    private Registros registros;
    private FuncionDiagnostico funcion;
    private int resultadoDiagnostico;
    private CargarRegistros cargarRegistros;

    public Monitor() {
        CargarSintomas cargarSintomas = new CargarSintomas();
        sintomas = cargarSintomas.getSintomas();
        //this.funcion = new DiagnosticoSimple(this.sintomas);
        //registros = new Registros();
        //cargarRegistros = new CargarRegistros(sintomas);
    }

    public void monitorear() {
        //Registro registro = this.cargarRegistros.getRegistro();
        //this.registros.push(registro);
        this.resultadoDiagnostico = this.funcion.diagnostico(this.registros);
    }

    public int getResultado() {
        return this.resultadoDiagnostico;
    }
}