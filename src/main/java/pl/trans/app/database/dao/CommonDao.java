package pl.trans.app.database.dao;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.omg.CORBA.portable.ApplicationException;
import pl.trans.app.database.dbutils.DbManager;
import pl.trans.app.database.models.BaseModel;
import pl.trans.app.utils.FxmlUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommonDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> void creatOrUpdate(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
    }


    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
        return null;
    }


    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
        return null;
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        }
        return null;
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws ApplicationException {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }
}
