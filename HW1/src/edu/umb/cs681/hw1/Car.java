package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Car {

    private String make, model;
    private int mileage, year;
    private int price;
    private int dominationCount;

    public Car(String make, String model, int year, int mileage, int price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public int getDominationCount() {
        return dominationCount;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
    public int getPrice() {
        return price;
    }
    public int getMileage() {
        return mileage;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        for (Car car : cars) {
            if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
                    && (car.getYear() <= this.getYear())) {
                this.dominationCount++;
            }
        }
        this.dominationCount--;
    }

    @Override
    public boolean equals(Object obj) {
        Car car = (Car) obj;
        if(make.equals(car.getMake()) && model.equals(car.getModel()) && year==car.getYear() && mileage==car.getMileage() && price==car.getPrice() && dominationCount==car.getDominationCount())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.make+" "+this.model+" "+this.year+"; Mileage:"+this.mileage+" - $"+this.price;
    }

    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Porsche", "Kayene", 2019, 20, 50000));
        carList.add(new Car("Bugatti", "Veyron", 2016, 25, 60000));
        carList.add(new Car("Aston-Martin", "XZ", 2015,22 , 75000));
        carList.add(new Car("Audi", "Q7", 2021, 18, 96000));

        System.out.println("Cars list acc to Price:");
        List<Car> orderByPrice=carList.stream().sorted(Comparator.comparingInt(Car::getPrice)).collect(Collectors.toList());
        orderByPrice.forEach(System.out::println);

        System.out.println("\nCars list acc to Year:");
        List<Car> orderByYear=carList.stream().sorted(Comparator.comparingInt(Car::getYear)).collect(Collectors.toList());
        orderByYear.forEach(System.out::println);

        System.out.println("\nCars list acc to Mileage:");
        List<Car> orderByMileage=carList.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(Collectors.toList());
        orderByMileage.forEach(System.out::println);

        System.out.println("\nCars list acc to Domination rank:");
        List<Car> orderByDomination=carList.stream().sorted(Comparator.comparingInt(Car::getDominationCount)).collect(Collectors.toList());
        orderByDomination.forEach(System.out::println);
    }
}