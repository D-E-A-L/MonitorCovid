package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoSimple;

public class Monitor {
    private Sintomas sintomas;
    private Registros registros;
    private FuncionDiagnostico funcion;
    private int resultadoDiagnostico;
    private CargarRegistros cargarRegistros;

    public Monitor() {
        CargarSintomas cargarSintomas = new CargarSintomas();
        this.sintomas = cargarSintomas.getSintomas();
        this.funcion = new DiagnosticoSimple(this.sintomas);
        this.registros = new Registros();
        this.cargarRegistros = new CargarRegistros(this.sintomas);
    }

    public void monitorear() {
        Registro registro = this.cargarRegistros.getRegistro();
        this.registros.push(registro);
        this.resultadoDiagnostico = this.funcion.diagnostico(this.registros);
    }

    public int getResultado() {
        return this.resultadoDiagnostico;
    }
}