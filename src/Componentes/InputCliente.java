/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author araluce
 */
public class InputCliente extends JTextField {

    private Modelo m;
    private TablaClientes tablaClientes;
    private boolean band = true;
    private String placeholder = "";
    private Color phColor = new Color(0, 0, 0);

    public InputCliente(Modelo modelo, TablaClientes tabla) {
        super();
        this.m = modelo;
        this.tablaClientes = tabla;

        setMargin(new Insets(3, 24, 3, 6));
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                band = (getText().length() > 0) ? false : true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                band = false;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            }

        });
        setPlaceholder("Buscar cliente");

        KeyListener keyListener = new KeyListener() {
            public void keyPressed(KeyEvent keyEvent) {
                
            }

            public void keyReleased(KeyEvent keyEvent) {

            }

            public void keyTyped(KeyEvent keyEvent) {
                String busqueda = getText().toUpperCase();
                System.out.println("Busqueda: '" + busqueda + "'");
                if (busqueda.trim().equals("")) {
                    ClienteManager cm = new ClienteManager();
                    ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
                    arrayClientes = cm.findAllClientes(m.getConnection());
                    DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
                    model.setRowCount(0);
                    for (Cliente c : arrayClientes) {
                        model.addRow(new Object[]{c.getNombre(), c.getApellido1() + " " + c.getApellido2(), c.getDni(), c.getTelefono()});
                    }
                } else {
                    ClienteManager cm = new ClienteManager();
                    ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
                    Hashtable<String, String> parametros = new Hashtable<String, String>();
                    parametros.put("nombre", busqueda);
                    parametros.put("apellido1", busqueda);
                    parametros.put("apellido2", busqueda);
                    parametros.put("dni", busqueda);
                    parametros.put("telefono", busqueda);
                    DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
                    model.setRowCount(0);
                    arrayClientes = cm.findClientesBy(m.getConnection(), parametros);
                    for (Cliente c : arrayClientes) {
                        model.addRow(new Object[]{c.getNombre(), c.getApellido1() + " " + c.getApellido2(), c.getDni(), c.getTelefono()});
                    }
                }
            }
        };

        addKeyListener(keyListener);
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public Color getPhColor() {
        return phColor;
    }

    public void setPhColor(Color phColor) {
        this.phColor = phColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //color de placeholder 
        g.setColor(new Color(phColor.getRed(), phColor.getGreen(), phColor.getBlue(), 90));
        //dibuja texto
        g.drawString((band) ? placeholder : "", getMargin().left, (getSize().height) / 2 + getFont().getSize() / 2);
    }

}
