package pl.trans.app.utils.fillDatabase;

import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.SemiTrailerDao;
import pl.trans.app.database.dbutils.DbManager;
import pl.trans.app.database.models.SemiTrailer;

import java.util.Date;

public class FillDatabaseSemiTrailer {

    public static void fillDatabase(){

        SemiTrailer semiTrailer = new SemiTrailer();
        semiTrailer.setMark("Test");
        semiTrailer.setModel("Test");
        semiTrailer.setCategory("Test");
        semiTrailer.setYear(new Date());
        semiTrailer.setRoad("Test");
        semiTrailer.setPermissibleMass("123");
        semiTrailer.setNumberOfAxles("123");
        semiTrailer.setMass("123");
        semiTrailer.setWidth("123");
        semiTrailer.setLenght("123");
        semiTrailer.setHeight("123");
        semiTrailer.setSaddleLoad("123");
        semiTrailer.setAxleLoad("123");

        SemiTrailer semiTrailer2 = new SemiTrailer();
        semiTrailer2.setMark("Test");
        semiTrailer2.setModel("Test");
        semiTrailer2.setCategory("Test");
        semiTrailer2.setYear(new Date());
        semiTrailer2.setRoad("Test");
        semiTrailer2.setPermissibleMass("123");
        semiTrailer2.setNumberOfAxles("123");
        semiTrailer2.setMass("123");
        semiTrailer2.setWidth("123");
        semiTrailer2.setLenght("123");
        semiTrailer2.setHeight("123");
        semiTrailer2.setSaddleLoad("123");
        semiTrailer2.setAxleLoad("123");

        SemiTrailerDao semiTrailerDao = new SemiTrailerDao();
        try {
            semiTrailerDao.creatOrUpdate(semiTrailer);
            semiTrailerDao.creatOrUpdate(semiTrailer2);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();
    }
}
