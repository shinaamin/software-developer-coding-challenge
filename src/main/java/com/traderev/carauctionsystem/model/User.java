package com.traderev.carauctionsystem.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * User is entity class to store user related information.
 *
 * @author SHINA.
 * @version 1.0
 * @since 2018-08-07
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Email
    @Size(min = 5, max = 100)
    @Column(name = "email_id", unique = true)
    private String emailId;

    @NotNull
    @Size(min = 5)
    @Column(name = "password")
    private String password;

    @Column(name = "admin", columnDefinition = "TINYINT(1)")
    private boolean adminUser;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdminUser() {
        return adminUser;
    }

    public void setAdminUser(boolean adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(emailId, user.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, emailId);
    }
}


