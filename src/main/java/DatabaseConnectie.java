import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class DatabaseConnectie {

    public static Connection dbConnect () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/nerdygadgets", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void insertRoute(int routeID, double berekentijd, double totaalKM, int isKlaar) {
        Connection con = dbConnect();
        PreparedStatement stmt = null;
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTime = calendar.getTime();
        long time = currentTime.getTime();

        try {
            stmt = con.prepareStatement("insert into routes (RouteID, Datum, Berekentijd, TotaalKilometers, IsKlaar)" +
                    "Values ("+routeID+", ?, "+berekentijd+", "+totaalKM+", "+isKlaar+") ");

            stmt.setTimestamp(1, new Timestamp(time));
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void insertTussenstop(int routeID, int tussenstopID, double lat, double longt) {
        Connection con = dbConnect();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("insert into tussenstop (TussenstopID, RouteID, lat, longt) " +
                    "Values("+tussenstopID+", "+routeID+", "+lat+", "+longt+") ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt = con.createStatement();
            stmt.executeUpdate("insert into tussenstopOrders (Tussenstop_TussenstopID, RouteID, orders_OrderID) " +
                    "Values("+tussenstopID+", "+routeID+", "+tussenstopID+") ");
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public static double[][] getCords(){
            Connection con = dbConnect();
            Statement stmt = null;
            ArrayList<ArrayList<Double>> cords = new ArrayList<>();
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT `customers`.xCord, `customers`.yCord, `orders`.OrderID FROM orders " +
                        "INNER JOIN customers on `customers`.`CustomerID` = `orders`.`CustomerID` " +
                        "where `orders`.OrderDate = DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY) ");
                int i = 0;
                while (rs.next()){
                    cords.add(new ArrayList<>());
                    cords.get(i).add(rs.getDouble(1));
                    cords.get(i).add(rs.getDouble(2));
                    cords.get(i).add(rs.getDouble(3));

                    i++;
                }
                double[][] output = new double[cords.size()][3];
                int j=0;
                for (ArrayList<Double> stop:
                     cords) {
                    output[j][0] = stop.get(0);
                    output[j][1] = stop.get(1);
                    output[j][2] = stop.get(2);
                    j++;
                }
                con.close();

                return output;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return new double[1][0];
    }
    public static int getRetoursAmount(){
        Connection con = dbConnect();
        Statement stmt = null;
        int aantal = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM routes");

            while (rs.next()){
             aantal =  rs.getInt(1);
            }

            con.close();

            return aantal;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return aantal;
    }
    public static int getRouteID(){
        Connection con = dbConnect();
        Statement stmt = null;
        int id = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM routes WHERE RouteID = 1");

            while (rs.next()){
                id =  rs.getInt(1);
            }

            con.close();

            return id;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public static double getAantalKm(){
        Connection con = dbConnect();
        Statement stmt = null;
        double km = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TotaalKilometers FROM routes WHERE RouteID = 1");
            while (rs.next()){
                km =  rs.getDouble(1);
            }

            con.close();

            return km;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return km;
    }
    public static ArrayList<Integer> getRetourID() {
        Connection con = dbConnect();

        ArrayList<Integer> ids = new ArrayList<>();

        try {
            String query = "SELECT idRetour FROM retour";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ids.add(rs.getInt("idRetour"));
            }

            con.close();
            return ids;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return ids;
    }

    public static ArrayList<Integer> getOrderID() {
        Connection con = dbConnect();

        ArrayList<Integer> order = new ArrayList<>();

        try {
            String query = "SELECT OrderID FROM orders";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                order.add(rs.getInt("OrderID"));
            }

            con.close();
            return order;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return order;
    }

    public static ArrayList<Integer> getProductnummer() {
        Connection con = dbConnect();

        ArrayList<Integer> productnummer = new ArrayList<>();

        try {
            String query = "SELECT StockItemID FROM `orderlines` "
                    + "INNER JOIN retour on `OrderlineID` = `orderlines_OrderLineID` ";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                productnummer.add(rs.getInt("StockItemID"));
            }

            con.close();
            return productnummer;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return productnummer;
    }

    public static ArrayList<String> getStatus() {
        Connection con = dbConnect();

        ArrayList<String> status = new ArrayList<>();

        try {
            String query = "SELECT status FROM retour";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                status.add(rs.getString("Status"));
            }

            con.close();
            return status;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return status;
    }

    public static ArrayList<String> getReden() {
        Connection con = dbConnect();

        ArrayList<String> reden = new ArrayList<>();

        try {
            String query = "SELECT `Reden retour` FROM retour";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                reden.add(rs.getString("Reden retour"));
            }

            con.close();
            return reden;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return reden;
    }

    public static ArrayList<String> getConditie() {
        Connection con = dbConnect();

        ArrayList<String> conditie = new ArrayList<>();

        try {
            String query = "SELECT `Conditie` FROM retour";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                conditie.add(rs.getString("Conditie"));
            }

            con.close();
            return conditie;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return conditie;
    }


    public static int getAantalRetours() {
        Connection con = dbConnect();

        int aantal = 0;

        try {
            String query = "SELECT COUNT(*) AS aantal FROM retour";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                aantal = rs.getInt("aantal");
            }

            con.close();
            return aantal;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return aantal;
    }

    public static ArrayList<String> getCustomerName(int RetourID) {
        Connection con = dbConnect();
        PreparedStatement stmt = null;

        ArrayList<String> customerNaam = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CustomerName FROM customers " +
                    "JOIN orders ON customers.CustomerID = orders.CustomerID " +
                    "JOIN orderlines ON orders.OrderID = Orderlines.OrderID " +
                    "JOIN retour ON orderlines.OrderlineID = retour.orderlines_OrderlineID " +
                    "WHERE retour.idRetour = ?");

            stmt.setInt(1, RetourID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customerNaam.add(rs.getString("CustomerName"));
            }

            con.close();
            return customerNaam;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return customerNaam;
    }

    public static ArrayList<String> getCustomerID(int RetourID) {
        Connection con = dbConnect();
        PreparedStatement stmt = null;

        ArrayList<String> customerID = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT customers.CustomerID FROM customers " +
                    "JOIN orders ON customers.CustomerID = orders.CustomerID " +
                    "JOIN orderlines ON orders.OrderID = Orderlines.OrderID " +
                    "JOIN retour ON orderlines.OrderlineID = retour.orderlines_OrderlineID " +
                    "WHERE retour.idRetour = ?");

            stmt.setInt(1, RetourID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customerID.add(rs.getString("CustomerID"));
            }

            con.close();
            return customerID;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return customerID;
    }

    public static ArrayList<String> getCustomerAdres(int RetourID) {
        Connection con = dbConnect();
        PreparedStatement stmt = null;

        ArrayList<String> customerAdres = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT customers.DeliveryAddressLine2 FROM customers " +
                    "JOIN orders ON customers.CustomerID = orders.CustomerID " +
                    "JOIN orderlines ON orders.OrderID = Orderlines.OrderID " +
                    "JOIN retour ON orderlines.OrderlineID = retour.orderlines_OrderlineID " +
                    "WHERE retour.idRetour = ?");

            stmt.setInt(1, RetourID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customerAdres.add(rs.getString("DeliveryAddressLine2"));
            }

            con.close();
            return customerAdres;
        } catch (Exception e) {
            System.err.println("exception");
            System.err.println(e.getMessage());
        }
        return customerAdres;
    }
}