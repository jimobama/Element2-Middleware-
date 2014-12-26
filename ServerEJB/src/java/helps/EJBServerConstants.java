/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helps;

/**
 *
 * @author 21187498
 */
public interface EJBServerConstants {

    public static interface Remote {

        public static int INTERNET_PORT = 8080;
        public static String INTERNET_HOST = "localhost";
    }
    //RMI IDS

    public static interface Beans {

        public final static String IEntrySite = "IEntrySite/remote";
        public final static String ICONNECTION = "IConnection/remote";
        public final static String IEntryStructure = "IEntryStructure/remote";
        public final static String PERSISTENCE_UNIT = "ServerEJBPU";
    }

    //RANKS
    public static interface SiteFlags {

        public static final String GOLD = "gold";
        public static final String SILVER = "silver";
        public static final String BRONZE = "bronze";
    }

    public static interface SiteRegion {

        //REGIONS
        public static final String SOUTH_EAST = "South East";
        public static final String LONDON = "London";
        public static final String SOUTH_WEST = "South West";
        public static final String MIDLANDS = "Midlands";
        public static final String NORTH_EAST = "North East";
        public static final String NORTH_WEST = "North West";

    }
}
