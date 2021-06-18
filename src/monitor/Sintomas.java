package monitor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Sintomas implements Iterable<Sintoma>, Serializable {
    private Set<Sintoma> sintomas = new HashSet();

    public Sintomas() {
    }

    public void add(Sintoma s) {
        this.sintomas.add(s);
    }

    public Iterator<Sintoma> iterator() {
        return this.sintomas.iterator();
    }
}
