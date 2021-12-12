

import accommodations.Accommodation;
import application.DatabaseAPI;
import users.Broker;
import users.Privilege;
import users.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AccommodationGenerator {
    ArrayList<String> cities = new ArrayList<>();
    private ArrayList<String> roads = new ArrayList<>();


    public void parseCity() {
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
