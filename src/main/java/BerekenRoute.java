import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BerekenRoute {
    //21740 coordinaten in Nederland
    public static double[][] Cords;


    private static double[] CordsBeginpunt = {52.164150752591766, 5.423199824923482};
    private static final int plekInWagen = 100;
    private static ArrayList<Integer> bezet = new ArrayList<Integer>();
    private static ArrayList<ArrayList<ArrayList<Double>>> Routes = new ArrayList<>();
    private static ArrayList<Route> RouteOverzicht = new ArrayList<>();

    public static void getCords(){
        DatabaseConnectie db = new DatabaseConnectie();
        double[][] outputGetCords = db.getCords();
        Cords = new double [outputGetCords.length][3];
        Cords = outputGetCords.clone();
    }
    public static void insertRouteInDB(int routeID, double berekentijd, double afstand, int isKlaar){
        DatabaseConnectie db = new DatabaseConnectie();
        db.insertRoute(routeID,berekentijd,afstand,isKlaar);
    }

    public static void insertTussenStopInDB(int routeID, int tussenstopID, double lat, double longt){
        DatabaseConnectie db = new DatabaseConnectie();
        db.insertTussenstop(routeID,tussenstopID,lat,longt);
    }
    public static double CalculateDistance(double[] PointA, double[] PointB){
        //Wortel van ( PointA[0] - PointB[0] )^2 + ( PointA[1] - PointB[1] )^2
        double distance;
        double a = PointA[0] - PointB[0];
        double b = PointA[1] - PointB[1];

        distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return distance;

    }

    //Vind dichstbijzijnde punt van mee gegeven punt
    public static double[] NearestNeighbour(double[] beginpunt, double[][] Cords){
        int indexNearest = -1;
        double distanceNearest = 0;
        for (int i = 0; i < Cords.length; i++) {
            double distance = CalculateDistance(beginpunt, Cords[i]);
            if (indexNearest == -1 && !bezet.contains(i)){
                indexNearest = i;
                distanceNearest = distance;
            }else if (distanceNearest > distance && !bezet.contains(i)){
                indexNearest = i;
                distanceNearest = distance;
            }
        }
        //Check of er nog cords over zijn waardoor je geen foute data returned
        if(indexNearest == -1) {
            return null;
        }
        double[] output = {indexNearest, distanceNearest};
        return output;
    }


    public static void CreateRoute(){
        double aantalRoutes = Cords.length / (double) plekInWagen;
        for (int indexRoutes = 0; indexRoutes <= aantalRoutes; indexRoutes++) {
            // Vind eerste stop
            double[] eerstePlek = NearestNeighbour(CordsBeginpunt, Cords);
            if (eerstePlek != null) {
                int eerstePlekIndex = (int) eerstePlek[0];
                int plekIndexBegin = eerstePlekIndex;
                bezet.add(eerstePlekIndex);

                //Maak route aan
                Routes.add(new ArrayList<>());
                long startFirst = System.nanoTime() / 1000;
                //Maak eerste tussenstop aan
                Routes.get(indexRoutes).add(new ArrayList<Double>());
                Routes.get(indexRoutes).get(0).add(Cords[plekIndexBegin][0]);
                Routes.get(indexRoutes).get(0).add(Cords[plekIndexBegin][1]);
                Routes.get(indexRoutes).get(0).add(0.0);
                long endFirst = System.nanoTime()  / 1000;
                double timeFirst = (double) endFirst - startFirst;
                Routes.get(indexRoutes).get(0).add(timeFirst);


                for (int i = 1; i <= plekInWagen; i++) {
                    if (bezet.size() < Cords.length) {
                        long start = System.nanoTime()  / 1000;
                        //Maak tussenstop aan
                        Routes.get(indexRoutes).add(new ArrayList<Double>());
                        if (i == 1) {
                            double[] plek = NearestNeighbour(Cords[eerstePlekIndex], Cords);
                            plekIndexBegin = (int) plek[0];

                            bezet.add(plekIndexBegin);
                            Routes.get(indexRoutes).get(i).add(Cords[plekIndexBegin][0]);
                            Routes.get(indexRoutes).get(i).add(Cords[plekIndexBegin][1]);
                            Routes.get(indexRoutes).get(i).add(plek[1]);
                            long end = System.nanoTime()  / 1000;
                            double time = (double) end - start;
                            Routes.get(indexRoutes).get(i).add(time);
                        } else {
                            double[] plek = NearestNeighbour(Cords[plekIndexBegin], Cords);
                            int plekIndex = (int) plek[0];
                            plekIndexBegin = plekIndex;
                            bezet.add(plekIndex);
                            Routes.get(indexRoutes).get(i).add(Cords[plekIndexBegin][0]);
                            Routes.get(indexRoutes).get(i).add(Cords[plekIndexBegin][1]);
                            Routes.get(indexRoutes).get(i).add(plek[1]);
                            long end = System.nanoTime()  / 1000;
                            double time = (double) end - start;
                            Routes.get(indexRoutes).get(i).add(time);
                        }
                    }
                }

            }
        }

                // Zet om naar Route en Tussenstop objecten
                int RouteID = 1;

                for (ArrayList<ArrayList<Double>> Route : Routes) {
                    double afstand = 0.0;
                    double tijd = 0.0;
                    ArrayList<Tussenstop> lijstTussenstops = new ArrayList<>();
                    int TussenstopID = 1;
                    for (ArrayList<Double> Tussenstops : Route) {
                    insertTussenStopInDB(RouteID, TussenstopID, Tussenstops.get(0), Tussenstops.get(1));
                        Tussenstop tussenstop = new Tussenstop(Tussenstops.get(0), Tussenstops.get(1), RouteID, TussenstopID);
                        TussenstopID++;
                        lijstTussenstops.add(tussenstop);
                        afstand += Tussenstops.get(2);
                        tijd += Tussenstops.get(3);
                    }
                    insertRouteInDB(RouteID, tijd, afstand, 0);
                    Route RouteObject = new Route(lijstTussenstops, RouteID, afstand, tijd);
                    RouteOverzicht.add(RouteObject);
                    RouteID++;
                }
            }



    public static void main(String[] args) {
        getCords();
        CreateRoute();
        System.out.println(RouteOverzicht.toString());
    }
}



