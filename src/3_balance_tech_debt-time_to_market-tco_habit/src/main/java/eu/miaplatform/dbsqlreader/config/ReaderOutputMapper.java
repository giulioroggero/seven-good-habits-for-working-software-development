package eu.miaplatform.dbsqlreader.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReaderOutputMapper {

    private ObjectMapper mapper;
    private DateFormat dateFormat;

    ReaderOutputMapper(String stringDateFormat){
        this.mapper =  new ObjectMapper();
        this.dateFormat = new SimpleDateFormat(stringDateFormat);
        mapper.setDateFormat(this.dateFormat);

    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }
}
