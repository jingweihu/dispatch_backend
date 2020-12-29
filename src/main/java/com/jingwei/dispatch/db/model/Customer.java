package com.jingwei.dispatch.db.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
public class Customer {
    @Positive(message = "Id can't be negative")
    private Long id;

    @NotBlank(message = "Username can't be empty")
    private String username;

    @NotBlank(message = "Password can't be empty")
    private String password;

    @NotBlank(message = "Create can't be empty")
    private String createOn;

    private boolean isActive;

}
