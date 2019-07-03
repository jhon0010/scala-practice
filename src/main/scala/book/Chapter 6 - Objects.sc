/**
  * Use objects when you need a class with a SINGLE instance.
  * or when you want to find a home for miscellaneous values or functions.
  *
  * The key points of this chapter are:
  * • Use objects for singletons and utility methods.
  * • A class can have a companion object with the same name.
  * • Objects can extend classes or traits.
  * • The apply method of an object is usually used for constructing new instances of the
  * companion class.
  * • To avoid the main method, use an object that extends the App trait.
  * • You can implement enumerations by extending the Enumeration object.
  */

/**
  * -------------------------------------------------
  * 6.1 Singletons
  * Scala has no static methods or fields. Instead, you use the object construct. An object defines a
  * single instance of a class with the features that you want.
  *
  * The constructor of an object is executed when the object is first used.
  *
  * You cannot provide constructor parameters.
  *
  * Use when you need:
  * • As a home for utility functions or constants
  * • When a single immutable instance can be shared efficiently
  * • When a single instance is required to coordinate some service (the singleton design pattern)
  */
object Accounts {
  private var lastNumber = 0
  def newUniqueNumber() = { lastNumber += 1; lastNumber }
}

/**
  * -------------------------------------------------
  * 6.2 Companion Objects
  * In Java or C++, you often have a class with both instance methods and static methods. In Scala, you
  * can achieve this by having a class and a “companion” object of the same name.
  */
class Account {
  val id = Account.newUniqueNumber()
  private var balance = 0.0
  def deposit(amount: Double) { balance += amount }
}
object Account { // The companion object
  private var lastNumber = 0
  private def newUniqueNumber() = { lastNumber += 1; lastNumber }
}

/**
  * -------------------------------------------------
  * 6.3 Objects Extending a Class or Trait
  *
  * One useful application is to specify default objects that can be shared. For example, consider a class
  * for undoable actions in a program.
  */

abstract class UndoableAction(val description: String) {
  def undo(): Unit
  def redo(): Unit
}

object DoNothingAction extends UndoableAction("Do nothing") {
  override def undo() {}
  override def redo() {}
}

//The DoNothingAction object can be shared across all places that need this default.
val actions = Map("open" -> DoNothingAction, "save" -> DoNothingAction)


/**
  * -------------------------------------------------
  * 6.4 The apply Method
  *
  * It is common to have objects with an apply method. The apply method is called for expressions of
  * the form
  * Object(arg1, ..., argN)
  *
  */
//for example
Array("Jhon", "Test") // use apply method

//Why doesn’t one just use a constructor? Not having the new keyword is handy for nested expressions, such as
Array(Array(1, 7), Array(2, 9))

/**
Caution
It is easy to confuse Array(100) and new Array(100). The first expression calls
apply(100), yielding an Array[Int] with a single element, the integer 100. The second
expression invokes the constructor this(100). The result is an Array[Nothing] with
100 null elements.
  */

class Account2 private (val id: Int, initialBalance: Double) {
  private var balance = initialBalance
}
object Account2 { // The companion object

  def apply(initialBalance: Double) =
    new Account2(newUniqueNumber(), initialBalance)

  private var lastNumber = 0
  private def newUniqueNumber() = { lastNumber += 1; lastNumber }
}

val account: Account2 = Account2(39.88)


/**
  * -------------------------------------------------
  *6.5 Application Objects
  * Each Scala program must start with an object’s main method of type Array[String] =>
  *
  * Instead of providing a main method for your application, you can extend the App trait and place the
  * program code into the constructor body:
  */

object Hello2 {
  def main(args: Array[String]) {
    println("Hello, World!")
  }
}

object Hello3 extends App {
  println("Hi", args)
}

/**
  * -------------------------------------------------
  * 6.6 Enumerations
  *
  * Unlike Java or C++, Scala does not have enumerated types. However, the standard library provides
  * an Enumeration helper class that you can use to produce enumerations.
  * Define an object that extends the Enumeration class and initialize each value in your enumeration
  * with a call to the Value method. For example,
  *
  */
object TrafficLightColor extends Enumeration {

  val Red, Yellow, Green = Value

  //Each call to the Value method returns a new instance of an inner class, also called Value.
  //Alternatively, you can pass IDs, names, or both to the Value method:
  val Red2 = Value(15, "Stop")
  val Yellow2 = Value(10) // Name "Yellow"
  val Green2 = Value("Go") // ID 11

}

val green2:TrafficLightColor.Value = TrafficLightColor.Green2 // generate a object with type Value

//Remember that the type of the enumeration is TrafficLightColor.Value and not
//TrafficLightColor—that’s the type of the object holding the values. Some people recommend
//that you add a type alias

object TrafficLightColorAlias extends Enumeration {
  type TrafficLightColor = Value
  val Red, Yellow, Green = Value
}

val green:TrafficLightColorAlias.TrafficLightColor = TrafficLightColorAlias.Green // also generate a object with type Value but can be alias of TrafficLightColor

//Finally, you can look up an enumeration value by its ID or name. Both of the following yield the

TrafficLightColor(15) // Calls Enumeration.apply
TrafficLightColor.withName("Red")



//1. Write an object Conversions with methods inchesToCentimeters,
//gallonsToLiters, and milesToKilometers.

object  Conversion {
  def inchesToCentimeters(inches: Double): Double ={
    inches * 100
  }

  def gallonsToLiters(){}
  def milesToKilometers(){}
}

//2. The preceding problem wasn’t very object-oriented. Provide a general superclass
//UnitConversion and define objects InchesToCentimeters, GallonsToLiters,
//and MilesToKilometers that extend it.

class UnitConversion(val valueToConvert: Double) {

  object InchesToCentimeters {}
  object gallonsToLiters {}
  object milesToKilometers {}

}

//3. Define an Origin object that extends java.awt.Point. Why is this not actually a good
//idea? (Have a close look at the methods of the Point class.)

object Origin extends java.awt.Point {
    // is bad idea bc the constructor have implicit call to this constructor method and
   //the attributes are public and have getters and setters java bean style already defined.
}


//4. Define a Point class with a companion object so that you can construct Point instances as
//Point(3, 4), without using new.

class Point (x: Double, y: Double) {
}

object Point{
  def apply(x: Double, y: Double): Point ={
    new Point(x,y)
  }
}

val point = Point(3.22,5.33) // with out use of new clause

//5. Write a Scala application, using the App trait, that prints its command-line arguments in
//reverse order, separated by spaces. For example, scala Reverse Hello World should
//print World Hello.

object scalaApp extends App {
  for (elem <- args.reverse) {println(elem)}
}

//6. Write an enumeration describing the four playing card suits so that the toString method
//returns ♣, ♦, ♥, or ♠.
object Cards extends Enumeration {
  val clovers = Value(1,"♣")
  val diamonds = Value(2,"♦")
  val hearts = Value(3,"♥")
  val swords = Value(4,"♥")
}

val card = Cards(1)


//7. Implement a function that checks whether a card suit value from the preceding exercise is red.
