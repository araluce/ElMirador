/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.*;
import Componentes.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author araluce
 */
public class Principal extends javax.swing.JFrame {

    private Model model = null;
    private ClientsTable clientsTable = null;
    private BillsTable billsTable = null;
    private JLabel inputSearchClientes = null;

    // Tab1
    private JTextField inputName = new JTextField();
    private JTextField inputLastname1 = new JTextField();
    private JTextField inputLastname2 = new JTextField();
    private JTextField inputDni = new JTextField();
    private JTextField inputPhone = new JTextField();
    private JButton registerButtonTab1 = new JButton();

    // Tab2
    private JComboBox clientSelect;
    private Client client_selected_new_bill;
    private JTextField dni_client = new JTextField();
    private JTextField inputLotNumber = new JTextField();
    private JTextField inputWeight = new JTextField();
    private JTextField inputPrice = new JTextField();
    private JTextField inputTReception = new JTextField();
    private JTextField inputNumHams = new JTextField();
    private JTextField inputNumPalettes = new JTextField();
    private JButton registerButtonTab2 = new JButton();

    /**
     * Creates new form Principal
     *
     * @param Model modelo -> The model
     */
    public Principal(Model modelo) {
        setUndecorated(true);
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        this.model = modelo;
        billsTable = new BillsTable(modelo);
        clientsTable = new ClientsTable(modelo, billsTable);

        initComponents();

        this.setTitle("EM - Gestión");
        this.title.setText("  JAMONES EL MIRADOR - GESTIÓN DE ALBARANES");

        this.hideTabHeader();
        this.initializeComponents();
        this.componentsStyles();
        this.addComponentTab1();
        this.addComponentTab2();

        panelResultadoBusquedaClientes.getViewport().add(this.clientsTable);
        this.billsTable.setVisible(false);
        panelResultadosAlbaranes.getViewport().add(this.billsTable);
        panelBusquedaClientes.add(this.inputSearchClientes);

        BillManager bm = new BillManager(model);
        ArrayList<Bill> billsArray = bm.findAll(true);
        System.out.println("Total bills: " + billsArray.size());

    }

    private void hideTabHeader() {
        jTabbedPane1.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                return super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
            }

            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
            }
        });
    }

    private void initializeComponents() {
        this.initializeInputSearchClientes();
        this.initializeClientTable();
        this.initializeBillsTable();

        this.updateClientSelector(false, null);

        this.setClientSelectActionListener();
    }

    private void initializeInputSearchClientes() {
        InputCliente inputClientes = new InputCliente(this.model, clientsTable);

        this.inputSearchClientes = new JLabel();
        this.inputSearchClientes.setBorder(BorderFactory.createLineBorder(Color.black));
        this.inputSearchClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Search-24.png")));
        this.inputSearchClientes.setLayout(new BorderLayout());
        this.inputSearchClientes.add(inputClientes);
    }

    private void initializeClientTable() {
        ClientManager cm = new ClientManager(model);
        ArrayList<Client> clientsArray = new ArrayList<Client>();
        clientsArray = cm.findAllClients(false);
        DefaultTableModel dtmodel = (DefaultTableModel) this.clientsTable.getModel();

        for (Client c : clientsArray) {
            dtmodel.addRow(new Object[]{c.getName(), c.getLastname1() + " " + c.getLastname2(), c.getDni(), c.getPhone()});
        }
    }

    private void initializeBillsTable() {
        BillManager bm = new BillManager(model);
        ArrayList<Bill> billsArray = bm.findAll(true);
        
        System.out.println(billsArray.size() + " bills was founded");
        
        DefaultTableModel dtmodel = (DefaultTableModel) billsTable.getModel();
        for (Bill b : billsArray) {
            ArrayList<Input> inputs = b.getInputs();
            ArrayList<Output> outputs = b.getOutputs();
            ArrayList<Unsubscribe> unsubscribes = b.getUnsubscribes();

            dtmodel.addRow(new Object[]{b.getId(), inputs.size(), outputs.size(), unsubscribes.size()});
            System.out.println(
                    "Added to bill table\n"
                    + "\tid: " + b.getId()
                    + "\tnum_inputs: " + inputs.size()
                    + "\tnum_outputs: " + outputs.size()
                    + "\tnum_unsubscribes: " + unsubscribes.size()
            );
        }

        billsTable.setVisible(true);
    }

    private void setClientSelectActionListener() {
        this.clientSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientSelectActionPerformed(evt);
            }
        });
    }

    private void componentsStyles() {
        this.headerStyle();
        this.buttonStyles();
        this.panelStyles();
    }

    private void buttonStyles() {
        this.exitButton.setBackground(Color.WHITE);
        this.exitButton.setFocusPainted(false);

        this.minimizeButton.setBackground(Color.WHITE);
        this.minimizeButton.setFocusPainted(false);

        this.buttonClients.setBackground(Color.WHITE);
        this.buttonClients.setFocusPainted(false);

        this.buttonAltaClientes.setBackground(Color.WHITE);
        this.buttonAltaClientes.setFocusPainted(false);

        this.buttonGestionAlbaranes.setBackground(Color.WHITE);
        this.buttonGestionAlbaranes.setFocusPainted(false);
    }

    private void panelStyles() {
        getContentPane().setBackground(Color.WHITE);

        this.jTabbedPane1.setBackground(Color.WHITE);
        this.jTabbedPane1.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        this.panelAreaClientes.setBackground(Color.WHITE);
        this.panelAreaClientes.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        this.panelClientBills.setBackground(Color.WHITE);
        this.panelClientBills.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        this.panelBusquedaClientes.setBackground(Color.WHITE);

        this.panelResultadoBusquedaClientes.getViewport().setBackground(Color.WHITE);

        this.panelAltaClientes.setBackground(Color.WHITE);
        this.panelAltaClientes.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        this.panelAltaAlbaran.setBackground(Color.WHITE);
        this.panelAltaAlbaran.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    private void headerStyle() {
        Color colorHeader = new Color(40, 96, 144);
        this.header.setBackground(colorHeader);
    }

    private void updateClientSelector(boolean setClient, Client client) {
        if (setClient) {

        } else {
            ClientManager cm = new ClientManager(model);
            ArrayList<Client> clients = cm.findAllClients(false);
            Vector client_model = new Vector();
            for (Client c : clients) {
                client_model.addElement(c);
            }

            this.clientSelect = new JComboBox(client_model);
            this.clientSelect.setRenderer(new ClientRenderer());
        }
    }

    private void addComponentTab1() {
        final Principal that = this;

        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(360, 330);
        setLayout(null);

        JLabel titulo = new JLabel("ALTA DE CLIENTES");
        titulo.setBounds(30, 7, 500, 30);
        titulo.setFont(new java.awt.Font("DejaVu Sans", 1, 24));

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
        registerButtonTab1 = new JButton("Registrar");
        registerButtonTab1.setToolTipText("Dar de alta al usuario");
        registerButtonTab1.setBackground(new Color(40, 96, 144));
        registerButtonTab1.setForeground(Color.WHITE);
        registerButtonTab1.setFocusPainted(false);
        registerButtonTab1.setBounds(170, 230, 140, 30);
        registerButtonTab1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientManager cm = new ClientManager(model);
                ArrayList<Client> clientList = new ArrayList<Client>();
                clientList = cm.findClientsByDni(inputDni.getText());
                if (clientList.isEmpty()) {
                    // Creamos al nuevo cliente
                    Client cliente = new Client(model);
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

                    int resultado = cm.flush(cliente);
                    if (resultado == 0) {
                        JOptionPane.showMessageDialog(null, "Se ha producido un error y no se ha dado de alta al cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente dado de alta correctamente");
                        ArrayList<Client> arrayClientes = new ArrayList<Client>();
                        arrayClientes = cm.findAllClients(true);
                        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
                        model.setRowCount(0);
                        for (Client c : arrayClientes) {
                            model.addRow(new Object[]{c.getName(), c.getLastname1() + " " + c.getLastname2(), c.getDni(), c.getPhone()});
                        }
                    }
                    that.updateClientSelector(false, null);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con este DNI", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panelAltaClientes.add(titulo);
        panelAltaClientes.add(nombre);
        panelAltaClientes.add(apellido1);
        panelAltaClientes.add(apellido2);
        panelAltaClientes.add(dni);
        panelAltaClientes.add(telefono);

        panelAltaClientes.add(inputLastname1);
        panelAltaClientes.add(inputLastname2);
        panelAltaClientes.add(inputDni);
        panelAltaClientes.add(inputName);
        panelAltaClientes.add(inputPhone);

        panelAltaClientes.add(registerButtonTab1);
    }

    private void addComponentTab2() {
        final Principal that = this;

        JLabel title = new JLabel("NUEVO ALBARÁN");
        title.setBounds(30, 7, 500, 30);
        title.setFont(new java.awt.Font("DejaVu Sans", 1, 24));

        JLabel client_label = new JLabel("Cliente");
        client_label.setBounds(30, 50, 150, 30);

        JLabel dni_label = new JLabel("Dni cliente");
        dni_label.setBounds(30, 85, 150, 30);

        JLabel lot_number = new JLabel("Número de lote");
        lot_number.setBounds(30, 120, 150, 30);

        JLabel weight = new JLabel("Peso");
        weight.setBounds(30, 155, 150, 30);

        JLabel price = new JLabel("Precio");
        price.setBounds(30, 190, 150, 30);

        JLabel t_reception = new JLabel("Tª Recepción");
        t_reception.setBounds(30, 225, 150, 30);

        JLabel num_hams = new JLabel("Número de jamones");
        num_hams.setBounds(30, 260, 150, 30);

        JLabel num_palettes = new JLabel("Número de paletas");
        num_palettes.setBounds(30, 295, 150, 30);

        clientSelect.setBounds(190, 50, 400, 30);

        dni_client = new JTextField();
        dni_client.setEditable(false);
        dni_client.setBounds(190, 85, 200, 30);

        inputLotNumber = new JTextField();
        inputLotNumber.setBounds(190, 120, 200, 30);

        inputWeight = new JTextField();
        inputWeight.setBounds(190, 155, 200, 30);

        inputPrice = new JTextField();
        inputPrice.setBounds(190, 190, 200, 30);

        inputTReception = new JTextField();
        inputTReception.setBounds(190, 225, 200, 30);

        inputNumHams = new JTextField();
        inputNumHams.setBounds(190, 260, 200, 30);

        inputNumPalettes = new JTextField();
        inputNumPalettes.setBounds(190, 295, 200, 30);

        // Definiendo los botones
        registerButtonTab2 = new JButton("Alta");
        registerButtonTab2.setToolTipText("Dar de alta el albarán");
        registerButtonTab2.setBackground(new Color(40, 96, 144));
        registerButtonTab2.setForeground(Color.WHITE);
        registerButtonTab2.setFocusPainted(false);
        registerButtonTab2.setBounds(30, 330, 140, 30);
        registerButtonTab2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;

                BillManager bm = new BillManager(model);
                Bill bill = new Bill(model);
                bill.setId(bm.getLastId());

                if (client_selected_new_bill != null) {
                    bill.setClient(client_selected_new_bill);
                } else {
                    isValid = false;
                }

                Input input = new Input(model);
                input.setBill(bill);

                if ("".equals(inputLotNumber.getText().trim())) {
                    isValid = false;
                } else {
                    input.setLotNumber(Integer.parseInt(inputLotNumber.getText()));
                }

                if ("".equals(inputNumHams.getText().trim())) {
                    isValid = false;
                } else {
                    input.setNumHams(Integer.parseInt(inputNumHams.getText()));
                }

                if ("".equals(inputNumPalettes.getText())) {
                    isValid = false;
                } else {
                    input.setNumPalettes(Integer.parseInt(inputNumPalettes.getText()));
                }

                if ("".equals(inputPrice.getText().trim())) {
                    isValid = false;
                } else {
                    input.setPrice(Float.parseFloat(inputPrice.getText()));
                }

                if ("".equals(inputTReception.getText().trim())) {
                    isValid = false;
                } else {
                    input.setTReception(inputTReception.getText());
                }

                if ("".equals(inputWeight.getText().trim())) {
                    isValid = false;
                } else {
                    input.setWeight(Float.parseFloat(inputWeight.getText()));
                }

                bill.addInput(input);
                if (isValid) {
                    int resultado = bm.flush(bill);
                    if (resultado == 0) {
                        JOptionPane.showMessageDialog(null, "Se ha producido un error y no se ha dado de insertado el albarán", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Albarán creado correctamente");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panelAltaAlbaran.add(title);
        panelAltaAlbaran.add(client_label);
        panelAltaAlbaran.add(dni_label);
        panelAltaAlbaran.add(lot_number);
        panelAltaAlbaran.add(weight);
        panelAltaAlbaran.add(price);
        panelAltaAlbaran.add(t_reception);
        panelAltaAlbaran.add(num_hams);
        panelAltaAlbaran.add(num_palettes);

        panelAltaAlbaran.add(clientSelect);
        panelAltaAlbaran.add(dni_client);
        panelAltaAlbaran.add(inputLotNumber);
        panelAltaAlbaran.add(inputWeight);
        panelAltaAlbaran.add(inputPrice);
        panelAltaAlbaran.add(inputTReception);
        panelAltaAlbaran.add(inputNumHams);
        panelAltaAlbaran.add(inputNumPalettes);

        panelAltaAlbaran.add(registerButtonTab2);
    }

    /**
     * s
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        buttonClients = new javax.swing.JButton();
        buttonAltaClientes = new javax.swing.JButton();
        buttonGestionAlbaranes = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelAreaClientes = new javax.swing.JPanel();
        panelBusquedaClientes = new javax.swing.JPanel();
        panelResultadoBusquedaClientes = new javax.swing.JScrollPane();
        panelClientBills = new javax.swing.JPanel();
        panelResultadosAlbaranes = new javax.swing.JScrollPane();
        jButtonNewBill = new javax.swing.JButton();
        panelAltaClientes = new javax.swing.JPanel();
        panelAltaAlbaran = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setAlignmentX(0.0F);
        header.setAlignmentY(0.0F);
        header.setMaximumSize(getMaximumSize());

        title.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));

        minimizeButton.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Minimize-16.png"))); // NOI18N
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Exit-16.png"))); // NOI18N
        exitButton.setPreferredSize(new java.awt.Dimension(24, 24));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        buttonClients.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Client-64.png"))); // NOI18N
        buttonClients.setText("Clientes");
        buttonClients.setToolTipText("");
        buttonClients.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonClients.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClientsActionPerformed(evt);
            }
        });
        jPanel1.add(buttonClients, new java.awt.GridBagConstraints());

        buttonAltaClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/AddUser-64.png"))); // NOI18N
        buttonAltaClientes.setText("Alta de clientes");
        buttonAltaClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAltaClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAltaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAltaClientesMouseClicked(evt);
            }
        });
        buttonAltaClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAltaClientesActionPerformed(evt);
            }
        });
        jPanel1.add(buttonAltaClientes, new java.awt.GridBagConstraints());

        buttonGestionAlbaranes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Bill-64.png"))); // NOI18N
        buttonGestionAlbaranes.setText("Gestión de albaranes");
        buttonGestionAlbaranes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonGestionAlbaranes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGestionAlbaranes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGestionAlbaranesActionPerformed(evt);
            }
        });
        jPanel1.add(buttonGestionAlbaranes, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(null);

        panelAreaClientes.setBorder(null);
        panelAreaClientes.setFocusable(false);

        panelBusquedaClientes.setLayout(new java.awt.GridLayout(1, 0));

        jButtonNewBill.setText("Nuevo Albarán");
        jButtonNewBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewBillActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelClientBillsLayout = new javax.swing.GroupLayout(panelClientBills);
        panelClientBills.setLayout(panelClientBillsLayout);
        panelClientBillsLayout.setHorizontalGroup(
            panelClientBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientBillsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelResultadosAlbaranes, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonNewBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelClientBillsLayout.setVerticalGroup(
            panelClientBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientBillsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClientBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNewBill, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelResultadosAlbaranes, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout panelAreaClientesLayout = new javax.swing.GroupLayout(panelAreaClientes);
        panelAreaClientes.setLayout(panelAreaClientesLayout);
        panelAreaClientesLayout.setHorizontalGroup(
            panelAreaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelResultadoBusquedaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
            .addComponent(panelBusquedaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelClientBills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelAreaClientesLayout.setVerticalGroup(
            panelAreaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAreaClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBusquedaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResultadoBusquedaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelClientBills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", panelAreaClientes);

        javax.swing.GroupLayout panelAltaClientesLayout = new javax.swing.GroupLayout(panelAltaClientes);
        panelAltaClientes.setLayout(panelAltaClientesLayout);
        panelAltaClientesLayout.setHorizontalGroup(
            panelAltaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        panelAltaClientesLayout.setVerticalGroup(
            panelAltaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", panelAltaClientes);

        javax.swing.GroupLayout panelAltaAlbaranLayout = new javax.swing.GroupLayout(panelAltaAlbaran);
        panelAltaAlbaran.setLayout(panelAltaAlbaranLayout);
        panelAltaAlbaranLayout.setHorizontalGroup(
            panelAltaAlbaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        panelAltaAlbaranLayout.setVerticalGroup(
            panelAltaAlbaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", panelAltaAlbaran);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientSelectActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox comboBox = (JComboBox) evt.getSource();
        Client client = (Client) comboBox.getSelectedItem();
        this.client_selected_new_bill = client;
        this.dni_client.setText(client.getDni());
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void buttonGestionAlbaranesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGestionAlbaranesActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_buttonGestionAlbaranesActionPerformed

    private void buttonAltaClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAltaClientesActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_buttonAltaClientesActionPerformed

    private void buttonAltaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAltaClientesMouseClicked
        //ClientRegister ac = new ClientRegister(model, clientsTable);
        //ac.setLocationRelativeTo(this);
        //ac.setVisible(true);
    }//GEN-LAST:event_buttonAltaClientesMouseClicked

    private void buttonClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClientsActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_buttonClientsActionPerformed

    private void jButtonNewBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewBillActionPerformed
        updateClientSelector(false, null);
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButtonNewBillActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAltaClientes;
    private javax.swing.JButton buttonClients;
    private javax.swing.JButton buttonGestionAlbaranes;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButtonNewBill;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JPanel panelAltaAlbaran;
    private javax.swing.JPanel panelAltaClientes;
    private javax.swing.JPanel panelAreaClientes;
    private javax.swing.JPanel panelBusquedaClientes;
    private javax.swing.JPanel panelClientBills;
    private javax.swing.JScrollPane panelResultadoBusquedaClientes;
    private javax.swing.JScrollPane panelResultadosAlbaranes;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
