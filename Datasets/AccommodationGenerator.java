

import backend.accommodations.Accommodation;
import backend.application.DatabaseAPI;
import backend.users.Broker;
import backend.users.Privilege;
import backend.users.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A class used for generating Accommodations by parsing 2 datasets of Roads and Cities
 * It provides a method for creating a desired amount of Accommodations
 *
 * @author Edward Koulakidis
 * @see UserGenerator
 */
public class AccommodationGenerator {
    private ArrayList<String> cities = new ArrayList<>(); //List to store cities
    private ArrayList<String> roads = new ArrayList<>(); // List to store roads


    /**
     * Method for parsing the cities dataset stored in greekcities.csv and storing it in cities field
     */
    private void parseCity() {
        String filename = "Datasets/greekcities.csv";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            cities.add(scanner.next().replaceAll("\"", ""));
            scanner.nextLine();
        }
    }

    /**
     * Method for parsing the roads dataset stored in dathensroads.csv and storing it in roads field
     */
    public void parseRoads() {
        String filename = "Datasets/dathensroads.csv";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        while (scanner.hasNext()) {
            roads.add(scanner.next());
            scanner.nextLine();
        }
    }

    /**
     * Method for generating Accommodations. If cities or road is empty it runs the parse needed. Space and price are
     * generated at Random values and Descriptions are left empty. The broker who owns the accommodation is selected at
     * random as well. This method makes an API call to write the Data to the Database directly
     * @param numberOfAccommodations The number of Accommodations to generate
     */
    public void generateAccommodations(int numberOfAccommodations) {
        DatabaseAPI.loadData();
        Random random = new Random();
        if (cities.size() < 1)
            parseCity();
        if (roads.size() < 1)
            parseRoads();
        for (int i = 0; i < numberOfAccommodations; i++) {
            String city = cities.get(random.nextInt(cities.size()));
            String address = roads.get(random.nextInt(roads.size())).replaceAll(",", "") + " " + random.nextInt(200);
            int space = random.nextInt(500) + 20;
            int price = (int) (space * random.nextFloat() * .5f + 20);
            Accommodation accommodation = new Accommodation(space, city, address, null, "", price);
            List<User> users = DatabaseAPI.userConfirmationsDatabase.selectAllUsers();
            User broker = users.get(random.nextInt(users.size()));
            while (broker.getPrivilege() != Privilege.BROKER)
                broker = users.get(random.nextInt(users.size()));
            DatabaseAPI.brokerAccommodationsDatabase.insertAccommodation((Broker) broker, accommodation);
        }
        DatabaseAPI.writeData();
    }
}
