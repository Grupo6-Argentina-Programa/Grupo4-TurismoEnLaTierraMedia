package testeos;

import sugeribles.atracciones.Atraccion;
import sugeribles.atracciones.ENUMTIPO;
import sistema.Sistema;
import usuarios.Usuario;
import org.junit.Test;
import sugeribles.promociones.IPromocion;
import sugeribles.promociones.PromocionAbsoluta;
import sugeribles.promociones.PromocionAxB;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {
    @Test
    public void testConstructor(){
        Atraccion atraccion = new Atraccion("asd",2,3,4, ENUMTIPO.PAISAJE);
        System.out.println(atraccion);
        assertNotNull(atraccion);
    }

    @Test
    public void testSistema() throws Exception {
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(new Usuario(ENUMTIPO.PAISAJE, 7, 10));
        usuarioList.add(new Usuario(ENUMTIPO.ADVENTURA, 9, 15));
        usuarioList.add(new Usuario(ENUMTIPO.PAISAJE, 100, 123));
        Atraccion[] atracciones = new Atraccion[5];
        atracciones[0] = new Atraccion("Atraccion prueba 1", 12, 3, 5, ENUMTIPO.PAISAJE);
        atracciones[1] = new Atraccion("Atraccion prueba 2", 23, 8, 3, ENUMTIPO.PAISAJE);
        atracciones[2] = new Atraccion("Atraccion prueba 3", 5, 6, 63, ENUMTIPO.ADVENTURA);
        atracciones[3] = new Atraccion("Atraccion prueba 4", 12, 1, 6, ENUMTIPO.ADVENTURA);
        atracciones[4] = new Atraccion("Atraccion prueba 5", 7, 4, 32, ENUMTIPO.PAISAJE);
        IPromocion[] promociones = new IPromocion[2];
        promociones[0] = new PromocionAbsoluta(atracciones[2], atracciones[3], 6);
        promociones[1] = new PromocionAxB(atracciones[0], atracciones[1], atracciones[4]);
        Sistema sistema = new Sistema(usuarioList, atracciones, promociones);
        int i = 0;
        for (Usuario usuario : usuarioList) {
            System.out.println("Usuario numero " + i++ + "\n");
            sistema.sugerir(usuario);
        }
        assertNotEquals(7, usuarioList.get(0).getPresupuesto());


    }
}
