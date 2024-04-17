package fractale.Model;

public class Complex {

    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * @return double
     */
    public double getReal() {
        return this.real;
    }

    /**
     * @param real
     */
    public void setReal(double real) {
        this.real = real;
    }

    /**
     * @return double
     */
    public double getImaginary() {
        return this.imaginary;
    }

    /**
     * @param imaginary
     */
    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    /**
     * @param number1
     * @param number2
     * @return Complex
     */
    public static Complex add(Complex number1, Complex number2) {
        return new Complex(number1.getReal() + number2.getReal(), number1.getImaginary() + number2.getImaginary());

    }

    /**
     * @param number
     * @return Complex
     *         Calcule a + bi + 2aib
     */
    public static Complex sqrt(Complex number) {
        return new Complex(number.getReal() * number.getReal()
                - number.getImaginary() * number.getImaginary(),
                2 * number.getImaginary() * number.getReal());
    }

    /**
     * @param number
     * @return double
     *         Calcule le module d'un complexe donné
     */
    public static double magnitude(Complex number) {
        double a = Math.pow(number.getReal(), 2);
        double b = Math.pow(number.getImaginary(), 2);
        return Math.sqrt(a + b);

    }

    /**
     * @return String
     */
    public String toString() {
        if (imaginary == 0)
            return real + "";
        if (real == 0)
            return imaginary + "i";
        if (imaginary < 0)
            return real + " - " + (-imaginary) + "i";
        return real + " + " + imaginary + "i";
    }

}
