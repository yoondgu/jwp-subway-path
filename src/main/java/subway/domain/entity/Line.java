package subway.domain.entity;

import java.util.Objects;

public class Line {
    private Long id;
    private String name;
    private String color;
    private long additionalCharge;

    public Line() {
    }

    public Line(final Long id, final String name, final String color, final long additionalCharge) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.additionalCharge = additionalCharge;
    }

    public Line(final String name, final String color, final long additionalCharge) {
        this(null, name, color, additionalCharge);
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(id, line.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
