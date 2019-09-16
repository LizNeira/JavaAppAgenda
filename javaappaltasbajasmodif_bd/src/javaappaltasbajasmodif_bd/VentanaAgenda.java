// VentanaAgenda.java
// Una agenda de Contactos simple
package javaappaltasbajasmodif_bd;

import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaAgenda extends JFrame {

    private Persona entradaActual;
    public ConsultasPersona consultasPersona;
    private List< Persona> resultados;
    private int numeroDeEntradas = 0;
    private int indiceEntradaActual;
    private JButton botonExplorar;
    private JLabel etiquetaEmail;
    private JTextField campoTextoEmail;

    private JLabel etiquetaNombre;
    private JTextField campoTextoNombre;
    private JLabel etiquetaID;
    private JTextField campoTextoID;
    private JTextField campoTextoIndice;
    private JLabel etiquetaApellido;
    private JTextField campoTextoApellido;
    private JTextField campoTextoMax;
    private JButton botonSiguiente;
    private JLabel etiquetaDe;
    private JLabel etiquetaTelefono;
    private JTextField campoTextoTelefono;
    private JButton botonAnterior;
    private JButton botonConsulta;
    private JLabel etiquetaConsulta;
    private JPanel panelConsulta;
    private JPanel panelNavegar;
    private JPanel panelMostrar;
    private JTextField campoTextoConsulta;
    private JButton botonInsertar;
    private JButton botonGrabar;
    private JButton botonBorrar;
    private JButton botonModificar;
    private JButton botonGrabarModif;
    private JTable jtabletExplorar;
    private DefaultTableModel defaultTableModel;
    private Connection conexion=null; // maneja la conexión
// constructor sin argumentos

    public VentanaAgenda() throws SQLException, ClassNotFoundException {
        super("Agenda de Contactos");
// establece la conexión a la base de datos y las instrucciones PreparedStatement
        consultasPersona = new ConsultasPersona();
        defaultTableModel  = new DefaultTableModel();
// crea la GUI
        panelNavegar = new JPanel();
        botonAnterior = new JButton();
        campoTextoIndice = new JTextField(2);
        etiquetaDe = new JLabel();
        campoTextoMax = new JTextField(2);
        botonSiguiente = new JButton();
        panelMostrar = new JPanel();
        etiquetaID = new JLabel();
        campoTextoID = new JTextField(10);
        etiquetaNombre = new JLabel();
        campoTextoNombre = new JTextField(10);
        etiquetaApellido = new JLabel();
        campoTextoApellido = new JTextField(10);
        etiquetaEmail = new JLabel();
        campoTextoEmail = new JTextField(10);
        etiquetaTelefono = new JLabel();
        campoTextoTelefono = new JTextField(10);
        panelConsulta = new JPanel();
        etiquetaConsulta = new JLabel();
        campoTextoConsulta = new JTextField(10);
        botonConsulta = new JButton();
        botonExplorar = new JButton();
        botonInsertar = new JButton();
        botonGrabar = new JButton();
        botonBorrar = new JButton();
        botonModificar = new JButton();
        botonGrabarModif = new JButton();

        
        
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setSize(400, 800);//300
        setResizable(true);
        panelNavegar.setLayout(
        new BoxLayout(panelNavegar, BoxLayout.X_AXIS));
        botonAnterior.setText("Anterior");
        botonAnterior.setEnabled(false);

        botonAnterior.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAnteriorActionPerformed(evt);
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        panelNavegar.add(botonAnterior);
        panelNavegar.add(Box.createHorizontalStrut(10));
        campoTextoIndice.setHorizontalAlignment(
                JTextField.CENTER);
        campoTextoIndice.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                campoTextoIndiceActionPerformed(evt);
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        panelNavegar.add(campoTextoIndice);
        panelNavegar.add(Box.createHorizontalStrut(10));
        etiquetaDe.setText("de");
        panelNavegar.add(etiquetaDe);
        panelNavegar.add(Box.createHorizontalStrut(10));
        campoTextoMax.setHorizontalAlignment(
                JTextField.CENTER);
        campoTextoMax.setEditable(false);
        panelNavegar.add(campoTextoMax);
        panelNavegar.add(Box.createHorizontalStrut(10));
        botonSiguiente.setText("Siguiente");
        botonSiguiente.setEnabled(false);
        botonSiguiente.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        panelNavegar.add(botonSiguiente);
        add(panelNavegar);
        panelMostrar.setLayout(new GridLayout(5, 2, 4, 4));
        etiquetaID.setText("ID Contacto:");
        panelMostrar.add(etiquetaID);
        campoTextoID.setEditable(false);
        panelMostrar.add(campoTextoID);
        etiquetaNombre.setText("Nombre:");
        panelMostrar.add(etiquetaNombre);
        panelMostrar.add(campoTextoNombre);
        etiquetaApellido.setText("Apellido:");
        panelMostrar.add(etiquetaApellido);
        panelMostrar.add(campoTextoApellido);
        etiquetaEmail.setText("Email:");
        panelMostrar.add(etiquetaEmail);

        panelMostrar.add(campoTextoEmail);
        etiquetaTelefono.setText("Telefono:");
        panelMostrar.add(etiquetaTelefono);
        panelMostrar.add(campoTextoTelefono);
        add(panelMostrar);
        panelConsulta.setLayout(
                new BoxLayout(panelConsulta, BoxLayout.X_AXIS));
        panelConsulta.setBorder(BorderFactory.createTitledBorder(
                "Buscar un registro por idContacto"));
        etiquetaConsulta.setText("idContacto:");
        panelConsulta.add(Box.createHorizontalStrut(5));
        panelConsulta.add(etiquetaConsulta);
        panelConsulta.add(Box.createHorizontalStrut(10));
        panelConsulta.add(campoTextoConsulta);
        panelConsulta.add(Box.createHorizontalStrut(10));
        botonConsulta.setText("Buscar");
        botonConsulta.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonConsultaActionPerformed(evt);
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        panelConsulta.add(botonConsulta);
        panelConsulta.add(Box.createHorizontalStrut(5));
        add(panelConsulta);

        botonExplorar.setText("View all");
        botonExplorar.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
              mostrarTodo();
               
              botonExplorarActionPerformed(evt);
                
            } // fin del método actionPerformed
            } // fin de la clase interna anónima
                
            ); // fin de la llamada a addActionListener
            add(botonExplorar);

            botonInsertar.setText (

            "Insertar");
            botonInsertar.addActionListener (
                     new ActionListener() {
            

            public void actionPerformed(ActionEvent evt) {
                botonInsertarActionPerformed(evt);

            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        add(botonInsertar);
////////////////////////
        botonGrabar.setText("Grabar");
        botonGrabar.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonGrabarActionPerformed(evt);
                mostrarTodo();
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener

        add(botonGrabar);
        botonGrabar.setVisible(false);
//////////////
        botonBorrar.setText("borrar");
        botonBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonBorrarActionPerformed(evt);
                mostrarTodo();
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        add(botonBorrar);
///////////////
        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonModificarActionPerformed(evt);
                mostrarTodo();
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        add(botonModificar);
//////////
        botonGrabarModif.setText("grabar modif");
        botonGrabarModif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonGrabarModifActionPerformed(evt);
                mostrarTodo();
            } // fin del método actionPerformed
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        add(botonGrabarModif);
        botonGrabarModif.setVisible(false);
         jtabletExplorar = new JTable(defaultTableModel);
         add(jtabletExplorar);
///////////////
        addWindowListener(
                new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                consultasPersona.close(); // cierra la conexión a la base de datos
                System.exit(0);
            } // fin del método windowClosing
        } // fin de la clase interna anónima
        ); // fin de la llamada a addWindowListener
        botonExplorarActionPerformed(null);
        setVisible(true);
    } // fin del constructor sin argumentos
// maneja la llamada cuando se hace clic en botonAnterior

    private void botonAnteriorActionPerformed(ActionEvent evt) {
        indiceEntradaActual--;
        if (indiceEntradaActual < 0) {
            indiceEntradaActual = numeroDeEntradas - 1;
        }
        campoTextoIndice.setText("" + (indiceEntradaActual + 1));
        campoTextoIndiceActionPerformed(evt);
    } // fin del método botonAnteriorActionPerformed
// maneja la llamada cuando se hace clic en botonSiguiente

    private void mostrarTodo(){
        defaultTableModel.setColumnCount(0);
        defaultTableModel.setRowCount(0);
        defaultTableModel.addColumn("Id");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Apellido");
        defaultTableModel.addColumn("E-mail");
        defaultTableModel.addColumn("Telefono");
        resultados = consultasPersona.obtenerTodasLasPersonas();
                
       for( int i =0;  i<resultados.size(); i++)
       {
           Vector registro = new Vector();
     
            
            registro.addElement( resultados.get(i).getIdContacto() );
            registro.addElement( resultados.get(i).getNombre());
            registro.addElement( resultados.get(i).getApellido());
            registro.addElement( resultados.get(i).getEmail());
            registro.addElement( resultados.get(i).getNumeroTelefono());
           
            defaultTableModel.addRow(registro);
            
      }
      //  scrollPane.setBounds(5, 10, 50, 100);
            //scrollPane.setViewportView(jtabletExplorar);
           

       // getContentPane().add( new JScrollPane(jtabletExplorar) );
       
    }
    
    private void botonSiguienteActionPerformed(ActionEvent evt) {
        indiceEntradaActual++;
        if (indiceEntradaActual >= numeroDeEntradas) {
            indiceEntradaActual = 0;
        }
        campoTextoIndice.setText("" + (indiceEntradaActual + 1));
        campoTextoIndiceActionPerformed(evt);
    } // fin del método botonSiguienteActionPerformed
// maneja la llamada cuando se hace clic en botonConsulta

    private void botonConsultaActionPerformed(ActionEvent evt) {
        String idPersona = campoTextoConsulta.getText();
        if (idPersona.equals("")) {
            JOptionPane.showMessageDialog(this, "debe ingresar un nro de ID");
            campoTextoConsulta.requestFocusInWindow();
        } else {
            resultados
                    = consultasPersona.obtenerPersonasPorIdContacto(Integer.parseInt(campoTextoConsulta.getText()));
            numeroDeEntradas = resultados.size();
            if (numeroDeEntradas != 0) {
                indiceEntradaActual = 0;
                entradaActual = resultados.get(indiceEntradaActual);
                campoTextoID.setText("" + entradaActual.getIdContacto());
                campoTextoNombre.setText(entradaActual.getNombre());
                campoTextoApellido.setText(entradaActual.getApellido());
                campoTextoEmail.setText(entradaActual.getEmail());
                campoTextoTelefono.setText(entradaActual.getNumeroTelefono());
                campoTextoMax.setText("" + numeroDeEntradas);
                campoTextoIndice.setText("" + (indiceEntradaActual + 1));
                botonSiguiente.setEnabled(true);
                botonAnterior.setEnabled(true);
            } // end if
            else {
                campoTextoID.setText("");
                campoTextoNombre.setText("");
                campoTextoApellido.setText("");
                campoTextoEmail.setText("");
                campoTextoTelefono.setText("");
                campoTextoMax.setText("0");
                campoTextoIndice.setText("0");
                botonSiguiente.setEnabled(true);
                botonAnterior.setEnabled(true);
//botonExplorarActionPerformed( evt );
            }
            campoTextoConsulta.requestFocusInWindow();
        }
    } // fin del método botonConsultaActionPerformed
// maneja la llamada cuando se introduce un nuevo valor en campoTextoIndice

    private void campoTextoIndiceActionPerformed(ActionEvent evt) {
        indiceEntradaActual
                = (Integer.parseInt(campoTextoIndice.getText()) - 1);
        if (numeroDeEntradas != 0 && indiceEntradaActual < numeroDeEntradas) {
            entradaActual = resultados.get(indiceEntradaActual);
            campoTextoID.setText("" + entradaActual.getIdContacto());
            campoTextoNombre.setText(entradaActual.getNombre());
            campoTextoApellido.setText(entradaActual.getApellido());
            campoTextoEmail.setText(entradaActual.getEmail());
            campoTextoTelefono.setText(entradaActual.getNumeroTelefono());
            campoTextoMax.setText("" + numeroDeEntradas);
            campoTextoIndice.setText("" + (indiceEntradaActual + 1));
        } // fin de if
    } // fin del método campoTextoIndiceActionPerformed

// maneja la llamada cuando se hace clic en botonExplorar
    private void botonExplorarActionPerformed(ActionEvent evt) {
        try {
            resultados = consultasPersona.obtenerTodasLasPersonas();
            numeroDeEntradas = resultados.size();
            if (numeroDeEntradas != 0) {
                indiceEntradaActual = 0;
                entradaActual = resultados.get(indiceEntradaActual);
                campoTextoID.setText("" + entradaActual.getIdContacto());
                campoTextoNombre.setText(entradaActual.getNombre());
                campoTextoApellido.setText(entradaActual.getApellido());
                campoTextoEmail.setText(entradaActual.getEmail());
                campoTextoTelefono.setText(entradaActual.getNumeroTelefono());
                campoTextoMax.setText("" + numeroDeEntradas);
                campoTextoIndice.setText("" + (indiceEntradaActual + 1));
                botonSiguiente.setEnabled(true);
                botonAnterior.setEnabled(true);
            } // fin de if
        } // fin de try
        catch (Exception e) {
            e.printStackTrace();
        } // fin de catch
        
        
        
    } // fin del método botonExplorarActionPerformed

    //maneja la llamada cuando se hace clic en botonInsertar
    private void botonInsertarActionPerformed(ActionEvent evt) {
        campoTextoID.setEditable(false);
        campoTextoID.setText("");
        campoTextoNombre.setText("");
        campoTextoApellido.setText("");
        campoTextoEmail.setText("");
        campoTextoTelefono.setText("");
        JOptionPane.showMessageDialog(null,
                "Para agregar debe llenar al menos el campo Apellido");
        this.campoTextoApellido.requestFocusInWindow();
        this.botonInsertar.setVisible(false);
        this.botonGrabar.setText("Grabar datos del nuevo registro");
        this.panelConsulta.setVisible(false);
        this.botonGrabar.setVisible(true);
        this.botonBorrar.setVisible(false);
        this.botonModificar.setVisible(false);
        this.botonExplorar.setVisible(false);
        ///
//botonExplorarActionPerformed( evt );
    } // fin del método botonInsertarActionPerformed

    private void botonGrabarActionPerformed(ActionEvent evt) {
        if (campoTextoApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "No se agrego ninguna persona, el campo Apellido no fue completado");
        } else {
            int resultado
                    = consultasPersona.agregarPersona(campoTextoNombre.getText().toString(),
                            campoTextoApellido.getText().toString(), campoTextoEmail.getText().toString(),
                            campoTextoTelefono.getText().toString());
            String persona = campoTextoNombre.getText().toString()
                    + " " + campoTextoApellido.getText().toString();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(this, "Se agrego a la persona " + persona,
                        "Se agrego persona", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se agrego persona!",
                        "Error", JOptionPane.PLAIN_MESSAGE);
            }
        }
        this.panelConsulta.setVisible(true);
        this.botonGrabar.setText("Grabar");
        this.botonGrabar.setVisible(false);
        this.botonExplorar.setVisible(true);
        this.botonInsertar.setVisible(true);
        this.botonBorrar.setVisible(true);
        this.botonModificar.setVisible(true);
        botonExplorarActionPerformed(evt);
    } // fin botonGrabarActionPerformed

    private void botonBorrarActionPerformed(ActionEvent evt) {
        if (this.campoTextoID.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "No se borro ninguna persona, el campo ID esta vacio");
        } else {
            String persona = this.campoTextoNombre.getText()
                    + " " + this.campoTextoApellido.getText();
            int resp = JOptionPane.showConfirmDialog(this,
                    "Esta seguro de que quiere borrar a " + persona);
            if (resp == JOptionPane.YES_OPTION) {
                int resultado
                        = consultasPersona.borrarPersona(Integer.parseInt(campoTextoID.getText().toString()));
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(this, "Se borro a " + persona,
                            "Se borro persona", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se borro persona!",
                            "Error", JOptionPane.PLAIN_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se borro a " + persona);
            }
        }
        botonExplorarActionPerformed(evt);
    }

    private void botonModificarActionPerformed(ActionEvent evt) {
        this.botonModificar.setVisible(false);
        this.botonGrabarModif.setVisible(true);
        this.botonInsertar.setVisible(false);
        this.botonBorrar.setVisible(false);
        this.botonModificar.setVisible(false);
        this.botonExplorar.setVisible(false);
        this.campoTextoApellido.requestFocusInWindow();
    }

    private void botonGrabarModifActionPerformed(ActionEvent evt) {
        if (campoTextoApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "No se modifico ninguna persona, el campo Apellido no fue completado");
        } else {
            int resultado
                    = consultasPersona.modificarPersona(Integer.parseInt(campoTextoID.getText().toString()),
                            campoTextoNombre.getText().toString(),
                            campoTextoApellido.getText().toString(), campoTextoEmail.getText().toString(),
                            campoTextoTelefono.getText().toString());
            String persona = campoTextoNombre.getText().toString()
                    + " " + campoTextoApellido.getText().toString();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(this,
                        "Se modificaron los datos de la persona " + persona,
                        "Se agrego persona", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se modificaron los datos de la persona!",
                        "Error", JOptionPane.PLAIN_MESSAGE);
            }
        }
        this.botonGrabar.setVisible(false);
        this.botonGrabarModif.setVisible(false);
        this.botonExplorar.setVisible(true);
        this.botonModificar.setVisible(true);
        this.botonInsertar.setVisible(true);
        this.botonBorrar.setVisible(true);
        botonExplorarActionPerformed(evt);
    } // fin botonGrabarModifActionPerformed
} // fin de la clase VentanaAgenda
