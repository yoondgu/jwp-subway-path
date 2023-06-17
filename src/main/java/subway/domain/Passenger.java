package subway.domain;

public class Passenger {

    private final Age age;

    public Passenger(final Age age) {
        this.age = age;
    }

    public Age getAge() {
        return age;
    }
}
