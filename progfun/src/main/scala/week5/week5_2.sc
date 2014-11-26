def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

def removeAt[T](xs: List[T], n: Int) = {
  if (n > xs.length) xs
  else (xs take n) ++ (xs drop n + 1)
}
