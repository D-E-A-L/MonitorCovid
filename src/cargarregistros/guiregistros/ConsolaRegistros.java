package cargarregistros.guiregistros;

import cargarregistros.GestorRegistros;
import cargarregistros.utilitariosreg.OpeMedReg;
import monitor.Registros;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static cargarregistros.utilitariosreg.ConvertidorReg.regHoy;
import static cargarregistros.utilitariosreg.OrdenarSintomasReg.ordAlf;
import static cargarregistros.utilitariosreg.OrdenarSintomasReg.ordPorCat;

public class ConsolaRegistros {

    private final OpeMedReg OPR;

    private final List<List<String>> LISST_SINT_REG;

    private final GestorRegistros GESTOR;
    private final Registros REGS;

    private final Sintomas SINTOMAS;

    public ConsolaRegistros(String ruta_reg, Sintomas sintomas){
        OPR = new OpeMedReg(sintomas);
        GESTOR = new GestorRegistros(ruta_reg);
        LISST_SINT_REG = new ArrayList<>();
        REGS = GESTOR.getRegistrosArchivo();
        SINTOMAS = sintomas;
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
            System.out.println("0.- Realizar registro; 1.- Ordenar; 2.- Salir");
            opc = sc.nextInt();
            switch (opc) {
                case 0 -> {
                    OPR.restablecer();
                    if(!regHoy(GESTOR.getRegistrosArchivo())) {
                        mostrarOpciones();
                    } else {
                        System.out.println("Ya se registro, no puede volver a registrar por hoy");
                    }

                }
                case 1 -> ordenar();
                case 2 -> rb = false;
            }
        }
    }

    private void ordenar(){
        Scanner sc = new Scanner(System.in);
        int opc;
        boolean rbl = true;
        while(rbl) {
            System.out.println("0.- Orden por categoria; 1.- Orden Alfabetico; 2.- Salir");
            opc = sc.nextInt();
            switch (opc) {
                case 0 -> {
                    OPR.modificarSintomasOrd(ordPorCat(SINTOMAS));
                    System.out.println("cambio de orden correcto");
                    System.out.println(OPR.mostOpc1());
                }
                case 1 -> {
                    OPR.modificarSintomasOrd(ordAlf(SINTOMAS));
                    System.out.println("cambio de orden correcto");
                    System.out.println(OPR.mostOpc1());
                }
                case 2 -> rbl = false;
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
