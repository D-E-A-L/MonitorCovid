package monitor;

public abstract class FuncionDiagnostico {
    private Sintomas sintomas;

    public FuncionDiagnostico(Sintomas s) {
        this.sintomas = s;
    }

    public abstract int diagnostico(Registros var1);
}