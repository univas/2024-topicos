package br.edu.univas.si7.topicos.delivery.suporte;

public class IdNotFoundException {
	private String message;

    public IdNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }	
}
