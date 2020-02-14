package pl.trans.app.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.WorkerDao;
import pl.trans.app.database.models.Worker;
import pl.trans.app.utils.converters.ConverterWorker;

public class WorkerModel {

    private ObjectProperty<WorkerFx> workerFxObjectProperty = new SimpleObjectProperty<>(new WorkerFx());

    public void init(){

    }

    public void saveWorkerInDataBase() {
        Worker worker = ConverterWorker.converterToWorker(this.getWorkerFxObjectProperty());
        WorkerDao workerDao = new WorkerDao();
        try {
            workerDao.creatOrUpdate(worker);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }


    }

    public WorkerFx getWorkerFxObjectProperty() {
        return workerFxObjectProperty.get();
    }

    public ObjectProperty<WorkerFx> workerFxObjectPropertyProperty() {
        return workerFxObjectProperty;
    }

    public void setWorkerFxObjectProperty(WorkerFx workerFxObjectProperty) {
        this.workerFxObjectProperty.set(workerFxObjectProperty);
    }
}
