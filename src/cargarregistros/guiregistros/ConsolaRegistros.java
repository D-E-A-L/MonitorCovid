package cargarregistros.guiregistros;

import cargarsintomas.CargarSintomas;
import cargarsintomas.GestorSintomas;
import monitor.Sintomas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsolaRegistros {

    private Sintomas sintomas;

    private final OperacionRegisro OPR;
    private final GestorSintomas G_SINT;
    private final CargarSintomas C_SINT;

    private List<List<Object>> reg;
    private List<List<String>> listReg;

    public ConsolaRegistros(String rsint){
        OPR = new OperacionRegisro(rsint);
        G_SINT = new GestorSintomas(rsint);
        C_SINT = new CargarSintomas();
        sintomas = new Sintomas();
        listReg = new ArrayList<>();
        reg = new ArrayList<>();
    }


    public void realizarRegistros() {
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        boolean rbl = true;
        while(rbl) {
            System.out.println(OPR.mostOpc());
            opc = sc.nextInt();
            if(opc < OPR.tamDic()) {

            } else if (opc == OPR.tamDic()) {

            }
        }
    }

}
