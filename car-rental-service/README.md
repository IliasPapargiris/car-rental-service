# Car Rental Service Application

This is a Car Rental Service application that calculates trip expenses based on:

- **Vehicle type** (Car, SUV, Van, Bus)
- **Fuel type** (Petrol, Diesel)
- **Distance** to destination
- **Number of passengers**
- Whether **air conditioning** is required
- Vehicle-specific **discounts** and **additional charges**

## Technologies Used

- **Java 17**
- **Spring Boot**
- **JUnit 5** 
- **Maven** 
- **Lombok**

## Application Structure

### Providers:
- **DistanceProvider**, **RateProvider**, **VehicleInfoProvider** manage different aspects of expense calculation (distance, rates, vehicle information).

### Services:
- **ExpenseCalculatorImpl** calculates the total expense by using the data from the providers.

### Enums:
- **VehicleType**, **FuelType** are enums used to define vehicle and fuel categories.


### Possible Future Implementations:

- **Database Integration**: You could implement providers such as `DatabaseDistanceProvider` or `DatabaseRateProvider` to fetch distance and rates information from a relational or NoSQL database.

- **External APIs**: If data needs to be fetched from external services, you could create providers like `ApiDistanceProvider` or `ApiRateProvider` that call external APIs to fetch data.

- **Dynamic Selection of Providers**: By using **Spring's `@Primary` annotation** or **`@Qualifier`**, you can easily switch between different provider implementations. For example, you could mark a database provider as `@Primary` to make it the default when available, or use `@Qualifier` to explicitly inject the desired provider at runtime. Additionally, you can implement a selector class or factory method to dynamically choose the appropriate provider (database, API, hardcoded) based on runtime conditions such as configurations or environment.

- **Plug-and-Unplug Logic**: The flexible architecture allows for easy "plug-and-unplug" of different implementations. For instance, if you need to switch from a hardcoded provider to an API-based provider, you only need to swap the implementation in the configuration without modifying the core business logic of the application.


### Running the Application

1. **Clone the repository:**

    ```bash
    git clone https://github.com/IliasPapargiris/car-rental-service.git
    ```

2. **Build the project:**

    ```bash
    mvn clean install
    ```

3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

