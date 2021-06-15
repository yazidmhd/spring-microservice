package com.microservice.learn.user.VO;

import com.microservice.learn.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    //ResponseTemplateVO is used to for the return type
    //so you can return the user and its department
    private User user;
    private Department department;
}
