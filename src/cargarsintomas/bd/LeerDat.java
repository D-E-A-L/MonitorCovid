package cargarsintomas.bd;

import java.io.*;
import java.util.*;

public final class LeerDat {
    //region metodos publicos
    public static List<Object> leerDat(String ruta) {
        List<Object> listObjetos = new ArrayList<>();
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(ruta));
            listObjetos = (List<Object>) entrada.readObject();
            entrada.close();
        } catch (Exception exc) {
        }

        return listObjetos;
    }
    //endregion


}
