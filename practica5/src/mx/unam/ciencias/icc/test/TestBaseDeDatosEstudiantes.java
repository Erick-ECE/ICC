package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosEstudiantes;
import mx.unam.ciencias.icc.Estudiante;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.Registro;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link BaseDeDatosEstudiantes}.
 */
public class TestBaseDeDatosEstudiantes {

    private static String[] NOMBRES = {
        "Aaron", "Benito", "Carlos", "Daniel", "Ernesto",
    };
    private static String[] APELLIDOS = {
        "Zapata", "Yacaxtle", "Xola", "Wellington", "Valdés",
    };    

    private Random random;
    private BaseDeDatosEstudiantes bdd;
    private int total;

    /**
     * Crea un generador de números aleatorios para cada prueba y una base de
     * datos de estudiantes.
     */
    public TestBaseDeDatosEstudiantes() {
        random = new Random();
        bdd = new BaseDeDatosEstudiantes();
        total = 1 + random.nextInt(100);
    }

    private String generaNombreAleatorio() {
        int n = random.nextInt(NOMBRES.length);
        int ap = random.nextInt(APELLIDOS.length);
        int am = random.nextInt(APELLIDOS.length);
        return NOMBRES[n] + " " + APELLIDOS[ap] + " " + APELLIDOS[am];
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosEstudiantes#BaseDeDatosEstudiantes}.
     */
    @Test public void testConstructor() {
        Lista registros = bdd.getRegistros();
        Assert.assertFalse(registros == null);
        Assert.assertTrue(registros.getLongitud() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testGetNumRegistros() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          random.nextInt(1000000),
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Lista l = bdd.getRegistros();
        Lista r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Estudiante[] estudiantes = new Estudiante[total];
        for (int i = 0; i < total; i++) {
            estudiantes[i] = new Estudiante(generaNombreAleatorio(),
                                            random.nextInt(1000000),
                                            random.nextDouble() * 10,
                                            18 + random.nextInt(10));
            bdd.agregaRegistro(estudiantes[i]);
        }
        l = bdd.getRegistros();
        l.primero();
        for (int i = 0; i < total; i++) {
            Assert.assertTrue(estudiantes[i].equals(l.get()));
            l.siguiente();
        }
        l.elimina(estudiantes[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          random.nextInt(1000000),
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
            Assert.assertFalse(bdd.getRegistros().contiene(e));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            Lista l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          ini + i,
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Estudiante e = (Estudiante)bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          random.nextInt(1000000),
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
            bdd.agregaRegistro(e);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        Lista l = bdd.getRegistros();
        l.primero();
        for (int i = 0; i < total; i++) {
            Estudiante e = (Estudiante)l.get();
            String el = String.format("%s\t%d\t%2.2f\t%d",
                                      e.getNombre(),
                                      e.getCuenta(),
                                      e.getPromedio(),
                                      e.getEdad());
            Assert.assertTrue(lineas[i].equals(el));
            l.siguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Estudiante[] estudiantes = new Estudiante[total];
        for (int i = 0; i < total; i++) {
            estudiantes[i] = new Estudiante(generaNombreAleatorio(),
                                            ini + i,
                                            /* Estúpida precisión. */
                                            random.nextInt(100) / 10.0,
                                            18 + random.nextInt(10));
            entrada += String.format("%s\t%d\t%2.2f\t%d\n",
                                     estudiantes[i].getNombre(),
                                     estudiantes[i].getCuenta(),
                                     estudiantes[i].getPromedio(),
                                     estudiantes[i].getEdad());
            bdd.agregaRegistro(estudiantes[i]);
        }
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Lista l = bdd.getRegistros();
        Assert.assertTrue(l.getLongitud() == total);
        l.primero();
        for (int i = 0; i < total; i++) {
            Estudiante e = (Estudiante)l.get();
            Assert.assertTrue(estudiantes[i].equals(e));
            l.siguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosEstudiantes#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Estudiante estudiante = new Estudiante(generaNombreAleatorio(),
                                                   ini + i,
                                                   random.nextDouble() * 10,
                                                   18 + random.nextInt(10));
            bdd.agregaRegistro(estudiante);
        }

        String busqueda = String.valueOf(ini).substring(0, 2);
        Lista l = bdd.buscaRegistros("cuenta", busqueda);
        Assert.assertTrue(l.getLongitud() == total);
        l.primero();
        while (l.iteradorValido()) {
            Estudiante e = (Estudiante)l.get();
            String c = String.valueOf(e.getCuenta());
            Assert.assertTrue(c.indexOf(busqueda) != -1);
            l.siguiente();
        }
        busqueda = String.valueOf(9999999);
        l = bdd.buscaRegistros("cuenta", busqueda);
        Assert.assertTrue(l.getLongitud() == 0);

        busqueda = String.valueOf(1);
        l = bdd.buscaRegistros("cuenta", busqueda);
        Assert.assertTrue(l.getLongitud() == total);
        l.primero();
        while (l.iteradorValido()) {
            Estudiante e = (Estudiante)l.get();
            String c = String.valueOf(e.getCuenta());
            Assert.assertTrue(c.indexOf(busqueda) != -1);
            l.siguiente();
        }
        busqueda = String.valueOf(9999999);
        l = bdd.buscaRegistros("cuenta", busqueda);
        Assert.assertFalse(l.getLongitud() != 0);

        try {
            l = bdd.buscaRegistros("no-existe", "");
        } catch (IllegalArgumentException iae) {
            return;
        }
        Assert.fail();
    }
}
