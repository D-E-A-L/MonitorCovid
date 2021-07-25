package cargarsintomas.guisintomas;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cargarsintomas.GestorSintomas;
import cargarsintomas.LeerSubClases;
import cargarsintomas.utilitarios.OperacionSinonimos;
import cargarsintomas.utilitarios.OperacionSintomas;
import cargarsintomas.validacion.ValidarEntrada;
import monitor.Sintoma;
import monitor.Sintomas;

import static cargarsintomas.utilitarios.OperacionMedia.dicIntString;
import static cargarsintomas.utilitarios.OperacionMedia.mostOpLista;
import static cargarsintomas.utilitarios.OperacionMedia.noRepetido;
import static cargarsintomas.utilitarios.OperacionSimple.mostSintReg;
import static cargarsintomas.utilitarios.OperacionSimple.mostListDatos;
import static cargarsintomas.utilitarios.Ordenar.ordAlf;
import static cargarsintomas.utilitarios.Ordenar.ordPorCat;

public class ConsolaSintomas {

    private final Sintomas SINTOMAS;
    private final OperacionSintomas OPSINT;
    private final OperacionSinonimos OPALT;
    private final ValidarEntrada CTRENT;
    private final GestorSintomas GESTOR_SINTOMAS;

    private final List<String> LISTAHIJOS;
    private final Map<Integer,String> DICCLISTHIJOS;

    private List<List<String>> dobleListSint;
    private boolean rbool;

    public ConsolaSintomas(String rSint, String rSino, String rHijos){
        GESTOR_SINTOMAS = new GestorSintomas(rSint,rHijos);
        OPALT = new OperacionSinonimos(rSino);
        CTRENT = new ValidarEntrada();
        OPSINT = new OperacionSintomas();
        LeerSubClases LESC = new LeerSubClases();
        SINTOMAS = GESTOR_SINTOMAS.getSintomasArchivo();
        LISTAHIJOS = LESC.listarHijos(rHijos, Sintoma.class);
        DICCLISTHIJOS = dicIntString(LISTAHIJOS);
        dobleListSint = OPSINT.aDobleListaString(SINTOMAS);
        rbool = true;
        mostNuevaOpc();
    }

    private void mostNuevaOpc(){
        System.out.println("\nMENU SINTOMAS\n");
        Scanner sc = new Scanner(System.in);
        int n;
        while(rbool) {
            System.out.println("0.- Mostrar sintomas; 1.- Registrar nuevo sintoma; 2.- Orden para mostrar; 3.- Salir");
            n = sc.nextInt();
            switch (n){
                case 0 -> System.out.println(mostSintReg(SINTOMAS));
                case 1 -> mostrarTipoSint();
                case 2 -> ordenar();
                case 3 -> {
                    System.out.println("Gracias por confiar en nosostros");
                    rbool = false;
                }
            }
        }
    }

    private void mostrarTipoSint() {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println(mostSintReg(SINTOMAS));
        System.out.println(mostOpLista(LISTAHIJOS));
        while (rbool) {
            n = sc.nextInt();
            if ( n < DICCLISTHIJOS.size()) {
                registrar(DICCLISTHIJOS.get(n));
            } else if (n == DICCLISTHIJOS.size()) {
                registrarSintoma();
                System.out.println("Gracias por confiar en nosostros");
                rbool = false;
            }
        }
    }

    private void registrar(String nuCad) {
        String sintCad;
        Scanner sc = new Scanner(System.in);
        System.out.print(nuCad + ": ");
        sintCad = sc.nextLine();
        if(CTRENT.comprEntr(sintCad)) {
            if(!OPALT.empate(sintCad,dobleListSint) ) {
                dobleListSint = OPSINT.regDobListaSintoma(nuCad,sintCad,dobleListSint);
            } else {
                System.out.println("datos duplicados");
                System.out.println(mostListDatos(dobleListSint));
            }
        } else {
            System.out.println("datos incorrectos, ingrese de nuevo");
        }
        selOpcSeguirRegSint();
    }

    private void selOpcSeguirRegSint () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seguir registrando sintomas: 0.- Si; 1.- No (Salir y guardar)");
        int a = sc.nextInt();
        while(rbool) {
            switch (a) {
                case 0 -> mostrarTipoSint();
                case 1 -> {
                    registrarSintoma();
                    rbool = false;
                }
            }
        }
    }

    private void ordenar(){
        Sintomas sint;
        Scanner sc = new Scanner(System.in);
        int opc;
        boolean rbl = true;
        while(rbl) {
            System.out.println(mostSintReg(SINTOMAS));
            System.out.println("\n0.- Orden por categoria; 1.- Orden Alfabetico; 2.- Salir\n");
            opc = sc.nextInt();
            switch (opc) {
                case 0 -> {
                    sint = ordPorCat(SINTOMAS);
                    System.out.println("cambio de orden correcto");
                    System.out.println(mostSintReg(sint));
                }
                case 1 -> {
                    sint = ordAlf(SINTOMAS);
                    System.out.println("cambio de orden correcto");
                    System.out.println(mostSintReg(sint));
                }
                case 2 -> rbl = false;
            }
        }
    }

    private void registrarSintoma() {
        List<List<String>> nlist = OPSINT.aDobleListaString(SINTOMAS);
        dobleListSint = noRepetido(nlist,dobleListSint);
        GESTOR_SINTOMAS.escribir(dobleListSint);
        System.out.println(mostSintReg(SINTOMAS));
    }
}