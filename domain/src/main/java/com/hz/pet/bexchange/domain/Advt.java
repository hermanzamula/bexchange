package com.hz.pet.bexchange.domain;

import java.util.Date;

/**
 * @author Herman Zamula
 */
public interface Advt {

    User getCreator();

    Date getCreationTime();

    Location getLocation();

    String getComment();

    Currency getCurrency();

    Float getExchangeRate();

    Long getAmount();

    AdvtType getAdvtType();

    AdvtStatus getAdvtStatus();

    enum AdvtType {
        SELL, BUY
    }

    enum AdvtStatus {
        ACTIVE, DONE
    }

    enum Currency {
        USD, EUR, RUB, UAH
    }

    interface Location {

        String getCity();

        String getArea();

    }

}
