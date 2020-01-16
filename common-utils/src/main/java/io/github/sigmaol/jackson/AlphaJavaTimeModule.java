package io.github.sigmaol.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.github.sigmaol.help.DateTimeHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * java 8 时间默认序列化
 */
public class AlphaJavaTimeModule extends SimpleModule {

	public AlphaJavaTimeModule() {
		super(PackageVersion.VERSION);
		this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeHelper.DATETIME_FORMAT));
		this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeHelper.DATE_FORMAT));
		this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeHelper.TIME_FORMAT));
		this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeHelper.DATETIME_FORMAT));
		this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeHelper.DATE_FORMAT));
		this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeHelper.TIME_FORMAT));
	}

}
