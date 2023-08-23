import java.sql.*;

public class base_de_datos {
    static final String DB_URL = "jdbc:mysql://localhost/PROYECTO_FINAL_POO";
    static final String USER = "root";
    static final String PASS = "root";
    static final String QUERY = "Select * From productos";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
        ) {while(rs.next()){
            System.out.println("codigo producto: "+rs.getInt("cod_prod"));
            System.out.println("nombre: "+rs.getString("nom_prod"));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
