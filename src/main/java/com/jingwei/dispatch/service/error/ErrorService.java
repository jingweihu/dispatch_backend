package com.jingwei.dispatch.service.error;

import com.jingwei.dispatch.api.base.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ErrorService {

    public String errorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors().get(0).getDefaultMessage();
    }
}
