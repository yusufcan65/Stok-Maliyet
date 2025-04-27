package com.inonu.stok_takip.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class PurchaseForm extends BaseEntity{  // alım şekli

    private String name;

    @OneToMany(mappedBy = "purchaseForm")
    private List<Tender> tenders;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tender> getTenders() {
        return tenders;
    }

    public void setTenders(List<Tender> tenders) {
        this.tenders = tenders;
    }
}
