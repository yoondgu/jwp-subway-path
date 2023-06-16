package subway.dto;

import javax.validation.constraints.NotNull;

public class LineRequest {

    @NotNull
    private String name;
    @NotNull
    private String color;
    @NotNull
    private long additionalCharge;

    public LineRequest() {
    }

    public LineRequest(final String name, final String color) {
        this(name, color, 0);
    }

    public LineRequest(final String name, final String color, final long additionalCharge) {
        this.name = name;
        this.color = color;
        this.additionalCharge = additionalCharge;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public long getAdditionalCharge() {
        return additionalCharge;
    }
}
