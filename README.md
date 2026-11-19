# Resolución Numérica de EDOs en Java: Método de Euler y Runge-Kutta de 4to Orden

Implementación modular de dos métodos numéricos para **Ecuaciones Diferenciales Ordinarias (EDOs)**: el **Método de Euler** y el **Método de Runge-Kutta de 4to orden (RK4)**. Con el problema de valor inicial y el tamaño de paso `h` se obtienen los valores aproximados `yₙ`, que se comparan contra la **solución analítica exacta** mediante el **error absoluto** `|y_exacta(xₙ) − yₙ|` en cada paso.

**Autor:** Marco Antonio Pavia Flores.
**Materia:** SCC-1017 Métodos Numéricos — Unidad 6
**Docente:** Víctor Hugo Vásquez Herrera
**Institución:** Instituto Tecnológico Superior de Xalapa

## Lenguaje de programación

**Java** (JDK 8 o superior; se recomienda JDK 17 o superior).

## Estructura del proyecto

```text
MetodoEuler-y-RungeKutta4to-Orden/
├── src/
│   ├── MetodoEuler.java
│   └── MetodoRungeKutta.java
├── .gitattributes
├── README.md
└── ResolucionNumerica.iml
```

| Archivo | Descripción |
|---|---|
| `MetodoEuler.java` | Contiene `evaluarFuncion` (f(x, y) = x − y), `solucionExacta` y el método `main` con el ciclo de Euler. |
| `MetodoRungeKutta.java` | Contiene `evaluarFuncion` (f(x, y) = x + y), `solucionExacta` y el método `main` con el ciclo de RK4 (k1, k2, k3, k4). |
| `ResolucionNumerica.iml` | Archivo de configuración del módulo de IntelliJ IDEA. |

Las clases no declaran paquete, por lo que se ejecutan directamente como `MetodoEuler` y `MetodoRungeKutta`.

## Fundamento matemático

Para un problema de valor inicial `y′ = f(x, y)`, `y(x0) = y0`, se construye la malla `xₙ = x0 + n·h` y el criterio de paro es el número total de pasos:

```text
N = (x_final − x0) / h
Error absoluto: Eₙ = |y_exacta(xₙ) − yₙ|
```

**Método de Euler** (orden 1, error global O(h)):

```text
yₙ₊₁ = yₙ + h · f(xₙ, yₙ)
```

**Método RK4** (orden 4, error global O(h⁴)):

```text
k1 = f(xₙ, yₙ)
k2 = f(xₙ + h/2, yₙ + (h/2)·k1)
k3 = f(xₙ + h/2, yₙ + (h/2)·k2)
k4 = f(xₙ + h,   yₙ + h·k3)
yₙ₊₁ = yₙ + (h/6)·(k1 + 2·k2 + 2·k3 + k4)
```

Las líneas implementadas en el ciclo `for` de cada clase son:

```java
// MetodoEuler.java
double k = evaluarFuncion(x, y);
y = y + h * k;
x = x0 + (i + 1) * h;
```

```java
// MetodoRungeKutta.java
double k1 = evaluarFuncion(x, y);
double k2 = evaluarFuncion(x + h / 2.0, y + (h / 2.0) * k1);
double k3 = evaluarFuncion(x + h / 2.0, y + (h / 2.0) * k2);
double k4 = evaluarFuncion(x + h, y + h * k3);
y = y + (h / 6.0) * (k1 + 2.0 * k2 + 2.0 * k3 + k4);
x = x0 + (i + 1) * h;
```

La abscisa se calcula como `x0 + (i + 1)·h` en lugar de acumular `x += h`, para evitar la acumulación de error de redondeo.

## Consideraciones sobre h y N

El programa no muestra mensajes de error por valores de `h` inadecuados, por lo que conviene tener en cuenta:

- `N` se calcula con `Math.round((xFinal - x0) / h)`; con `xFinal = 0.3`, los valores `h = 0.1`, `0.05` y `0.01` dan `N = 3`, `6` y `30`.
- Si `h` no divide exactamente a `xFinal − x0`, el redondeo de `N` hace que el último nodo no coincida con `xFinal`. Por ejemplo, `h = 0.5` da `N = 1` y llega a `x = 0.5`, que sobrepasa `0.3`.
- Si `h` es mayor que `2·(xFinal − x0)`, entonces `N = 0` y solo se imprime la fila inicial.
- `h` debe ser un número positivo; un valor no numérico produce un `NumberFormatException`.
- El error de RK4 se imprime en notación científica (`%.3e`) porque es del orden de 10⁻⁷ o menor.

## Datos de prueba

Definidos directamente en el método `main` de cada clase (`h` se puede cambiar desde la terminal):

| | Actividad 1 (Euler) | Actividad 2 (RK4) |
|---|---|---|
| EDO | y′ = x − y | y′ = x + y |
| Condición inicial | y(0) = 1 | y(0) = 1 |
| x_final | 0.3 | 0.3 |
| Solución exacta | y = x − 1 + 2e⁻ˣ | y = 2eˣ − x − 1 |

- Valores de `h` probados: **0.1** (N = 3), **0.05** (N = 6) y **0.01** (N = 30).

## Cómo clonar, compilar y ejecutar

### Desde la terminal

1. Clona el repositorio y entra a la carpeta:
   ```bash
   git clone https://github.com/Marco-Pavia/MetodoEuler-y-RungeKutta4to-Orden.git
   cd MetodoEuler-y-RungeKutta4to-Orden
   ```
2. Compila desde la carpeta raíz:
   ```bash
   javac -d out src/MetodoEuler.java src/MetodoRungeKutta.java
   ```
3. Ejecuta indicando `h` como argumento:
   ```bash
   java -cp out MetodoEuler 0.1
   java -cp out MetodoEuler 0.05
   java -cp out MetodoEuler 0.01
   java -cp out MetodoRungeKutta 0.1
   ```

### Desde IntelliJ IDEA

1. Abre la carpeta del proyecto con *File → Open*.
2. Verifica que `MetodoEuler.java` y `MetodoRungeKutta.java` estén dentro de la carpeta `src`.
3. Abre el archivo y presiona el botón verde ▶ junto al método `main`. Para cambiar `h`, modifica la variable `h` en `main` o agrégala en *Run → Edit Configurations → Program arguments*.

## Ejemplo de ejecución

**Método de Euler** (`java -cp out MetodoEuler 0.1`):

```text
Metodo de Euler: y' = x - y, y(0) = 1, h = 0.1, N = 3
n    x_n      y_n (Euler)   y_exacta     Error abs
-------------------------------------------------------------
0    0.0000   1.000000      1.000000     0.000000
1    0.1000   0.900000      0.909675     0.009675
2    0.2000   0.820000      0.837462     0.017462
3    0.3000   0.758000      0.781636     0.023636
```

**Método RK4** (`java -cp out MetodoRungeKutta 0.1`):

```text
Metodo RK4: y' = x + y, y(0) = 1, h = 0.1, N = 3
n    x_n      y_n (RK4)     y_exacta     Error abs
-------------------------------------------------------------
0    0.0000   1.000000      1.000000     0.000e+00
1    0.1000   1.110342      1.110342     1.695e-07
2    0.2000   1.242805      1.242806     3.746e-07
3    0.3000   1.399717      1.399718     6.210e-07
```

## Resultados esperados

Error absoluto en `x = 0.3`:

| Método | h | N | yₙ | Error absoluto |
|---|---|---|---|---|
| Euler | 0.1 | 3 | 0.758000 | 0.023636 |
| Euler | 0.05 | 6 | 0.770184 | 0.011453 |
| Euler | 0.01 | 30 | 0.779401 | 0.002236 |
| RK4 | 0.1 | 3 | 1.399717 | 6.210e-07 |
| RK4 | 0.05 | 6 | 1.399718 | 4.046e-08 |
| RK4 | 0.01 | 30 | 1.399718 | 6.693e-11 |

- En Euler, el error disminuye aproximadamente en proporción a `h` (convergencia O(h)).
- En RK4, reducir `h` a la mitad divide el error entre ≈ 16 (convergencia O(h⁴)).
- Euler y RK4 resuelven EDOs distintas, por lo que la tabla compara el comportamiento de cada método y no su error sobre el mismo problema.
