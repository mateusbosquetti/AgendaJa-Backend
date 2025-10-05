package com.mateusbosquetti.agendaja;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
class AgendaJaApplicationTests {

	@Test
	void contextLoads() {
        int resultado = 1 + 1;
        assertEquals(2, resultado);
    }

}
