package pl.trans.app.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.WorkerDao;
import pl.trans.app.database.models.Worker;
import pl.trans.app.utils.converters.ConverterWorker;

import java.util.ArrayList;
import java.util.List;

public class ListWorkerModel {

    private ObservableList<WorkerFx> workerFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<WorkerFx> workerFxObjectPropertyEdit = new SimpleObjectProperty<>(new WorkerFx());

    public List<WorkerFx> workerFxList = new ArrayList<>();

    public void init() throws ApplicationException{
        WorkerDao workerDao = new WorkerDao();
        List<Worker> workers = workerDao.queryForAll(Worker.class);
        workerFxList.clear();
        workers.forEach(worker -> {
            this.workerFxList.add(ConverterWorker.converterToWorkerFx(worker));
        });
        this.workerFxObservableList.setAll(workerFxList);
    }

    public void saveWorkerEditInDataBase(){
        WorkerDao workerDao = new WorkerDao();
        Worker worker = ConverterWorker.converterToWorker(this.getWorkerFxObjectPropertyEdit());
        try {
            workerDao.creatOrUpdate(worker);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public void deleteWorker(WorkerFx workerFx) throws ApplicationException{
        WorkerDao workerDao = new WorkerDao();
        workerDao.deleteById(Worker.class, workerFx.getId());
        init();
    }

    public ObservableList<WorkerFx> getWorkerFxObservableList() {
        return workerFxObservableList;
    }

    public void setWorkerFxObservableList(ObservableList<WorkerFx> workerFxObservableList) {
        this.workerFxObservableList = workerFxObservableList;
    }

    public WorkerFx getWorkerFxObjectPropertyEdit() {
        return workerFxObjectPropertyEdit.get();
    }

    public ObjectProperty<WorkerFx> workerFxObjectPropertyEditProperty() {
        return workerFxObjectPropertyEdit;
    }

    public void setWorkerFxObjectPropertyEdit(WorkerFx workerFxObjectPropertyEdit) {
        this.workerFxObjectPropertyEdit.set(workerFxObjectPropertyEdit);
    }

    public List<WorkerFx> getWorkerFxList() {
        return workerFxList;
    }

    public void setWorkerFxList(List<WorkerFx> workerFxList) {
        this.workerFxList = workerFxList;
    }
}
