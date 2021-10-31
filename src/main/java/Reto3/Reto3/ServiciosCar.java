/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosCar {
     @Autowired
    private RepositorioCar metodosCrud;

    public List<Car> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Car> getCar(int carId) {
        return metodosCrud.getCar(carId);
    }

    public Car save(Car car){
        if(car.getId()==null){
            return metodosCrud.save(car);
        }else{
            Optional<Car> e=metodosCrud.getCar(car.getId());
            if(e.isEmpty()){
                return metodosCrud.save(car);
            }else{
                return car;
            }
        }
    }

    public Car update(Car car){
        if(car.getId()!=null){
            Optional<Car> e=metodosCrud.getCar(car.getId());
            if(!e.isEmpty()){
                if(car.getName()!=null){
                    e.get().setName(car.getName());
                }
                if(car.getBrand()!=null){
                    e.get().setBrand(car.getBrand());
                }
                if(car.getYear()!=null){
                    e.get().setYear(car.getYear());
                }
                if(car.getDescription()!=null){
                    e.get().setDescription(car.getDescription());
                }
                if(car.getCategory()!=null){
                    e.get().setCategory(car.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return car;
            }
        }else{
            return car;
        }
    }


    public boolean deleteCar(int carId) {
        Boolean aBoolean = getCar(carId).map(car -> {
            metodosCrud.delete(car);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
