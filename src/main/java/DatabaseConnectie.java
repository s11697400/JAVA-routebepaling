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


}