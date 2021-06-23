package cargarsintomas.guisintomas;

import cargarsintomas.GestorSintomas;
import cargarsintomas.LeerSubClases;
import monitor.Sintoma;
import monitor.Sintomas;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static cargarsintomas.extra.OperacionMedia.*;
import static cargarsintomas.extra.OperacionSimple.mostListDatos;
import static cargarsintomas.extra.OperacionSimple.obtExt;

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
        mostrarTipoSint();
    }

    private void mostrarTipoSint() {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println(mostOpLista(LISTAHIJOS));
        while (rbool) {
            n = sc.nextInt();
            if ( n < DICCLISTHIJOS.size()) {
                registrar(DICCLISTHIJOS.get(n));
            } else if (n == DICCLISTHIJOS.size()) {
                registrarSintoma();
                System.out.println("Gracias por confiar en nosostros");
                rbool = false;
                //System.exit(0);
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

    public void registrarSintoma() {
        List<List<String>> nlist = OPSINT.aDobleListaString(SINTOMAS);
        dobleListSint = noRepetido(nlist,dobleListSint);
        GESTOR_SINTOMAS.escribir(dobleListSint);
        for(Sintoma s : GESTOR_SINTOMAS.getSintomasArchivo()) {
            //System.out.println(obtExt(""+s.getClass())+"-->"+s.getNombre());
            System.out.println(obtExt(""+s.getClass())+"-->"+s.toString());
        }
    }

}