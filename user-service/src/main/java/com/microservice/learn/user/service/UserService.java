package com.microservice.learn.user.service;

import com.microservice.learn.user.VO.Department;
import com.microservice.learn.user.VO.ResponseTemplateVO;
import com.microservice.learn.user.entity.User;
import com.microservice.learn.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //with the restTemplate, you can call the Department service and get the object
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //you need the Department from the particular user
    //to get the Department which is another microservice
    //so you need to call the microservice and get the Department object from there
    //RestTemplate object is required - create in UserServiceApplication as a @Bean
    //and in UserService and @Autowired it
    //need to return as ResponseTemplateVO
    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();

        //get the specific user object with findByUserId
        User user = userRepository.findByUserId(userId);

        //returns the department object from the Department service
        //to get Department object, need to do a REST call to the other microservice(Department)
        //if the services are connected in the same service registry
        //you can put application name as the url
        //need to tell restTemplate that we are connected to ServiceRegistry - @LoadBalanced
        Department department = restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                Department.class
        );

        vo.setUser(user);
        vo.setDepartment(department);

        return vo;

    }
}
