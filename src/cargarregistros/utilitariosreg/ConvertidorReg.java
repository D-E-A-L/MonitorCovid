package cargarregistros.utilitariosreg;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class ConvertidorReg {

    //region private methods
    private static Sintomas devSintomas(String paquete, String tipo, String nombreSintoma, Sintomas sintomas){
        Sintoma sintoma = null;
        try {
            Class<?> cl = Class.forName(paquete + "." + tipo);
            Constructor<?> constructor = cl.getConstructor(String.class);
            sintoma = (Sintoma)constructor.newInstance(nombreSintoma);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        sintomas.add(sintoma);
        return sintomas;
    }

    private static List<Registro> listaRegistros(Registros registros) {
        List<Registro> lreg = new ArrayList<>();
        if(!registros.isEmpty()) {
            for(Registro re: registros) {
                lreg.add(re);
            }
        }
        return lreg;
    }

    private static String convDateString(Date date){
        SimpleDateFormat sdatef = new SimpleDateFormat("yyy/MM/dd");
        return sdatef.format(date);
    }

    private static List<Sintoma> convStackList(Stack<Sintoma> st) {
        List<Sintoma> list = new ArrayList<>();
        if(!st.isEmpty()) {
            list.addAll(st);
        } return list;
    }
    //endregion

    //region public methods used in ConsolaRegistros
    public static boolean regHoy(Registros registros){
       List<Registro> reg = listaRegistros(registros);
       String s1 = convDateString(reg.get(reg.size()-1).getFecha());
       String s2 = convDateString(new Date());
       return s1.equals(s2);
    }
    //endregion

    //region public methods used in GestorRegistros
    public static Sintomas devolverSints (String paquete, List<List<String>> lsint) {
        Sintomas sintomas = new Sintomas();
        for(List<String> ls: lsint) {
            sintomas = devSintomas(paquete,ls.get(0),ls.get(1),sintomas);
        }
        return sintomas;
    }
    //endregion

    //region public methods used in OrdenarSintomasReg
    public static List<String> convrStackList(Stack<String> st){
        List<String> list = new ArrayList<>();
        if(!st.isEmpty()) {
            list.addAll(st);
        } return list;
    }

    public static Sintomas convrStackSint(Stack<Sintoma> st){
        Sintomas sint = new Sintomas();
        List<Sintoma> ns = convStackList(st);
        if(!ns.isEmpty()) {
            for(Sintoma re : ns) {
                sint.add(re);
            }
        } return sint;
    }
    //endregion

}
