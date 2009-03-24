package java_quetico_trip_planner;

/**
 *
 * @author cleblanc
 */
public final class TripInformation {
    private static TripInformation instance = null;

    public String groupLeader = "";
    public int totalGuests;
    public int adults;
    public int children;
    public String startDate = "";
    public String endDate = "";
    public boolean tow;
    public boolean canoeRental;
    public String paymentType = "";

    public String tripDuration = "";
    public String totalCampingFees = "";


    public String [] canoeHeader = {"Canoe",
                                    "Manufacturer",
                                    "Layup",
                                    "Weight",
                                    "Capacity"}; 
    public String canoes[][] = new String[10][5];

    public boolean updated;

    private TripInformation(){ }

    public static TripInformation getInstance()
    {
        if (instance == null)
            instance = new TripInformation();
        return instance;
    }
}
