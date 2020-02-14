package pl.trans.app.utils.converters;

import pl.trans.app.database.models.Worker;
import pl.trans.app.modelFx.WorkerFx;
import pl.trans.app.utils.Utils;

public class ConverterWorker {

    public static Worker converterToWorker(WorkerFx workerFx){
        Worker worker = new Worker();
        worker.setId(workerFx.getId());
        worker.setName(workerFx.getName());
        worker.setSurname(workerFx.getSurname());
        worker.setWorkplace(workerFx.getWorkplace());
        worker.setDateOfEmployment(Utils.converterToDate(workerFx.getDateOfEmployment()));
        worker.setPosition(workerFx.getPosition());
        worker.setPayment(workerFx.getPayment());
        worker.setAddress(workerFx.getAdress());
        worker.setEducation(workerFx.getEducation());
        worker.setPesel(workerFx.getPesel());
        worker.setDateBirthday(Utils.converterToDate(workerFx.getDateBirthday()));
        return worker;
    }

    public static WorkerFx converterToWorkerFx(Worker worker){
        WorkerFx workerFx = new WorkerFx();
        workerFx.setId(worker.getId());
        workerFx.setName(worker.getName());
        workerFx.setSurname(worker.getSurname());
        workerFx.setWorkplace(worker.getWorkplace());
        workerFx.setDateOfEmployment(Utils.converterToLocalDate(worker.getDateOfEmployment()));
        workerFx.setPosition(worker.getPosition());
        workerFx.setPayment(worker.getPayment());
        workerFx.setAdress(worker.getAddress());
        workerFx.setEducation(worker.getEducation());
        workerFx.setPesel(worker.getPesel());
        workerFx.setDateBirthday(Utils.converterToLocalDate(worker.getDateBirthday()));
        return workerFx;
    }
}
