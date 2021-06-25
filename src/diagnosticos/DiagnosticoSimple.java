package diagnosticos;

import monitor.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DiagnosticoSimple extends FuncionDiagnostico {
    private Map<Sintoma, Integer> pesos = new HashMap();

    public DiagnosticoSimple(Sintomas ls) {
        super(ls);
        Iterator var2 = ls.iterator();

        while (var2.hasNext()) {
            Sintoma s = (Sintoma) var2.next();
            this.pesos.put(s, s.peso());
        }

    }

    public int diagnostico(Registros registros) {
        int pesoSintomas = 0;
        if (!registros.isEmpty()) {
            Registro registro = registros.peek();
            Sintomas sintomas = registro.getSintomas();
            Iterator iterator = sintomas.iterator();

            while (iterator.hasNext()) {
                Sintoma sint = (Sintoma) iterator.next();
                if (pesos.containsKey(sint)) {
                    pesoSintomas += (Integer) this.pesos.get(sint);
                }
            }
        }
        return pesoSintomas;
    }
}