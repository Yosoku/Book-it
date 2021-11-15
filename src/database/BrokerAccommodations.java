package database;

import accommodations.Accommodation;
import users.Broker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrokerAccommodations extends Database {
    private final HashMap<Broker, ArrayList<Accommodation>> brokerProperties;

    public BrokerAccommodations() {
        super(DatabaseType.BROKER_ACCOMMODATIONS, "");
        brokerProperties = new HashMap<Broker, ArrayList<Accommodation>>();
    }


    public void insertAccommodation(Accommodation accommodation, Broker broker) {
        if (broker == null || accommodation == null)
            return;
        ArrayList<Accommodation> accommodationList = brokerProperties.get(broker);
        if (accommodationList == null)
            accommodationList = new ArrayList<>();
        accommodationList.add(accommodation);
        brokerProperties.put(broker, accommodationList);
    }

    public List<Accommodation> selectAccommodation(Broker broker) {
        if (brokerProperties.containsKey(broker))
            return brokerProperties.get(broker);
        return null;
    }


    public void dropAccommodation(Accommodation accommodation, Broker broker) {
        if (broker == null || accommodation == null)
            return;
        brokerProperties.get(broker).remove(accommodation);
    }

    @Override
    public void write() {

    }

    @Override
    public void read() {

    }
}
