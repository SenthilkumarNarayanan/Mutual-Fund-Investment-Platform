package com.mfplatform.user.service;

import com.mfplatform.user.dto.request.InvestorLoginRequest;
import com.mfplatform.user.dto.response.InvestorLoginResponse;

public interface InvestorLoginService {

    InvestorLoginResponse loginInvestor(InvestorLoginRequest request);
}