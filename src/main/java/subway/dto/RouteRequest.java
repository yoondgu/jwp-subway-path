package subway.dto;

import javax.validation.constraints.NotNull;

public class RouteRequest {

    @NotNull
    private long sourceStationId;
    @NotNull
    private long targetStationId;
    @NotNull
    private int passengerAge;

    public RouteRequest() {
    }

    public RouteRequest(final long sourceStationId, final long targetStationId, final int passengerAge) {
        this.sourceStationId = sourceStationId;
        this.targetStationId = targetStationId;
        this.passengerAge = passengerAge;
    }

    public long getSourceStationId() {
        return sourceStationId;
    }

    public long getTargetStationId() {
        return targetStationId;
    }

    public int getPassengerAge() {
        return passengerAge;
    }
}
