


List(1,2,3).map(_*2)

val myMap = Map("a" -> 1, "b" -> 2, "c" -> 3)


// you can use collect like a filter
Set("a", "d") collect myMap
List("a").collect(myMap)


case class Person(name: String, age: Int)



case class UserId(id: Int)
val friends = Map(
  UserId(1) -> Person("John", 21),
  UserId(2) -> Person("Jack", 23),
  UserId(3) -> Person("Mary", 25)
)

friends collect { case (id, p) if (p.age > 22) => p}




val myList = List(1, "a", "b", Some(2))
// myList: List[Any]
val stringList = myList collect {
  case a: String    => a
  case i: Int       => i.toString
  case Some(i: Int) => i.toString
}

/**
val ids = Seq(1,2,3)
val ids2 = Seq(1,2,3)

assert(ids.forall(id => ids2.contains(id)))
assert(ids2.forall(id => ids.contains(id)))
assert(ids.length == ids2.length + 1 , "El tamaño de las listas no coincide")
**/

/**
  * Used for give format to a string
  */
printf("'%5d'",1)


/**
  * Recursion
  */
def factorial(number: BigInt) : BigInt = {
  if (number <= 1) {
    1
  } else {
    number * factorial( number - 1)
  }
}

println("Factorial number for 8 is " + factorial(8))
