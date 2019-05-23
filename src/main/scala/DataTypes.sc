/**
  The collections also have an mutable object for example
  */
val mutableList = collection.mutable.MutableList("Jhon", "Peter")

mutableList.foreach(println)

mutableList.+=("Juan")

mutableList.foreach(println)


/**
  * Tuples
  */

val jhonAccount = (1,"Jhon", 123.20)

println("The " + jhonAccount._2  + " id is " + jhonAccount._1 +  " and his balance is " + jhonAccount._3)


jhonAccount.productIterator.foreach(println)
