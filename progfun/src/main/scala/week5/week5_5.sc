/*def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => y * y :: squareList(ys)
}

def squareList(xs: List[Int]): List[Int] =
  xs map (x => x * x)
*/

val data = List("a", "a", "a", "b", "c", "c", "a")

def pack[T](xs: List[T]) : List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
//    List(xs takeWhile (y => y == x)) ::: pack(xs1 dropWhile(y => y != x))
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

def encode[T](xs: List[T]) : List[(T, Int)] = {
  pack(xs) map (ys => (ys.head, ys.length))
}

pack(data)
encode(data)