package edu.csus.csc131.euc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Example taken from https://mkyong.com/java/jackson-how-to-parse-json/
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class StaffTest {
	private static String tempFile = "staff.json";

	@Test
	@Order(1)
	public void objectToJson() {
		ObjectMapper mapper = new ObjectMapper();
		Staff staff = createStaff();

		assertDoesNotThrow(() -> {
			// Java objects to JSON file
			mapper.writeValue(new File(tempFile), staff);

			// Java objects to JSON string - compact-print
			String jsonString = mapper.writeValueAsString(staff);
			// System.out.println(jsonString);
			assertTrue(jsonString.length() > 0);

		});
	}

	@Test
	@Order(2)
	public void jsonToObject() {
		ObjectMapper mapper = new ObjectMapper();
		Staff staff = createStaff();

		assertDoesNotThrow(() -> {
			// JSON file to Java object
			Staff staff1 = mapper.readValue(new File(tempFile), Staff.class);
			// System.out.println(staff1);
			assertEquals(staff, staff1);

			// JSON string to Java object
			String jsonInString = "{\"name\":\"mkyong\",\"age\":38,\"position\":[\"Founder\",\"CTO\",\"Writer\"],\"skills\":[\"java\",\"python\",\"node\",\"kotlin\"],\"salary\":{\"2018\":14000,\"2012\":12000,\"2010\":10000}}";
			Staff staff2 = mapper.readValue(jsonInString, Staff.class);
			// compact print
			// System.out.println(staff2);
			assertEquals(staff, staff2);
		});
	}

	@AfterAll
	static void tearDownAll() {
		File file = new File(tempFile);

		if (!file.delete()) {
			System.out.println("Failed to delete the file: " + tempFile);
		}
	}

	private static Staff createStaff() {
		Staff staff = new Staff();

		staff.setName("mkyong");
		staff.setAge(38);
		staff.setPosition(new String[] { "Founder", "CTO", "Writer" });

		Map<String, BigDecimal> salary = new HashMap<String, BigDecimal>();
		salary.put("2010", new BigDecimal(10000));
		salary.put("2012", new BigDecimal(12000));
		salary.put("2018", new BigDecimal(14000));
		staff.setSalary(salary);

		staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

		return staff;
	}
}
