package subway.domain;

public interface FarePolicy {

    Fare calculate(final Route route, final Passenger passenger);
}
