package daos;
import models.Car;

import java.util.List;
import java.util.Set;

public interface CarDAO {
    Car getCarById(int id);
    List<Car> getAllCars();
    boolean createCar(Car car);
    boolean updateCar(Car car);
    boolean deleteCar(Car car);
}
