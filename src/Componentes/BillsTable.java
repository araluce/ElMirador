/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo.*;
import Vista.ClientDirectory;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author araluce
 */
public class BillsTable extends JTable {

    private Model model;
    private BillsTable thisTable = null;

    public BillsTable(Model model) {
        setBackground(Color.WHITE);
        thisTable = this;
        this.model = model;
        this.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Albarán", "Entradas", "Retiradas", "Bajas"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        this.setToolTipText("Tabla de albaranes");
        this.setColumnSelectionAllowed(true);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        this.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (this.getColumnModel().getColumnCount() > 0) {
            this.getColumnModel().getColumn(0).setResizable(false);
            this.getColumnModel().getColumn(1).setResizable(false);
            this.getColumnModel().getColumn(2).setResizable(false);
            this.getColumnModel().getColumn(3).setResizable(false);
        }
        final JTable table = this;
        final Model m = this.model;
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting()) {
                    
                }
            }
        }
        );
        Color colorHeader = new Color(40, 96, 144);
        JTableHeader header = getTableHeader();
        header.setBackground(colorHeader);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        setBackground(Color.WHITE);
    }

}
