
package com.mfplatform.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.mfplatform.user.dto.request.InvestorRegistrationRequest;
import com.mfplatform.user.dto.response.InvestorRegistrationResponse;
import com.mfplatform.user.service.InvestorRegistrationService;

@RestController
@RequestMapping("/api/v1/users")
public class InvestorRegistrationController {

    private final InvestorRegistrationService registrationService;

    public InvestorRegistrationController(
            InvestorRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<InvestorRegistrationResponse> registerInvestor(
            @Valid @RequestBody InvestorRegistrationRequest request) {

        InvestorRegistrationResponse response =
                registrationService.registerInvestor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
