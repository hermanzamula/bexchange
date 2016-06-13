package com.hz.pet.bexchange.domain;

import java.util.Date;

/**
 * @author Herman Zamula
 */
public interface Advt {

    User getCreator();

    Date getCreationTime();

    String getCity();

    String getCityRegion();

    String getComment();

    String getCurrency();

    float getExchangeRate();

    long getAmount();

    BidType getBidType();

    BidStatus getBidStatus();

    enum BidType {
        SELL, BUY
    }

    enum BidStatus {
        ACTIVE, DONE
    }

}
