package com.hz.pet.bexchange.domain.user;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

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

    private String password;

    private boolean admin;

    public User() {
    }

    public User(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
