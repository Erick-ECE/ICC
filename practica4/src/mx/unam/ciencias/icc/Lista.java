package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas tienen un iterador para poder recorrerlas.</p>
 */
public class Lista {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
        public Object elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(Object elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Nodo iterador. */
    private Nodo iterador;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
         if(cabeza==null && rabo==null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(Object elemento) {
       Nodo n= new Nodo(elemento);
        if (esVacia()==true) {     
            rabo=n;
            cabeza=n;
        }
        else{
            rabo.siguiente=n;
            n.anterior=rabo;
            rabo=n;
        }
        longitud++;
        iterador=cabeza;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(Object elemento) {
        Nodo nodo= new Nodo(elemento);
        if (esVacia()==true) {
            cabeza=nodo;
            rabo=nodo;            
        }
        else{
             cabeza.anterior=nodo;
             nodo.siguiente=cabeza;
            cabeza=nodo;
        }
             longitud++;
             iterador=cabeza;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica. Si un elemento de la lista es
     * modificado, el iterador se mueve al primer elemento.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {
       for (Nodo n=cabeza;n!=null ;n=n.siguiente ) {
            if (n.elemento.equals(elemento)) {
                if (n.elemento.equals(cabeza.elemento)) {
                    eliminaPrimero();
                    iterador=cabeza;
                }
                else if (n.elemento.equals(rabo.elemento)) {
                    eliminaUltimo();
                    iterador=cabeza;
                }
                else{
                    n.anterior.siguiente=n.siguiente;
                    n.siguiente.anterior=n.anterior;
                    longitud --;
                    iterador=cabeza;
                }
            }
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public Object eliminaPrimero() {
        if (esVacia()==true) {
         return null;   
        }
        else{
            Object x=cabeza.elemento;
            if (cabeza==rabo) {
                cabeza=rabo=null;
            }
            else{
            cabeza.siguiente.anterior=null;
            cabeza=cabeza.siguiente;
        }
            longitud--;
            return x;
        }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public Object eliminaUltimo() {
        if (esVacia()==false) {
            longitud--;
            Object x=rabo.elemento;
        if (rabo==cabeza) { 
            rabo=null;
            cabeza=null;                     
             
            }
            else{
            rabo.anterior.siguiente=null;
             rabo=rabo.anterior; 
                }
                  return x;
       }
       else{ 
        return  null;
 
       }
    }

    /**
     * Nos dice si un elemento está en la lista. El iterador no se mueve.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(Object elemento) {
        return buscanodo(elemento)!=null;

    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
         Lista r= new Lista();
       Nodo t=cabeza;
       while (t!=null) {
           r.agregaInicio(t.elemento);
           t=t.siguiente;
       }
       return r;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista copia() {
        Lista x= new Lista(); 
         Nodo n=cabeza;
         while (n!=null) {
           x.agregaFinal(n.elemento);
             n=n.siguiente;
         }
         return x;
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {
        cabeza=null;
        rabo= null;
        longitud=0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getPrimero() {
         if(cabeza!=null){
            return cabeza.elemento;
        }
        else{
            return null;
        }
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getUltimo() {
        if(rabo!=null){
        return rabo.elemento;
       }
       else{
        return null;
       }
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista. Si el índice es menor
     * que cero o mayor o igual que el número de elementos de la lista, el
     * método regresa <tt>null</tt>.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista;
     *         <tt>null</tt> en otro caso.
     */
    public Object get(int i) {
        Nodo n=cabeza;
        int j=0;
        while (n!=null) {
            if (i==j) {
                return n.elemento;
            }
            n=n.siguiente;
            j=j+1;
        }
        return null;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si
     *         el elemento no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
         Nodo n=cabeza;
        int j=0;
        while (n!=null) {
            if (n.elemento.equals(elemento)) {
                return j;
            }
            n=n.siguiente;
            j=j+1;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
         String s = "[";
        
        return toString(cabeza,s); 
        }

    private String toString(Nodo n,String s){
        if (esVacia()) 
            return s+="]";

        if (n==rabo)
             return s += String.format("%s]", rabo.elemento);
     s += String.format("%s, ", n.elemento);
    
     return toString(n=n.siguiente,s=s);
    }

    /**
     * Mueve el iterador de la lista a su primer elemento.
     */
    public void primero() {
        iterador = cabeza;
    }

    /**
     * Mueve el iterador de la lista a su último elemento.
     */
    public void ultimo() {
        iterador = rabo;
    }

    /**
     * Mueve el iterador al siguiente elemento.
     */
    public void siguiente() {
        iterador = iterador.siguiente;
    }

    /**
     * Mueve el iterador al elemento anterior.
     */
    public void anterior() {
      iterador = iterador.anterior;
    }

    /**
     * Regresa el elemento al que el iterador apunta.
     * @return el elemento al que el iterador apunta, o <tt>null</tt> si el
     *         iterador es inválido.
     */
    public Object get() {
        if (iteradorValido()==true){
            return iterador.elemento;
        }
        else{
            return null;
        }
    }

    /**
     * Nos dice si el iterador es válido.
     * @return <tt>true</tt> si el iterador es válido; <tt>false</tt> en otro
     *         caso.
     */
    public boolean iteradorValido() {
        if(iterador!=null){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Lista))
            return false; 
            Lista a= (Lista)o;
            Nodo r=a.cabeza;
        if (longitud==a.longitud) {
        for (Nodo n=cabeza ;n!=null ;n=n.siguiente ) {
           if (n.elemento.equals(r.elemento)!=true) {
             return false;
            }
            r=r.siguiente;   
        }
        return true;

    }
    return false;
    }

       private Nodo buscanodo(Object elemento){
        Nodo n=cabeza;
        while (n!=null) {
            if (n.elemento.equals(elemento)) {
                return n;
            }
            n= n.siguiente;
        }
        return null;
    }
}
