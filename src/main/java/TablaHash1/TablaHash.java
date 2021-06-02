package TablaHash1;

public class TablaHash {

    static final int TAMTABLA = 80;
    static final double R = 0.618034;
    private int numElementos;
    private Elemento[] tabla;

    public TablaHash() {
        tabla = new Elemento[TAMTABLA];

        for (int j = 0; j < TAMTABLA; j++)
            tabla[j] = null;

        numElementos = 0;
    }

    /*public int ValorHash(Persona persona) {
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
    }*/

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

    public void insertar(Persona persona)
    {
        int posicion, i=0;
        Elemento nuevo;
        posicion = dispersion(transformaCadena(persona.elCodigo()));

        System.out.println( posicion + ", Codigo: " + persona.elCodigo() +
                ", Nombre: " + persona.getNombre()
                + ", Edad: " + persona.getEdad());

        if ( tabla[posicion]!=null) {
            System.out.println( "*" + posicion + ", Codigo: " + tabla[posicion].getPersona().elCodigo() +
                    ", Nombre: " + tabla[posicion].getPersona().getNombre()
                    + ", Edad: " + tabla[posicion].getPersona().getEdad());
        }

        nuevo = new Elemento(persona);
        nuevo.sgte = tabla[posicion];
        tabla[posicion] = nuevo;
        numElementos++;
    }

    boolean conforme(Persona p) {
        return p != null;
    }

    public void eliminar(String codigo) {
        int posicion;
        posicion = dispersion(transformaCadena(codigo));

        if (tabla[posicion] != null) // no está vacía
        {
            Elemento anterior, actual;

            anterior = null;
            actual = tabla[posicion];
            while ((actual.sgte != null) &&
                    actual.getPersona().elCodigo() != codigo)
            {
                anterior = actual;
                actual = actual.sgte;
            }
            if (actual.getPersona().elCodigo() != codigo)
                System.out.println("No se encuentra en la tabla la persona " + codigo);

            else if (conforme(actual.getPersona())) { //se retira el nodo

                if (anterior == null) // primer nodo
                    tabla[posicion] = actual.sgte;
                else
                    anterior.sgte = actual.sgte;
                actual = null;
                numElementos--;
            }
        }
    }

    public Elemento buscar(String codigo)
    {
        Elemento p = null;
        int posicion;
        posicion = dispersion(transformaCadena(codigo));
        if (tabla[posicion] != null)
        {
            p = tabla[posicion];
            while ((p.sgte != null) && p.getPersona().elCodigo() != codigo)
                p = p.sgte;
            if (p.getPersona().elCodigo() != codigo)
                p = null;
        }

        return p;
    }

    public void Listar() {
        int i = 0;
        while (i < TAMTABLA) {
            String texto;

            if(tabla[i] != null) {
                texto = i + ", Codigo: " + tabla[i].getPersona().elCodigo();

                Elemento el2 = tabla[i].sgte;
                while(el2 != null) {
                    texto = texto + " --> " + tabla[i].sgte.getPersona().elCodigo();
                    el2 = el2.sgte;
                }

                if(tabla[i].sgte == null) {
                    texto = texto + ", Nombre: " + tabla[i].getPersona().getNombre()
                            + ", Edad: " + tabla[i].getPersona().getEdad();
                }

                System.out.println(texto);
            } else {
                System.out.println(i + ", null");
            }
            i++;
        }
    }

}
