package eu.miaplatform.dbsqlreader.controller;

import eu.miaplatform.dbsqlreader.resultset.ReaderResultSet;
import eu.miaplatform.dbsqlreader.service.ReaderQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/")
public class ReaderController {

    final static Logger logger = LoggerFactory.getLogger(ReaderController.class);

    @Autowired
    ReaderQueryService readerQueryService = new ReaderQueryService();

    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json")
    public ReaderResultSet getValuesGet(@RequestParam Map<String, String> params) throws IOException {
        ReaderResultSet result;
        result = readerQueryService.find();

        return result;
    }

}

