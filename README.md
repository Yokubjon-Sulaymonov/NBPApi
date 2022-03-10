# NBPApi
Project is created using SpringBoot in Java and NBPApi

## General info
This project is a very functional service written in Java programming language using SpringBoot and NBPApi. Server port of the program is 3000. User can see exchange-rate PLN to {currencyCode} or average gold price with the following endpoints:

#### GET "localhost:3000/api/exchange-rates/{currencyCode}"
{currencyCode} is a variable is given from the User side. The program will read it as a currency code and return the exchange-rate PLN to {currencyCode} for the last 5 business(working) days in JSON format.

<br />If there is no update for the last working day yet, then the program will execute data for available last 4 days.
<br />Else if there is no update for most of days in this time interval, then the program will execute for the available day(s).
<br />Else there is not any update for this time interval, then the User will get a notification list.

<br />Entering incorrect currency code will also cause to get the notification list.
<br />Entering incorrect path or leaving {currencyCode} empty will cause 404 Not Found message to be returned.

#### GET "localhost:3000/api/gold-price/average"
Program returns average gold price for the last 14 business(working) days in JSON format.

<br />If there is no update for the last working day yet, then the program will execute data for available last 13 days.
<br />Else if there is no update for most of days in this time interval, then the program will execute for the available day(s).
<br />Else there is not any update for this time interval, then the User will get a notification list.

<br />Entering incorrect path will cause 404 Not Found message to be returned.

## SetUp
0. (Make sure if you are connected to Internet :) )
1. Run the service with IntelliJ IDEA or any other Integrated Development Environment(IDE) for JVM languages.
2. Install Postman, enter the correct endpoint and check out the data.
