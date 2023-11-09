## Hugo Viana BJSS assigment - Basket



## Compiling

For compiling project, 
   1. run ./gradlew build
   2. app.properties could be changed for a currency needed
   3. java -jar build/libs/Hugo.Viana.BJSSAssignment-1.0-SNAPSHOT.jar Soup Apples 

## Configuration

1. Java 19
2. Gradle 8.4
3. Junit 5 with Mockito



## Offer Strategy 
Implementation of design pattern The Chain Of responsibility. (based on https://www.youtube.com/watch?v=FafNcoBvVQo)
All the discounts are available at org.jbs.offer.discounts.

## Extras
A mocking database json file is provided if you want to load different data to the products.
file location: src/main/resources/Products.json.
You can change the currency for something you want in app.properties. (by default UK is configured) 



