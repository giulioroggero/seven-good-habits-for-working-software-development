package eu.miaplatform.dbsqlreader.service;


import eu.miaplatform.dbsqlreader.dao.ReaderDao;
import eu.miaplatform.dbsqlreader.resultset.ReaderResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReaderQueryService {
    final static Logger logger = LoggerFactory.getLogger(ReaderQueryService.class);

    @Autowired
    ReaderDao readerDao;
      
    public ReaderResultSet find() {
        ReaderResultSet result = new ReaderResultSet();
        String sql = "SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY FROM HR.JOBS";
        result.setSql(sql);

        try {
            result.setList(readerDao.find(sql));
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setError(Boolean.TRUE);
            String msg = (ex.getMessage() == null) ? Arrays.toString(ex.getStackTrace()) : ex.getMessage();
            result.setErrorMsg(msg);
        }
        return result;
    }
}
