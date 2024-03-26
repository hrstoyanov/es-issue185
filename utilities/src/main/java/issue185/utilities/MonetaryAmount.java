package issue185.utilities;

import java.util.Currency;

import static java.lang.Long.compare;

public record MonetaryAmount(Currency currency, long monetaryAmount) implements Comparable<MonetaryAmount> {

    @Override
    public int compareTo(MonetaryAmount m) {
        return compare(monetaryAmount,m.monetaryAmount);
    }
}
