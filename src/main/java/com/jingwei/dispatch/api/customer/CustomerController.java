package com.jingwei.dispatch.api.customer;

import com.jingwei.dispatch.api.base.ApiBaseResponse;
import com.jingwei.dispatch.api.base.ApiConstant;
import com.jingwei.dispatch.api.base.ApiErrorResponse;
import com.jingwei.dispatch.db.model.Customer;
import com.jingwei.dispatch.service.error.ErrorService;
import com.jingwei.dispatch.service.security.EncryptService;
import com.jingwei.dispatch.service.user.ICustomerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private EncryptService encryptService;
    @Autowired
    private ErrorService errorService;

    @PostMapping(path = "/customer/login")
    public ResponseEntity userLogin(@Valid @RequestBody LoginParam loginParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(new ApiBaseResponse<>(ApiConstant.ERROR, errorService.errorMessage(bindingResult)));
        }
        Optional<Customer> optional = customerService.findByUserName(loginParam.getUsername());
        if (optional.isPresent() && encryptService.check(loginParam.getPassword(), optional.get().getPassword())) {
            return ResponseEntity.ok(new ApiBaseResponse<>(ApiConstant.SUCCESS, "You have successfully login"));
        } else if (!optional.isPresent()) {
            return ResponseEntity.ok(new ApiErrorResponse(ApiConstant.ERROR, "User didn't exist"));
        } else {
            return ResponseEntity.ok(new ApiErrorResponse(ApiConstant.ERROR, "InValid Password"));
        }
    }

    @PostMapping(path = "/customer/register")
    public ResponseEntity userSignup(@RequestBody LoginParam loginParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(new ApiBaseResponse<>(ApiConstant.ERROR, errorService.errorMessage(bindingResult)));
        }
        Optional<Customer> optional = customerService.findByUserName(loginParam.getUsername());
        if (optional.isPresent()) {
            return ResponseEntity.ok(new ApiErrorResponse(ApiConstant.ERROR, "User already exists"));
        } else {
            Optional<Customer> customer = customerService.save(loginParam.getUsername(), loginParam.getPassword());
            return ResponseEntity.ok(new ApiErrorResponse(ApiConstant.SUCCESS, "User has successfully registered"));
        }
    }

    @PostMapping(path = "/customer/update")
    public ResponseEntity userUpdate(@RequestBody Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(new ApiBaseResponse<>(ApiConstant.ERROR, errorService.errorMessage(bindingResult)));
        }
        Optional<Customer> optional = customerService.findById(customer.getId());
        if (!optional.isPresent()) {
            return ResponseEntity.ok(new ApiErrorResponse(ApiConstant.ERROR, "User didn't exist"));
        } else {
            Customer existingCustomer = optional.get();
            existingCustomer.setUsername(customer.getUsername());
            existingCustomer.setPassword(customer.getPassword());
            existingCustomer.setActive(customer.isActive());
            customerService.update(existingCustomer);
            return ResponseEntity.ok(new ApiBaseResponse<>(ApiConstant.SUCCESS, "User has been updated"));
        }
    }

}

@Getter
class LoginParam {
    @NotBlank(message = "Username can't be empty")
    private String username;
    @NotBlank(message = "Password can't be empty")
    private String password;
}
