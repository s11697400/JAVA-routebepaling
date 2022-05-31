import java.util.ArrayList;

public class Route {
    public ArrayList<Tussenstop> Tussenstops = new ArrayList<Tussenstop>();
    private int RouteID;
    private double afstand;
    private double berekentijd;

    public Route(ArrayList<Tussenstop> Tussenstops, int RouteID, double afstand, double berekentijd){
        this.Tussenstops = Tussenstops;
        this.RouteID = RouteID;
        this.afstand = afstand;
        this.berekentijd = berekentijd;
    }

    @Override
    public String toString() {
        return "Route{" +
                "RouteID=" + RouteID +
                ", Tussenstops=" + Tussenstops +
                ", Afstand = " + afstand +
                ", Tijd = " + berekentijd +
                " }";
    }

    public int getRouteID() {
        return RouteID;
    }

    public double getAfstand() {
        return afstand;
    }

    public double getBerekentijd() {
        return berekentijd;
    }
}