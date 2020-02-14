package pl.trans.app.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.TruckDao;
import pl.trans.app.database.dao.WorkerDao;
import pl.trans.app.database.models.Truck;
import pl.trans.app.database.models.Worker;
import pl.trans.app.utils.converters.ConverterTruck;

import java.util.ArrayList;
import java.util.List;

public class ListTruckModel {

    private ObservableList<TruckFx> truckFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<TruckFx> truckFxObjectPropertyEdit = new SimpleObjectProperty<>(new TruckFx());

    public List<TruckFx> truckFxList = new ArrayList<>();

    public void init() throws ApplicationException {
        TruckDao truckDao = new TruckDao();
        List<Truck> trucks = truckDao.queryForAll(Truck.class);
        truckFxList.clear();
        trucks.forEach(truck -> {
            this.truckFxList.add(ConverterTruck.converterToTruckFx(truck));
        });
        this.truckFxObservableList.setAll(truckFxList);
    }

    public void saveTruckEditDataBase(){
        TruckDao truckDao = new TruckDao();
        Truck truck = ConverterTruck.converterToTruck(this.getTruckFxObjectPropertyEdit());
        try {
            truckDao.creatOrUpdate(truck);
            this.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public void deleteTruck(TruckFx truckFx) throws ApplicationException{
        TruckDao truckDao = new TruckDao();
        truckDao.deleteById(Truck.class, truckFx.getId());
        init();
    }

    public ObservableList<TruckFx> getTruckFxObservableList() {
        return truckFxObservableList;
    }

    public void setTruckFxObservableList(ObservableList<TruckFx> truckFxObservableList) {
        this.truckFxObservableList = truckFxObservableList;
    }

    public TruckFx getTruckFxObjectPropertyEdit() {
        return truckFxObjectPropertyEdit.get();
    }

    public ObjectProperty<TruckFx> truckFxObjectPropertyEditProperty() {
        return truckFxObjectPropertyEdit;
    }

    public void setTruckFxObjectPropertyEdit(TruckFx truckFxObjectPropertyEdit) {
        this.truckFxObjectPropertyEdit.set(truckFxObjectPropertyEdit);
    }

    public List<TruckFx> getTruckFxList() {
        return truckFxList;
    }

    public void setTruckFxList(List<TruckFx> truckFxList) {
        this.truckFxList = truckFxList;
    }
}
