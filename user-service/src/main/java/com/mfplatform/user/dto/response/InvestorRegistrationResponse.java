
package com.mfplatform.user.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import com.mfplatform.user.enums.AccountStatus;
import com.mfplatform.user.enums.RoleName;

public class InvestorRegistrationResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private AccountStatus accountStatus;

    private Set<RoleName> roles;

    private LocalDateTime createdAt;

    public InvestorRegistrationResponse() {
    }

    public InvestorRegistrationResponse(
            Long id,
            String firstName,
            String lastName,
            String email,
            String mobileNumber,
            AccountStatus accountStatus,
            Set<RoleName> roles,
            LocalDateTime createdAt) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountStatus = accountStatus;
        this.roles = roles;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
