package cargarregistros.guiregistros;

import cargarregistros.GestorRegistros;
import cargarregistros.utilitariosreg.OperacionRegisro;
import monitor.Registros;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsolaRegistros {

    private final OperacionRegisro OPR;

    private final List<List<String>> LISST_SINT_REG;

    private final GestorRegistros GESTOR;
    private final Registros REGS;

    public ConsolaRegistros(String ruta_reg, Sintomas sintomas){
        OPR = new OperacionRegisro(sintomas);
        GESTOR = new GestorRegistros(ruta_reg);
        LISST_SINT_REG = new ArrayList<>();
        REGS = GESTOR.getRegistrosArchivo();
        registrarRegistros();
    }

    private void registrarRegistros() {
        System.out.println("\nConsola para registros\n");
        System.out.println(OPR.mostrarRegistros(REGS));
        if(OPR.tamSintomas() > 0) {
            realizarReg();
        }
    }

    private void realizarReg (){
        Scanner sc = new Scanner(System.in);
        int opc;
        boolean rb = true;
        while(rb) {
            System.out.println("0.- Realizar registro; 1.- Salir");
            opc = sc.nextInt();
            switch (opc) {
                case 0 -> {
                    OPR.restablecer();
                    mostrarOpciones();
                }
                case 1 -> rb = false;
            }
        }
    }

    private void mostrarOpciones() {
        Scanner sc = new Scanner(System.in);
        int opc;
        boolean rBool = true;
        while(rBool) {
            System.out.println(OPR.mostOpc());
            opc = sc.nextInt();
            if(opc < OPR.tamSintomas()) {
                LISST_SINT_REG.add(OPR.obtTipoSint(opc));
            } else if (opc == OPR.tamSintomas()) {
                GESTOR.guardarRegistro(LISST_SINT_REG);
                System.out.println("Se registro correctamente\n");
                System.out.println(OPR.mostrarRegistros(REGS));
                rBool = false;
            }
        }
    }

}
