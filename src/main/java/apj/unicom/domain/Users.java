package apj.unicom.domain;

public class Users {
    int userId;
    String id;
    String name;
    String email;
    String phoneNumber;
    double cgpa;
    int creditsCompleted;
    int password;

    public Users(String id, String name, String email, String phoneNumber, double cgpa, int creditsCompleted, int password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cgpa = cgpa;
        this.creditsCompleted = creditsCompleted;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getCreditsCompleted() {
        return creditsCompleted;
    }

    public int getPassword() {
        return password;
    }
}
