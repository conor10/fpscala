books flatMap(b =>
  b.authors withFilter(a => a startsWith "Bird")).map(y => y.title)