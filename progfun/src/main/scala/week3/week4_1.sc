import week3._

def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

singleton(1)
singleton(true)

//def nth(n: Int, list: List[T]) : T = {
//  val count = 0;
//  def iterate(list: List[T], count: Int) : T = {
//    if (list.isEmpty) throw new IndexOutOfBoundsException("Invalid index specified")
//    else if (count == n) return list.head
//    else iterate(list.tail, count += 1)
//  }
//}

def nth[T](n: Int, xs: List[T]): T =
  if (xs.isEmpty) throw new IndexOutOfBoundsException("Invalid index")
  else if (n == 0) xs.head
  else nth(n - 1, xs.tail)

val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
nth(2, list)
nth(-1, list)
nth(4, list)