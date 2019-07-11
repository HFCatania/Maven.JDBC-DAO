package daos;

import com.mysql.cj.xdevapi.SqlDataResult;
import models.Car;
import models.ConnectionFactory;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
public class carConcreteDAO implements CarDAO {
    private Connection connection;
    private Statement stmt;
    private ResultSet results;

    public carConcreteDAO(){
        connection = ConnectionFactory.getConnection();
    }

    private Car extractCarFromResultSet(ResultSet results) throws SQLException {
        Car car = new Car();

        car.setId(results.getInt("id"));
        car.setMake(results.getString("make"));
        car.setModel(results.getString("model"));
        car.setYear(results.getInt("year"));
        car.setColor(results.getString("color"));
        car.setVin(results.getInt("vin"));

        return car;
    }

    public Car getCarById(int id) {
            try{
                stmt = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM car_table WHERE id = ?");
                ps.setInt(1, id);

                if(results.next()){
                    return extractCarFromResultSet(results);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        return null;
    }

    public List<Car> getAllCars() {
        try{
            Statement stmt = connection.createStatement();
            results = stmt.executeQuery("SELECT * FROM car_table");

            List cars = new ArrayList();

            while(results.next()){
                Car car = extractCarFromResultSet(results);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean createCar(Car car) {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO car_table VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, car.getId());
            ps.setString(2, car.getMake());
            ps.setString(3, car.getModel());
            ps.setInt(4, car.getYear());
            ps.setString(5, car.getColor());
            ps.setInt(6, car.getVin());
            int i = ps.executeUpdate();

            if(i == 1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("Duplicates")
    public boolean updateCar(Car car) {
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE car SET id=?, make=?, model=?, year=?, color=?, vin=?");
            ps.setInt(1, car.getId());
            ps.setString(2, car.getMake());
            ps.setString(3, car.getModel());
            ps.setInt(4, car.getYear());
            ps.setString(5, car.getColor());
            ps.setInt(6, car.getVin());
            int i = ps.executeUpdate();

            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCar(Car car) {
        try{
            stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM car_table WHERE id=?");
            ps.setInt(1, car.getId());
            int i = ps.executeUpdate();
            if(i == 1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
