// Marco Antonio Pavia Flores
// MetodoEuler.java

public class MetodoEuler {

    // f(x, y) = x - y
    public static double evaluarFuncion(double x, double y) {
        return x - y;
    }

    // Solucion analitica exacta: y(x) = x - 1 + 2e^(-x)
    public static double solucionExacta(double x) {
        return x - 1.0 + 2.0 * Math.exp(-x);
    }

    public static void main(String[] args) {
        double x0 = 0.0;
        double y0 = 1.0;
        double h = 0.01; // Variar este valor en el reporte (ej. 0.1, 0.05, 0.01)
        if (args.length > 0) {
            h = Double.parseDouble(args[0]); // h desde la terminal
        }
        double xFinal = 0.3;

        // Criterio de paro (numero total de pasos N)
        int nPasos = (int) Math.round((xFinal - x0) / h);
        double x = x0;
        double y = y0;

        System.out.println("Metodo de Euler: y' = x - y, y(0) = 1, h = " + h
                + ", N = " + nPasos);
        System.out.println("n\t x_n\t y_n (Euler)\t y_exacta\t "
                + "Error abs");
        System.out.println("-----------------------------------------------"
                + "--------------");
        System.out.printf("%d\t %.4f\t %.6f\t %.6f\t %.6f\n",
                0, x, y, solucionExacta(x), Math.abs(solucionExacta(x) - y));

        for (int i = 0; i < nPasos; i++) {
            // Pendiente en el punto actual
            double k = evaluarFuncion(x, y);
            // Actualizacion de y y x
            y = y + h * k;
            x = x0 + (i + 1) * h; // evita acumular error de redondeo
            double yEx = solucionExacta(x);
            System.out.printf("%d\t %.4f\t %.6f\t %.6f\t %.6f\n",
                    i + 1, x, y, yEx, Math.abs(yEx - y));
        }
    }
}