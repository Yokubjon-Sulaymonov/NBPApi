package com.example.Application.DAO;

import java.util.Date;
import java.util.LinkedList;

/**
 * AbstractDAO contains a set of abstract methods are used to create a service
 * @author Yokubjon Sulaymonov
 */
public interface AbstractDAO {

    /**
     * Retrieves the data in JSON format from given url and appends it to the StringBuilder object
     * @return currency exchange rate PLN to currency code for the last 5 business days.
     * @param currencyCode contains user input as a currency code
     */
    StringBuilder getExchangeRatePLNToCurrencyCode(String currencyCode);

    /**
     * Retrieves the data in JSON format from given url and appends it to the StringBuilder object
     * @return average gold price for the last 14 business days
     */
    StringBuilder getGoldPriceAverage();

    /**
     * Retrieves the dates of weekdays from Calendar class and adds them to the LinkedList object
     * @return last 5 business dates
     */
    LinkedList<Date> getLast5BusinessDates();

    /**
     * Retrieves the dates of weekdays from Calendar class and adds them to the LinkedList object
     * @return last 14 business dates
     */
    LinkedList<Date> getLast14BusinessDates();
}
