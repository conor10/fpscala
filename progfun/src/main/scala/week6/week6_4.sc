val books: List[Book] = List(
  Book(title
    authors
    Book(title
      authors
      Book(title
        authors = List(”Bloch, Joshua”)),
Book(title = ”Java Puzzlers”,
authors = List(”Bloch, Joshua”, ”Gafter, Neal”)),
Book(title = ”Programming in Scala”,
authors = List(”Odersky, Martin”, ”Spoon, Lex”, ”Venners, Bill”)))

for {
  b1 <- books
  b2 <- books
  if b1 != b2
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1