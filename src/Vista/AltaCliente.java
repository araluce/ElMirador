/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

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
public class AltaCliente extends javax.swing.JFrame {

    private Modelo model = null;
    private JTextField inputNombre = new JTextField();
    private JTextField inputApellido1 = new JTextField();
    private JTextField inputApellido2 = new JTextField();
    private JTextField inputDni = new JTextField();
    private JTextField inputTelefono = new JTextField();
    private JButton botonSalir = new JButton();
    private JButton botonRegistrar = new JButton();

    private JFrame esteFormulario = this;
    private JTable tablaBusquedaClientes = new JTable();

    /**
     * Creates new form AltaCliente
     */
    public AltaCliente(Modelo modelo, JTable tbc) {
        this.model = modelo;
        this.tablaBusquedaClientes = tbc;

        initComponents();

        getContentPane().setBackground(Color.WHITE);
        setTitle("Alta de clientes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(360, 330);
        setLayout(null);
        
        JLabel titulo = new JLabel("ALTA DE CLIENTES");
        titulo.setBounds(110, 7, 150, 30);

        JLabel nombre = new JLabel("Nombre");
        nombre.setBounds(30, 50, 90, 30);

        JLabel apellido1 = new JLabel("Apellido 1");
        apellido1.setBounds(30, 85, 90, 30);

        JLabel apellido2 = new JLabel("Apellido 2");
        apellido2.setBounds(30, 120, 90, 30);

        JLabel dni = new JLabel("DNI");
        dni.setBounds(30, 155, 90, 30);

        JLabel telefono = new JLabel("Teléfono");
        telefono.setBounds(30, 190, 90, 30);

        inputNombre = new JTextField();
        inputNombre.setBounds(110, 50, 200, 30);

        inputApellido1 = new JTextField();
        inputApellido1.setBounds(110, 85, 200, 30);

        inputApellido2 = new JTextField();
        inputApellido2.setBounds(110, 120, 200, 30);

        inputDni = new JTextField();
        inputDni.setBounds(110, 155, 200, 30);
        inputDni.addKeyListener(new KeyAdapter() {
            Border bordeRojo = BorderFactory.createLineBorder(Color.RED);
            Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK);

            public void keyTyped(KeyEvent e) {
                String dni = inputDni.getText().toUpperCase();
                int dniLenght = dni.length();
                if (dniLenght < 8) {
                    inputDni.setBorder(bordeRojo);
                    inputDni.setText(dni);
                    inputDni.repaint();
                } else {
                    inputDni.setBorder(bordeNegro);
                    inputDni.setText(dni);
                    inputDni.repaint();
                }
            }
        });

        inputTelefono = new JTextField();
        inputTelefono.setBounds(110, 190, 200, 30);
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
        botonSalir = new JButton("Cancelar");
        botonSalir.setToolTipText("Cancelar la operación");
        botonSalir.setBackground(Color.WHITE);
        botonSalir.setFocusPainted(false);
        botonSalir.setBounds(25, 230, 140, 30);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esteFormulario.dispose();
            }
        });

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setToolTipText("Dar de alta al usuario");
        botonRegistrar.setBackground(new Color(40, 96, 144));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFocusPainted(false);
        botonRegistrar.setBounds(170, 230, 140, 30);
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteManager cm = new ClienteManager();
                ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
                listaClientes = cm.findClientesByDni(model.getConnection(), inputDni.getText());
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

                    int resultado = cm.clienteFlush(model.getConnection(), cliente);
                    System.out.println(resultado);
                    if (resultado == 0) {
                        JOptionPane.showMessageDialog(esteFormulario, "Se ha prodicido un error y no se ha dado de alta al cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(esteFormulario, "Cliente dado de alta correctamente");
                        ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
                        arrayClientes = cm.findAllClientes(model.getConnection());
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
        add(dni);
        add(telefono);

        add(inputApellido1);
        add(inputApellido2);
        add(inputDni);
        add(inputNombre);
        add(inputTelefono);

        add(botonSalir);
        add(botonRegistrar);
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
            .addGap(0, 393, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
