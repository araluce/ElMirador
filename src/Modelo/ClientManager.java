/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Response;

/**
 *
 * @author araluce
 */
public class ClientManager {
    
    private SimpleDateFormat sdf;
    private Model model;
    private Connection conn;

    /**
     * Constructor de ClienteManager
     */
    public ClientManager(Model model) {
        this.model = model;
        this.conn = model.getConnection();
        this.sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.GERMANY);
    }

    /* Encuentra un cliente por dni
     *
     * @param id
     * @return Client|null
     */
    public Client find(int id) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE id LIKE '" + id + "';");
            if (result.next()) {
                Client client = new Client(model);
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
                client.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("created_at")));
                    client.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    client.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                return client;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Find client by dni
     *
     * @param dni
     * @return Client|null
     */
    public Client findOneClientByDni(String dni) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE dni LIKE '" + dni + "';");
            if (result.next()) {
                Client client = new Client(model);
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
                client.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("created_at")));
                    client.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    client.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                return client;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Find client by phone number
     *
     * @param phone 9 int
     * @return Client|null
     */
    public Client findOneClientByPhone(int phone) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE phone='" + phone + "';");
            if (result.next()) {
                Client client = new Client(model);
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
                client.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("created_at")));
                    client.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    client.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }

                return client;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    /**
     * Find one client by output
     * @param output
     * @return 
     */
    public Client findOneByOutput(Output output) {
        Client client = new Client(model);
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT client_id FROM Output WHERE id = " + output.getId() + ";");
            if (result.next()) {
                client = find(result.getInt("client_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }

    /**
     * Find all clients by dni
     *
     * @param dni
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByDni(String dni) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE dni LIKE '%" + dni + "%';");
            clientList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    /**
     * Find all clients by phone number
     *
     * @param phone
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByPhone(String phone) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE phone LIKE '%" + phone + "%';");
            clientList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    /**
     * Insert a new client into Client DB table
     *
     * @param client
     * @return int
     */
    public int flush(Client client) {
        int result = 0;
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO Client (name, lastname1, lastname2, dni, phone, delete, created_at, updated_at) "
                    //            String sql = "INSERT INTO Client (name, lastname1, lastname2, dni, phone) "
                    + "VALUES ("
                    + "'" + client.getName().toUpperCase()
                    + "', '" + client.getLastname1().toUpperCase()
                    + "', '" + client.getLastname2().toUpperCase()
                    + "', '" + client.getDni().toUpperCase()
                    + "', '" + client.getPhone()
                    + "', " + client.getDelete()
                    + ", '" + sdf.format((Date) client.getCreatedAt().getTime()) 
                    + "', '" + sdf.format((Date) client.getUpdatedAt().getTime())
                    + "');";
            result = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Find all clients by name
     *
     * @param name
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByName(String name) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE name LIKE '%" + name + "%';");
            clientList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    /**
     * Find all clients by the first lastname
     *
     * @param lastname1
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByLastname1(String lastname1) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE lastname1 LIKE '%" + lastname1 + "%';");
            clientList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    /**
     * Find all clients by the second lastname
     *
     * @param lastname2
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByLastName2(String lastname2) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE lastname2 LIKE '%" + lastname2 + "%';");
            clientList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    /**
     * Find all clients
     *
     * @param all
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findAllClients(boolean all) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        try {
            ResultSet result = null;
            Statement st = conn.createStatement();
            if (all) {
                result = st.executeQuery("SELECT * FROM Client;");
            } else {
                result = st.executeQuery("SELECT * FROM Client WHERE delete = false;");
            }
            clientList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    /**
     * Find all clients by hashtable parameters
     * Encuentra a los clientes que coincidan con los parametros dados
     *
     * @param parameters
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsBy(Hashtable<String, String> parameters) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ArrayList<String> dniList = new ArrayList<String>();
        ResultSet result = null;
        boolean isSetName = parameters.containsKey("name");
        boolean isSetLastname1 = parameters.containsKey("lastname1");
        boolean isSetLastname2 = parameters.containsKey("lastname2");
        boolean isSetDni = parameters.containsKey("dni");
        boolean isSetPhone = parameters.containsKey("phone");

        if (parameters.get("name").equals("")
                && parameters.get("lastname1").equals("")
                && parameters.get("lastname2").equals("")
                && parameters.get("dni").equals("")
                && parameters.get("phone").equals("")
                && parameters.get("created_at").equals("")
                && parameters.get("updated_at").equals("")) {
            return clientList;
        }

        try {
            Statement st = conn.createStatement();
            if (isSetName) {
                result = st.executeQuery("SELECT * FROM Client WHERE name LIKE '%" + parameters.get("name") + "%';");
                while (result.next()) {
                    Client client = new Client(model);
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
                    client.setDelete(result.getBoolean("delete"));
                    Calendar date = Calendar.getInstance();
                    try {
                        date.setTime(sdf.parse(result.getString("created_at")));
                        client.setCreatedAt(date);
                        date.setTime(sdf.parse(result.getString("updated_at")));
                        client.setUpdatedAt(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetLastname1) {
                result = st.executeQuery("SELECT * FROM Client WHERE lastname1 LIKE '%" + parameters.get("lastname1") + "%';");
                while (result.next()) {
                    Client client = new Client(model);
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
                    client.setDelete(result.getBoolean("delete"));
                    Calendar date = Calendar.getInstance();
                    try {
                        date.setTime(sdf.parse(result.getString("created_at")));
                        client.setCreatedAt(date);
                        date.setTime(sdf.parse(result.getString("updated_at")));
                        client.setUpdatedAt(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetLastname2) {
                result = st.executeQuery("SELECT * FROM Client WHERE lastname2 LIKE '%" + parameters.get("lastname2") + "%';");
                while (result.next()) {
                    Client client = new Client(model);
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
                    client.setDelete(result.getBoolean("delete"));
                    Calendar date = Calendar.getInstance();
                    try {
                        date.setTime(sdf.parse(result.getString("created_at")));
                        client.setCreatedAt(date);
                        date.setTime(sdf.parse(result.getString("updated_at")));
                        client.setUpdatedAt(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetDni) {
                result = st.executeQuery("SELECT * FROM Client WHERE dni LIKE '%" + parameters.get("dni") + "%';");
                while (result.next()) {
                    Client client = new Client(model);
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
                    client.setDelete(result.getBoolean("delete"));
                    Calendar date = Calendar.getInstance();
                    try {
                        date.setTime(sdf.parse(result.getString("created_at")));
                        client.setCreatedAt(date);
                        date.setTime(sdf.parse(result.getString("updated_at")));
                        client.setUpdatedAt(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetPhone) {
                result = st.executeQuery("SELECT * FROM Client WHERE phone LIKE '%" + parameters.get("phone") + "%';");
                while (result.next()) {
                    Client client = new Client(model);
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
                    client.setDelete(result.getBoolean("delete"));
                    Calendar date = Calendar.getInstance();
                    try {
                        date.setTime(sdf.parse(result.getString("created_at")));
                        client.setCreatedAt(date);
                        date.setTime(sdf.parse(result.getString("updated_at")));
                        client.setUpdatedAt(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    private ArrayList<Client> resultSetToArray(ResultSet result) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        try {
            while (result.next()) {
                Client client = new Client(model);
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
                client.setDelete(result.getBoolean("delete"));
                Calendar date = Calendar.getInstance();
                try {
                    date.setTime(sdf.parse(result.getString("created_at")));
                    client.setCreatedAt(date);
                    date.setTime(sdf.parse(result.getString("updated_at")));
                    client.setUpdatedAt(date);
                } catch (ParseException ex) {
                    Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                clientList.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientList;
    }

    public Response removeClient(Client client) {
        Response resp = new Response();
        resp.setCode(0);

        try {
            Statement st = conn.createStatement();
            resp.setResponse("Cliente eliminado correctamente");
            resp.setCode(st.executeUpdate("UPDATE Client "
                    + "SET delete = true "
                    + "WHERE id = " + client.getId() + ";"));
        } catch (SQLException ex) {
            resp.setResponse("El cliente no ha podido ser eliminado");
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }

}
