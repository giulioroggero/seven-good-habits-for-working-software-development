/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.miaplatform.dbsqlreader.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 *
 * @author hadoop
 */
public interface ReaderDao {

	List<Map<String, Object>> find(String sql) throws SQLException;

}