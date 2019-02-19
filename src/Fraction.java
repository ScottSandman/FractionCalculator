/**
 * Fraction class containing constructors and methods for the FractionCalcultor
 * class.
 * @author Scott Sandman
 * @version 1.0
 */

public class Fraction {
    
    private int numerator;
    private int denominator;
    
    /**
     * Constructor accepting numerator and denominator
     * @param numerator
     * @param denominator
     */
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator can not be zero.");
        } else if (denominator < 0) {
            if (numerator < 0) {
                this.numerator = Math.abs(numerator);
                this.denominator = Math.abs(denominator);
            } else {
                this.numerator = numerator * -1;
                this.denominator = Math.abs(denominator);
            }
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }
    
    /**
     * Constructor accepting numerator only
     * @param numerator
     */
    public Fraction(int numerator) {
        this(numerator, 1);
    }
    
    /**
     * Constructor that returns the value of zero denominator
     */
    public Fraction() {
        this(0,1);
    }
    
    /**
     * Getter method to retrieve numerator
     * @return
     */
    public int getNumerator() {
        return numerator;
    }
    
    /**
     * Getter method to retrieve denominator
     * @return
     */
    public int getDenominator() {
        return denominator;
    }
    
    /**
     * Method that returns a fraction in string format.
     * Overrides the java.lang.toString method
     * @return
     */
    public String toString() {
        String a = Integer.toString(numerator);
        String b = Integer.toString(denominator);
        return a + "/" + b;
    }
    
    /**
     * Method that returns the value of numerator divided by denominator.
     * @return
     */
    public double getDouble() {
        return numerator / denominator;
    }
    
    /**
     * Method that adds two fractions and reduces to lowest terms.
     * @param other
     * @return
     */
    public Fraction add(Fraction other) {
        Fraction addFraction = new Fraction(((this.numerator *
                other.denominator) + (this.denominator * other.numerator)),
                (this.denominator * other.denominator));
        addFraction.toLowestTerms();
        return addFraction;
    }
    
    /**
     * Method that subtracts two fractions and reduces to lowest terms.
     * @param other
     * @return
     */
    public Fraction subtract(Fraction other) {
        Fraction subtractFraction = new Fraction(((this.numerator
                * other.denominator) - (this.denominator * other.numerator)),
                (this.denominator * other.denominator));
        subtractFraction.toLowestTerms();
        return subtractFraction;
    }
    
    /**
     * Method that multiplies two fractions and reduces to lowest terms.
     * @param other
     * @return
     */
    public Fraction multiply(Fraction other) {
        Fraction multiplyFraction = new Fraction((this.numerator
                * other.numerator), (this.denominator * other.denominator));
        multiplyFraction.toLowestTerms();
        return multiplyFraction;
    }
    
    /**
     * Method that divides two fractions and reduces to lowest terms.
     * @param other
     * @return
     */
    public Fraction divide(Fraction other) {
        Fraction divideFraction = new Fraction((this.numerator
                * other.denominator), (this.denominator * other.numerator));
        divideFraction.toLowestTerms();
        return divideFraction;
    }
    
    /**
     * Method that determines if two fractions are equal
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction fractionA = (Fraction) other;
            fractionA.toLowestTerms();
            Fraction fractionB = new Fraction(this.numerator, this.denominator);
            fractionB.toLowestTerms();
            
            return (fractionA.numerator == fractionB.numerator &&
                    fractionA.denominator == fractionB.denominator);
        } else {
            return false;
        }
    }
    
    /**
     * Helper method that reduces fractions to lowest terms.
     */
    private void toLowestTerms() {
        int gcd = gcd(this.numerator, this.denominator);
        numerator = this.numerator / gcd;
        denominator = this.denominator / gcd;
    }
    
    /**
     * Helper method that calculates the greatest common denominator of two
     * fractions.
     * @param num
     * @param den
     * @return
     */
    private static int gcd(int num, int den) {
        while (num > 0 && den > 0) {
            int remainder = num % den;
            num = den;
            den = remainder;
        }
        return num;
    }
}
