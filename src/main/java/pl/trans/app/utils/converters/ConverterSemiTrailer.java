package pl.trans.app.utils.converters;

import pl.trans.app.database.models.SemiTrailer;
import pl.trans.app.modelFx.SemiTrailerFx;
import pl.trans.app.utils.Utils;

public class ConverterSemiTrailer {

    public static SemiTrailer converterToSemiTrailer(SemiTrailerFx semiTrailerFx){
        SemiTrailer semiTrailer = new SemiTrailer();
        semiTrailer.setId(semiTrailerFx.getId());
        semiTrailer.setMark(semiTrailerFx.getMark());
        semiTrailer.setModel(semiTrailerFx.getModel());
        semiTrailer.setCategory(semiTrailerFx.getCategory());
        semiTrailer.setYear(Utils.converterToDate(semiTrailerFx.getYear()));
        semiTrailer.setRoad(semiTrailerFx.getRoad());
        semiTrailer.setPermissibleMass(semiTrailerFx.getPermissibleMass());
        semiTrailer.setNumberOfAxles(semiTrailerFx.getNumberOfAxles());
        semiTrailer.setMass(semiTrailerFx.getMass());
        semiTrailer.setWidth(semiTrailerFx.getWidth());
        semiTrailer.setLenght(semiTrailerFx.getLength());
        semiTrailer.setHeight(semiTrailerFx.getHeight());
        semiTrailer.setSaddleLoad(semiTrailerFx.getSaddleLoad());
        semiTrailer.setAxleLoad(semiTrailerFx.getAxleLoad());

        return semiTrailer;
    }

    public static SemiTrailerFx converterToSemiTrailerFx(SemiTrailer semiTrailer){
        SemiTrailerFx semiTrailerFx = new SemiTrailerFx();
        semiTrailerFx.setId(semiTrailer.getId());
        semiTrailerFx.setMark(semiTrailer.getMark());
        semiTrailerFx.setModel(semiTrailer.getModel());
        semiTrailerFx.setCategory(semiTrailer.getCategory());
        semiTrailerFx.setYear(Utils.converterToLocalDate(semiTrailer.getYear()));
        semiTrailerFx.setRoad(semiTrailer.getRoad());
        semiTrailerFx.setPermissibleMass(semiTrailer.getPermissibleMass());
        semiTrailerFx.setNumberOfAxles(semiTrailer.getNumberOfAxles());
        semiTrailerFx.setMass(semiTrailer.getMass());
        semiTrailerFx.setWidth(semiTrailer.getWidth());
        semiTrailerFx.setLength(semiTrailer.getLenght());
        semiTrailerFx.setHeight(semiTrailer.getHeight());
        semiTrailerFx.setSaddleLoad(semiTrailer.getSaddleLoad());
        semiTrailerFx.setAxleLoad(semiTrailer.getAxleLoad());

        return semiTrailerFx;
    }
}
