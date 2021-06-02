package TablaHash1;

public class TablaHash {

    static final int TAMTABLA = 80;
    static final double R = 0.618034;
    private int numElementos;
    private double factorCarga;
    private Persona[] tabla;

    public TablaHash() {
        tabla = new Persona[TAMTABLA];
        for (int j = 0; j < TAMTABLA; j++) {
            tabla[j] = null;
        }
        numElementos = 0;
        factorCarga = 0.0;
    }


    public int ValorHash(Persona persona) {
        int i = 0, p;
        long d;
        d = transformaCadena(persona.elCodigo());

        p = dispersion(d);

        System.out.println( p + ", Codigo: " + persona.elCodigo() + ", Nombre: " + persona.getNombre()
                + ", Edad: " + persona.getEdad());

        while (tabla[p] != null && !tabla[p].elCodigo().equals(persona.elCodigo())) {
            i++;
            p = p + i * i;   
            p = p % TAMTABLA; // considera el array como circular
            System.out.println( "*" + p + ", Codigo: " + persona.elCodigo() + ", Nombre: " + persona.getNombre()
                    + ", Edad: " + persona.getEdad());
        }

        return p;
    }

    long transformaCadena(String c) {
        long d;
        d = 0;
        for (int j = 0; j < Math.min(10, c.length()); j++) {
            d = d * 27 + (int) c.charAt(j);
        }
        if (d < 0) {
            d = -d;
        }
        return d;
    }

    static int dispersion(long x)
    {
        double t;
        int v;
        t = R * x - Math.floor(R * x); // parte decimal
        v = (int) (TAMTABLA * t);
        return v;
    }

    public void insertar(Persona persona) {
        int posicion;
        posicion = ValorHash(persona);

        tabla[posicion] = persona;
        numElementos++;
    }

    public Persona buscar(String clave) {
        Persona pr;
        int posicion;
        posicion = dispersion(transformaCadena(clave));
        pr = tabla[posicion];
        return pr;
    }

    public void eliminar(String clave) {
        int posicion;
        posicion = dispersion(transformaCadena(clave));
        tabla[posicion] = null;
    }

    public void Listar() {
        int i = 0;
        while(i < TAMTABLA) {
            if(tabla[i] != null) {
                int j = 0, p;
                long d;
                String texto = "";
                d = transformaCadena(tabla[i].elCodigo());
                p = dispersion(d);

                texto = p + ", Codigo: " + tabla[i].elCodigo();

                while (tabla[p] != null && !tabla[p].elCodigo().equals(tabla[i].elCodigo())) {
                    j++;
                    texto = texto + " --> " + tabla[p].elCodigo() ;
                    p = p + j * j;
                    p = p % TAMTABLA; // considera el array como circular
                }

                texto = texto + ", Nombre: " + tabla[i].getNombre()
                        + ", Edad: " + tabla[i].getEdad();

                System.out.println(texto);
            }
            i++;
        }
    }

}
