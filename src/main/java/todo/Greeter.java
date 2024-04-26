package todo;

public class Greeter {

    public String sayHello() {
        return "Hello";
    }

    public String greetPerson(String name) {
        return String.format("Hello %s", name);
    }
}
