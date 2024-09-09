package se.meteTurkan.calcBody;

import java.util.Scanner; // Allows user to input
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONObject;


// User welcoming
class Welcomer {
    static void welcoming() {
        String welcomeMessage =
            "**********************************************************************\n" +
            "**********************************************************************\n" +
            "*                                                                    *\n" +
            "*  ~~~   Welcome to the Currency Converter Application v1.0.1  ~~~   *\n" +
            "*                                                                    *\n" +
            "**********************************************************************\n" +
            "**********************************************************************\n\n" +
            "The application utilizes ISO 4217 three-letter currency codes, such as USD for US Dollars and EUR for Euro.\n" +
            "Check this web site to find out your currency unidcode:\n" +
                "https://docs.1010data.com/1010dataReferenceManual/DataTypesAndFormats/currencyUnitCodes.html";

        System.out.println(welcomeMessage);
    }
}


// User currencies input
class InputCurrency {
    String curBase; // Declaring base currency for compare
    String curTarget; // Declaring target currency for compare

    public void userInputs() {
        Scanner myScanner = new Scanner(System.in); // Scanner object to get input

        boolean key = true; // Key to check if user satisfied with input
        do {
            // Base currency
            System.out.print("Please input the base currency\t:");
            curBase = myScanner.nextLine().toUpperCase();; // User inputSystem.out.print("Please input the base currency\t:");

            // Target currency
            System.out.print("Please input the target currency\t:");
            curTarget = myScanner.nextLine().toUpperCase();; // User input

            // User input feedbacks
            System.out.println("\nYou have inputed \"" + curBase + "\" as base currency and \"" + curTarget + "\" as target currency");
            System.out.println("Are you satisfied with currencies choose? \n1- (To accept)\t0- (To Retry)");

            // User input for satisfaction with currencies
            String userChose = myScanner.nextLine();

            // Ending loop based on user preference
            if (userChose.equals("1")) {
                key = false;
            }
            System.out.println("\n\n"); // Leaving 2 lines empty for next messages
        } while(key);
    }
}


public class Calculator {
    public static void main(String[] args) {
        Welcomer.welcoming(); // Prints out welcoming message

        InputCurrency inputObj = new InputCurrency(); // Creating object of InputCurrency class

        inputObj.userInputs(); // Captures user-selected currencies for comparison



        // Scratching currencies data
        try {
            // Adjusting URL to use in Get request
            String url = "https://v6.exchangerate-api.com/v6/17ac7d3a83e814c245ef9ea9/pair/" + inputObj.curBase +
                    "/" + inputObj.curTarget;

            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create a GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Get the JSON response string
            String jsonResponseString = response.body();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(jsonResponseString);

            // Extract the conversion_rate
            double conversionRate = jsonResponse.getDouble("conversion_rate");

            System.out.println("Conversion Rate (USD to TRY):  " + conversionRate);

            // Exception handling
        } catch (Exception e) {
            e.printStackTrace();
        }



       ;
    }
}

/**
 *
 *
 *
 */

