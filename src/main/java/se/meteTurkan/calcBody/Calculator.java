package se.meteTurkan.calcBody;

import java.util.Scanner; // Allows user to input
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
    String curBase; String curTarget;
    double quantBase;

    public void userInputs() {
        Scanner myScanner = new Scanner(System.in); // Scanner object to get input

        boolean key = true; // Key to check if user satisfied with input
        do {
            // Base currency type
            System.out.print("\nPlease input the base currency\t:");
            curBase = myScanner.nextLine().toUpperCase();; // User inputSystem.out.print("Please input the base currency\t:");

            // Base currency quantity
            System.out.printf("How much %S\t:", curBase);
            quantBase = myScanner.nextDouble(); // User inputSystem.out.print("Please input the base currency\t:");
            myScanner.nextLine(); // Consume the leftover newline

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


class CurrencyConverter {
    static double myCalc (double baseAmount , double ratio) {
        return baseAmount * ratio;
    }
}

public class Calculator {
    public static void main(String[] args) {
        Welcomer.welcoming(); // Prints out welcoming message

        InputCurrency inputObj = new InputCurrency(); // Creating object of InputCurrency class

        double conversionRate = 0.0; // Initializing ratio variable between 2 currencies as default set to 0

        boolean key = true;

        // Keep making new currency conversations until user states otherwise
        while (key) {
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
                conversionRate = jsonResponse.getDouble("conversion_rate");

                System.out.printf("Conversion Rate (%S to %S):  %f\n", inputObj.curBase, inputObj.curTarget, conversionRate);

                // Exception handling
            } catch (Exception e) {
                e.printStackTrace();
            }

            double result = CurrencyConverter.myCalc(inputObj.quantBase, conversionRate);

            System.out.printf("%g %S =====> %g %S\n", inputObj.quantBase, inputObj.curBase, result, inputObj.curTarget);

            System.out.println("\n\nDo you want to make another calculation or exit the program?\n" +
                    "(1) - New calculation\t\t(0) - Exit");

            // Creating object to accept user input
            Scanner newScanner = new Scanner(System.in);
            String nextProcess = newScanner.nextLine();

            // If user wants to exit
            if (nextProcess.equals("0")) {
                key = false;
            }
        }

        // Farewell message
        System.out.println("\n¨¨¨¨¨¨¨¨                               <|>                                          ¨¨¨¨¨¨¨¨\n" +
                             "Thanks for using our Currency Converter! May your investments grow and your wallet stay fat.\n" +
                              "¨¨¨¨¨¨¨¨                               <|>                                          ¨¨¨¨¨¨¨¨");
    }
}

