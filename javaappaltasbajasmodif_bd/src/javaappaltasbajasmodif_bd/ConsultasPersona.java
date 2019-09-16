/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappaltasbajasmodif_bd;

// Instrucciones PreparedStatement utilizadas por la aplicación Libreta de direcciones
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConsultasPersona {

    static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    static final String URL_BASEDATOS = "jdbc:mysql://localhost/agenda";
    private Connection conexion = null; // maneja la conexión
    private PreparedStatement seleccionarTodasLasPersonas = null;
    private PreparedStatement seleccionarPersonaPorIdContacto = null;
    private PreparedStatement insertarNuevaPersona = null;
    private PreparedStatement borrarIdDireccion = null;
    private PreparedStatement modificarDatosPersona = null;
// constructor

    public ConsultasPersona() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL_BASEDATOS, "root", "");
// crea una consulta que selecciona todas las entradas en la Agenda
            seleccionarTodasLasPersonas
                    = conexion.prepareStatement("SELECT * FROM Contactos");
// crea una consulta que selecciona las entradas con un apellido específico
            seleccionarPersonaPorIdContacto = conexion.prepareStatement(
                    "SELECT * FROM Contactos WHERE idContacto = ?");
// crea instrucción insert para agregar una nueva entrada en la base de datos
//String inser="INSERT INTO `Agenda`.`Contactos` (`idContacto`,
//`Nombre`, ` Apellido`, `Email`, `NumeroTelefono`) VALUES
//(NULL, 'huiy', 'yyyyyyyy', '333', '9432-6176')";

            String insertar = "INSERT INTO `Agenda`.`Contactos` (`idContacto`, `Nombre`, `Apellido`,`Email`, `NumeroTelefono`) VALUES (NULL, ?, ?, ?, ?)";
            insertarNuevaPersona = conexion.prepareStatement(insertar);

            String borrar = "DELETE FROM `Agenda`.`Contactos` WHERE `Contactos`.`idContacto` = ?";
            this.borrarIdDireccion = conexion.prepareStatement(borrar);

            String modificar = "UPDATE `Agenda`.`Contactos` SET `Nombre` = ? ,`Apellido` = ? ,`Email`= ?,`NumeroTelefono` = ? WHERE `Contactos`.`idContacto` = ?";
            this.modificarDatosPersona = conexion.prepareStatement(modificar);
        } // fin de try
        catch (SQLException excepcionSql) {
//excepcionSql.printStackTrace();
            System.exit(1);
        } // fin de catch
    } // fin del constructor de ConsultasPersona
// selecciona todas las Contactos en la base de datos

    public List< Persona> obtenerTodasLasPersonas() {
        List< Persona> resultados = null;
        ResultSet conjuntoResultados = null;
        try {
// executeQuery devuelve ResultSet que contiene las entradas que coinciden
            conjuntoResultados = seleccionarTodasLasPersonas.executeQuery();
            resultados = new ArrayList< Persona>();
            while (conjuntoResultados.next()) {
                resultados.add(new Persona(
                        conjuntoResultados.getInt(1),
                        conjuntoResultados.getString(2),
                        conjuntoResultados.getString(3),
                        conjuntoResultados.getString(4),
                        conjuntoResultados.getString(5)));
            } // fin de while
        } // fin de try
        catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null,
                    "error en executeQuery de seleccionar todas las personas");
//excepcionSql.printStackTrace();
        } // fin de catch
        finally {
            try {
                conjuntoResultados.close();
            } // fin de try
            catch (SQLException excepcionSql) {
                excepcionSql.printStackTrace();
                close();
            } // fin de catch
        } // fin de finally
        return resultados;
    } // fin del método obtenerTodasLasPersonaas
// selecciona persona por ID

    public List< Persona> obtenerPersonasPorIdContacto(int id) {
        List< Persona> resultados = null;
        ResultSet conjuntoResultados = null;
        try {
            seleccionarPersonaPorIdContacto.setInt(1, id);
// executeQuery devuelve ResultSet que contiene las entradas que coinciden
            conjuntoResultados = seleccionarPersonaPorIdContacto.executeQuery();
            resultados = new ArrayList< Persona>();
            while (conjuntoResultados.next()) {
                resultados.add(new Persona(
                        conjuntoResultados.getInt(1),
                        conjuntoResultados.getString(2),
                        conjuntoResultados.getString(3),
                        conjuntoResultados.getString(4),
                        conjuntoResultados.getString(5)));
            } // fin de while
        } // fin de try
        catch (SQLException excepcionSql) {
//excepcionSql.printStackTrace();
        } // fin de catch
        finally {
            try {
                conjuntoResultados.close();
            } // fin de try
            catch (SQLException excepcionSql) {
//excepcionSql.printStackTrace();
                close();
            } // fin de catch
        } // fin de finally

        return resultados;
    } // fin del método obtenerPersonasPorApellido
// agrega una entrada

    public int agregarPersona(
            String nombre, String apellido, String email, String num) {
        int resultado = 0;
// establece los parámetros, después ejecuta insertarNuevaPersona
        try {
            insertarNuevaPersona.setString(1, nombre);
            insertarNuevaPersona.setString(2, apellido);
            insertarNuevaPersona.setString(3, email);
            insertarNuevaPersona.setString(4, num);
// inserta la nueva entrada; devuelve cant de filas actualizadas
            resultado = insertarNuevaPersona.executeUpdate();
        } // fin de try
        catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null,
                    "excepcion no pudo insertar nuevo registro");
            excepcionSql.printStackTrace();
//close();
        } // fin de catch
        return resultado;
    } // fin del método agregarPersona
// cierra la conexión a la base de datos

    public void close() {
        try {
            conexion.close();
        } // fin de try
        catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        } // fin de catch
    } // fin del método close

    public int borrarPersona(int id) {
        int resultado = 0;
// establece los parámetros, después ejecuta insertarNuevaPersona
        try {
            borrarIdDireccion.setInt(1, id);
// inserta la nueva entrada; devuelve cant de filas actualizadas
            resultado = borrarIdDireccion.executeUpdate();
        } // fin de try
        catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null, "excepcion no pudo borrar registro");
            excepcionSql.printStackTrace();
//close();
        } // fin de catch
        return resultado;
    } // fin del método borrarPersona
/////////

    public int modificarPersona(
            int ID, String nombre, String apellido, String email, String num) {
        int resultado = 0;
// establece los parámetros, después ejecuta insertarNuevaPersona
        try {
            modificarDatosPersona.setInt(5, ID);
            modificarDatosPersona.setString(1, nombre);
            modificarDatosPersona.setString(2, apellido);
            modificarDatosPersona.setString(3, email);
            modificarDatosPersona.setString(4, num);
// inserta la nueva entrada; devuelve # de filas actualizadas
            resultado = modificarDatosPersona.executeUpdate();
        } // fin de try
        catch (SQLException excepcionSql) {
            JOptionPane.showMessageDialog(null, "excepcion, no se pudo modificar el registro");
            excepcionSql.printStackTrace();
//close();
        } // fin de catch
        return resultado;
    } // fin del método modificarPersona

} // fin de la clase ConsultasPersona
