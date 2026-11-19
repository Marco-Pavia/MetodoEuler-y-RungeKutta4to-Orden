// Marco Antonio Pavia Flores
// MetodoRungeKutta.java

public class MetodoRungeKutta {

    // f(x, y) = x + y
    public static double evaluarFuncion(double x, double y) {
        return x + y;
    }

    // Solucion analitica exacta: y(x) = 2e^x - x - 1
    public static double solucionExacta(double x) {
        return 2.0 * Math.exp(x) - x - 1.0;
    }

    public static void main(String[] args) {
        double x0 = 0.0;
        double y0 = 1.0;
        double h = 0.1;
        if (args.length > 0) {
            h = Double.parseDouble(args[0]); // h desde la terminal
        }
        double xFinal = 0.3;

        int nPasos = (int) Math.round((xFinal - x0) / h);
        double x = x0;
        double y = y0;

        System.out.println("Metodo RK4: y' = x + y, y(0) = 1, h = " + h
                + ", N = " + nPasos);
        System.out.println("n\t x_n\t y_n (RK4)\t y_exacta\t "
                + "Error abs");
        System.out.println("-----------------------------------------------"
                + "--------------");
        System.out.printf("%d\t %.4f\t %.6f\t %.6f\t %.3e\n",
                0, x, y, solucionExacta(x), Math.abs(solucionExacta(x) - y));

        for (int i = 0; i < nPasos; i++) {
            // Las 4 pendientes
            double k1 = evaluarFuncion(x, y);
            double k2 = evaluarFuncion(x + h / 2.0, y + (h / 2.0) * k1);
            double k3 = evaluarFuncion(x + h / 2.0, y + (h / 2.0) * k2);
            double k4 = evaluarFuncion(x + h, y + h * k3);

            // Actualizacion ponderada de y y avance de x en h
            y = y + (h / 6.0) * (k1 + 2.0 * k2 + 2.0 * k3 + k4);
            x = x0 + (i + 1) * h;
            double yEx = solucionExacta(x);
            System.out.printf("%d\t %.4f\t %.6f\t %.6f\t %.3e\n",
                    i + 1, x, y, yEx, Math.abs(yEx - y));
        }
    }
}