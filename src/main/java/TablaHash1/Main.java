package TablaHash1;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here

        TablaHash t = new TablaHash();

        for (int j = 0; j < 50; j++) {
            String Codigo;
            String Nombre;
            int Edad;

            Codigo = "FX-" + (100 + j) +"Y";
            Nombre = "Bob_" + j;
            Edad = (int) (10 + (Math.random() * 50));

            Persona persona = new Persona(Codigo, Nombre, Edad);
            Elemento elemento = new Elemento(persona);
            
            t.insertar(persona);
        }

        System.out.println("");

        t.Listar();

       /* Elemento elemento = t.buscar("FX-105Y");
        Persona persona = elemento.getPersona();

        persona.datos();*/
    }

}
