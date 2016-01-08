package com.example.payroll.model;

import java.time.LocalDateTime;

public interface Affiliation {
    double getFees(LocalDateTime feeDate);

    Affiliation EMPTY = new NoAffiliation();
    class NoAffiliation implements Affiliation {
        public double getFees(final LocalDateTime feeDate) {
            return 0;
        }
    }
}
