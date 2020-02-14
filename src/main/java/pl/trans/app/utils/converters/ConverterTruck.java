package pl.trans.app.utils.converters;

import pl.trans.app.database.models.Truck;
import pl.trans.app.modelFx.TruckFx;
import pl.trans.app.utils.Utils;

public class ConverterTruck {

    public static Truck converterToTruck(TruckFx truckFx){
        Truck truck = new Truck();
        truck.setId(truckFx.getId());
        truck.setBrand(truckFx.getBrand());
        truck.setModel(truckFx.getModel());
        truck.setPower(truckFx.getPower());
        truck.setYear(Utils.converterToDate(truckFx.getYear()));
        truck.setMileage(truckFx.getMileage());
        truck.setRoad(truckFx.getRoad());
        truck.setBody(truckFx.getBody());
        truck.setPack(truckFx.getPack());
        truck.setCapacity(truckFx.getCapacity());
        truck.setLenght(truckFx.getLenght());
        truck.setWidth(truckFx.getWidth());
        truck.setTransmission(truckFx.getTransmission());
        truck.setFuel(truckFx.getFuel());

        return truck;
    }

    public static TruckFx converterToTruckFx(Truck truck){
        TruckFx truckFx = new TruckFx();
        truckFx.setId(truck.getId());
        truckFx.setBrand(truck.getBrand());
        truckFx.setModel(truck.getModel());
        truckFx.setPower(truck.getPower());
        truckFx.setYear(Utils.converterToLocalDate(truck.getYear()));
        truckFx.setMileage(truck.getMileage());
        truckFx.setRoad(truck.getRoad());
        truckFx.setBody(truck.getBody());
        truckFx.setPack(truck.getPack());
        truckFx.setCapacity(truck.getCapacity());
        truckFx.setLenght(truck.getLenght());
        truckFx.setWidth(truck.getWidth());
        truckFx.setTransmission(truck.getTransmission());
        truckFx.setFuel(truck.getFuel());

        return truckFx;
    }
}
