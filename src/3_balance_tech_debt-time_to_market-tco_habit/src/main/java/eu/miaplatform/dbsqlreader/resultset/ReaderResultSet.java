package eu.miaplatform.dbsqlreader.resultset;

import java.util.List;
import java.util.Map;

public class ReaderResultSet {

	
	private List<Map<String, Object>> list;
	private Boolean error;
	private String errorMessage;
	private String sql;

	public ReaderResultSet() {
		this(null);
	}

	public ReaderResultSet(List<Map<String, Object>> list) {
		this.list = list;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public Boolean getError() {
		return this.error;
	}

	public void setErrorMsg(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMsg() {
		return this.errorMessage;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return this.sql;
	}

}
