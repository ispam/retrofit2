package destinum.tech.requests;

public class User {

    private Integer id;
    private int age;
    private String name, email;
    private String topics;

    public Integer getId() {
        return id;
    }

    public User(String name, String email, int age,  String topics) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.topics = topics;
    }

}
