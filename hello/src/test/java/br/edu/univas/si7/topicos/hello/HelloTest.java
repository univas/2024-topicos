package br.edu.univas.si7.topicos.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.edu.univas.si7.topicos.hello.controller.HelloController;

public class HelloTest {
	
	HelloController controller = new HelloController();

	@Test
	public void testHello() {
		String resultado = controller.hello();
		assertEquals("Hello v1!", resultado);
	}
}
