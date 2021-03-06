package backend.database;

import backend.accommodations.Accommodation;
import backend.application.DatabaseAPI;
import backend.users.Broker;

import javax.swing.*;
import java.io.Serial;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 * The BrokerAccommodations is a class intended to handle information about Accommodations a Broker owns and manages in
 * an application. It is implemented by a
 * Hashtable holding Brokers as keys and a <b>HashSet of Accommodations</b> as values</p>
 * <p>
 * The BrokerAccommodations inherits from Database inheriting read/write methods and implementing Serializable interface. It also
 * provides basic queries like SELECT,INSERT,UPDATE and DROP. It is also important to note that <b>null</b> values are not stored
 * in this class</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see Database
 */

public class BrokerAccommodationsDB extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private final HashMap<Broker, HashSet<Accommodation>> brokerProperties;
    private int totalAccommodations;

    /**
     * An initializer constructor used to initialize the map,set totalAccommodations to 0 and call the Base class with
     * the specified filename to store the map. <b>The Hashset of each Broker key gets initialized after the first entry</b>
     */
    public BrokerAccommodationsDB() {
        super("src/config/brokerAccommodations.ser");
        brokerProperties = new HashMap<Broker, HashSet<Accommodation>>();
        totalAccommodations = 0;
    }


    /**
     * A query for inserting new Accommodations in the Hashset of a Broker. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. If accommodation is the first entry this method also initilizes the Hashset
     * Runs in O(1) time
     *
     * @param broker        The broker who "owns" the accommodation
     * @param accommodation The accommodation to be added in the Hashset
     */
    public void insertAccommodation(Broker broker, Accommodation accommodation) {
        if (broker == null || accommodation == null)
            return;
        HashSet<Accommodation> accommodationList = brokerProperties.get(broker);
        if (accommodationList == null)
            accommodationList = new HashSet<Accommodation>();
        accommodation.setID(getNextID());
        accommodationList.add(accommodation);
        brokerProperties.put(broker, accommodationList);
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Databases");
            }
        }.execute();
    }

    /**
     * A query for selecting Accommodations based on their owner(Broker). If the Broker is found in the map the method
     * returns the Hashset of Accommodations,null otherwise. Runs in O(1) time
     *
     * @param broker The broker key used to query the Hashmap
     * @return The Hashset of Accommodations mapped to the parameter broker,if present in the map, null otherwise
     */
    public HashSet<Accommodation> selectAllAccommodationsFromBroker(Broker broker) {
        return brokerProperties.get(broker) == null ? new HashSet<Accommodation>() : brokerProperties.get(broker);
    }

    /**
     * A drop(delete) query for deleting entries in the map. If the parameters are null the method doesn't change the map
     * If the parameters are not found in the map,the map doesn't change
     *
     * @param broker        The Broker key mapping to the HashSet of Accommodations
     * @param accommodation The accommodation to be deleted
     */
    public void dropAccommodation(Broker broker, Accommodation accommodation) {
        if (broker == null || accommodation == null)
            return;
        brokerProperties.get(broker).remove(accommodation);
        totalAccommodations--;
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Database");
            }
        }.execute();
    }

    /**
     * A query for updating current Accommodations in the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. Runs in O(1) time
     *
     * @param newAccommodation The newAccommodation replacing oldAccommodation
     * @param oldAccommodation The oldAccommodation will be replaced by newAccommodation
     * @param broker           The broker owning the oldAccommodation
     */
    public void updateAccommodation(Accommodation newAccommodation, Accommodation oldAccommodation, Broker broker) {
        if (broker == null || oldAccommodation == null || newAccommodation == null)
            return;
        brokerProperties.get(broker).remove(oldAccommodation);
        brokerProperties.get(broker).add(newAccommodation);
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Database");
            }
        }.execute();
    }


    /**
     * A method useful for selecting all the Accommodations,regardless of the Broker who owns them, in the Database.
     * Runs in O(b*a) time,where b is the number of Brokers in the map and a is the number of accommodations they map to
     *
     * @return Returns a Hashset with all the Accommodations registered in the map
     */
    public HashSet<Accommodation> selectAllAccommodations() {
        HashSet<Accommodation> temp = new HashSet<>();
        for (HashSet<Accommodation> ac : brokerProperties.values())
            temp.addAll(ac);
        return temp;
    }

    private int getNextID() {
        return ++totalAccommodations;
    }

    /**
     * A method useful for selecting Specific Accommodation based on its ID regardless of Broker. The method is useful when
     * finding the Broker who owns the Accommodation is impossible. Runs in O(b*a) time,where b is the number of Brokers
     * in the map and a is the number of accommodations they map to
     *
     * @param id The ID we wish to match
     * @return Returns an Accommodation if the ID matches any ID in the map,null otherwise
     */
    public Accommodation selectAccommodationByID(int id) {
        for (HashSet<Accommodation> accommodations : brokerProperties.values())
            for (Accommodation accommodation : accommodations)
                if (id == accommodation.getID())
                    return accommodation;
        return null;
    }

    /**
     * A method useful for selecting Specific Accommodation based on its ID and its owner. The method is faster than <code>
     * selectAccommodationByID(id)</code> when the Broker instance who owns the accommodation is available.
     * Runs in O(n) time,where b is the number of accommodations the Broke instance owns
     *
     * @param id     The ID we wish to match
     * @param broker The broker instance owning the id
     * @return Returns an Accommodation if the ID matches any ID in the map,null otherwise
     */
    public Accommodation selectAccommodationByID(Broker broker, int id) {
        if (brokerProperties.get(broker) == null)
            return null;
        for (Accommodation accommodation : brokerProperties.get(broker))
            if (id == accommodation.getID())
                return accommodation;
        return null;
    }


    public HashSet<Accommodation> selectAccommodationsBySpaceAndPrice(int lowSpace, int highSpace, int lowPrice, int highPrice) {
        HashSet<Accommodation> values = new HashSet<>();
        if (lowSpace >= highSpace || lowPrice >= highPrice)
            return values;
        for (Accommodation ac : selectAllAccommodations()) {
            if (ac.getSpace() >= lowSpace && ac.getSpace() <= highSpace && ac.getPrice() >= lowPrice && ac.getPrice() <= highPrice)
                values.add(ac);
        }
        return values;
    }


    public HashSet<Accommodation> selectAccommodationsBySpaceAndPriceAndCity(String city, int highSpace, int highPrice) {
        HashSet<Accommodation> values = new HashSet<>();
        for (Accommodation ac : selectAllAccommodations()) {
            if (ac.getSpace() <= highSpace && ac.getPrice() <= highPrice && Objects.equals(city, ac.getCity()))
                values.add(ac);
        }
        return values;
    }

    public HashSet<Accommodation> selectAccommodationsBySpaceAndPriceAndCityAndOwner(String city, int highSpace, int highPrice, Broker broker) {
        HashSet<Accommodation> values = new HashSet<>();
        if (broker == null)
            return selectAccommodationsBySpaceAndPriceAndCity(city, highSpace, highPrice);
        else {
            for (Accommodation ac : selectAllAccommodationsFromBroker(broker)) {
                if (ac.getSpace() <= highSpace && ac.getPrice() <= highPrice) {
                    if (Objects.equals(city, ""))
                        values.add(ac);
                    else if (Objects.equals(ac.getCity(), city))
                        values.add(ac);
                }
            }
            return values;
        }
    }

    public HashSet<Accommodation> selectAllAccommodationsWithReservations(Broker broker) {
        HashSet<Accommodation> all = new HashSet<Accommodation>();
        if (broker == null)
            return all;
        for (Accommodation accommodation : brokerProperties.get(broker)) {
            if (!DatabaseAPI.reservationDatabase.selectReservationsByAccommodation(accommodation).isEmpty())
                all.add(accommodation);
        }
        return all;
    }

    public Broker selectBrokerFromAccommodation(Accommodation accommodation) {
        for (Map.Entry<Broker, HashSet<Accommodation>> entry : brokerProperties.entrySet()) {
            if (entry.getValue().contains(accommodation))
                return entry.getKey();
        }
        return null;
    }
}
