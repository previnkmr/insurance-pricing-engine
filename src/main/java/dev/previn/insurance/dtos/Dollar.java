package dev.previn.insurance.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * DTO for a dollar insurance estimate
 */
public class Dollar {
    private BigDecimal value;

    public Dollar(final BigDecimal value) {
        requireNonNull(value);
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The dollar estimate in cents cannot be negative");
        }
        // Round up to the nearest cent
        this.value = requireNonNull(value.setScale(2, RoundingMode.CEILING));
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "$" + value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dollar)) return false;
        Dollar dollar = (Dollar) o;
        return getValue().equals(dollar.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
