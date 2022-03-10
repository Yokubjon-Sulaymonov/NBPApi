package com.example.Application.Service;

import com.example.Application.DAO.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * UserService class contains a set of methods with the same name as AbstractDAO interface
 * @author Yokubjon Sulaymonov
 */
@Service
public class UserService {
    private final AbstractDAO abstractDAO;

    /**
     * Defines final object of current class abstractDAO to the value given in parameter
     * @param abstractDAO will be used to define the same type object of current class
     */
    @Autowired
    public UserService(@Qualifier("DAO") AbstractDAO abstractDAO) {
        this.abstractDAO = abstractDAO;
    }

    /**
     * Calls the method with the same name from AbstractDAO interface
     * @return currency exchange rate PLN to currency code for the last 5 business days.
     * @param currencyCode contains user input as a currency code
     */
    public StringBuilder getExchangeRatePLNToCurrencyCode(String currencyCode) {
        return abstractDAO.getExchangeRatePLNToCurrencyCode(currencyCode);
    }

    /**
     * Calls the method with the same name from AbstractDAO interface
     * @return average gold price for the last 14 business days
     */
    public StringBuilder getGoldPriceAverage() {
        return abstractDAO.getGoldPriceAverage();
    }
}
