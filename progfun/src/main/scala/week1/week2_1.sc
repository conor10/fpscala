object week2_1 {
  def factorial(n: Int): Int = {
    def loop(n: Int, sum: Int): Int = {
      if (n == 0) sum
      else loop(n - 1, n * sum)
    }

    loop(n, 1)
  }

  factorial(10)
}