package com.example.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation {
    private final List<ServiceCharge> serviceCharges;
    private final double dues;

    public UnionAffiliation(final double dues) {
        serviceCharges = new ArrayList<ServiceCharge>();
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(final LocalDate chargeDate) {
        for(ServiceCharge charge: serviceCharges) {
            if(charge.getChargeDate().equals(chargeDate)) {
                return charge;
            }
        }
        return ServiceCharge.EMPTY;
    }

    public void addServiceCharge(final ServiceCharge due) {
        serviceCharges.add(due);
    }

    public double getFees(final LocalDateTime feeDate) {
        return 0;
    }
}
