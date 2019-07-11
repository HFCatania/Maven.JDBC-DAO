import daos.CarDAO;
import daos.carConcreteDAO;
import models.Car;

import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        carConcreteDAO carDAO = new carConcreteDAO();

        ArrayList<Car> cars = (ArrayList<Car>) carDAO.getAllCars();
        listPrinter(cars);

        System.out.println(carDAO.getCarById(1));

        System.out.println("\n" + "Car List Size = " + cars.size() + "\n");

        Car testcar = new Car(72, "dodge", "charger", 2014, "yellow", 513956);
        carDAO.createCar(testcar);

        cars = (ArrayList<Car>) carDAO.getAllCars();

        System.out.println("Car List Size = " + cars.size());

        carPrint(carDAO.getCarById(3));

        testcar.setYear(2001);
        testcar.setModel("caravan");

        carDAO.updateCar(testcar);

        listPrinter(cars);

        carDAO.deleteCar(testcar);

        listPrinter(cars);
    }

    public static void listPrinter(ArrayList<Car> cars){
        for (int i = 0; i < cars.size(); i++) {
            carPrint(cars.get(i));
        }
    }

    public static void carPrint(Car car){
        System.out.println("*******************" +
                            "\n* Id: " + car.getId() +
                            "\n* Make: " + car.getMake() +
                            "\n* Model: " + car.getModel() +
                            "\n* Year: " + car.getYear() +
                            "\n* Color: " + car.getColor() +
                            "\n* Vin: " + car.getVin() +
                            "\n*******************"
                            );
    }
}

