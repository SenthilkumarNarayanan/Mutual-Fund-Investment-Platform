package com.mfplatform.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mfplatform.user.dto.request.InvestorLoginRequest;
import com.mfplatform.user.dto.response.InvestorLoginResponse;
import com.mfplatform.user.service.InvestorLoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class InvestorLoginController {

    private final InvestorLoginService investorLoginService;

    public InvestorLoginController(
            InvestorLoginService investorLoginService) {

        this.investorLoginService = investorLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<InvestorLoginResponse> loginInvestor(
            @Valid @RequestBody InvestorLoginRequest request) {

        InvestorLoginResponse response =
                investorLoginService.loginInvestor(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}