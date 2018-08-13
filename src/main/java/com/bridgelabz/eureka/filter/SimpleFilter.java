package com.bridgelabz.eureka.filter;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class SimpleFilter extends ZuulFilter {

 @Value("${key}")
 private String key;

 @Override
 public boolean shouldFilter() {
     return true;
 }

 @Override
 public String filterType() {
     return "pre";
 }

 @Override
 public int filterOrder() {
     return 1;
 }

 @Override
 public Object run() {

     RequestContext ctx = RequestContext.getCurrentContext();
     HttpServletRequest request = ctx.getRequest();
     System.out.println(request.getRequestURI());
     System.out.println(request.getHeader("token"));
     
     if (!request.getRequestURI().contains("/user")) {
    	 String token = request.getHeader("token");
    	 Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token)
 				.getBody();
System.out.println("inside filter");
System.out.println(token);
         ctx.addZuulRequestHeader("userId",claims.getSubject());

         return "Successfully authorized";
     }
     return "";
 }
}
