package com.hz.pet.bexchange.domain.advt;

import com.hz.pet.bexchange.domain.user.User;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;

/**
 * @author Herman Zamula
 */
@Entity
@Data
public class Advt implements com.hz.pet.bexchange.domain.Advt, Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false)
    private User creator;

    @Column(nullable = false)
    private Date creationTime;

    @Embedded
    private AdvtLocation location;

    @Lob
    private String comment;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Currency currency;

    @Column(nullable = false)
    private Float exchangeRate;

    @Column(nullable = false)
    private Long amount;

    @Enumerated(STRING)
    @Column(nullable = false)
    private AdvtType advtType;

    @Column(nullable = false)
    @Enumerated(STRING)
    private AdvtStatus advtStatus;

    @Version
    private long version;

    @LastModifiedDate
    private Date lastModified;

    @Override
    public User getCreator() {
        return creator;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public AdvtLocation getLocation() {
        return location;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public Float getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public Long getAmount() {
        return amount;
    }

    public AdvtType getAdvtType() {
        return advtType;
    }

    @Override
    public AdvtStatus getAdvtStatus() {
        return advtStatus;
    }

    @Override
    public Long getId() {
        return id;
    }
}
