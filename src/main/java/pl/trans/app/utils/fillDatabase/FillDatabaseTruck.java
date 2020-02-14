package pl.trans.app.utils.fillDatabase;

import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.TruckDao;
import pl.trans.app.database.dbutils.DbManager;
import pl.trans.app.database.models.Truck;

import java.util.Date;

public class FillDatabaseTruck {

    public static void fillDatabase(){

        Truck truck = new Truck();
        truck.setBrand("Scania");
        truck.setModel("Topline");
        truck.setPower("125");
        truck.setYear(new Date());
        truck.setMileage("182098");
        truck.setRoad("Tak");
        /*NOWO DODANE:*/
        truck.setBody("Nadwozie");
        truck.setPack("Pakiet");
        truck.setCapacity("123");
        truck.setLenght("123");
        truck.setWidth("123");
        truck.setTransmission("Skrzynia");
        truck.setFuel("Paliwo");

        Truck truck2 = new Truck();
        truck2.setBrand("Scania");
        truck2.setModel("Topline");
        truck2.setPower("360");
        truck2.setYear(new Date());
        truck2.setMileage("738921");
        truck2.setRoad("Tak");
        truck2.setBody("Nadwozie");
        truck2.setPack("Pakiet");
        truck2.setCapacity("123");
        truck2.setLenght("123");
        truck2.setWidth("123");
        truck2.setTransmission("Skrzynia");
        truck2.setFuel("Paliwo");

        Truck truck3 = new Truck();
        truck3.setBrand("Man");
        truck3.setModel("TGX");
        truck3.setPower("380");
        truck3.setYear(new Date());
        truck3.setMileage("321921");
        truck3.setRoad("Tak");
        truck3.setBody("Nadwozie");
        truck3.setPack("Pakiet");
        truck3.setCapacity("123");
        truck3.setLenght("123");
        truck3.setWidth("123");
        truck3.setTransmission("Skrzynia");
        truck3.setFuel("Paliwo");

        Truck truck4 = new Truck();
        truck4.setBrand("Man");
        truck4.setModel("TGS");
        truck4.setPower("360");
        truck4.setYear(new Date());
        truck4.setMileage("328921");
        truck4.setRoad("Tak");
        truck4.setBody("Nadwozie");
        truck4.setPack("Pakiet");
        truck4.setCapacity("123");
        truck4.setLenght("123");
        truck4.setWidth("123");
        truck4.setTransmission("Skrzynia");
        truck4.setFuel("Paliwo");

        Truck truck5 = new Truck();
        truck5.setBrand("Volvo");
        truck5.setModel("FH16");
        truck5.setPower("360");
        truck5.setYear(new Date());
        truck5.setMileage("328921");
        truck5.setRoad("Nie");
        truck5.setBody("Nadwozie");
        truck5.setPack("Pakiet");
        truck5.setCapacity("123");
        truck5.setLenght("123");
        truck5.setWidth("123");
        truck5.setTransmission("Skrzynia");
        truck5.setFuel("Paliwo");

        Truck truck6 = new Truck();
        truck6.setBrand("Man");
        truck6.setModel("FH");
        truck6.setPower("360");
        truck6.setYear(new Date());
        truck6.setMileage("328921");
        truck6.setRoad("Tak");
        truck6.setBody("Nadwozie");
        truck6.setPack("Pakiet");
        truck6.setCapacity("123");
        truck6.setLenght("123");
        truck6.setWidth("123");
        truck6.setTransmission("Skrzynia");
        truck6.setFuel("Paliwo");

        TruckDao truckDao = new TruckDao();
        try {
            truckDao.creatOrUpdate(truck);
            truckDao.creatOrUpdate(truck2);
            truckDao.creatOrUpdate(truck3);
            truckDao.creatOrUpdate(truck4);
            truckDao.creatOrUpdate(truck5);
            truckDao.creatOrUpdate(truck6);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        DbManager.closeConnectionSource();
    }
}
