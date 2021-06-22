package monitor;

import java.io.Serializable;
import java.util.Date;

public class Registro implements Serializable {
    private Date fecha;
    private Sintomas sintomas;

    public Registro(Date f, Sintomas s) {
        this.fecha = f;
        this.sintomas = s;
    }

    public Sintomas getSintomas() {
        return this.sintomas;
    }
}
