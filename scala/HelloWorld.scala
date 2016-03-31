/**
 * Pakage for learning, no paticular usage
 */
package test.hello

import math._
import scala.util.control.Breaks._
import java.text.MessageFormat
import java.net.MalformedURLException
import java.io.IOException

/**
 * @author quanna
 *
 */
object HelloWorld {

  // normal function can omit return type
  def abs(x: Double) = if (x >= 0) x else -x
  // recursive function can not
  def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)
  //+ return in scala work like break of function without the value following
  //+ Hindley-Milner algorithm has problem when applying to oopl
  // default values
  def decorate(str: String, left: String = "[", right: String = "]") = left + str + right
  // variable arguments
  def sum(args: Int*): Int = {
    var result: Int = 0
    for (arg <- args) result += arg
    result
  }
  def recSum(args: Int*): Int = {
    if (args.length == 0) 0
    else args.head + recSum(args.tail: _*) // use collection as argument list
  }

  // procedures
  def box(s: String) { // no =, return Unit type
    var border = "-" * s.length + "--\n"
    println(border + "|" + s + "|\n" + border)
  }
  // some like function def style better
  def starBox(s: String): Unit = {
    var border = "*" * s.length + "**\n"
    println(border + "*" + s + "*\n" + border)
  }

  // Exception is subclass of java.lang.Throwable
  def errorFunction(x: Double) = { // it is not "checked"
    if (x >= 0) sqrt(x)
    else throw new IllegalArgumentException("x should not be negative") // throw expression get type Nothing, if else expression get type of other branch
  }

  def main(args: Array[String]): Unit = {
    // var:variables, val:immutable values
    val anInt = 1;
    var anotherInt = 2;
    anotherInt = anInt

    // data types, all classes
    var aByte: Byte = -1;
    var aChar: Char = 'a';
    var aShort: Short = -1;
    var aLong: Long = -1;
    var aFloat: Float = -1.1f;
    var aDouble: Double = -1.1;
    val aString = ":Hello World !!!"

    // "parse" using to... method
    var s: String = "11"
    val i: Int = s.toInt
    println(i + 7)
    s = "135.67"
    println(s.toDouble + 13)

    // operator can be used with BigInt and BigDecimal
    val x: BigDecimal = 11223344556677.9967
    println(x * x * x)

    // print to console
    print("Hello ")
    println("World!!!")
    println(anInt + aString)
    printf("%s %d\n", aString, anInt)

    // Some method call from math package
    println(min(pow(sqrt(2), 3), Pi))

    // Each class can have a companion object whose methods act just like static methods in Java
    println(BigInt.probablePrime(100, scala.util.Random))

    // extension classes: Int -> RichInt, String -> StringOps
    println(1.to(10).toString)
    println("abcd".intersect("cedf"))

    // The apply method
    println("Hello"(4) + "=" + "Hello".apply(4)) // = operator ()
    println(BigInt("1234567") * 6) // call apply method of companion object is way of creating object
    println(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)(5))

    // If else expression
    println(if (2 > 3) 2 else 3)
    println(if (2 > 3) 2) // in case else is not specified, Unit object is used
    println(if (2 > 3) 2 else ())
    val ifval = if (1 > 2) 2
    else 1 // else can be on next line
    println(ifval)

    // block expression
    if (anInt > 3) { anotherInt = anInt; anotherInt -= 1 } else anotherInt -= 2 // semicolon is only needed to separate 2 expression
    anotherInt = 1 * 2 *
      3 * 4 * 5 * 6 // use operator to tell compiler that more is coming
    if (anotherInt > 100) { // or just use the { or (
      anotherInt = 100
      anotherInt -= 1
    }
    println(anotherInt)
    println({
      anInt
      anotherInt
      1 * 2 * 3 * 4 * 5
      "Hello" * 9
    }) // value of block is the value of last expression in block
    println(anotherInt = 1) // assignment expression's value is Unit, do not chain or use as return in functions

    // read from console
    //val name = readLine("Your Name:")
    //anotherInt = readInt
    //aDouble = readDouble
    //aByte = readByte
    //aShort = readShort
    //aLong = readLong
    //aFloat = readFloat
    //val aBoolean = readBoolean
    //aChar = readChar

    // Loops
    var n: Int = 10
    var r: Int = 1
    while (n > 0) { // simple while
      r = r * n
      n -= 1
    }
    do { // simple do while
      r = r + n
      n += 1
    } while (n < 10)
    for (i <- 1 to n) // for ( i <- collection )
      r = r * i
    s = "Hello world !!!"
    for (i <- 0 until s.length - 1)
      println(s(i))
    for (ch <- s)
      println(ch)

    // break and continue is not in scala
    // the package scala.util.control.Breaks use exception to "simulate" breaking
    breakable {
      for (i <- 1 to 10)
        for (j <- i to 20) {
          print(i * j + " "); if (i == 5 && j == 10) break
        }
    }

    // advanced for loop
    for (i <- 1 to 3; j <- 1 to 3) print(10 * i + j + " ") // multiple generators
    println
    for (i <- 1 to 3; j <- 1 to 3 if i != j) print(10 * i + j + " ") // a guard start with if without ;
    println
    for (i <- 1 to 3; from = 4 - i; j <- from to 3) print(10 * i + j + " ") // you can have any number of definitions
    println
    for (i <- 1 to 10) yield i % 3 // a "for comprehension" that generate a collection
    println(for (c <- "hello"; i <- 0 to 1) yield (c + i).toChar) // collection generated is compatible with first generator
    for {
      i <- 1 to 3
      from = 4 - i
      j <- from to 3
    } println(i + j * 10) // use {} and newline instead of () and ;

    // calling function with named argument
    println(decorate(left = ">>>[", str = "Me"))

    // call functions with variable argument
    println(sum(999, 666, 7782))
    println(sum(1 to 100: _*))

    // when calling java function with variable argument you must convert each argument
    println(MessageFormat.format("The answer to {0} is {1}", "everything", 33.asInstanceOf[AnyRef]))

    // call procedures
    box("Hello Box !!!")
    starBox("Comment World !!!")

    // Evaluated as soon as words is defined
    //val words = scala.io.Source.fromFile("/usr/share/dict/words").mkString
    // Evaluated the first time words is used
    lazy val words1 = scala.io.Source.fromFile("/usr/share/dict/words").mkString
    // Evaluated every time words is used
    def words2 = scala.io.Source.fromFile("/usr/share/dict/words").mkString

    try {
      errorFunction(-1)
    } catch {
      // catch work with pattern matching
      case _: MalformedURLException => println("Bad URL") // do not need exception val
      case ex: IOException => ex.printStackTrace()
      case _: IllegalArgumentException => println("sh*t")
    } finally {
      println("Close resources") // always executed
    }
  }
}
