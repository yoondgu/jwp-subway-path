package subway.domain.entity;

import subway.domain.vo.Age;

public class Passenger {

    private final Age age;

    public Passenger(final Age age) {
        this.age = age;
    }

    public Age getAge() {
        return age;
    }
}
