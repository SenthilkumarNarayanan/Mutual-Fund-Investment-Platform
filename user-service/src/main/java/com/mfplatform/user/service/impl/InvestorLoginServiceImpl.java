package com.mfplatform.user.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.mfplatform.user.dto.request.InvestorLoginRequest;
import com.mfplatform.user.dto.response.InvestorLoginResponse;
import com.mfplatform.user.entity.User;
import com.mfplatform.user.enums.AccountStatus;
import com.mfplatform.user.enums.RoleName;
import com.mfplatform.user.repository.UserRepository;
import com.mfplatform.user.security.JwtService;
import com.mfplatform.user.service.InvestorLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InvestorLoginServiceImpl implements InvestorLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final Logger logger =
            LoggerFactory.getLogger(InvestorLoginServiceImpl.class);

    public InvestorLoginServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional(readOnly = true)
    public InvestorLoginResponse loginInvestor(
            InvestorLoginRequest request) {

        // 1. Normalize email
        String email = request.getEmail().trim().toLowerCase();

        // 2. Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("Investor login failed: invalid credentials");
                    return new ResponseStatusException(
                            HttpStatus.UNAUTHORIZED,
                            "Invalid email or password");
                });
        // 3. Verify password
        if (!passwordEncoder.matches(
                request.getPassword(), user.getPasswordHash())) {

            logger.warn("Investor login failed: invalid credentials");

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password");
            }

        // 4. Check account status
        if (user.getAccountStatus() != AccountStatus.ACTIVE) {

            switch (user.getAccountStatus()) {

                case PENDING:
                    logger.warn(
                        "Investor login denied: account is PENDING. User ID: {}",
                        user.getId()
                    );
                    break;

                case INACTIVE:
                    logger.warn(
                        "Investor login denied: account is INACTIVE. User ID: {}",
                        user.getId()
                    );
                    break;

                case BLOCKED:
                    logger.warn(
                        "Investor login denied: account is BLOCKED. User ID: {}",
                        user.getId()
                    );
                    break;

                default:
                    logger.warn(
                        "Investor login denied: unexpected account status. User ID: {}",
                        user.getId()
                    );
            }

            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "Your account is not active"
            );
        }
        // 5. Generate JWT access token
        String accessToken = jwtService.generateAccessToken(user);

        logger.info("Investor login successful. User ID: {}",
                user.getId());

        // 6. Get assigned roles
        Set<RoleName> roles = user.getRoles()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet());

        // 7. Prepare login response
        InvestorLoginResponse response = new InvestorLoginResponse();

        response.setAccessToken(accessToken);
        response.setTokenType("Bearer");
        response.setExpiresIn(jwtService.getExpirationSeconds());
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRoles(roles);

        return response;
    }
}
