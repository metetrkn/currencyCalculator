# Currency Converter Application
## Overview
* Welcome to the Currency Converter Application v1.0.1! This Java-based application allows you to convert amounts between different currencies using real-time exchange rates. The application utilizes the ISO 4217 three-letter currency codes, such as USD for US Dollars and EUR for Euro.

## Features
* User-Friendly Input: Easily input base and target currencies along with amounts.
* Real-Time Conversion: Fetches live exchange rates from an API.
* Interactive: Allows multiple conversions until the user decides to exit.
  
##  Getting Started
### Prerequisites
* Java 11 or higher
* Maven (for managing dependencies and building the project)

### Installation
1. Clone the Repository:
```bash
git clone https://github.com/yourusername/currency-converter.git ```

2. Navigate to the Project Directory:
 ```bash
cd currency-converter ```

3. Install Dependencies:
Make sure Maven is installed. Run the following command to install dependencies:
 ```bash
mvn install
```

4. Run the Application:
Use the following command to run the application:
 ```bash
mvn exec:java -Dexec.mainClass="se.meteTurkan.calcBody.Calculator"
```

### Usage
1. Start the Application:
* The application will welcome you and provide instructions on using ISO 4217 codes.

2. Input Currencies and Amounts:
* Enter the base currency code (e.g., USD).
* Input the amount you wish to convert.
* Enter the target currency code (e.g., EUR).

3. View Results:
* The application will display the conversion rate and the converted amount.

4. Continue or Exit:
* Choose whether to perform another conversion or exit the application.

### Example
### Example Output

```vbnet
Please input the base currency    : USD
How much USD    : 50
Please input the target currency    : EUR

You have inputed "USD" as base currency and "EUR" as target currency
Are you satisfied with currencies choose? 
1- (To accept)    0- (To Retry)

Conversion Rate (USD to EUR):  0.90632
50 USD =====> 45.316 EUR

Do you want to make another calculation or exit the program?
(1) - New calculation        (0) - Exit
```


## Contributing
Feel free to open issues or submit pull requests if you have suggestions or improvements.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
For any questions or feedback, please reach out to [your email address].
