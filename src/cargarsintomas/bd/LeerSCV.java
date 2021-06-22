package cargarsintomas.bd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cargarsintomas.extra.OperacionSimple.deArrayAList;

public final class LeerSCV {

    private static final char SEPARADOR = ';';

    //region metodos privados
    private static List<List<String>> leerArchCSV(String rutArch) {
        try {
            List<List<String>> listFileCSV;
            try (BufferedReader reader = new BufferedReader(new FileReader(rutArch))) {
                listFileCSV = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    List<String> tList = deArrayAList(line.split(Character.toString(SEPARADOR)));
                    listFileCSV.add(tList);
                }
            }
            return listFileCSV;
        } catch (IOException exReader) {
            exReader.printStackTrace();
            return null;
        }
    }
    //endregion metodos privados
    public static List<List<String>> leerCSV(String nr) {
        List<List<String>> listR = leerArchCSV(nr);
        List<List<String>> nList = new ArrayList<>();
        for (int i = 1; i < (listR != null ? listR.size() : 0); i++) {
            nList.add(listR.get(i));
        }
        return nList;
    }

}
