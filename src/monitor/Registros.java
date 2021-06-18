package monitor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Stack;

public class Registros implements Iterable<Registro>, Serializable {
    private Stack<Registro> registros = new Stack();

    public Registros() {
    }

    public void push(Registro r) {
        this.registros.push(r);
    }

    public Registro peek() {
        return (Registro)this.registros.peek();
    }

    public boolean isEmpty() {
        return this.registros.isEmpty();
    }

    public Iterator<Registro> iterator() {
        return this.registros.iterator();
    }
}
