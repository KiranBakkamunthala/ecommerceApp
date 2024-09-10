package com.example.democust.filter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

public class RequestResponseLoggingFilterTest {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilterTest.class);

    private RequestResponseLoggingFilter requestResponseLoggingFilter;

    @BeforeEach
    public void setUp() {
        requestResponseLoggingFilter = new RequestResponseLoggingFilter();
    }

    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        // Mock the HTTP request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/api/test");
        request.setParameter("param", "value");

        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = Mockito.mock(FilterChain.class);

        // Perform the filter
        requestResponseLoggingFilter.doFilterInternal(request, response, filterChain);

        // Verify filter chain continues to the next filter
        verify(filterChain, times(1)).doFilter(request, response);

        // Log the response status
        logger.info("Response status after filter: {}", response.getStatus());
    }
}
