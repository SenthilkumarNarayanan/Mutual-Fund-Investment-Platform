
package com.mfplatform.user.service.impl;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfplatform.user.dto.request.InvestorRegistrationRequest;
import com.mfplatform.user.dto.response.InvestorRegistrationResponse;
import com.mfplatform.user.entity.Role;
import com.mfplatform.user.entity.User;
import com.mfplatform.user.enums.AccountStatus;
import com.mfplatform.user.enums.RoleName;
import com.mfplatform.user.repository.RoleRepository;
import com.mfplatform.user.repository.UserRepository;
import com.mfplatform.user.service.InvestorRegistrationService;

@Service
public class InvestorRegistrationServiceImpl
        implements InvestorRegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public InvestorRegistrationServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public InvestorRegistrationResponse registerInvestor(
            InvestorRegistrationRequest request) {

        // 1. Normalize email
        String email = request.getEmail().trim().toLowerCase();

        // 2. Check duplicate email
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        // 3. Normalize mobile number
        String mobileNumber = request.getMobileNumber();

        if (mobileNumber != null && mobileNumber.isBlank()) {
            mobileNumber = null;
        } else if (mobileNumber != null) {
            mobileNumber = mobileNumber.trim();
        }

        // 4. Check duplicate mobile number
        if (mobileNumber != null &&
                userRepository.existsByMobileNumber(mobileNumber)) {

            throw new IllegalArgumentException(
                    "Mobile number is already registered");
        }

        // 5. Find the existing INVESTOR role
        Role investorRole = roleRepository.findByName(RoleName.INVESTOR)
                .orElseThrow(() -> new IllegalStateException(
                        "INVESTOR role not found"));

        // 6. Create user entity
        User user = new User();

        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(email);
        user.setMobileNumber(mobileNumber);

        // 7. Hash password using BCrypt
        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword()));

        // 8. Set initial account status
        user.setAccountStatus(AccountStatus.PENDING);

        // 9. Assign INVESTOR role
        user.setRoles(Set.of(investorRole));

        // 10. Save user
        User savedUser = userRepository.save(user);

        // 11. Prepare response
        InvestorRegistrationResponse response =
                new InvestorRegistrationResponse();

        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());
        response.setMobileNumber(savedUser.getMobileNumber());
        response.setAccountStatus(savedUser.getAccountStatus());
        response.setRoles(Set.of(investorRole.getName()));
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;
    }
}
