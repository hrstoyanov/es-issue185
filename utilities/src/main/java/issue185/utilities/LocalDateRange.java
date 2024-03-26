package issue185.utilities;

import java.time.LocalDate;


public  record LocalDateRange(LocalDate start, LocalDate end)
        implements TemporalRange<LocalDate> {

    public LocalDateRange {
        Range.validate(start,end);
    }

    @Override
    public int compare(LocalDate o1, LocalDate o2) {
        return o1.compareTo(o2);
    }

    @Override
    public LocalDateRange of(LocalDate s, LocalDate e) {
        return new LocalDateRange(s,e);
    }
}
