package com.example.ProductServiceAPI.Security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JwtObject {

    private String email;
    private Date createdAt;

    private Long userId;
    private Date expiryAt;

    private List<Role> roles =new ArrayList<>();
}
