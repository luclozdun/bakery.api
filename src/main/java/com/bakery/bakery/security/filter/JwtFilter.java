package com.bakery.bakery.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakery.bakery.exception.ResourceNotFoundException;
import com.bakery.bakery.security.service.BakerService;
import com.bakery.bakery.security.service.CustomerService;
import com.bakery.bakery.security.service.OwnerService;
import com.bakery.bakery.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BakerService bakerService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OwnerService ownerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        String role = null;
        
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            //"Bearer " 7 chars
            token = authorizationHeader.substring(7);
            var data = jwtUtil.getAllClaimsFromToken(token); 
            System.out.println(data.get("role").toString());
            username = data.get("sub").toString();
            role = data.get("role").toString();
        }
        
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails;
            if(role.equals("CUSTOMER")) {
                var customer = customerService.getByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
                userDetails = customerService.loadUserByUsername(customer.getUsername(), customer.getPassword()); 
            }
            else if(role.equals("BAKER")) {
                var baker = bakerService.getBakerByUsername(username).orElseThrow(() -> new ResourceNotFoundException("message"));
                userDetails = bakerService.loadUserByUsername(baker.getUsername(), baker.getPassword()); 
            }
            else if(role.equals("OWNER")){
                var owner = ownerService.getByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Owner not found"));
                userDetails = ownerService.loadUserByUsername(owner.getUsername(), owner.getPassword());
            }
            else{ throw new ResourceNotFoundException("Excuse me but I don't know"); }            

            if(jwtUtil.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            
        }
        filterChain.doFilter(request, response);
    }

}
