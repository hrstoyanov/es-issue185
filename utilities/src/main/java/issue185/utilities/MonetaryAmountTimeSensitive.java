package issue185.utilities;


import java.util.Currency;

import static java.lang.Long.compare;

public record MonetaryAmountTimeSensitive< R extends TemporalRange<?> >(Currency currency, R validForRange, long monetaryAmount)
implements Comparable<MonetaryAmountTimeSensitive<R>>{

    @Override
    public int compareTo(MonetaryAmountTimeSensitive<R> m) {
        return compare(monetaryAmount,m.monetaryAmount);
    }


}
