val numbers = 1 to 10

/**
  * First argument initial value = (0)
  *  a pre-defined combining operation op that, again, takes two arguments, i.e. the accumulated value acc and the current value i
  */
numbers.foldLeft(0) {(acum,i) => acum+i}
numbers.foldLeft(0) (_ + _)

numbers.foldRight(0){(acum,i) => acum+i}


