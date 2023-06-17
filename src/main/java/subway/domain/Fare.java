package subway.domain;

import java.util.Objects;

public class Fare {

    private final long value;

    public Fare(final long value) {
        this.value = value;
    }

    public Fare plus(Fare other) {
        return new Fare(value + other.value);
    }

    public Fare minus(Fare other) {
        return new Fare(value - other.value);
    }

    public Fare multiply(double rate) {
        return new Fare((long) (value * rate));
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Fare fare = (Fare) o;
        return value == fare.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
