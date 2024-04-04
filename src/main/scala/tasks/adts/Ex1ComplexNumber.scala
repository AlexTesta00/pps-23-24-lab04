package tasks.adts

object Ex1ComplexNumbers:

  trait ComplexADT:
    type Complex
    def complex(re: Double, im: Double): Complex
    extension (complex: Complex)
      def re(): Double
      def im(): Double
      def sum(other: Complex): Complex
      def subtract(other: Complex): Complex
      def asString(): String

  object BasicComplexADT extends ComplexADT:

    private case class ComplexNumber(re: Double, im: Double)

    opaque type Complex = ComplexNumber

    def complex(re: Double, im: Double): Complex = ComplexNumber(re, im)

    extension (complex: Complex)
      def re(): Double = complex match { case Complex(re, _) => re }

      def im(): Double = complex match { case Complex(_, im) => im }
      def sum(other: Complex): Complex =
        ComplexNumber(complex.re() + other.re, complex.im() + other.im())
      def subtract(other: Complex): Complex =
        ComplexNumber(complex.re() - other.re, complex.im() - other.im())

      // TODO: Refactor obs
      def asString(): String = complex match
        case ComplexNumber(re, im) if re > 0 && im > 0 => re + " + " + im + "i"
        case ComplexNumber(re, im) if re > 0 && im < 0 =>
          re + " - " + im.abs + "i"
        case ComplexNumber(re, im) if re == 0 && im > 0  => im + "i"
        case ComplexNumber(re, im) if re == 0 && im < 0  => "-" + im.abs + "i"
        case ComplexNumber(re, im) if re == 0 && im == 0 => re + ""
        case ComplexNumber(re, im) if re > 0 && im == 0  => re + ""
