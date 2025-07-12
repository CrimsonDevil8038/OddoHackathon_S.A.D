

import java.util.*;

public class SkillSwapApp {
    private static final List<User> users = new ArrayList<>();
    private static final List<SwapRequest> swaps = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        createAdmin();

        while (true) {
            if (loggedInUser == null) {
                System.out.println("\n--- Skill Swap CLI ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> register(scanner);
                    case 2 -> login(scanner);
                    case 0 -> System.exit(0);
                    default -> System.out.println("Invalid input.");
                }
            } else {
                showMenu(scanner);
            }
        }
    }

    private static void createAdmin() {
        users.add(new User("admin", "HQ", "Always", true, "admin", "admin123", true));
    }

    private static void register(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Location: ");
        String loc = sc.nextLine();
        System.out.print("Availability: ");
        String avail = sc.nextLine();
        System.out.print("Public profile? (true/false): ");
        boolean pub = sc.nextBoolean();
        sc.nextLine();
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        users.add(new User(name, loc, avail, pub, user, pass, false));
        System.out.println("Registered.");
    }

    private static void login(Scanner sc) {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        for (User usr : users) {
            if (usr.getUsername().equals(u) && usr.getPassword().equals(p)) {
                loggedInUser = usr;
                System.out.println("Welcome, " + usr.getName());
                return;
            }
        }
        System.out.println("Login failed.");
    }

    private static void showMenu(Scanner sc) {
        System.out.println("\n1. Add Skill\n2. View Users\n3. Search by Skill\n4. Send Swap\n5. View Swaps\n6. Logout");
        int ch = sc.nextInt();
        sc.nextLine();
        switch (ch) {
            case 1 -> addSkill(sc);
            case 2 -> viewUsers();
            case 3 -> searchBySkill(sc);
            case 4 -> sendSwap(sc);
            case 5 -> viewSwaps();
            case 6 -> logout();
            default -> System.out.println("Invalid.");
        }
    }

    private static void addSkill(Scanner sc) {
        System.out.print("Skill type (offered/wanted): ");
        String type = sc.nextLine();
        System.out.print("Skill name: ");
        String skill = sc.nextLine();
        if (type.equalsIgnoreCase("offered")) loggedInUser.addOfferedSkill(skill);
        else loggedInUser.addWantedSkill(skill);
        System.out.println("Skill added.");
    }

    private static void viewUsers() {
        for (User u : users) {
            if (u.isPublic() || u == loggedInUser) {
                System.out.println(u);
                System.out.println("Offers: " + u.getSkillsOffered());
                System.out.println("Wants: " + u.getSkillsWanted());
            }
        }
    }

    private static void searchBySkill(Scanner sc) {
        System.out.print("Search offered skill: ");
        String s = sc.nextLine();
        for (User u : users) {
            if (u.isPublic() && u.getSkillsOffered().contains(s)) {
                System.out.println(u);
            }
        }
    }

    private static void sendSwap(Scanner sc) {
        viewUsers();
        System.out.print("Receiver username: ");
        String ru = sc.nextLine();
        User receiver = users.stream().filter(u -> u.getUsername().equals(ru)).findFirst().orElse(null);
        if (receiver == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Your offered skill: ");
        String offered = sc.nextLine();
        System.out.print("Skill you want from them: ");
        String wanted = sc.nextLine();
        swaps.add(new SwapRequest(loggedInUser, receiver, offered, wanted));
        System.out.println("Swap request sent.");
    }

    private static void viewSwaps() {
        for (SwapRequest sr : swaps) {
            if (sr.getSender() == loggedInUser || sr.getReceiver() == loggedInUser) {
                System.out.println(sr);
            }
        }
    }

    private static void logout() {
        loggedInUser = null;
        System.out.println("Logged out.");
    }
}
