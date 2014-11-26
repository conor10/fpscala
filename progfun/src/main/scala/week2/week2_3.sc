object week2_3 {
  /* My implementation
    def product(f: Int => Int): (Int, Int) => Int = {
    def productF(a: Int, b: Int): Int =
      if (a > b) 1
      else f(a) * productF(a + 1, b)
    productF
  }

  def productInts = product(x => x)
  productInts(1, 10)

  def factorial(x: Int) = productInts(1, x: Int)
     */

  /* Original model implementation
  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
  product(x => x * x)(3, 4)
  */

  def fact(n: Int) = product(x => x)(1, n)

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

  def product(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)

  fact(10)
}