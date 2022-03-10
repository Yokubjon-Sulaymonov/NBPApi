package com.example.Application.API;

import com.example.Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController class contains a set of methods with the same name as UserService class
 * @author Yokubjon Sulaymonov
 */
@RestController
public class UserController {
    private final UserService userService;

    /**
     * Defines final object of current class userService to the value given in parameter
     * @param userService will be used to define the same type object of current class
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Calls the method with the same name from UserService class
     * @return currency exchange rate PLN to USD for the last 5 business days.
     * @param currencyCode contains user input as a currency code
     */
    //giving endpoint - "api/exchange-rates/{currencyCode}"
    @GetMapping("api/exchange-rates/{currencyCode}")
    public StringBuilder getExchangeRatePLNToCurrencyCode(@PathVariable("currencyCode") String currencyCode) {
        return userService.getExchangeRatePLNToCurrencyCode(currencyCode);
    }

    /**
     * Calls the method with the same name from UserService class
     * @return average gold price for the last 14 business days
     */
    //giving endpoint - "api/gold-price/average"
    @GetMapping("api/gold-price/average")
    public StringBuilder getGoldPriceAverage() {
        return userService.getGoldPriceAverage();
    }
}
