public class Tussenstop {
    private double xCord;
    private double yCord;
    private int TussenStopID;
    private int RouteID;

    public Tussenstop(double xCord, double yCord, int RouteID, int TussenStopID){
        this.RouteID = RouteID;
        this.xCord = xCord;
        this.yCord = yCord;
        this.TussenStopID = TussenStopID;
    }

    @Override
    public String toString() {
        return "Tussenstop{" +
                "xCord=" + xCord +
                ", yCord=" + yCord +
                ", TussenStopID=" + TussenStopID +
                '}';
    }
}
