import java.util.ArrayList;

public class Route {
    public ArrayList<Tussenstop> Tussenstops = new ArrayList<Tussenstop>();
    private int RouteID;
    private double afstand;
    private double berekentijd;

    public Route(ArrayList<Tussenstop> Tussenstops, int RouteID){
        this.Tussenstops = Tussenstops;
        this.RouteID = RouteID;
    }

    @Override
    public String toString() {
        return "Route{" +
                "RouteID=" + RouteID +
                ", Tussenstops=" + Tussenstops +
                " }";
    }
}