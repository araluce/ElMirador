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
public class ClientRegister extends javax.swing.JFrame {

    private Model model = null;
    private JTextField inputName = new JTextField();
    private JTextField inputLastname1 = new JTextField();
    private JTextField inputLastname2 = new JTextField();
    private JTextField inputDni = new JTextField();
    private JTextField inputPhone = new JTextField();
    private JButton exitButton = new JButton();
    private JButton registerButton = new JButton();

    private JFrame thisForm = this;
    private JTable clientSearchTable = new JTable();

    /**
     * Creates new form AltaCliente
     */
    public ClientRegister(Model external_model, JTable tbc) {
        this.model = external_model;
        this.clientSearchTable = tbc;

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

        inputName = new JTextField();
        inputName.setBounds(110, 50, 200, 30);

        inputLastname1 = new JTextField();
        inputLastname1.setBounds(110, 85, 200, 30);

        inputLastname2 = new JTextField();
        inputLastname2.setBounds(110, 120, 200, 30);

        inputDni = new JTextField();
        inputDni.setBounds(110, 155, 200, 30);
        inputDni.addKeyListener(new KeyAdapter() {
            Border redBorder = BorderFactory.createLineBorder(Color.RED);
            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

            public void keyTyped(KeyEvent e) {
                String dni = inputDni.getText().toUpperCase();
                int dniLenght = dni.length();
                if (dniLenght < 8) {
                    inputDni.setBorder(redBorder);
                    inputDni.setText(dni);
                    inputDni.repaint();
                } else {
                    inputDni.setBorder(blackBorder);
                    inputDni.setText(dni);
                    inputDni.repaint();
                }
            }
        });

        inputPhone = new JTextField();
        inputPhone.setBounds(110, 190, 200, 30);
        inputPhone.addKeyListener(new KeyAdapter() {
            Border bordeRojo = BorderFactory.createLineBorder(Color.RED);
            Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK);

            public void keyTyped(KeyEvent e) {
                String telefono = inputPhone.getText();
                int telefonoLenght = telefono.length();
                if (telefonoLenght < 8) {
                    inputPhone.setBorder(bordeRojo);
                    inputPhone.setText(telefono);
                    inputPhone.repaint();
                } else {
                    inputPhone.setBorder(bordeNegro);
                    inputPhone.setText(telefono);
                    inputPhone.repaint();
                }
            }
        });

        // Definiendo los botones
        exitButton = new JButton("Terminar");
        exitButton.setToolTipText("Cancelar la operación");
        exitButton.setBackground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(25, 230, 140, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisForm.dispose();
            }
        });

        registerButton = new JButton("Registrar");
        registerButton.setToolTipText("Dar de alta al usuario");
        registerButton.setBackground(new Color(40, 96, 144));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBounds(170, 230, 140, 30);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientManager cm = new ClientManager();
                ArrayList<Client> clientList = new ArrayList<Client>();
                clientList = cm.findClientsByDni(model.getConnection(), inputDni.getText());
                if (clientList.isEmpty()) {
                    // Creamos al nuevo cliente
                    Client cliente = new Client();
                    cliente.setName(inputName.getText().toUpperCase());
                    cliente.setLastname1(inputLastname1.getText().toUpperCase());
                    cliente.setLastname2(inputLastname2.getText().toUpperCase());
                    cliente.setDni(inputDni.getText().toUpperCase());
                    cliente.setPhone(inputPhone.getText());

                    // Vaciamos los campos
                    inputName.setText("");
                    inputLastname1.setText("");
                    inputLastname2.setText("");
                    inputDni.setText("");
                    inputPhone.setText("");

                    int resultado = cm.flush(model.getConnection(), cliente);
                    if (resultado == 0) {
                        JOptionPane.showMessageDialog(thisForm, "Se ha prodicido un error y no se ha dado de alta al cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(thisForm, "Cliente dado de alta correctamente");
                        ArrayList<Client> arrayClientes = new ArrayList<Client>();
                        arrayClientes = cm.findAllClients(model.getConnection(), true);
                        DefaultTableModel model = (DefaultTableModel) clientSearchTable.getModel();
                        model.setRowCount(0);
                        for (Client c : arrayClientes) {
                            model.addRow(new Object[]{c.getName(), c.getLastname1()+ " " + c.getLastname2(), c.getDni(), c.getPhone()});
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(thisForm, "Ya existe un usuario con este DNI", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(titulo);
        add(nombre);
        add(apellido1);
        add(apellido2);
        add(dni);
        add(telefono);

        add(inputLastname1);
        add(inputLastname2);
        add(inputDni);
        add(inputName);
        add(inputPhone);

        add(exitButton);
        add(registerButton);
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
