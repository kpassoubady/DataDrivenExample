package com.kavinschool.pattern.examples;

public class CarInternalBuilderDemo {


    public static void main(String[] args) {
        Car car1 = Car.newBuilder().build();
        System.out.println("car1 = " + car1);

        Car car2 = Car.newBuilder().withColor("Blue")
                .withCurrentSpeed(80).withFuelCapacity(20).withMake("Toyota").withModel("Sienna")
                .withMaximumSpeed(150).withWheels(4).withYear(2012).build();
        System.out.println("car2 = " + car2);


        //cloning car2 to car3 using copy method
        Car car3 = Car.newBuilder(car2).build();
        System.out.println("car3 = " + car3);

        //You can clone car3, with some property changes
        Car car4 = Car.newBuilder(car3).withColor("Red").build();
        System.out.println("car4 = " + car4);
        
    }

}
