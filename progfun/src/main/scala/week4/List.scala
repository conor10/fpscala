package week4

trait List[T] {
  def head: T
  def tail: List[T]
  def isEmpty: Boolean
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("head of EmptyList")
  def tail: Nothing = throw new NoSuchElementException("tail of EmptyList")
}

object List {
  def apply[T](x1: T, x2: T) : List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T](x: T) : List[T] = new Cons(x, new Nil)
  def apply[T]() = new Nil
}