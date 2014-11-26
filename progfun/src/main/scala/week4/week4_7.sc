trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }

  def show(e: Expr): String = this match {
    case Number(n) => n.toString
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
  }
}

case class Number(n: Int) extends Expr {
  //def eval: Int = n
}

case class Sum(e1: Expr, e2: Expr) extends Expr {
  //def eval: Int = e1.eval + e2.eval
}

Expr.show(Sum(Number(1), Number(44)))