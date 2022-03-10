package com.example.Application.DAO;

import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AbstractDAOImpl implements AbstractDao contains a set of methods are used to create a service
 * @author Yokubjon Sulaymonov
 */
@Repository("DAO")
public class AbstractDAOImpl implements AbstractDAO{
    @Override
    public StringBuilder getExchangeRatePLNToCurrencyCode(String currencyCode) {
        //getting the date of the last business day
        Date lastBusinessDay = getLast5BusinessDates().get(getLast5BusinessDates().size() - 1);
        //getting the date before 5 business days
        Date before5BusinessDays = getLast5BusinessDates().get(0);

        //defining the format for dates
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //formatting the last business day
        String endDate = simpleDateFormat.format(lastBusinessDay);
        //formatting the date before 5 business days
        String startDate = simpleDateFormat.format(before5BusinessDays);

        //defining the link with gathered data above to the table 'a'
        String link = "http://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode + "/" + startDate + "/" + endDate;

        //creating stringBuilder object to keep all exchange-rates data
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //if response code is not 200
            if(httpURLConnection.getResponseCode() != 200) {
                // this if statement will help to handle infinite incorrect inputs of the user
            } else {
                Scanner scanner = new Scanner(url.openStream());
                //this while loop will run until the scanner finishes reading all the data from url
                while(scanner.hasNext()) {
                    //appending the data to stringBuilder object
                    stringBuilder.append(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //if there is not any exchange-rate for this currency code in the table 'a' then we will check table 'b'
        if(stringBuilder.isEmpty()) {
            //defining the link with gathered data above to the table 'b'
            link = "http://api.nbp.pl/api/exchangerates/rates/b/" + currencyCode + "/" + startDate + "/" + endDate;
            try {
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                //if response code is not 200
                if (httpURLConnection.getResponseCode() != 200) {
                    // this if statement will help to handle infinite incorrect inputs of the user
                    stringBuilder.append("Oops, data is not found");
                    stringBuilder.append("\nTake care of the following cases: ");
                    stringBuilder.append("\n-Make sure the currency code is not PLN and was entered according to ISO 8601 standard!");
                    stringBuilder.append("\n-No changes in this time interval, determine longer period!");
                } else {
                    Scanner scanner = new Scanner(url.openStream());
                    //this while loop will run until the scanner finishes reading all the data from url
                    while (scanner.hasNext()) {
                        //appending the data to stringBuilder object
                        stringBuilder.append(scanner.nextLine());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //returning the stringBuilder object which contains all data
        return stringBuilder;
    }

    @Override
    public StringBuilder getGoldPriceAverage() {
        //getting the date of the last business day
        Date lastBusinessDay = getLast14BusinessDates().get(getLast14BusinessDates().size() - 1);
        //getting the date before 14 business days
        Date before14BusinessDays = getLast14BusinessDates().get(0);

        //defining the format for dates
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //formatting the last business day
        String endDate = simpleDateFormat.format(lastBusinessDay);
        //formatting the date before 5 business days
        String startDate = simpleDateFormat.format(before14BusinessDays);

        //defining the link with gathered data above
        String link = "http://api.nbp.pl/api/cenyzlota/" + startDate + "/" + endDate;

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //if response code is not 200
            if(httpURLConnection.getResponseCode() != 200) {
                // this if statement will help to handle infinite incorrect inputs of the user
                stringBuilder.append("Oops, data is not found");
                stringBuilder.append("\nTake care of the following cases: ");
                stringBuilder.append("\n-No changes in this time interval, determine longer period!");
            } else {
                Scanner scanner = new Scanner(url.openStream());
                //this while loop will run until the scanner finishes reading all the data from url
                while(scanner.hasNext()) {
                    //appending the data to stringBuilder object
                    stringBuilder.append(scanner.nextLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //returning stringBuilder object which contains all data
        return stringBuilder;
    }

    @Override
    public LinkedList<Date> getLast14BusinessDates() {
        //creating a list to contain last 14 business dates
        LinkedList<Date> last14BusinessDates = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();
        //getting the date of today
        Date date = new Date();
        //setting the date of today to calendar time
        calendar.setTime(date);

        //this loop will run until size will be 14, with obtained dates
        while(last14BusinessDates.size() != 14) {
            if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                //if, day is weekend, assign calendar date to one previous date and continue
                calendar.add(Calendar.DAY_OF_WEEK, -1);
            } else {
                /* else, day is weekday, add it to the last14BusinessDates object
                   assign calendar date to one previous date and continue        */
                last14BusinessDates.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_WEEK, -1);
            }
        }

        //creating temp object of LinkedList type
        LinkedList<Date> temp = new LinkedList<>();


        for(int i = 13; i >= 0; i--) {
            //adding value to temp object in ascending order
            temp.add(last14BusinessDates.get(i));
        }
        //defining last14BusinessDates in ascending order
        last14BusinessDates = temp;

        //returning last14BusinessDates object which contains all dates
        return last14BusinessDates;
    }

    @Override
    public LinkedList<Date> getLast5BusinessDates() {
        //creating a list to contain last 5 business dates
        LinkedList<Date> last5BusinessDates = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();
        //getting the date of today
        Date date = new Date();
        //setting the date of today to calendar time
        calendar.setTime(date);

        //this loop will run until size will be 5, with obtained dates
        while(last5BusinessDates.size() != 5) {
            if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                //if, day is weekend, assign calendar date to one previous date and continue
                calendar.add(Calendar.DAY_OF_WEEK, -1);
            } else {
                /* else, day is weekday, add it to the last5BusinessDates object,
                   assign calendar date to one previous date and continue        */
                last5BusinessDates.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_WEEK, -1);
            }
        }

        //creating temp object of LinkedList type
        LinkedList<Date> temp = new LinkedList<>();

        for(int i = 4; i >= 0; i--) {
            //adding value to temp object in ascending order
            temp.add(last5BusinessDates.get(i));
        }
        //defining last5BusinessDates in ascending order
        last5BusinessDates = temp;

        //returning last5BusinessDates object which contains all dates
        return last5BusinessDates;
    }
}
