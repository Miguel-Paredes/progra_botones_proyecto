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
    static String DB_URL = "jdbc:mysql://localhost/medical";
    static String USER = "root";
    static String PASS = "root";
    static String SELECT_QUERY = "SELECT * FROM producto";
    static String INSERT_QUERY = "INSERT INTO producto (idProducto, nombreProducto, descripcionProducto, stock, precio) VALUES (?, ?, ?, ?, ?)";
    static String UPDATE_QUERY = "UPDATE producto SET nombreProducto = ?, stock = ?, precio = ? WHERE idProducto = ?";
    static String DELETE_QUERY = "DELETE FROM producto WHERE idProducto = ?";

    public Productos() {
        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerProductos();
            }
        });

        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }
        });

        actualizarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
    }

    public void obtenerProductos() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_QUERY)) {
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("idProducto") + ", nombre: " + rs.getString("nombreProducto"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void crearProducto() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY)) {
            pstmt.setInt(1, 1); // Cambiar 1 por el ID deseado
            pstmt.setString(2, "Nuevo producto");
            pstmt.setString(3, "Descripción del nuevo producto");
            pstmt.setInt(4, 10);
            pstmt.setDouble(5, 99.99);
            pstmt.executeUpdate();
            System.out.println("Producto creado correctamente.");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void actualizarProducto() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_QUERY)) {
            pstmt.setString(1, "Producto actualizado");
            pstmt.setInt(2, 20);
            pstmt.setDouble(3, 109.99);
            pstmt.setInt(4, 1); // Cambiar 1 por el ID del producto a actualizar
            pstmt.executeUpdate();
            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void eliminarProducto() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, 1); // Cambiar 1 por el ID del producto a eliminar
            pstmt.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Productos");
        frame.setContentPane(new Productos().Productos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
