package com.hz.pet.bexchange.domain.advt;

import com.hz.pet.bexchange.domain.Advt;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Herman Zamula
 */
@Embeddable
@Data
public class AdvtLocation implements Advt.Location {

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String area;

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getArea() {
        return area;
    }
}
