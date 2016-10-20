package mx.unam.ciencias.icc.test;

import java.util.Iterator;
import java.util.Random;
import mx.unam.ciencias.icc.ListaCadena;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link ListaCadena}.
 */
public class TestListaCadena {

    private Random random;
    private int total;
    private ListaCadena lista;

    /**
     * Crea un generador de números aleatorios para cada prueba, un número total
     * de elementos para nuestra lista, y una lista.
     */
    public TestListaCadena() {
        random = new Random();
        total = 10 + random.nextInt(90);
        lista = new ListaCadena();
    }

    /**
     * Prueba unitaria para {@link ListaCadena#getLongitud}.
     */
    @Test public void testGetLongitud() {
        Assert.assertTrue(lista.getLongitud() == 0);
        for (int i = 0; i < total/2; i++) {
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
            Assert.assertTrue(lista.getLongitud() == i + 1);
        }
        for (int i = total/2; i < total; i++) {
            lista.agregaInicio(String.valueOf(random.nextInt(total)));
            Assert.assertTrue(lista.getLongitud() == i + 1);
        }
        Assert.assertTrue(lista.getLongitud() == total);
    }

    /**
     * Prueba unitaria para {@link ListaCadena#esVacia}.
     */
    @Test public void testEsVacia() {
        Assert.assertTrue(lista.esVacia());
        lista.agregaFinal("1");
        Assert.assertFalse(lista.esVacia());
        lista.eliminaUltimo();
        Assert.assertTrue(lista.esVacia());
    }

    /**
     * Prueba unitaria para {@link ListaCadena#agregaFinal}.
     */
    @Test public void testAgregaFinal() {
        lista.agregaFinal("1");
        Assert.assertTrue("1".equals(lista.getUltimo()));
        lista.agregaInicio("2");
        Assert.assertFalse("2".equals(lista.getUltimo()));
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaFinal(e);
            Assert.assertTrue(e.equals(lista.getUltimo()));
        }
    }

    /**
     * Prueba unitaria para {@link ListaCadena#agregaInicio}.
     */
    @Test public void testAgregaInicio() {
        lista.agregaInicio("1");
        Assert.assertTrue("1".equals(lista.getPrimero()));
        lista.agregaFinal("2");
        Assert.assertFalse("2".equals(lista.getPrimero()));
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaInicio(e);
            Assert.assertTrue(e.equals(lista.getPrimero()));
        }
    }

    /**
     * Prueba unitaria para {@link ListaCadena#elimina}.
     */
    @Test public void testElimina() {
        lista.elimina("0");
        lista.agregaFinal("1");
        Assert.assertFalse(lista.esVacia());
        lista.eliminaUltimo();
        Assert.assertTrue(lista.esVacia());
        int d = random.nextInt(total);
        String m = "";
        for (int i = 0; i < total; i++) {
            lista.agregaInicio(String.valueOf(d++));
            if (i == total / 2)
                m = String.valueOf(d - 1);
        }
        String p = lista.getPrimero();
        String u = lista.getUltimo();
        Assert.assertTrue(lista.contiene(p));
        Assert.assertTrue(lista.contiene(m));
        Assert.assertTrue(lista.contiene(u));
        lista.elimina(p);
        Assert.assertFalse(lista.contiene(p));
        Assert.assertTrue(lista.getLongitud() == --total);
        lista.elimina(m);
        Assert.assertFalse(lista.contiene(m));
        Assert.assertTrue(lista.getLongitud() == --total);
        lista.elimina(u);
        Assert.assertFalse(lista.contiene(u));
        Assert.assertTrue(lista.getLongitud() == --total);
        while (lista.getLongitud() > 0) {
            lista.elimina(lista.getPrimero());
            Assert.assertTrue(lista.getLongitud() == --total);
            if (lista.getLongitud() == 0)
                continue;
            lista.elimina(lista.getUltimo());
            Assert.assertTrue(lista.getLongitud() == --total);
        }
        Assert.assertTrue(lista.getPrimero() == null);
        Assert.assertTrue(lista.getUltimo() == null);
    }

    /**
     * Prueba unitaria para {@link ListaCadena#eliminaPrimero}.
     */
    @Test public void testEliminaPrimero() {
        Assert.assertTrue(lista.eliminaPrimero() == null);
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(random.nextInt(total));
            lista.agregaFinal(a[i]);
        }
        int i = 0;
        int n = total;
        while (lista.getLongitud() > 0) {
            Assert.assertTrue(n-- == lista.getLongitud());
            String k = lista.eliminaPrimero();
            Assert.assertTrue(k.equals(a[i++]));
        }
        Assert.assertTrue(lista.getPrimero() == null);
        Assert.assertTrue(lista.getUltimo() == null);
    }

    /**
     * Prueba unitaria para {@link ListaCadena#eliminaUltimo}.
     */
    @Test public void testEliminaUltimo() {
        Assert.assertTrue(lista.eliminaUltimo() == null);
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(random.nextInt(total));
            lista.agregaFinal(a[i]);
        }
        int i = 0;
        int n = total;
        while (lista.getLongitud() > 0) {
            Assert.assertTrue(n-- == lista.getLongitud());
            String k = lista.eliminaUltimo();
            Assert.assertTrue(k.equals(a[total - ++i]));
        }
        Assert.assertTrue(lista.getPrimero() == null);
        Assert.assertTrue(lista.getUltimo() == null);
    }

    /**
     * Prueba unitaria para {@link ListaCadena#contiene}.
     */
    @Test public void testContiene() {
        Assert.assertFalse(lista.contiene("0"));
        int d = random.nextInt(total);
        String m = "";
        String n = String.valueOf(d - 1);
        for (int i = 0; i < total; i++) {
            lista.agregaFinal(String.valueOf(d++));
            if (i == total/2)
                m = String.valueOf(d - 1);
        }
        Assert.assertTrue(lista.contiene(m));
        Assert.assertTrue(lista.contiene(new String(m)));
        Assert.assertFalse(lista.contiene(n));
    }

    /**
     * Prueba unitaria para {@link ListaCadena#reversa}.
     */
    @Test public void testReversa() {
        ListaCadena reversa = lista.reversa();
        Assert.assertTrue(reversa.esVacia());
        Assert.assertFalse(reversa == lista);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        reversa = lista.reversa();
        Assert.assertTrue(reversa.getLongitud() == lista.getLongitud());
        lista.primero();
        reversa.ultimo();
        while (lista.iteradorValido() && reversa.iteradorValido()) {
            Assert.assertTrue(lista.get() == reversa.get());
            lista.siguiente();
            reversa.anterior();
        }
        Assert.assertFalse(lista.iteradorValido());
        Assert.assertFalse(reversa.iteradorValido());
    }

    /**
     * Prueba unitaria para {@link ListaCadena#copia}.
     */
    @Test public void testCopia() {
        ListaCadena copia = lista.copia();
        Assert.assertTrue(copia.esVacia());
        Assert.assertFalse(copia == lista);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        copia = lista.copia();
        Assert.assertFalse(lista == copia);
        lista.primero();
        copia.primero();
        while (lista.iteradorValido() && copia.iteradorValido()) {
            Assert.assertTrue(lista.get() == copia.get());
            lista.siguiente();
            copia.siguiente();
        }
        Assert.assertFalse(lista.iteradorValido());
        Assert.assertFalse(copia.iteradorValido());
    }

    /**
     * Prueba unitaria para {@link ListaCadena#limpia}.
     */
    @Test public void testLimpia() {
        String primero = String.valueOf(random.nextInt(total));
        lista.agregaFinal(primero);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        String ultimo = String.valueOf(random.nextInt(total));
        lista.agregaFinal(ultimo);
        Assert.assertFalse(lista.esVacia());
        Assert.assertTrue(primero.equals(lista.getPrimero()));
        Assert.assertTrue(ultimo.equals(lista.getUltimo()));
        Assert.assertTrue(lista.getLongitud() != 0);
        lista.limpia();
        Assert.assertTrue(lista.esVacia());
        Assert.assertTrue(lista.getLongitud() == 0);
        Assert.assertTrue(lista.getPrimero() == null);
        Assert.assertTrue(lista.getUltimo() == null);
    }

    /**
     * Prueba unitaria para {@link ListaCadena#getPrimero}.
     */
    @Test public void testGetPrimero() {
        Assert.assertTrue(null == lista.getPrimero());
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaInicio(e);
            Assert.assertTrue(e.equals(lista.getPrimero()));
        }
    }

    /**
     * Prueba unitaria para {@link ListaCadena#getUltimo}.
     */
    @Test public void testGetUltimo() {
        Assert.assertTrue(null == lista.getUltimo());
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaFinal(e);
            Assert.assertTrue(e.equals(lista.getUltimo()));
        }
    }

    /**
     * Prueba unitaria para {@link ListaCadena#get(int)}.
     */
    @Test public void testGet() {
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(random.nextInt(total));
            lista.agregaFinal(a[i]);
        }
        for (int i = 0; i < total; i++)
            Assert.assertTrue(lista.get(i) == a[i]);
        Assert.assertTrue(lista.get(-1) == null);
        Assert.assertTrue(lista.get(total) == null);
    }

    /**
     * Prueba unitaria para {@link ListaCadena#indiceDe}.
     */
    @Test public void testIndiceDe() {
        Assert.assertTrue(lista.indiceDe("0") == -1);
        int ini = random.nextInt(total);
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(ini + i);
            lista.agregaFinal(a[i]);
        }
        for (int i = 0; i < total; i ++)
            Assert.assertTrue(i == lista.indiceDe(a[i]));
        Assert.assertTrue(lista.indiceDe(String.valueOf(ini - 10)) == -1);
    }

    /**
     * Prueba unitaria para {@link ListaCadena#toString}.
     */
    @Test public void testToString() {
        Assert.assertTrue("[]".equals(lista.toString()));
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(i);
            lista.agregaFinal(a[i]);
        }
        String s = "[";
        for (int i = 0; i < total-1; i++)
            s += String.format("%s, ", a[i]);
        s += String.format("%s]", a[total-1]);
        Assert.assertTrue(s.equals(lista.toString()));
    }

    /**
     * Prueba unitaria para {@link ListaCadena#primero}.
     */
    @Test public void testPrimero() {
        lista.agregaInicio("1");
        lista.ultimo();
        lista.primero();
        Assert.assertTrue(lista.get().equals("1"));
        lista.agregaFinal("2");
        lista.ultimo();
        lista.primero();
        Assert.assertFalse(lista.get().equals("2"));
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaInicio(e);
            lista.ultimo();
            lista.primero();
            Assert.assertTrue(lista.get().equals(e));
        }
    }

    /**
     * Prueba unitaria para {@link ListaCadena#ultimo}.
     */
    @Test public void testUltimo() {
        for (int i = 0; i < total; i++) {
            String u = String.valueOf(random.nextInt(total));
            lista.agregaFinal(u);
            lista.ultimo();
            Assert.assertTrue(lista.get().equals(u));
        }
    }

    /**
     * Prueba unitaria para {@link ListaCadena#siguiente}.
     */
    @Test public void testSiguiente() {
        int ini = random.nextInt(total);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(ini + i));
        lista.primero();
        String n = (String)lista.get();
        lista.siguiente();
        Assert.assertFalse(lista.get().equals(n));
    }

    /**
     * Prueba unitaria para {@link ListaCadena#anterior}.
     */
    @Test public void testAnterior() {
        int ini = random.nextInt(total);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(ini + i));
        lista.ultimo();
        String n = (String)lista.get();
        lista.anterior();
        Assert.assertFalse(lista.get().equals(n));
    }

    /**
     * Prueba unitaria para {@link ListaCadena#get}.
     */
    @Test public void testGetIterador() {
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaFinal(e);
            a[i] = e;
        }
        int i = 0;
        lista.primero();
        while (lista.iteradorValido()) {
            Assert.assertTrue(lista.get().equals(a[i++]));
            lista.siguiente();
        }
        lista.limpia();
    }

    /**
     * Prueba unitaria para {@link ListaCadena#iteradorValido}.
     */
    @Test public void testIteradorValido() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        lista.primero();
        Assert.assertTrue(lista.iteradorValido());
        lista.ultimo();
        Assert.assertTrue(lista.iteradorValido());
        lista.primero();
        lista.anterior();
        Assert.assertFalse(lista.iteradorValido());
        lista.ultimo();
        lista.siguiente();
        Assert.assertFalse(lista.iteradorValido());
    }
}
