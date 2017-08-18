/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import Modelo.Client;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author araluce
 */
public class ClientRenderer extends BasicComboBoxRenderer{
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            if (value != null)
            {
                Client client = (Client)value;
                index = client.getId();
                setText( client.getName().toUpperCase() + " " + client.getLastname1() + " " + client.getLastname2() + " " + client.getDni()  );
            }
            
            if (index == -1)
            {
                setText( "No clients" );
            }
            return this;
        }
}