/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.manzerlacaz.web;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import group.acensi.manzerlacaz.service.DateTimeConstants;
import group.acensi.manzerlacaz.web.configuration.WebConfig;


/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@SpringBootTest(classes = {WebConfig.class, JacksonAutoConfiguration.class})
class ObjectMapperTests {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void testSerializer() throws JsonProcessingException {
		String testDate = "2022-09-13T14:48:43.331+04:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeConstants.DATE_TIME_FORMAT);
		ZonedDateTime zdtWithZoneOffset = ZonedDateTime.parse(testDate, formatter);
		TestPojoSerialization testPojoSerialization = new TestPojoSerialization("Test", zdtWithZoneOffset);
		Assert.assertEquals("{\"name\":\"Test\",\"dateTime\":\""+testDate+"\"}", this.objectMapper.writeValueAsString(testPojoSerialization));
	}
	
	private record TestPojoSerialization(String name, ZonedDateTime dateTime) {}

}
