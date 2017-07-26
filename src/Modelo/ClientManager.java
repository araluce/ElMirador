/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Response;

/**
 *
 * @author araluce
 */
public class ClientManager {

    /**
     * Constructor de ClienteManager
     */
    public ClientManager() {
    }

    /* Encuentra un cliente por dni
     *
     * @param dni 8 caracteres y 1 letra que identifican a un cliente
     * @return Client|null
     */
    public Client find(Connection conn, int id) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE id LIKE '" + id + "';");
            if (result.next()) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
//                Calendar date = Calendar.getInstance();
//                date.setTime(result.getDate("created_at"));
//                client.setCreatedAt(date);
//                date.setTime(result.getDate("updated_at"));
//                client.setUpdatedAt(date);
                return client;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Encuentra un cliente por dni
     *
     * @param dni 8 caracteres y 1 letra que identifican a un cliente
     * @return Client|null
     */
    public Client findOneClientByDni(Connection conn, String dni) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE dni LIKE '" + dni + "';");
            if (result.next()) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
//                Calendar date = Calendar.getInstance();
//                date.setTime(result.getDate("created_at"));
//                client.setCreatedAt(date);
//                date.setTime(result.getDate("updated_at"));
//                client.setUpdatedAt(date);
                return client;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Encuentra un cliente por telefono
     *
     * @param phone 9 enteros
     * @return Client|null
     */
    public Client findOneClientByPhone(Connection conn, int phone) {
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery("SELECT * FROM Client WHERE phone='" + phone + "';");
            if (result.next()) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
//                Calendar date = Calendar.getInstance();
//                date.setTime(result.getDate("created_at"));
//                client.setCreatedAt(date);
//                date.setTime(result.getDate("updated_at"));
//                client.setUpdatedAt(date);
                return client;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Encuentra a los clientes que coincidan con el dni dado
     *
     * @param dni
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByDni(Connection conn, String dni) {
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
     * Encuentra a los clientes que coincidan con el telefono dado
     *
     * @param phone
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByPhone(Connection conn, String phone) {
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
     * Inserta un nuevo cliente en la tabla Client
     *
     * @param conn
     * @param client
     * @return int
     */
    public int clientFlush(Connection conn, Client client) {
        int result = 0;
        try {
            System.out.println(client.getCreatedAt().getTime().getClass().toString());

            Statement st = conn.createStatement();
//            String sql = "INSERT INTO Client (name, lastname1, lastname2, dni, phone, created_at, updated_at) "
            String sql = "INSERT INTO Client (name, lastname1, lastname2, dni, phone) "
                    + "VALUES ("
                    + "'" + client.getName().toUpperCase() + "', "
                    + "'" + client.getLastname1().toUpperCase() + "', "
                    + "'" + client.getLastname2().toUpperCase() + "', "
                    + "'" + client.getDni().toUpperCase() + "', "
                    + "'" + client.getPhone() + "');";
//                    + "'" + client.getCreatedAt().currentTimeMillis() + "', "
//                    + "'" + client.getUpdatedAt().currentTimeMillis() + "');";
            result = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Encuentra a los clientes que coincidan con el nombre dado
     *
     * @param name
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByName(Connection conn, String name) {
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
     * Encuentra a los clientes que coincidan con el primer apellido dado
     *
     * @param lastname1
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByLastname1(Connection conn, String lastname1) {
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
     * Encuentra a los clientes que coincidan con el segundo apellido dado
     *
     * @param lastname2
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsByApellido2(Connection conn, String lastname2) {
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
     * Encuentra a todos los clientes
     *
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findAllClients(Connection conn, boolean all) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ResultSet result = null;
        try {
            Statement st = conn.createStatement();
            if (all) {
                result = st.executeQuery("SELECT * FROM Client;");
            } else {
                result = st.executeQuery("SELECT * FROM Client WHERE delete != false;");
            }
            clientList = resultSetToArray(result);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    /**
     * Encuentra a los clientes que coincidan con los parametros dados
     *
     * @param parameters
     * @return ArrayList<Client>|null
     */
    public ArrayList<Client> findClientsBy(Connection conn, Hashtable<String, String> parameters) {
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
                && parameters.get("phone").equals("")) {
//                && parameters.get("created_at").equals("")
//                && parameters.get("updated_at").equals("")) {
            return clientList;
        }

        try {
            Statement st = conn.createStatement();
            if (isSetName) {
                result = st.executeQuery("SELECT * FROM Client WHERE name LIKE '%" + parameters.get("name") + "%';");
                while (result.next()) {
                    Client client = new Client();
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
//                    Calendar date = Calendar.getInstance();
//                    date.setTime(result.getDate("created_at"));
//                    client.setCreatedAt(date);
//                    date.setTime(result.getDate("updated_at"));
//                    client.setUpdatedAt(date);
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetLastname1) {
                result = st.executeQuery("SELECT * FROM Client WHERE lastname1 LIKE '%" + parameters.get("lastname1") + "%';");
                while (result.next()) {
                    Client client = new Client();
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
//                    Calendar date = Calendar.getInstance();
//                    date.setTime(result.getDate("created_at"));
//                    client.setCreatedAt(date);
//                    date.setTime(result.getDate("updated_at"));
//                    client.setUpdatedAt(date);
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetLastname2) {
                result = st.executeQuery("SELECT * FROM Client WHERE lastname2 LIKE '%" + parameters.get("lastname2") + "%';");
                while (result.next()) {
                    Client client = new Client();
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
//                    Calendar date = Calendar.getInstance();
//                    date.setTime(result.getDate("created_at"));
//                    client.setCreatedAt(date);
//                    date.setTime(result.getDate("updated_at"));
//                    client.setUpdatedAt(date);
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetDni) {
                result = st.executeQuery("SELECT * FROM Client WHERE dni LIKE '%" + parameters.get("dni") + "%';");
                while (result.next()) {
                    Client client = new Client();
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
//                    Calendar date = Calendar.getInstance();
//                    date.setTime(result.getDate("created_at"));
//                    client.setCreatedAt(date);
//                    date.setTime(result.getDate("updated_at"));
//                    client.setUpdatedAt(date);
                    if (!dniList.contains(client.getDni())) {
                        dniList.add(client.getDni());
                        clientList.add(client);
                    }
                }
            }
            if (isSetPhone) {
                result = st.executeQuery("SELECT * FROM Client WHERE phone LIKE '%" + parameters.get("phone") + "%';");
                while (result.next()) {
                    Client client = new Client();
                    client.setId(result.getInt("id"));
                    client.setName(result.getString("name"));
                    client.setLastname1(result.getString("lastname1"));
                    client.setLastname2(result.getString("lastname2"));
                    client.setDni(result.getString("dni"));
                    client.setPhone(result.getString("phone"));
//                    Calendar date = Calendar.getInstance();
//                    date.setTime(result.getDate("created_at"));
//                    client.setCreatedAt(date);
//                    date.setTime(result.getDate("updated_at"));
//                    client.setUpdatedAt(date);
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
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setName(result.getString("name"));
                client.setLastname1(result.getString("lastname1"));
                client.setLastname2(result.getString("lastname2"));
                client.setDni(result.getString("dni"));
                client.setPhone(result.getString("phone"));
//                Calendar date = Calendar.getInstance();
//                date.setTime(result.getDate("created_at"));
//                client.setCreatedAt(date);
//                date.setTime(result.getDate("updated_at"));
//                client.setUpdatedAt(date);
                clientList.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientList;
    }

    public Response removeClient(Client client, Connection conn) {
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
