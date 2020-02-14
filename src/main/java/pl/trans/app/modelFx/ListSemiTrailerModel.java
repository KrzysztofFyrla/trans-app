package pl.trans.app.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dao.SemiTrailerDao;
import pl.trans.app.database.models.SemiTrailer;
import pl.trans.app.utils.DialogUtils;
import pl.trans.app.utils.converters.ConverterSemiTrailer;

import java.util.ArrayList;
import java.util.List;

public class ListSemiTrailerModel {

    private ObservableList<SemiTrailerFx> semiTrailerFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<SemiTrailerFx> semiTrailerFxObjectPropertyEdit = new SimpleObjectProperty<>(new SemiTrailerFx());

    public List<SemiTrailerFx> semiTrailerFxList = new ArrayList<>();

    public void init() throws org.omg.CORBA.portable.ApplicationException {
        SemiTrailerDao semiTrailerDao = new SemiTrailerDao();
        List<SemiTrailer> semiTrailers = semiTrailerDao.queryForAll(SemiTrailer.class);
        semiTrailerFxList.clear();
        semiTrailers.forEach(semiTrailer -> {
            this.semiTrailerFxList.add(ConverterSemiTrailer.converterToSemiTrailerFx(semiTrailer));
        });
        this.semiTrailerFxObservableList.setAll(semiTrailerFxList);
    }

    public void saveSemiTrailerEditInDataBase() {
        SemiTrailerDao semiTrailerDao = new SemiTrailerDao();
        SemiTrailer semiTrailer = ConverterSemiTrailer.converterToSemiTrailer(this.getSemiTrailerFxObjectPropertyEdit());
        try {
            semiTrailerDao.creatOrUpdate(semiTrailer);
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void deleteSemiTrailer(SemiTrailerFx semiTrailerFx){
        SemiTrailerDao semiTrailerDao = new SemiTrailerDao();
        try {
            semiTrailerDao.deleteById(SemiTrailer.class, semiTrailerFx.getId());
            init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<SemiTrailerFx> getSemiTrailerFxObservableList() {
        return semiTrailerFxObservableList;
    }

    public void setSemiTrailerFxObservableList(ObservableList<SemiTrailerFx> semiTrailerFxObservableList) {
        this.semiTrailerFxObservableList = semiTrailerFxObservableList;
    }

    public SemiTrailerFx getSemiTrailerFxObjectPropertyEdit() {
        return semiTrailerFxObjectPropertyEdit.get();
    }

    public ObjectProperty<SemiTrailerFx> semiTrailerFxObjectPropertyEditProperty() {
        return semiTrailerFxObjectPropertyEdit;
    }

    public void setSemiTrailerFxObjectPropertyEdit(SemiTrailerFx semiTrailerFxObjectPropertyEdit) {
        this.semiTrailerFxObjectPropertyEdit.set(semiTrailerFxObjectPropertyEdit);
    }

    public List<SemiTrailerFx> getSemiTrailerFxList() {
        return semiTrailerFxList;
    }

    public void setSemiTrailerFxList(List<SemiTrailerFx> semiTrailerFxList) {
        this.semiTrailerFxList = semiTrailerFxList;
    }
}
