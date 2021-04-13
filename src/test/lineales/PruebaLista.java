package test.lineales;
import lineales.dinamicas.*;

public class PruebaLista {
    public static void main(String[] args) {
        Lista l1 = new Lista();
        Lista l2 = new Lista();
        Lista l3 = new Lista();

        l1.insertar(2, 1);
        l1.insertar(4, 2);
        l1.insertar(6, 3);

        l2.insertar(5, 1);
        l2.insertar(1, 2);
        l2.insertar(6, 3);
        l2.insertar(7, 4);

        l3.insertar(9, 1);
        l3.insertar(6, 2);
        l3.insertar(5, 3);
        l3.insertar(0, 4);
        l3.insertar(9, 5);
        l3.insertar(6, 6);
        l3.insertar(5, 7);
        l3.insertar(0, 8);
        l3.insertar(5, 9);
        l3.insertar(6, 10);
        l3.insertar(9, 11);

        Lista concatenada = concatenar(l1, l2).clone();


        System.out.println("Cadena 1: " + l1.toString());
        System.out.println("Cadena 2: " + l2.toString());
        System.out.println("Cadena concatenada: " + concatenada.toString());
        System.out.println("Lista: " + l3.toString());

        System.out.println(comprobar(l3));
    }

    public static Lista concatenar(Lista l1, Lista l2) {
        Lista concatenada = new Lista();
        int i;

        for (i = 1; i <= l1.longitud(); i++) {
            concatenada.insertar(l1.recuperar(i), i);
        }

        for (int j = 0; j < l2.longitud(); j++) {
            concatenada.insertar(l2.recuperar(j + 1), (i + j));
        }

        return concatenada;
    }

    public static boolean comprobar(Lista l1) {
        int i = 1;
        int coincidentes = 0;
        int coincidentesInvertidos = 0;
        Cola cAux = new Cola();
        Pila pAux = new Pila();

        // Si en la lista hay un 0, entonces existe al menos una cadena
        if (l1.localizar(0) > 0) {
            // Mientras que no llegue al maximo de l1 y la posicion de la lista en i
            // sea distinta de 0
            while ((int) l1.recuperar(1) != 0) {
                cAux.poner(l1.recuperar(1));
                pAux.apilar(l1.recuperar(1));
                l1.eliminar(1);
            }

            l1.eliminar(1);

            // Mientras el frente de la cola sea igual al proximo elemento de la lista
            while (cAux.obtenerFrente() == l1.recuperar(1) && cAux != null) {
                coincidentes++;
                l1.eliminar(1);
                cAux.sacar();
            }

            // Si la posicion 1 en la que quedo la lista es 0, quiere decir que la segunda
            // cadena coincidio en numero de posiciones con la primera
            if ((int) l1.recuperar(1) == 0) {
                // Elimino el supuesto 0 que habra quedado en la primera posicion
                l1.eliminar(1);

                //Mientras que el tope de la pila sea el elemento siguiente de la lista
                while (pAux.obtenerTope() == l1.recuperar(i)) {
                    coincidentesInvertidos++;
                    l1.eliminar(1);
                    pAux.desapilar();
                }
            }
        }
        return coincidentes == coincidentesInvertidos;
    }
}
