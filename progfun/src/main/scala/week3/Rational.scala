package week3

/**
 * Created by Conor on 12/05/2014.
 */
class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be non-zero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def numer = x
  def denom = y

  def < (that: Rational) =
    numer * that.denom < that.numer * denom

  def max(that: Rational) =
    if (this < that) that else this

  def + (that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def - (that: Rational) = this + -that

  def unary_- : Rational =
    new Rational(-numer, denom) // prefix operator used is different from infix
  // you must remember to have space between unary_- and :

  override def toString = {
    val g = gcd(numer, denom)
    numer / g + "/" + denom / g
  }
}
