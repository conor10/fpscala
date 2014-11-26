val xs = Array(1, 2, 3, 44)
xs map (x => x * 2)

val s = "Test"
s filter (c => c.isUpper)

s exists (c => c.isUpper)
s forall (c=> c.isUpper)

val pairs = List(1, 2, 3) zip s
pairs.unzip

s flatMap (c => List('.', c))

xs.sum
xs.max

def isPrime(n: Int): Boolean = {
  2 until n forall (x => n % x != 0)
}