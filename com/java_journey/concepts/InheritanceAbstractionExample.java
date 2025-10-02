package com.java_journey.concepts;

/**
 * Inheritance and Abstraction Example in Java.
 *
 * Abstraction: The process of hiding complex implementation details and showing only the essential features.
 * In this example, `Vehicle` is an abstract class. It defines a template with shared properties
 * (make, model) and behaviors (accelerate), but leaves specific behaviors (`startEngine`) to be
 * defined by its subclasses.
 *
 * Inheritance: The mechanism by which one class inherits properties and methods from another.
 * The `Car` and `Motorcycle` classes inherit from `Vehicle`, reusing its code and providing
 * their own unique implementations for abstract methods and adding their own specific methods.
 *
 * --- CORRECTED OUTPUT ---
 * --- Creating Vehicles ---
 * A new Toyota Camry has been created.
 * A new Ducati Panigale has been created.
 *
 * --- Operating the Car ---
 * Starting the engine of the 2020 Toyota Camry...
 * The engine of the 2020 Toyota Camry is now running.
 * The 2020 Toyota Camry accelerated to 60 mph
 * The trunk of the Toyota Camry is now open.
 *
 * --- Operating the Motorcycle ---
 * Starting the engine of the 2023 Ducati Panigale...
 * The 2023 Ducati Panigale roars to life!
 * The Ducati Panigale is popping a wheelie!
 *
 * --- Demonstrating Polymorphism in the Garage ---
 * Operating the 2020 Toyota Camry...
 * The engine of the 2020 Toyota Camry is now running.
 * Operating the 2023 Ducati Panigale...
 * The 2023 Ducati Panigale roars to life!
 */

abstract class Vehicle {
    // IMPROVEMENT: `protected` allows subclasses to access these directly, which is common for inheritance.
    protected String make;
    protected String model;
    protected int year;
    protected int speed;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.speed = 0;
        System.out.println("A new " + year + " " + make + " " + model + " has been created.");
    }

    public void accelerate(int increment) {
        speed += increment;
        System.out.println("The " + year + " " + make + " " + model + " accelerated to " + speed + " mph");
    }

    // Abstract method - subclasses MUST provide an implementation.
    public abstract void startEngine();

    // A generic getter that all subclasses will inherit.
    public String getInfo() {
        return year + " " + make + " " + model;
    }
}

// Car class inherits from Vehicle
class Car extends Vehicle {
    public Car(String make, String model, int year) {
        super(make, model, year); // Calls the constructor of the superclass (Vehicle)
    }

    @Override
    public void startEngine() {
        System.out.println("Starting the engine of the " + getInfo() + "...");
        System.out.println("The engine of the " + getInfo() + " is now running.");
    }
    
    // Car-specific method
    public void openTrunk() {
        System.out.println("The trunk of the " + make + " " + model + " is now open.");
    }
}

// Motorcycle class also inherits from Vehicle
class Motorcycle extends Vehicle {
    // CORRECTED: Added the missing field declaration for `hasSidecar`.
    private boolean hasSidecar;

    // CORRECTED: The boolean parameter now has a name, `hasSidecar`.
    public Motorcycle(String make, String model, int year, boolean hasSidecar) {
        super(make, model, year);
        this.hasSidecar = hasSidecar;
    }

    // A different, more exciting implementation of the abstract method.
    @Override
    public void startEngine() {
        System.out.println("Starting the engine of the " + getInfo() + "...");
        System.out.println("The " + getInfo() + " roars to life!");
    }

    // Motorcycle-specific method
    public void popWheelie() {
        if (!hasSidecar) {
            System.out.println("The " + make + " " + model + " is popping a wheelie!");
        } else {
            System.out.println("Cannot pop a wheelie with a sidecar attached.");
        }
    }
}

public class InheritanceAbstractionExample {
    public static void main(String[] args) {
        System.out.println("--- Creating Vehicles ---");
        // Create an instance of Car
        Car myCar = new Car("Toyota", "Camry", 2020);
        // Create an instance of Motorcycle
        Motorcycle myBike = new Motorcycle("Ducati", "Panigale", 2023, false);

        System.out.println("\n--- Operating the Car ---");
        myCar.startEngine();
        myCar.accelerate(60);
        myCar.openTrunk();

        System.out.println("\n--- Operating the Motorcycle ---");
        myBike.startEngine();
        myBike.popWheelie();
        
        // This section truly shows the power of inheritance and abstraction.
        // We can treat different objects (Car, Motorcycle) as the same type (Vehicle).
        System.out.println("\n--- Demonstrating Polymorphism in the Garage ---");
        Vehicle[] myGarage = {myCar, myBike};
        
        for (Vehicle v : myGarage) {
            System.out.println("Operating the " + v.getInfo() + "...");
            // The correct startEngine() method is called for each object automatically!
            v.startEngine();
        }
    }
}

