package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos<Estudiante> {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override public Estudiante creaRegistro() {
        return new Estudiante (null,0,0,0);
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
       Lista<Estudiante> lista= new Lista<Estudiante>();

       for (Estudiante n : registros){
        String valor = "";

            switch(campo){
                case"nombre":
                valor = n.getNombre();
                break;

                case "cuenta":
                valor = String.valueOf(n.getCuenta());
                break;

                case "promedio":
                valor = String.valueOf(n.getPromedio());
                break;

                case"edad":
                valor = String.valueOf(n.getEdad());
                break;

                default:
                throw new IllegalArgumentException("Vuelve a intentarlo");
            }

                if(valor.contains(texto))
                lista.agregaFinal(n);
        }
        return lista;
    }
}
