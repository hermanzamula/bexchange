package com.hz.pet.bexchange.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author Herman Zamula
 */

@Entity
@Data
public class User implements com.hz.pet.bexchange.domain.User, Identifiable<Long> {

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[0][0-9]{9}$", message = "Example: 0443335566")
    private String phoneNumber;

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String password;

    private boolean admin;

    @Version
    private long version;

    @LastModifiedDate
    private Date lastModified;

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

}
