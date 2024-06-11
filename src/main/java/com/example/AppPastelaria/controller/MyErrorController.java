package com.example.AppPastelaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AppPastelaria.repository.UserRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class MyErrorController implements ErrorController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (statusCode) {
            	case 403:
            		return "403";
                
                case 404:
                    return "404";

                case 500:
                    return "500";
                    
            }
        }
        return "error";
    }

}
