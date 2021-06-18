package cargarsintomas.guisintomas;

import cargarsintomas.GestorSintomas;
import cargarsintomas.LeerSubClases;
import monitor.Sintoma;
import monitor.Sintomas;
import bd.LeerBD;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static extra.OperacionMedia.*;
import static extra.OperacionSimple.mostListDatos;
import static extra.OperacionSimple.obtExt;

public class ConsolaSintomas {

    private Sintomas sintomas;
    private final OperacionSintomas OPSINT;
    private final OperacionSinonimos OPALT;
    private final LeerBD leerBD;
    private final ValidarEntrada CTRENT;
    private final GestorSintomas GESTOR_SINTOMAS;

    private final List<String> LISTAHIJOS;
    private final Map<Integer,String> DICCLISTHIJOS;

    private final String RUTA_SINONIMOS;
    private final String RUTA_SINTOMAS;
    private final String RUTA_HIJOS;

    private List<List<String>> dobleListSint;

    public ConsolaSintomas(String rSint, String rSino, String rHijos){
        RUTA_SINONIMOS = rSino;
        RUTA_SINTOMAS = rSint;
        RUTA_HIJOS = rHijos;
        GESTOR_SINTOMAS = new GestorSintomas(RUTA_SINTOMAS);
        OPALT = new OperacionSinonimos(RUTA_SINONIMOS);
        CTRENT = new ValidarEntrada();
        OPSINT = new OperacionSintomas();
        leerBD = new LeerBD();
        LeerSubClases LESC = new LeerSubClases();
        sintomas = GESTOR_SINTOMAS.getSintomasArchivo();
        LISTAHIJOS = LESC.listarHijos(RUTA_HIJOS, Sintoma.class);
        DICCLISTHIJOS = dicIntString(LISTAHIJOS);
        dobleListSint = OPSINT.aDobleListaString(sintomas);
    }

    public void mostrarTipoSint() {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println(mostOpLista(LISTAHIJOS));
        while (true) {
            n = sc.nextInt();
            if ( n < DICCLISTHIJOS.size()) {
                registrar(DICCLISTHIJOS.get(n));
            } else if (n == DICCLISTHIJOS.size()) {
                registrarSintoma(/*dobleListSint*/);
                System.out.println("Gracias por confiar en nosostros");
                System.exit(0);
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
        switch (a) {
            case 0 -> mostrarTipoSint();
            case 1 -> {
                registrarSintoma();
                System.exit(0);
            }
        }
    }

    public void registrarSintoma() {
        List<List<String>> nlist = OPSINT.aDobleListaString(sintomas);
        dobleListSint = noRepetido(nlist,dobleListSint);
        Sintomas sin1 = OPSINT.regSintomas(dobleListSint);
        for(Sintoma s : sin1) {
            System.out.println(obtExt(""+s.getClass())+"-->"+s.getNombre());
        }
        GESTOR_SINTOMAS.escribir(dobleListSint);
    }

}