


val randomLetters = "LISFHWERHFGWERHGHWEHGPWEHGPQWEHGR"

for (i <- 0 until(randomLetters.length)) {
  println(randomLetters(i))
}

var i = 0
while (i <= 10) {
  i += 1
  print("while")
}

i = 0
do {
  i += 1
  println("" + i)
} while (i <= 10)


val eventList = for {
  d <- 1 to 20
  if (d% 2) == 0
} yield d

val favNumbers = 1 to 20

val dobleFavNumbers = for (elem <- favNumbers) yield elem * 2



// predefined functions


println("Sum = " + favNumbers.sum)
println("Min = " + favNumbers.min)
println("Max = " + favNumbers.max)

// ordered by major descending
favNumbers.sortWith(_ > _)


val favNum4 = for(num <- favNumbers if(num % 4 == 0)) yield num
favNum4.foreach(println)

// multidimensional array

var mulDimArray = Array.ofDim[Int](10,10)

for (i <- 0 to 9 ; j <- 0 to 9) {
  mulDimArray(i)(j) = i * j
}

for (i <- 0 to 9) {
  for (j <- 0 to 9 ){
    mulDimArray(i)(j) = i * j
  }
}


for (i <- 0 to 9) {
  for (j <- 0 to 9) {
    printf("%d : %d  = %d \n", i, j, mulDimArray(i)(j))
  }
}

