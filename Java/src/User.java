
import java.util.*;

public class User {
    private final String name;
    private final String location;
    private final String availability;
    private final boolean isPublic;
    private final String username;
    private final String password;
    private final boolean isAdmin;
    private final List<String> skillsOffered = new ArrayList<>();
    private final List<String> skillsWanted = new ArrayList<>();

    public User(String name, String location, String availability, boolean isPublic,
                String username, String password, boolean isAdmin) {
        this.name = name;
        this.location = location;
        this.availability = availability;
        this.isPublic = isPublic;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getAvailability() { return availability; }
    public boolean isPublic() { return isPublic; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isAdmin() { return isAdmin; }

    public void addOfferedSkill(String skill) {
        skillsOffered.add(skill);
    }

    public void addWantedSkill(String skill) {
        skillsWanted.add(skill);
    }

    public List<String> getSkillsOffered() {
        return skillsOffered;
    }

    public List<String> getSkillsWanted() {
        return skillsWanted;
    }

    @Override
    public String toString() {
        return "User: " + name + " (" + username + ") - Location: " + location + ", Available: " + availability +
                ", Public: " + isPublic;
    }
}