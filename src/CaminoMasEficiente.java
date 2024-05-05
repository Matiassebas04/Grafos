import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class CaminoMasEficiente {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario la cantidad de puntos
        System.out.print("Ingrese la cantidad de puntos: ");
        int numPuntos = scanner.nextInt();

        // Crear un array bidimensional para almacenar las coordenadas de los puntos
        double[][] puntos = new double[numPuntos][2];

        // Solicitar al usuario las coordenadas de cada punto
        for (int i = 0; i < numPuntos; i++) {
            System.out.println("Ingrese las coordenadas del punto " + (i+1));
            System.out.print("X: ");
            puntos[i][0] = scanner.nextDouble();
            System.out.print("Y: ");
            puntos[i][1] = scanner.nextDouble();
        }

        // Solicitar al usuario el punto de salida y el punto de llegada
        System.out.print("Ingrese el índice del punto de salida: ");
        int puntoSalida = scanner.nextInt() - 1; // Restamos 1 porque los índices empiezan en 0
        System.out.print("Ingrese el índice del punto de llegada: ");
        int puntoLlegada = scanner.nextInt() - 1; // Restamos 1 por la misma razón

        // Generar y comparar todas las rutas posibles
        double minDistancia = Double.MAX_VALUE;
        List<double[]> caminoMasEficiente = null;
        for (int i = 0; i < numPuntos; i++) {
            for (int j = i+1; j < numPuntos; j++) {
                for (int k = 0; k < numPuntos; k++) {
                    if (k!= i && k!= j && k!= puntoSalida && k!= puntoLlegada) {
                        double distanciaTotal = calcularDistancia(puntos[puntoSalida], puntos[j]) +
                                calcularDistancia(puntos[j], puntos[k]) +
                                calcularDistancia(puntos[k], puntos[puntoLlegada]);
                        if (distanciaTotal < minDistancia) {
                            minDistancia = distanciaTotal;
                            caminoMasEficiente = Arrays.asList(new double[]{puntos[puntoSalida][0], puntos[puntoSalida][1]}, puntos[j], puntos[k], puntos[puntoLlegada]);
                        }
                    }
                }
            }
        }

        // Mostrar todas las rutas posibles y la mejor
        System.out.println("\nRutas posibles:");
        for (int i = 0; i < numPuntos; i++) {
            for (int j = i+1; j < numPuntos; j++) {
                for (int k = 0; k < numPuntos; k++) {
                    if (k!= i && k!= j && k!= puntoSalida && k!= puntoLlegada) {
                        double distanciaTotal = calcularDistancia(puntos[puntoSalida], puntos[j]) +
                                calcularDistancia(puntos[j], puntos[k]) +
                                calcularDistancia(puntos[k], puntos[puntoLlegada]);
                        System.out.println("Ruta: (" + puntos[puntoSalida][0] + ", " + puntos[puntoSalida][1] + ") -> (" + puntos[j][0] + ", " + puntos[j][1] + ") -> (" + puntos[k][0] + ", " + puntos[k][1] + ") -> (" + puntos[puntoLlegada][0] + ", " + puntos[puntoLlegada][1] + ")");
                        System.out.println("Distancia total: " + distanciaTotal);
                    }
                }
            }
        }

        System.out.println("\nCamino más eficiente:");
        for (double[] punto : caminoMasEficiente) {
            System.out.println("(" + punto[0] + ", " + punto[1] + ")");
        }
        System.out.println("Distancia total: " + minDistancia);
    }

    private static double calcularDistancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private static double calcularDistancia(double[] punto1, double[] punto2) {
        return calcularDistancia(punto1[0], punto1[1], punto2[0], punto2[1]);
    }
}
