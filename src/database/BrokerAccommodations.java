package database;

import accommodations.Accommodation;
import users.Broker;

import java.io.Serial;
import java.util.HashMap;
import java.util.HashSet;

public class BrokerAccommodations extends Database {
    private int totalAccommodations;
    @Serial
    private static final long serialVersionUID = 0;
    private final HashMap<Broker, HashSet<Accommodation>> brokerProperties;

    public BrokerAccommodations() {
        super("src/config/brokerAccommodations.ser");
        brokerProperties = new HashMap<Broker, HashSet<Accommodation>>();
        totalAccommodations = 0;
    }


    public void insertAccommodation(Broker broker, Accommodation accommodation) {
        if (broker == null || accommodation == null)
            return;
        HashSet<Accommodation> accommodationList = brokerProperties.get(broker);
        if (accommodationList == null)
            accommodationList = new HashSet<Accommodation>();
        accommodation.setID(getNextID());
        accommodationList.add(accommodation);
        brokerProperties.put(broker, accommodationList);
    }

    public HashSet<Accommodation> selectAllAccommodationsFromBroker(Broker broker) {
        if (brokerProperties.containsKey(broker))
            return brokerProperties.get(broker);
        return null;
    }


    public void dropAccommodation(Broker broker, Accommodation accommodation) {
        if (broker == null || accommodation == null)
            return;
        brokerProperties.get(broker).remove(accommodation);
    }

    public void updateAccommodation(Accommodation newAccommodation, Accommodation oldAccommodation, Broker broker) {
        if (broker == null || oldAccommodation == null || newAccommodation == null)
            return;
        brokerProperties.get(broker).remove(oldAccommodation);
        brokerProperties.get(broker).add(newAccommodation);
    }


    public HashSet<Accommodation> selectAllAccommodations() {
        HashSet<Accommodation> temp = new HashSet<Accommodation>();
        for (HashSet<Accommodation> list : brokerProperties.values()) {
            temp.addAll(list);
        }
        return temp;
    }

    private int getNextID() {
        return ++totalAccommodations;
    }

    public Accommodation selectAccommodationByID(int id) {
        for (HashSet<Accommodation> accommodations : brokerProperties.values())
            for (Accommodation accommodation : accommodations)
                if (id == accommodation.getID())
                    return accommodation;
        return null;
    }

    public Accommodation selectAccommodationByID(Broker broker, int id) {
        for (Accommodation accommodation : brokerProperties.get(broker))
            if (id == accommodation.getID())
                return accommodation;
        return null;
    }
}
