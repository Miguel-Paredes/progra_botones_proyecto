import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Productos {
    private JPanel Productos;
    private JButton buscarProductoButton;
    private JButton agregarProductoButton;
    private JButton actualizarProductoButton;
    private JButton eliminarProductoButton;
    private JTextField idProducto;
    private JTextField nombreProducto;
    private JTextField descripcionProducto;
    private JTextField stock;
    private JTextField precio;
    static String DB_URL = "jdbc:mysql://localhost/medical";
    static String USER = "root";
    static String PASS = "root";
    static String QUERY = "SELECT * FROM producto";

    public Productos() {
        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(diferente(Integer.parseInt(idProducto.getText()))==false){
                    agregarProducto(Integer.parseInt(idProducto.getText()),nombreProducto.getText(),descripcionProducto.getText(),Integer.parseInt(stock.getText()),Float.parseFloat(precio.getText()));
                }
                else {
                    idProducto.setText("Ese id ya lo usa otro producto");
                }
            }
        });

        actualizarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto(Integer.parseInt(idProducto.getText()),nombreProducto.getText(),descripcionProducto.getText(),Integer.parseInt(stock.getText()),Float.parseFloat(precio.getText()));
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(Integer.parseInt(idProducto.getText()));
            }
        });
    }

    public void buscarProducto(){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String buscar = "SELECT * FROM Producto";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(buscar);
            while (rs.next()) {
                if(Integer.parseInt(idProducto.getText()) == rs.getInt("idProducto")){
                    idProducto.setText(rs.getString("idProducto"));
                    nombreProducto.setText(rs.getString("nombreProducto"));
                    descripcionProducto.setText(rs.getString("descripcionProducto"));
                    stock.setText(String.valueOf(rs.getInt("stock")));
                    precio.setText(String.valueOf(rs.getFloat("precio")));}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void agregarProducto(int id, String nombre, String descripcion, int stock, double precio) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String agregar = "Insert into Producto (idProducto, nombreProducto, descripcionProducto, stock, precio) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(agregar);
                stmt.setInt(1, id);
                stmt.setString(2, nombre);
                stmt.setString(3, descripcion);
                stmt.setInt(4, stock);
                stmt.setDouble(5, precio);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void actualizarProducto(int id, String nuevoNombre, String nuevaDescripcion, int nuevoStock, double nuevoPrecio){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String actualizar = "UPDATE Producto SET nombreProducto = ?, descripcionProducto = ?, stock = ?, precio = ? WHERE idProducto = ?";
            PreparedStatement stmt = conn.prepareStatement(actualizar);
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevaDescripcion);
            stmt.setInt(3, nuevoStock);
            stmt.setDouble(4, nuevoPrecio);
            stmt.setInt(5, id);
            stmt.executeUpdate();}
        catch (SQLException e) {
            e.printStackTrace();}}

    public void eliminarProducto(int id){
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String eliminar = "UPDATE Producto SET nombreProducto = NULL, descripcionProducto = NULL, stock = NULL, precio = NULL WHERE idProducto = ?";
            PreparedStatement stmt = conn.prepareStatement(eliminar);
            stmt.setInt(1, id);
            stmt.executeUpdate();}
        catch (SQLException e) {
            e.printStackTrace();}}

    public boolean diferente(int numero) {
        boolean esDiferente = false;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                if (idProducto == numero) {
                    esDiferente = true;
                    break;}}}
        catch (SQLException e) {
            throw new RuntimeException(e);}
        return esDiferente;}


    public static void main(String[] args) {
        JFrame frame = new JFrame("Productos");
        frame.setContentPane(new Productos().Productos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 450);
        frame.setVisible(true);
    }
}
