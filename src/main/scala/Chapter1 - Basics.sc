
  println("Welcome to the jungle baby")
  val cal = 5 * 5
  val name = "Jhon"
  name.toUpperCase()

  //If the method has no parameters, you don’t have to use parentheses
  1.toString
  "99.11".toDouble

  // you can write
  1.to(100)
  //or
  1 to 10

  "Hello"(2)
  "Hello".apply(2)
  "Hello".intersect("World")
  "Hello".count(_.isUpper)

  1.to(101, 10).foreach(println)

  math.sqrt(math.sqrt(3))

  "crazy" * 10

  10 max 11

  "crazy"

/**
  * Notes :
  *
  * 1. Often, a class has a companion object whose methods act just like static methods do in Java.
  * 2. The apply method is used by () for example "Hello"(4) is the method apply
  * 3. In Scala, you use square brackets for type parameters. A Seq[Char]
  *
*/
