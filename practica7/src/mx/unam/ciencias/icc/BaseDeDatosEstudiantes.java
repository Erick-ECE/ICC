package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos<Estudiante> {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override protected Estudiante creaRegistro() {
         Estudiante r = new Estudiante(null,0,0.0,0) ;
        return r;
    }

    /**
     * Busca estudiantes por un campo específico.
     * @param campo el campo del registro por el cuál buscar; puede ser
     *              <ul>
     *               <li><tt>"nombre"</tt></li>
     *               <li><tt>"cuenta"</tt></li>
     *               <li><tt>"promedio"</tt></li>
     *               <li><tt>"edad"</tt></li>
     *              </ul>
     * @param texto el texto a buscar.
     * @return una lista con los estudiantes tales que en el campo especificado
     *         contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es ninguno de los
     *         especificados arriba.
     */
    @Override public Lista<Estudiante> buscaRegistros(String campo, String texto) {
         Lista<Estudiante> l = new Lista<Estudiante>();
          
         switch (campo) {
             case "nombre":
             for(Estudiante e:registros){            
             if (e.getNombre().contains(texto)) 
                 l.agregaFinal(e);
            }
             break;
             case "cuenta":
             for(Estudiante e: registros){
             if (String.valueOf(e.getCuenta()).contains(texto)) 
                 l.agregaFinal(e);
            }
             break;
             case "promedio":
             for(Estudiante e:registros){
             if (String.valueOf(e.getPromedio()).contains(texto)) 
                 l.agregaFinal(e);
            }
             break;
             case "edad":
             for(Estudiante e:registros){
             if (String.valueOf(e.getEdad()).contains(texto)) 
                 l.agregaFinal(e);
            }

             break;

             default:
              throw new IllegalArgumentException();
         }
         return l;
    }
    
}
