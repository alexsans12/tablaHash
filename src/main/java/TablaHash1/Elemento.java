package TablaHash1;

public class Elemento {
    Persona persona;
    Elemento sgte;
    public Elemento(Persona e)
    {
        this.persona = e;
        this.sgte = null;
    }
    public Persona getPersona()
    {
        return this.persona;
    }
}
