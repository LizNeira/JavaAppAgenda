/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappaltasbajasmodif_bd;

public class Persona {

    private int idContacto;
    private String Nombre;
    private String Apellido;
    private String email;
    private String NumeroTelefono;

    public Persona(int iDDireccion, String Nombre, String Apellido, String email, String NumeroTelefono) {
        this.idContacto = iDDireccion;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.email = email;
        this.NumeroTelefono = NumeroTelefono;
    }

    public Persona() {
    }
    

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String NumeroTelefono) {
        this.NumeroTelefono = NumeroTelefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
