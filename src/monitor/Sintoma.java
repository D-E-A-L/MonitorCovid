package monitor;

import java.io.Serializable;
import java.util.Objects;

public abstract class Sintoma implements Comparable<Sintoma>, Serializable {

    private String nombre;

    public Sintoma(String nombre) {
        this.nombre = nombre;
    }

    public abstract int peso();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Sintoma sintoma = (Sintoma)obj;
            return this.nombre.equals(sintoma.nombre);
        }
    }

    public String toString() {
        return this.nombre;
    }

    public int compareTo(Sintoma sintoma) {
        return this.nombre.compareTo(sintoma.nombre);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.nombre});
    }
}
