import scala.language.strictEquality

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

/*
Let’s get some practice with structural recursion and write some methods for Tree. Implement
• size, which returns the number of values (Leafs) stored in the Tree;
• contains, which returns true if the Tree contains a given element of type A, and false otherwise; and
• map, which creates a Tree[B] given a function A => B
Use whichever you prefer of pattern matching or dynamic dispatch to implement the methods.

TODO: add unit tests
 */

enum Tree[A] derives CanEqual {
  case Leaf(a: A)
  case Node(left: Tree[A], right: Tree[A])

  // TODO: @scala.annotation.tailrec
  def size: BigInt = this match {
    case Leaf(_)           => 1
    case Node(left, right) => left.size + right.size
  }

  // TODO: @scala.annotation.tailrec
  def contains(x: A)(using eq: CanEqual[A, A]): Boolean = this match {
    case Leaf(a)           => a == x
    case Node(left, right) => left.contains(x) || right.contains(x)
  }

  // TODO: @scala.annotation.tailrec
  def map[B](f: A => B): Tree[B] = this match {
    case Leaf(x)           => Leaf(f(x))
    case Node(left, right) => Node(left.map(f), right.map(f))
  }

}
