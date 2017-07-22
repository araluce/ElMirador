/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo.*;
import Vista.DirectorioCliente;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

/**
 *
 * @author araluce
 */
public class TablaAlbaranesCliente extends JTable{
    private Modelo modelo;
    private TablaAlbaranesCliente estaTabla = this;
    private String dni = null;

    public TablaAlbaranesCliente(Modelo modelo, String ndni) {
        setBackground(Color.WHITE);
        this.dni = ndni;
        this.modelo = modelo;
        this.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nº ALBARÁN", "Nº RSI", "Nº LOTE", "PROCEDENCIA", "PESO TOTAL (Kg)", "F. ENTRADA", "PRECIO TOTAL"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.util.Date.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        this.setToolTipText("Tabla de de albaranes del cliente " + this.dni);
        this.setColumnSelectionAllowed(true);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        this.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (this.getColumnModel().getColumnCount() > 0) {
            this.getColumnModel().getColumn(0).setResizable(false);
            this.getColumnModel().getColumn(1).setResizable(false);
            this.getColumnModel().getColumn(2).setResizable(false);
            this.getColumnModel().getColumn(3).setResizable(false);
            this.getColumnModel().getColumn(4).setResizable(false);
            this.getColumnModel().getColumn(5).setResizable(false);
            this.getColumnModel().getColumn(6).setResizable(false);
        }
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting()) {
                    //String dni = table.getValueAt(table.getSelectedRow(), 2).toString();
                    //DirectorioCliente dc = new DirectorioCliente(m, dni, estaTabla);
                    //dc.setLocationRelativeTo(table);
                    //dc.setVisible(true);
                }
            }
        });
        Color colorHeader = new Color(40, 96, 144);
        JTableHeader header = getTableHeader();
        header.setBackground(colorHeader);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
    }
}
