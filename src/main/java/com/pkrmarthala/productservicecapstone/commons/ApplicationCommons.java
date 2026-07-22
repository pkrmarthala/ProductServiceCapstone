package com.pkrmarthala.productservicecapstone.commons;

import com.pkrmarthala.productservicecapstone.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationCommons
{
    RestTemplate restTemplate;

    public ApplicationCommons(@Qualifier("getLoadBalancedRestTemplate")
                              RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public void validateToken(String token)
    {

        if(token==null || token.isEmpty())
        {
            throw new RuntimeException("Invalid token: Token is empty!");
            // Change this to a custom exception. We should never throw a RuntimeException.
        }

        String url = "http://UserServiceCapstone/validate/" + token;
        Boolean isValidToken = restTemplate.getForObject(url, Boolean.class);
        /* The validate method inside the UserService will return if the token is valid or not.
         * So, we need to map that Boolean response to the Boolean.class
         */

        if(Boolean.FALSE.equals(isValidToken))
        {
            throw new RuntimeException("Invalid token: Token is not valid!");
        }
    }

}
