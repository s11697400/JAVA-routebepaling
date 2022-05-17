import java.sql.*;

public class DatabaseConnectie {
    public static void dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from cities");

            while (rs.next()) {
                String stad = rs.getString("CityName");
                System.out.println(stad);
            }


            con.close();
        } catch (Exception ex) {
            System.out.println("error");
        }
    }
}