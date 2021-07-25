package diagnosticos;

import diagnosticos.guiConsejos.ConsolaConsejos;
import monitor.*;

import java.util.Stack;

import static diagnosticos.utilitariosDiagnosticosPorFase.UtilitariosDiagnosticosPorFase.*;

public class DiagnosticoPorFase extends FuncionDiagnostico {

    private final ConsolaConsejos CONS_CONSEJO;
    private final DatosFase DATOS_FASE;
    private final Fase FASE;

    private boolean persiste;


    public DiagnosticoPorFase(Sintomas sintomas){
        super(sintomas);
        DATOS_FASE = new DatosFase();
        CONS_CONSEJO = new ConsolaConsejos();
        FASE = DATOS_FASE.leerDatosFase();
        persiste = false;
    }

    @Override
    public int diagnostico(Registros registros) {
        int dia = FASE.getDia()+1;
        int pesosSint = 0, sintDia = 0, pesoPorFase = 0, nrod = 0, sintFase = 0;
        if(FASE.getNombre().equals("PrimeraFase")) {
            sintDia = 70;
            pesoPorFase = 10;
            nrod = 3;
            sintFase = 180;
            CONS_CONSEJO.mostConsejos(dia);
        } if(FASE.getNombre().equals("SegundaFase")) {
            sintDia = 400;
            pesoPorFase = 100;
            nrod = 5;
            sintFase = 2000;
            CONS_CONSEJO.mostConsejos(dia);
        } if(!registros.isEmpty()) {
            persiste = controlReg(registros);
            Registro registro = registros.peek();
            Sintomas sintomas = registro.getSintomas();
            pesosSint = calcPesoSint(sintomas);
        } if(pesosSint >= sintDia) {
            CONS_CONSEJO.mostConsejos(dia);
            FASE.setDia(dia);
            DATOS_FASE.guardarDatosFase(FASE);
        } if (pesosSint < sintDia) {
            FASE.setDia(0);
            DATOS_FASE.guardarDatosFase(FASE);
            CONS_CONSEJO.mostConsejos(0);
        } if (!persiste) {
            CONS_CONSEJO.mostConsejos(10);
            if(!CONS_CONSEJO.getConsultaMedic()) {
                FASE.setDia(0);
            }
        } diagReg(registros,nrod,pesoPorFase,sintDia,sintFase);
        return pesosSint;
    }

    public void diagReg(Registros registros, int nrod,int pesoFase,int minSintD,int minSintFa) {
        Sintomas sin;
        int pesoRegistros = 0;
        boolean esContante;
        Stack<Registro> regs = convRegStack(registros);
        if (regs.size() >= nrod) {
            while (nrod > 0) {
                int pesoRegistro=0;
                sin = regs.pop().getSintomas();
                for (Sintoma sintoma : sin) {
                    if (sintoma.peso() == pesoFase) {
                        pesoRegistro = pesoRegistro + sintoma.peso();
                    }
                }
                if(pesoRegistro<minSintD){
                    pesoRegistros=pesoRegistros-pesoRegistro; }
                nrod--;
            }
            esContante=controlFase(registros,3);
            if (pesoRegistros >= minSintFa && esContante) {
                FASE.setNombre("SegundaFase");
                FASE.setDia(0);
                DATOS_FASE.guardarDatosFase(FASE);
                System.out.println("Peso total de fase: "+ pesoRegistros);
            }
        }
    }
}
