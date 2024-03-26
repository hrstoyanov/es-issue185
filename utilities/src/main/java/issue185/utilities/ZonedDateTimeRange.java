package issue185.utilities;

import java.time.ZonedDateTime;


public record ZonedDateTimeRange(ZonedDateTime start, ZonedDateTime end) implements TemporalRange<ZonedDateTime> {

    public ZonedDateTimeRange {
        validate();
    }

    @Override
    public Range<ZonedDateTime> of(ZonedDateTime s, ZonedDateTime e) {
        return new ZonedDateTimeRange(s,e);
    }

    @Override
    public int compare(ZonedDateTime o1, ZonedDateTime o2) {
        return o1.compareTo(o2);
    }
}
