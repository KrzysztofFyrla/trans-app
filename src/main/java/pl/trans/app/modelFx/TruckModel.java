package pl.trans.app.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.TruckDao;
import pl.trans.app.database.models.Truck;
import pl.trans.app.utils.converters.ConverterTruck;
import pl.trans.app.utils.converters.ConverterWorker;

import java.util.ArrayList;
import java.util.List;

public class TruckModel {

    private ObjectProperty<TruckFx> truckFxObjectProperty = new SimpleObjectProperty<>(new TruckFx());

    public void init() throws ApplicationException{

    }

    public void saveTruckInDataBase(){
        Truck truck = ConverterTruck.converterToTruck(this.getTruckFxObjectProperty());

        TruckDao truckDao = new TruckDao();
        try {
            truckDao.creatOrUpdate(truck);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public TruckFx getTruckFxObjectProperty() {
        return truckFxObjectProperty.get();
    }

    public ObjectProperty<TruckFx> truckFxObjectPropertyProperty() {
        return truckFxObjectProperty;
    }

    public void setTruckFxObjectProperty(TruckFx truckFxObjectProperty) {
        this.truckFxObjectProperty.set(truckFxObjectProperty);
    }
}
