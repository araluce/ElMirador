/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Componentes.TablaAlbaranesCliente;
import Componentes.TablaClientes;
import Modelo.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author araluce
 */
public class DirectorioEditarCliente extends javax.swing.JFrame {

    private Modelo modelo;
    private String dni;
    
    private JTextField inputNombre = new JTextField();
    private JTextField inputApellido1 = new JTextField();
    private JTextField inputApellido2 = new JTextField();
    private JTextField inputDni = new JTextField();
    private JTextField inputTelefono = new JTextField();
    private JButton botonRegistrar = new JButton();
    
    private JFrame esteFormulario = this;
    private TablaClientes tablaBusquedaClientes = null;

    /**
     * Creates new form DirectorioCliente
     */
    public DirectorioEditarCliente(Modelo m, String ndni, TablaClientes tabla) {
        this.modelo = m;
        this.dni = ndni;
        this.tablaBusquedaClientes = tabla;
        
        initComponents();
        
        getContentPane().setBackground(Color.WHITE);
        
        ClienteManager cm = new ClienteManager();
        Cliente cliente = cm.findOneClienteByDni(modelo.getConnection(), dni);
        
        setTitle("Directorio de " + this.dni);
        setDefaultCloseOperation(cerrarVentana());
        setSize(330, 330);
        setLayout(null);
        
        JLabel titulo = new JLabel("MODIFICAR CLIENTE");
        titulo.setBounds(110, 7, 150, 30);

        JLabel nombre = new JLabel("Nombre");
        nombre.setBounds(30, 50, 90, 30);

        JLabel apellido1 = new JLabel("Apellido 1");
        apellido1.setBounds(30, 85, 90, 30);

        JLabel apellido2 = new JLabel("Apellido 2");
        apellido2.setBounds(30, 120, 90, 30);

        JLabel dnil = new JLabel("DNI");
        dnil.setBounds(30, 155, 90, 30);

        JLabel telefono = new JLabel("Teléfono");
        telefono.setBounds(30, 190, 90, 30);

        inputNombre = new JTextField();
        inputNombre.setBounds(110, 50, 200, 30);
        inputNombre.setText(cliente.getNombre());

        inputApellido1 = new JTextField();
        inputApellido1.setBounds(110, 85, 200, 30);
        inputApellido1.setText(cliente.getApellido1());

        inputApellido2 = new JTextField();
        inputApellido2.setBounds(110, 120, 200, 30);
        inputApellido2.setText(cliente.getApellido2());

        inputDni = new JTextField();
        inputDni.setBounds(110, 155, 200, 30);
        inputDni.setText(dni);
        inputDni.setEditable(false);

        inputTelefono = new JTextField();
        inputTelefono.setBounds(110, 190, 200, 30);
        inputTelefono.setText(cliente.getTelefono());
        inputTelefono.addKeyListener(new KeyAdapter() {
            Border bordeRojo = BorderFactory.createLineBorder(Color.RED);
            Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK);

            public void keyTyped(KeyEvent e) {
                String telefono = inputTelefono.getText();
                int telefonoLenght = telefono.length();
                if (telefonoLenght < 8) {
                    inputTelefono.setBorder(bordeRojo);
                    inputTelefono.setText(telefono);
                    inputTelefono.repaint();
                } else {
                    inputTelefono.setBorder(bordeNegro);
                    inputTelefono.setText(telefono);
                    inputTelefono.repaint();
                }
            }
        });

        // Definiendo los botones

        botonRegistrar = new JButton("Modificar");
        botonRegistrar.setToolTipText("Modificar datos de un usuario");
        botonRegistrar.setBackground(new Color(40, 96, 144));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFocusPainted(false);
        botonRegistrar.setBounds(170, 230, 140, 30);
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteManager cm = new ClienteManager();
                ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
                listaClientes = cm.findClientesByDni(modelo.getConnection(), inputDni.getText());
                if (listaClientes.isEmpty()) {
                    // Creamos al nuevo cliente
                    Cliente cliente = new Cliente();
                    cliente.setNombre(inputNombre.getText().toUpperCase());
                    cliente.setApellido1(inputApellido1.getText().toUpperCase());
                    cliente.setApellido2(inputApellido2.getText().toUpperCase());
                    cliente.setDni(inputDni.getText().toUpperCase());
                    cliente.setTelefono(inputTelefono.getText());

                    // Vaciamos los campos
                    inputNombre.setText("");
                    inputApellido1.setText("");
                    inputApellido2.setText("");
                    inputDni.setText("");
                    inputTelefono.setText("");

                    int resultado = cm.clienteFlush(modelo.getConnection(), cliente);
                    System.out.println(resultado);
                    if (resultado == 0) {
                        JOptionPane.showMessageDialog(esteFormulario, "Se ha prodicido un error y no se ha dado de alta al cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(esteFormulario, "Cliente dado de alta correctamente");
                        ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
                        arrayClientes = cm.findAllClientes(modelo.getConnection());
                        DefaultTableModel model = (DefaultTableModel) tablaBusquedaClientes.getModel();
                        model.setRowCount(0);
                        for (Cliente c : arrayClientes) {
                            model.addRow(new Object[]{c.getNombre(), c.getApellido1() + " " + c.getApellido2(), c.getDni(), c.getTelefono()});
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(esteFormulario, "Ya existe un usuario con este DNI", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(titulo);
        add(nombre);
        add(apellido1);
        add(apellido2);
        add(dnil);
        add(telefono);

        add(inputApellido1);
        add(inputApellido2);
        add(inputDni);
        add(inputNombre);
        add(inputTelefono);
        add(botonRegistrar);
    }

    public int cerrarVentana() {
        int DO_NOTHING_ON_CLOSE = 0;
        int DISPOSE_ON_CLOSE = 2;
//        if (JOptionPane.showConfirmDialog(this,
//                "¿Estás seguro de cerrar esta ventana?", "Cerrar directorio",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//            return DISPOSE_ON_CLOSE;
//        }
//        return DO_NOTHING_ON_CLOSE;
        return DISPOSE_ON_CLOSE;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
