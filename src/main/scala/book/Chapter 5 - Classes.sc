import java.util.Calendar

import scala.beans.BeanProperty

/**
  * • Fields in classes automatically come with getters and setters.
  * • You can replace a field with a custom getter/setter without changing the client of a class—that
  * is the “uniform access principle.”
  * • Use the @BeanProperty annotation to generate the JavaBeans getXxx/setXxx methods.
  * • Every class has a primary constructor that is “interwoven” with the class definition. Its
  * parameters turn into the fields of the class. The primary constructor executes all statements in
  * the body of the class.
  * • Auxiliary constructors are optional. They are called this.
  */

/**
 * 5.1 Simple Classes and Parameterless Methods
 */
class Counter {

  private var value = 0 // You must initialize the field
  def increment() { value += 1 } // Methods are public by default
  def current() = value
}
val myCounter = new Counter // Or new Counter()
myCounter.increment()
println(myCounter.current)
myCounter.current // OK
myCounter.current() // Also OK
//Which form should you use? It is considered good style to use () for a mutator method (a method
//  that changes the object state), and to drop the () for an accessor method (a method that does not
//  change the object state).

/**
  * By default scala classes have private attributes and make the compiled classes
  * the getters and setters methods that are call
  * println(fred.age) // Calls the method fred.age()
  * fred.age = 21 // Calls fred.age_=(21)
  */

//At any time, you can redefine the getter and setter methods yourself. For example,
class Person {
  private var privateAge = 0 // Make private and rename
  def age = privateAge
  def age_=(newValue: Int) {
    if (newValue > privateAge) privateAge = newValue; // Can't get younger
  }
}

/**
  * It may sound scary that Scala generates getter and setter methods for every field. But you have
  * some control over this process.
  * • If the field is private, the getter and setter are private.
  * • If the field is a val, only a getter is generated.
  * • If you don’t want any getter or setter, declare the field as private[this] (see
  * Section 5.4, “Object-Private Fields,” on page 60).
  *
  * To summarize, you have four choices for implementing properties:
  * 1. var foo: Scala synthesizes a getter and a setter.
  * 2. val foo: Scala synthesizes a getter.
  * 3. You define methods foo and foo_=.
  * 4. You define a method foo.
  * In Scala, you cannot have a write-only property (that is, a property with a setter and no getter).
  */


/**
  * ---------------------------------------------
  * 5.4 Object-Private Fields
  * In Scala (as well as in Java or C++), a method can access the private fields of all objects of its class.
  *
  */
//Accessing other.value is legal because other is also a Counter object.
//Scala allows an even more severe access restriction with the private[this] qualifier:

private[this] var value = 0 // Accessing someObject.value is not allowed
// This is call object private

/**
  * --------------------------------------------
  * 5.5 Bean Properties
  * You can annotate your fields with and this generate the getters a setters java convention for you.
  */

/**
  * ---------------------------------------------
  * 5.6 Auxiliary Constructors
  *
  * 1. The auxiliary constructors are called this. (In Java or C++, constructors have the same name
  * as the class—which is not so convenient if you rename the class.)
  * 2. Each auxiliary constructor must start with a call to a previously defined auxiliary constructor
  * or the primary constructor.
  *
  */
class Car {

  private var color = ""
  private var antique = Calendar.getInstance()

  def this(color: String) { // An auxiliary constructor
    this()
    this.color = color
  }

  def this(color: String, antique: Calendar) {
    this(color)
    this.antique = antique
  }

}


/**
  * ---------------------------------------------
  * 5.7 The Primary Constructor
  *
  * 1. The parameters of the primary constructor are placed immediately after the class name.
  * 2. The primary constructor executes all statements in the class definition. For example, in the
  * following class
  */

class animal(tall: Int, name : String) {
  println("We are constructing a new animal !!")
}

//You can often eliminate auxiliary constructors by using default arguments in the primary
//constructor. For example:

class Person2(val name: String = "", val age: Int = 0)


//• If a parameter without val or var is used inside at least one method, it becomes a field. For
//example,
class Person3(name: String, age: Int) {
  def description = s"$name is $age years old"
}
//declares and initializes immutable fields name and age that are object-private.
//Such a field is the equivalent of a private[this] val field

//To make the primary constructor private, place the keyword private like this:
class Person4 private(val id: Int) {}


/**
  * ---------------------------------------------
  * 5.8 Nested Classes
  *
  */
import scala.collection.mutable.ArrayBuffer
class Network {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }
  private val members = new ArrayBuffer[Member]
  def join(name: String) = {
    val m = new Member(name)
    members += m
  }
}


//Alternatively, you can use a type projection Network#Member, which means “a Member of any
//  Network.” For example,

class Network2 {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Network2#Member]
  }

}


//In a nested class, you can access the this reference of the enclosing class as
//EnclosingClass.this, like in Java. If you like, you can establish an alias for that reference
//with the following syntax:

class Network3(val name: String) {
  outer =>

  class Member(val name: String) {
    def description = s"$name inside ${outer.name}"
  }
}



/**
  *  Exercises
  */

//Improve the Counter class in Section 5.1,“Simple Classes and Parameterless Methods,” on
//page 55 so that it doesn’t turn negative at Int.MaxValue.

class CounterImproved {
  private var value = 0 // You must initialize the field
  def increment() { if (value < Int.MaxValue) this.value += 1 } // Methods are public by default

  def current() = value
}

val count = new CounterImproved
count.increment()
count.current()
count.increment()
count.current()

//2. Write a class BankAccount with methods deposit and withdraw, and a read-only
//property balance.

class BankAccount {

  private var _balance = 0.0

  def balance () = { _balance }

  def deposit(value: Double): Unit = {
    if(value > 0) {_balance += value}
  }

  def withdraw(value: Double): Unit = {
    if (value <= balance){
      _balance -= value
    }
  }

}

val account = new BankAccount
account.balance
account.deposit(33.33)
account.balance


//3. Write a class Time with read-only properties hours and minutes and a method
//before(other: Time): Boolean that checks whether this time comes before the other.
//A Time object should be constructed as new Time(hrs, min), where hrs is in military
//time format (between 0 and 23).

class Time {

  private var hour: Int = 0
  private var minute: Int = 0

  def this(hour: Int, minute: Int) {
    this()
    if (hour > 0 &&  hour < 24) this.hour = hour
    if(minute > 0 && minute < 60) this.minute = minute
  }

  def before(time: Time): Boolean = {
    hour < time.hour || (hour == time.hour && minute < time.minute)
  }

}

val time1 = new Time(23,30)
val time2 = new Time(23,55)
time1.before(time2)
time2.before(time1)

//Reimplement the Time class from the preceding exercise so that the internal representation is
//the number of minutes since midnight (between 0 and 24 × 60 – 1). Do not change the public
//interface. That is, client code should be unaffected by your change.
class TimeReimplemented {

  private var hour: Int = 0
  private var minute: Int = 0
  private var timeInMinutes = 0

  def this(hour: Int, minute: Int) {
    this()
    if (hour > 0 &&  hour < 24) this.hour = hour
    if(minute > 0 && minute < 60) this.minute = minute
    timeInMinutes = hour * minute -1
  }

  def before(time: TimeReimplemented): Boolean = {
    timeInMinutes < time.timeInMinutes
  }
}

val time3 = new TimeReimplemented(23,30)
val time4 = new TimeReimplemented(23,55)
time3.before(time4)
time4.before(time3)


//5. Make a class Student with read-write JavaBeans properties name (of type String) and
//id (of type Long). What methods are generated? (Use javap to check.) Can you call the
//JavaBeans getters and setters in Scala? Should you?

class Student(@BeanProperty val name: String, @BeanProperty val id: Long){}
val student = new Student("Jhon", 1)
student.getName

//6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,” on page 55,
//provide a primary constructor that turns negative ages to 0.
class Personimproved { // This is Java
  var age = 0 // Frowned upon in Java

  def this(age: Int) {
    this()
    if (age < 0) this.age = 0
    else this.age = age
  }
}

val p = new Personimproved(-9)
p.age

//7. Write a class Person with a primary constructor that accepts a string containing a first name,
//a space, and a last name, such as new Person("Fred Smith"). Supply read-onlyproperties firstName and lastName. Should the primary constructor parameter be a var,
//a val, or a plain parameter? Why?

class PersonNames (private val fullName: String){

  def firstName() : String = {
    fullName.split(" ")(0)
  }

  def lastName() : String = {
    fullName.split(" ")(1)
  }
}

val pn = new PersonNames("Jhon Lotero")
pn.firstName()
pn.lastName()
