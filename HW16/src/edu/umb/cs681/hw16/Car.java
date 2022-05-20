package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String objMake, objModel;
    private int mileage, year,price, dominationCount;

    public Car(String objMake, String objModel, int year, int mileage, int price) {
        this.objMake = objMake;
        this.objModel = objModel;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    public int getDominationCount() {
        return dominationCount;
    }
    public String getstrMake() {
        return objMake;
    }
    public String getstrModel() {
        return objModel;
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

    public void setDominationCount(List<Car> cars) {
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
        if(objMake.equals(car.getstrMake()) && objModel.equals(car.getstrModel()) && year==car.getYear() && mileage==car.getMileage() && price==car.getPrice() && dominationCount==car.getDominationCount())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.objMake+" - "+this.objModel+" - "+this.year+" - Mileage:"+this.mileage+" - $"+this.price;
    }

    public static void main(String[] args) {
    	List<Car> carList = new ArrayList<>();
    	carList.add(new Car("Porsche", "Kayene", 2019, 20, 50000));
        carList.add(new Car("Bugatti", "Veyron", 2016, 25, 60000));
        carList.add(new Car("Aston-Martin", "XZ", 2015,22 , 75000));
        carList.add(new Car("Audi", "Q7", 2021, 18, 96000));
    	carList.forEach((Car car) -> car.setDominationCount(carList));

       
        Integer avgPrice = carList.stream().parallel().map((Car car) -> car.getPrice())
                .reduce(0, (result,carPrice) -> result+carPrice, (finalResult, intermediateResult) -> finalResult)/carList.size();
        System.out.println("Average price of all Cars: $" + avgPrice);
        
        Integer carMakerNum = carList.stream().parallel().map( (Car car)-> car.getstrMake() )
                .reduce(0,(result,carMake)-> ++result,
                        (finalResult,intermediateResult)-> finalResult + intermediateResult);
        System.out.println("Total number of Car Makers: "+carMakerNum);

        Integer carModelNum = carList.stream().parallel().map( (Car car)-> car.getstrModel() )
                .reduce(0,(result,carModel)-> ++result,
                        (finalResult,intermediateResult)-> finalResult + intermediateResult);
        System.out.println("Total number of Car Models: "+carModelNum);
        
    }

}