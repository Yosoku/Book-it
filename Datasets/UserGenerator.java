import application.DatabaseAPI;
import auth.Credentials;
import users.Broker;
import users.Customer;
import users.Gender;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;


public class UserGenerator {
    Random rand;
    private ArrayList<String> usernames;
    private ArrayList<String> fullnames;
    private ArrayList<Gender> genders;
    private ArrayList<String> emails;

    public UserGenerator() {
        emails = new ArrayList<>();
        rand = new Random();
        usernames = new ArrayList<>();
        fullnames = new ArrayList<>();
        genders = new ArrayList<>();
        parseReddit(); //usernames
        parseFacebook(); // fullnames& genders
        parseEmails();
        DatabaseAPI.loadData();

    }

    private void parseEmails() {
        String filename = "Datasets/enron-email-dataset-QueryResult.csv";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            try {
                emails.add(scanner.next());
                scanner.nextLine();
            } catch (Exception e) {
                break;
            }
        }
    }


    public void parseReddit() {
        String filename = "Datasets/redditUsernames.csv";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            usernames.add(scanner.next());
            scanner.nextLine();
        }
    }

    public void createUsers(int number) throws NoSuchAlgorithmException {
        HashSet<String> usernameTaken = new HashSet<>();
        HashSet<String> emailTaken = new HashSet<>();
        for (int i = 0; i < number; i++) {
            String email = emails.get(rand.nextInt(emails.size()));
            if (emailTaken.contains(email)) {
                email = getDomain(email);
                emailTaken.add(email);
            }
            String username = usernames.get(rand.nextInt(usernames.size()));
            while (usernameTaken.contains(username)) {
                username = usernames.get(rand.nextInt(usernames.size()));
                usernameTaken.add(username);
            }
            int fbIndex = rand.nextInt(fullnames.size());
            String fullname = fullnames.get(fbIndex);
            Gender gender = genders.get(fbIndex);
            Credentials credentials = new Credentials(username, getRandomPassword());
            if (rand.nextInt(100) > 50) {
                Broker broker = new Broker(credentials, fullname, rand.nextInt(60) + 18, email, gender, getPhone(), "");
                DatabaseAPI.getUserConfirmationsDatabase().insertUserConfirmation(broker);
                DatabaseAPI.getCredentialsUserDatabase().insertUser(broker.getCredentials(), broker);
            } else {
                Customer customer = new Customer(credentials, fullname, rand.nextInt(60) + 18, email, gender, getPhone());
                DatabaseAPI.getUserConfirmationsDatabase().insertUserConfirmation(customer);
                DatabaseAPI.getCredentialsUserDatabase().insertUser(customer.getCredentials(), customer);
            }

        }
        DatabaseAPI.writeData();
    }

    private String getPhone() {
        int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
        int num2 = rand.nextInt(743);
        int num3 = rand.nextInt(10000);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

        return df3.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);
    }


    private String getRandomPassword() {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
        for (int i = 0; i < 13; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }


    public void parseFacebook() {
        String filename = "Datasets/USA_01.csv";
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int i = 0;
        scanner.useDelimiter(":");
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            scanner.next();
            scanner.next();
            String name = scanner.next() + scanner.next();
            Gender gender;
            try {

                gender = Gender.getGender(scanner.next());
                if (gender == null) {
                    gender = rand.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
                }
            } catch (Exception e) {
                break;
            }
            fullnames.add(name);
            genders.add(gender);
        }
    }

    public String getDomain(String email) {
        String[] domains = {"hotmail", "yahoo", "outlook", "gmail", "Mail", "Zoho"};
        String[] countries = {"gr", "uk", "com", "as", "ger", "swe", "bg"};
        String randomDomain = domains[rand.nextInt(domains.length)];
        String randomCountry = countries[rand.nextInt(countries.length)];
        return email.split("@")[0] + randomDomain + "." + randomCountry;
    }
}
