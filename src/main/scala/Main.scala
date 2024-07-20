@main def hello(): Unit =
  println("Hello world!")
  println(msg)

def msg = "I was compiled by Scala 3. :)"

/*

Ch. 2.2

Exercise: Tree
To gain a bit of practice defining algebraic data types, code the following description in Scala
(your choice of version, or do both.).
A Tree with elements of type A is:
• a Leaf with a value of typeA; or
• a Node with a left and right child, which are both Trees with elements of type A.
 */

enum Tree[A] {
  case Leaf(a: A)
  case Node(left: A, right: A)
}
