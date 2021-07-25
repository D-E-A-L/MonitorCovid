package diagnosticos.guiConsejos;

import java.util.Scanner;

public class ConsolaConsejos {

    private boolean consultMed;

    public ConsolaConsejos(){
        consultMed = false;
    }

    public void mostConsejos(int dia){
        if(dia >= 0) {
            switch(dia) {
                case 0 -> System.out.println("Bienvenido al control de sintomas de covid. \nSiga registrandose de ahora en adelante");
                case 1 -> System.out.println("Transcurrio un dia despues de su ultimo registro. \nSiga registrandose y sea persistente");
                case 2 -> System.out.println("Transcurrio dos dias despues de su ultimo registro. \nSea constante su vida puede correr riesgo");
                case 3 -> System.out.println("Se registro tres dias. \nEsta por pasar a la segunda Fase, cuidese.\nSe le recomienda que visite al medico");
                case 4 -> System.out.println("Esta en el primer dia de regitros de la segunda fase. \nPara prevenir puede ir a un medico a consultar.");
                case 5 -> System.out.println("Esta en el segundo dia de regitros de la segunda fase. \nSe le recomienda que visite un medico");
                case 6 -> System.out.println("Esta en el tercer dia de regitros de la segunda fase. \nCuidese y vaya a hacerse un chequeo medico.");
                case 7 -> System.out.println("Esta en el cuarto dia de regitros de la segunda fase. \nAsista a un centro medico para su revision.");
                case 8 -> System.out.println("Esta en el quinto dia de regitros de la segunda fase. \nEsta terminando la segunda fase de pruebas.\nEs muy importante que visite a un medico cuanto antes");
                case 10 -> {
                    System.out.println("No es constante en sus registros. \nVisito al medico?\n0.- Si; 1.- No");
                    subOpc();
                }
            }
        }
    }

    private void subOpc(){
        Scanner sc = new Scanner(System.in);
        int opc = sc.nextInt();
        switch (opc){
            case 0 -> {
                consultMed = true;
                System.out.println("Esperemos que este bien de salud, no se olvide de ir al holspital");
            }
            case 1 -> consultMed = false;
        }
    }

    public boolean getConsultaMedic(){
        return consultMed;
    }

}
