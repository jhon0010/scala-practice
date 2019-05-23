
/**
  * This is  the definition that contains the primary constructor
  * @param name
  * @param sound
  */
class Animal(var name: String , var sound: String) {

  def getAnimal: String = this.name



  override def toString () : String = {
    this.name + this.sound
  }

  /**
    * this is the constructor inside scala
    * @param name
    */
  def this (name: String) {

    /**
      * Call the primary constructor
      */
    this("No name ", "no sound")
  }

  /**
    * This is the form for put the static methods
    */
  object Animal {
    def getId: Int = { 1 }
  }

}
