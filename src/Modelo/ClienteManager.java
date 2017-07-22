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
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author araluce
 */
public class ClienteManager {

    /**
     * Constructor de ClienteManager
     */
    public ClienteManager() {
    }

    /**
     * Encuentra un cliente por dni
     *
     * @param dni 8 caracteres y 1 letra que identifican a un cliente
     * @return Cliente|null
     */
    public Cliente findOneClienteByDni(Connection conn, String dni) {
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE dni LIKE '" + dni + "';");
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido1(resultado.getString("apellido1"));
                cliente.setApellido2(resultado.getString("apellido2"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setTelefono(resultado.getString("telefono"));
                return cliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Encuentra un cliente por telefono
     *
     * @param telefono 9 enteros
     * @return Cliente|null
     */
    public Cliente findOneClienteByTelefono(Connection conn, int telefono) {
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE telefono='" + telefono + "';");
            if (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido1(resultado.getString("apellido1"));
                cliente.setApellido2(resultado.getString("apellido2"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setTelefono(resultado.getString("telefono"));
                return cliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Encuentra a los clientes que coincidan con el dni dado
     *
     * @param dni
     * @return ArrayList<Cliente>|null
     */
    public ArrayList<Cliente> findClientesByDni(Connection conn, String dni) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE dni LIKE '%" + dni + "%';");
            listaClientes = resultSetToArray(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }

    /**
     * Encuentra a los clientes que coincidan con el telefono dado
     *
     * @param telefono
     * @return ArrayList<Cliente>|null
     */
    public ArrayList<Cliente> findClientesByTelefono(Connection conn, String telefono) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE telefono LIKE '%" + telefono + "%';");
            listaClientes = resultSetToArray(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }
    
    /**
     * Inserta un nuevo cliente en la tabla Cliente
     *
     * @param conn
     * @param cliente
     * @return int
     */
    public int clienteFlush(Connection conn, Cliente cliente) {
        int resultado = 0;
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO CLIENTE (nombre, apellido1, apellido2, dni, telefono) VALUES ('"+cliente.getNombre().toUpperCase()+"', '"+cliente.getApellido1().toUpperCase()+"', '"+cliente.getApellido2().toUpperCase()+"', '"+cliente.getDni().toUpperCase()+"', '"+cliente.getTelefono()+"');";
            resultado = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /**
     * Encuentra a los clientes que coincidan con el nombre dado
     *
     * @param nombre
     * @return ArrayList<Cliente>|null
     */
    public ArrayList<Cliente> findClientesByNombre(Connection conn, String nombre) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE nombre LIKE '%" + nombre + "%';");
            listaClientes = resultSetToArray(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }

    /**
     * Encuentra a los clientes que coincidan con el primer apellido dado
     *
     * @param apellido1
     * @return ArrayList<Cliente>|null
     */
    public ArrayList<Cliente> findClientesByApellido1(Connection conn, String apellido1) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE apellido1 LIKE '%" + apellido1 + "%';");
            listaClientes = resultSetToArray(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }

    /**
     * Encuentra a los clientes que coincidan con el segundo apellido dado
     *
     * @param apellido2
     * @return ArrayList<Cliente>|null
     */
    public ArrayList<Cliente> findClientesByApellido2(Connection conn, String apellido2) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE apellido2 LIKE '%" + apellido2 + "%';");
            listaClientes = resultSetToArray(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }
    
    /**
     * Encuentra a todos los clientes
     *
     * @return ArrayList<Cliente>|null
     */
    public ArrayList<Cliente> findAllClientes(Connection conn) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ResultSet resultado = null;
        try {
            Statement st = conn.createStatement();
            resultado = st.executeQuery("SELECT * FROM CLIENTE;");
            listaClientes = resultSetToArray(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }

    /**
     * Encuentra a los clientes que coincidan con los parametros dados
     *
     * @param parametros
     * @return ArrayList<Cliente>|null
     */
    public ArrayList<Cliente> findClientesBy(Connection conn, Hashtable<String, String> parametros) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        ArrayList<String> listaDni = new ArrayList<String>();
        ResultSet resultado = null;
        boolean isSetNombre = parametros.containsKey("nombre");
        boolean isSetApellido1 = parametros.containsKey("apellido1");
        boolean isSetApellido2 = parametros.containsKey("apellido2");
        boolean isSetDni = parametros.containsKey("dni");
        boolean isSetTelefono = parametros.containsKey("telefono");
        
        if(parametros.get("nombre").equals("") && parametros.get("apellido1").equals("") &&
                parametros.get("apellido2").equals("") && parametros.get("dni").equals("") &&
                parametros.get("telefono").equals("")){
            return listaClientes;
        }

        try {
            Statement st = conn.createStatement();
            if (isSetNombre) {
                resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE nombre LIKE '%" + parametros.get("nombre") + "%';");
                while (resultado.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido1(resultado.getString("apellido1"));
                    cliente.setApellido2(resultado.getString("apellido2"));
                    cliente.setDni(resultado.getString("dni"));
                    cliente.setTelefono(resultado.getString("telefono"));
                    if (!listaDni.contains(cliente.getDni())) {
                        listaDni.add(cliente.getDni());
                        listaClientes.add(cliente);
                    }
                }
            }
            if (isSetApellido1) {
                resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE apellido1 LIKE '%" + parametros.get("apellido1") + "%';");
                while (resultado.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido1(resultado.getString("apellido1"));
                    cliente.setApellido2(resultado.getString("apellido2"));
                    cliente.setDni(resultado.getString("dni"));
                    cliente.setTelefono(resultado.getString("telefono"));
                    if (!listaDni.contains(cliente.getDni())) {
                        listaDni.add(cliente.getDni());
                        listaClientes.add(cliente);
                    }
                }
            }
            if (isSetApellido2) {
                resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE apellido2 LIKE '%" + parametros.get("apellido2") + "%';");
                while (resultado.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido1(resultado.getString("apellido1"));
                    cliente.setApellido2(resultado.getString("apellido2"));
                    cliente.setDni(resultado.getString("dni"));
                    cliente.setTelefono(resultado.getString("telefono"));
                    if (!listaDni.contains(cliente.getDni())) {
                        listaDni.add(cliente.getDni());
                        listaClientes.add(cliente);
                    }
                }
            }
            if (isSetDni) {
                resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE dni LIKE '%" + parametros.get("dni") + "%';");
                while (resultado.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido1(resultado.getString("apellido1"));
                    cliente.setApellido2(resultado.getString("apellido2"));
                    cliente.setDni(resultado.getString("dni"));
                    cliente.setTelefono(resultado.getString("telefono"));
                    if (!listaDni.contains(cliente.getDni())) {
                        listaDni.add(cliente.getDni());
                        listaClientes.add(cliente);
                    }
                }
            }
            if (isSetTelefono) {
                resultado = st.executeQuery("SELECT * FROM CLIENTE WHERE telefono LIKE '%" + parametros.get("telefono") + "%';");
                while (resultado.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(resultado.getString("nombre"));
                    cliente.setApellido1(resultado.getString("apellido1"));
                    cliente.setApellido2(resultado.getString("apellido2"));
                    cliente.setDni(resultado.getString("dni"));
                    cliente.setTelefono(resultado.getString("telefono"));
                    if (!listaDni.contains(cliente.getDni())) {
                        listaDni.add(cliente.getDni());
                        listaClientes.add(cliente);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaClientes;
    }

    private ArrayList<Cliente> resultSetToArray(ResultSet resultado) {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido1(resultado.getString("apellido1"));
                cliente.setApellido2(resultado.getString("apellido2"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setTelefono(resultado.getString("telefono"));
                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }

}
