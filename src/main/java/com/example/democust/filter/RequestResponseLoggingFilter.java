package com.example.democust.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    // If you have initialization logic, you can put it in the constructor
    public RequestResponseLoggingFilter() {
        logger.info("RequestResponseLoggingFilter initialized");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        // Log request details
        logger.info("Request Method: {} | Request URI: {} | Request Params: {}", 
                     request.getMethod(), request.getRequestURI(), request.getParameterMap());

        // Proceed with the filter chain
        try {
			filterChain.doFilter(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Log response details
        logger.info("Response Status: {}", response.getStatus());
    }    
}