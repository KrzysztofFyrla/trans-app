package pl.trans.app.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.SemiTrailerDao;
import pl.trans.app.database.models.SemiTrailer;
import pl.trans.app.utils.converters.ConverterSemiTrailer;
import pl.trans.app.utils.converters.ConverterTruck;

public class SemiTrailerModel {

    private ObjectProperty<SemiTrailerFx> semiTrailerFxObjectProperty = new SimpleObjectProperty<>(new SemiTrailerFx());

    public void init() {

    }

    public void saveSemiTrailerInDataBase(){
        SemiTrailer semiTrailer = ConverterSemiTrailer.converterToSemiTrailer(this.getSemiTrailerFxObjectProperty());

        SemiTrailerDao semiTrailerDao = new SemiTrailerDao();
        try {
            semiTrailerDao.creatOrUpdate(semiTrailer);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public SemiTrailerFx getSemiTrailerFxObjectProperty() {
        return semiTrailerFxObjectProperty.get();
    }

    public ObjectProperty<SemiTrailerFx> semiTrailerFxObjectPropertyProperty() {
        return semiTrailerFxObjectProperty;
    }

    public void setSemiTrailerFxObjectProperty(SemiTrailerFx semiTrailerFxObjectProperty) {
        this.semiTrailerFxObjectProperty.set(semiTrailerFxObjectProperty);
    }
}
