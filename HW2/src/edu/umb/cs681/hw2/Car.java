package edu.umb.cs681.hw2;

import java.util.*;
import java.util.stream.Collectors;


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

/*
        Integer minPrice = carList.stream().map((Car car) -> car.getPrice()).reduce(Integer::min).get();
        Integer maxPrice = carList.stream().map((Car car) -> car.getPrice()).reduce(Integer::max).get();
*/

        OptionalInt minPrice = carList.stream().mapToInt((Car car) -> car.getPrice()).min();
        System.out.println("Low cost car: "+minPrice.getAsInt());

        OptionalInt maxPrice = carList.stream().mapToInt((Car car) -> car.getPrice()).max();
        System.out.println("High cost car: "+maxPrice.getAsInt());


        Integer avgPrice = carList.stream()
                .map((Car car) -> car.getPrice())
                .reduce(0, (result,price) -> result+price, (finalResult, intermediateResult) -> finalResult)/carList.size();

        System.out.println("Average price: " + avgPrice);
    }

}