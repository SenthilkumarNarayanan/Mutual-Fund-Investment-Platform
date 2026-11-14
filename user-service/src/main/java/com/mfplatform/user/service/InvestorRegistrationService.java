package com.mfplatform.user.service;

import com.mfplatform.user.dto.request.InvestorRegistrationRequest;
import com.mfplatform.user.dto.response.InvestorRegistrationResponse;

public interface InvestorRegistrationService {

    InvestorRegistrationResponse registerInvestor(
            InvestorRegistrationRequest request
    );
}