package eu.miaplatform.dbsqlreader.util;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonSqlDateSerializer extends JsonSerializer<Date> {

	// CustomDateSerializer class
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone(SqlDateManager.DEFAULT_TIMEZONE));
		String formattedDate = formatter.format(value);

		gen.writeString(formattedDate);

	}

}
