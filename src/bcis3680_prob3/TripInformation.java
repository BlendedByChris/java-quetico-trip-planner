package bcis3680_prob3;

/**
 *
 * @author cleblanc
 */
public final class TripInformation {
    private static TripInformation instance = null;

    public String groupLeader = "";
    public int totalGuests;
    public int Adults;
    public String startDate = "";
    public String endDate = "";
    public boolean tow;
    public boolean canoeRental;
    public String paymentType = "";

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
