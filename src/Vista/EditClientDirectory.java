/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Componentes.ClienteBillsTable;
import Componentes.ClientsTable;
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
public class EditClientDirectory extends javax.swing.JFrame {

    private Model model;
    private String dni;
    
    private JTextField inputName = new JTextField();
    private JTextField inputLastname1 = new JTextField();
    private JTextField inputLastname2 = new JTextField();
    private JTextField inputDni = new JTextField();
    private JTextField inputPhone = new JTextField();
    private JButton registerButton = new JButton();
    
    private JFrame thisForm = this;
    private ClientsTable clientSearchTable = null;

    /**
     * Creates new form DirectorioCliente
     */
    public EditClientDirectory(Model m, String ndni, ClientsTable tabla) {
        this.model = m;
        this.dni = ndni;
        this.clientSearchTable = tabla;
        
        initComponents();
        
        getContentPane().setBackground(Color.WHITE);
        
        ClientManager cm = new ClientManager(model);
        Client client = cm.findOneClientByDni(dni);
        
        setTitle("Directorio de " + this.dni);
        setDefaultCloseOperation(this.close());
        setSize(330, 330);
        setLayout(null);
        
        JLabel titulo = new JLabel("MODIFICAR CLIENTE");
        titulo.setBounds(110, 7, 150, 30);

        JLabel name = new JLabel("Nombre");
        name.setBounds(30, 50, 90, 30);

        JLabel lastname1 = new JLabel("Apellido 1");
        lastname1.setBounds(30, 85, 90, 30);

        JLabel lastname2 = new JLabel("Apellido 2");
        lastname2.setBounds(30, 120, 90, 30);

        JLabel dnil = new JLabel("DNI");
        dnil.setBounds(30, 155, 90, 30);

        JLabel phone = new JLabel("Teléfono");
        phone.setBounds(30, 190, 90, 30);

        inputName = new JTextField();
        inputName.setBounds(110, 50, 200, 30);
        inputName.setText(client.getName());

        inputLastname1 = new JTextField();
        inputLastname1.setBounds(110, 85, 200, 30);
        inputLastname1.setText(client.getLastname1());

        inputLastname2 = new JTextField();
        inputLastname2.setBounds(110, 120, 200, 30);
        inputLastname2.setText(client.getLastname2());

        inputDni = new JTextField();
        inputDni.setBounds(110, 155, 200, 30);
        inputDni.setText(dni);
        inputDni.setEditable(false);

        inputPhone = new JTextField();
        inputPhone.setBounds(110, 190, 200, 30);
        inputPhone.setText(client.getPhone());
        inputPhone.addKeyListener(new KeyAdapter() {
            Border redBorder = BorderFactory.createLineBorder(Color.RED);
            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

            public void keyTyped(KeyEvent e) {
                String phone = inputPhone.getText();
                int telefonoLenght = phone.length();
                if (telefonoLenght < 8) {
                    inputPhone.setBorder(redBorder);
                    inputPhone.setText(phone);
                    inputPhone.repaint();
                } else {
                    inputPhone.setBorder(blackBorder);
                    inputPhone.setText(phone);
                    inputPhone.repaint();
                }
            }
        });

        // Definiendo los botones

        registerButton = new JButton("Modificar");
        registerButton.setToolTipText("Modificar datos de un usuario");
        registerButton.setBackground(new Color(40, 96, 144));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBounds(170, 230, 140, 30);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientManager cm = new ClientManager(model);
                ArrayList<Client> clientList = new ArrayList<Client>();
                clientList = cm.findClientsByDni(inputDni.getText());
                if (clientList.isEmpty()) {
                    // Creamos al nuevo cliente
                    Client client = new Client(model);
                    client.setName(inputName.getText().toUpperCase());
                    client.setLastname1(inputLastname1.getText().toUpperCase());
                    client.setLastname2(inputLastname2.getText().toUpperCase());
                    client.setDni(inputDni.getText().toUpperCase());
                    client.setPhone(inputPhone.getText());

                    // Vaciamos los campos
                    inputName.setText("");
                    inputLastname1.setText("");
                    inputLastname2.setText("");
                    inputDni.setText("");
                    inputPhone.setText("");

                    int result = cm.flush(client);
                    if (result == 0) {
                        JOptionPane.showMessageDialog(thisForm, "Se ha prodicido un error y no se ha dado de alta al cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(thisForm, "Cliente dado de alta correctamente");
                        ArrayList<Client> arrayClientes = new ArrayList<Client>();
                        arrayClientes = cm.findAllClients(true);
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
        add(name);
        add(lastname1);
        add(lastname2);
        add(dnil);
        add(phone);

        add(inputLastname1);
        add(inputLastname2);
        add(inputDni);
        add(inputName);
        add(inputPhone);
        add(registerButton);
    }

    public int close() {
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
