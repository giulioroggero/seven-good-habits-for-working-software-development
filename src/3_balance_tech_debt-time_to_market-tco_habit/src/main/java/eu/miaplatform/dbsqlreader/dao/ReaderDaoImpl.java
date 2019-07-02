/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.miaplatform.dbsqlreader.dao;


// Oracle support
import oracle.jdbc.OracleConnection;
import java.sql.Connection;
import oracle.sql.TIMESTAMPTZ;

// MS SQL Support
import javax.sql.DataSource;

// Logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Connection Pool
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.nativejdbc.C3P0NativeJdbcExtractor;
import org.springframework.stereotype.Repository;

// Utils
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.util.stream.*;

/**
 *
 * @author Giulio Roggero
 */


@Repository
public class ReaderDaoImpl implements ReaderDao {
    
	final static Logger logger = LoggerFactory.getLogger(ReaderDaoImpl.class);

    protected DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Map<String, Object>> find(String sql) throws SQLException {
		logger.info("Executing {}", sql);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			Statement stm = connection.createStatement();
			list = _executeSqlQuery(stm, sql);
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		
		logger.info("SelectQuery found {} results", list.size());
		return list;
	}

	private List<Map<String, Object>> _executeSqlQuery(Statement stm, String sql)
			throws SQLException {
		ResultSet rs = stm.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		return _parseResult(rs, rsmd, stm.getConnection().unwrap(OracleConnection.class));
	}

	private List<Map<String, Object>> _parseResult(ResultSet rs, ResultSetMetaData rsmd, OracleConnection conn)
			throws SQLException {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		while (rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			int columnCount = rsmd.getColumnCount();

			// The column count starts from 1
			// the result is not sorted and getObject(label) doesn't works...
			for (int i = 1; i <= columnCount; i++) {
				String name = rsmd.getColumnName(i);
				Object obj = rs.getObject(i);
				if (Objects.nonNull(obj) && obj instanceof TIMESTAMPTZ) {
					obj = ((TIMESTAMPTZ)obj).dateValue(conn);
				}
				m.put(name.toLowerCase(), obj);
			}
			list.add(m);
		}

		return list;
	}
}
