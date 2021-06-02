package TablaHash1;

public class Persona {

    private String codigo;
    private String nombre;
    private int edad;

    public Persona(String vCodigo, String nombre, int edad) {
        this.codigo = vCodigo;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String elCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void datos() {
        System.out.println("\n Persona: " + this.codigo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Edad: " + this.edad);
    }

}
