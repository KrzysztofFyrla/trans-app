package pl.trans.app.utils.fillDatabase;

import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.WorkerDao;
import pl.trans.app.database.dbutils.DbManager;
import pl.trans.app.database.models.Worker;

import java.util.Date;

public class FillDatabaseWorker {

    public static void fillDatabase(){

        Worker worker = new Worker();
        worker.setName("Marcin");
        worker.setSurname("Dudkowski");
        worker.setWorkplace("Koszęcin");
        worker.setDateOfEmployment(new Date());
        worker.setPosition("Kierowca");
        worker.setPayment("2500");
        worker.setAddress("Korfantego");
        worker.setEducation("magister");
        worker.setPesel("967373636363");
        worker.setDateBirthday(new Date());

        Worker worker1 = new Worker();
        worker1.setName("Damiam");
        worker1.setSurname("Lubomirski");
        worker1.setWorkplace("Koszęcin");
        worker1.setDateOfEmployment(new Date());
        worker1.setPosition("Kierowca");
        worker1.setPayment("2500");
        worker1.setAddress("Korfantego");
        worker1.setEducation("magister");
        worker1.setPesel("967373636363");
        worker1.setDateBirthday(new Date());

        Worker worker2 = new Worker();
        worker2.setName("Karol");
        worker2.setSurname("Buna");
        worker2.setWorkplace("Tarnowskie Góry");
        worker2.setDateOfEmployment(new Date());
        worker2.setPosition("Kierownik");
        worker2.setPayment("4000");
        worker2.setAddress("Korfantego");
        worker2.setEducation("magister");
        worker2.setPesel("967373636363");
        worker2.setDateBirthday(new Date());

        Worker worker3 = new Worker();
        worker3.setName("Martyna");
        worker3.setSurname("Puławska");
        worker3.setWorkplace("Tarnowskie Góry");
        worker3.setDateOfEmployment(new Date());
        worker3.setPosition("Kierownik");
        worker3.setPayment("3500");
        worker3.setAddress("Korfantego");
        worker3.setEducation("magister");
        worker3.setPesel("967373636363");
        worker3.setDateBirthday(new Date());

        Worker worker4 = new Worker();
        worker4.setName("Mirosław");
        worker4.setSurname("Fryn");
        worker4.setWorkplace("Koszęcin");
        worker4.setDateOfEmployment(new Date());
        worker4.setPosition("Prezez");
        worker4.setPayment("8500");
        worker4.setAddress("Korfantego");
        worker4.setEducation("magister");
        worker4.setPesel("967373636363");
        worker4.setDateBirthday(new Date());

        Worker worker5 = new Worker();
        worker5.setName("Ada");
        worker5.setSurname("Duda");
        worker5.setWorkplace("Koszęcin");
        worker5.setDateOfEmployment(new Date());
        worker5.setPosition("Kierowca");
        worker5.setPayment("3500");
        worker5.setAddress("Korfantego");
        worker5.setEducation("magister");
        worker5.setPesel("967373636363");
        worker5.setDateBirthday(new Date());

        Worker worker6 = new Worker();
        worker6.setName("Stanisław");
        worker6.setSurname("Waś");
        worker6.setWorkplace("Tworóg");
        worker6.setDateOfEmployment(new Date());
        worker6.setPosition("Kierowca");
        worker6.setPayment("3500");
        worker6.setAddress("Korfantego");
        worker6.setEducation("magister");
        worker6.setPesel("967373636363");
        worker6.setDateBirthday(new Date());

        WorkerDao workerDao = new WorkerDao();
        try {
            workerDao.creatOrUpdate(worker);
            workerDao.creatOrUpdate(worker1);
            workerDao.creatOrUpdate(worker2);
            workerDao.creatOrUpdate(worker3);
            workerDao.creatOrUpdate(worker4);
            workerDao.creatOrUpdate(worker5);
            workerDao.creatOrUpdate(worker6);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();
    }
}
