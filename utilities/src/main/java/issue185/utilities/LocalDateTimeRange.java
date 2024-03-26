package issue185.utilities;

import java.time.LocalDateTime;

public record LocalDateTimeRange(LocalDateTime start, LocalDateTime end) implements TemporalRange<LocalDateTime> {

    public LocalDateTimeRange {
        validate();
    }
    @Override
    public LocalDateTimeRange of(LocalDateTime s, LocalDateTime e) {
        return new LocalDateTimeRange(s,e);
    }

    @Override
    public int compare(LocalDateTime o1, LocalDateTime o2) {
        return o1.compareTo(o2);
    }
}
