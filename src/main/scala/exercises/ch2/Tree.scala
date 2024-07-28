package exercises.ch2

import scala.util.control.TailCalls.TailRec
import scala.util.control.TailCalls.done
import scala.util.control.TailCalls.tailcall

import cats.Eq
import cats.Show
import cats.derived._
import cats.implicits._
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

TODO: consider using TailRec for `contains`, but avoid traversing the entire tree (return fast)
TODO: try to improve cont-based `map` using TailRec
TODO: add more detailed case-based tests
TODO: add case-based test for Functor laws
TODO: add property-based test for Functor laws
 */

enum Tree[A] derives Eq, Show {
  case Leaf(a: A)
  case Node(left: Tree[A], right: Tree[A])

  def size[B]: BigInt =
    // Note: unable to add @annotation.tailrec, but tests prove its safety
    def sizeRecursive(tree: Tree[A]): TailRec[BigInt] = tree match
      case Leaf(a) => done(1: BigInt)
      case Node(left, right) =>
        for {
          leftResult  <- tailcall(sizeRecursive(left))
          rightResult <- tailcall(sizeRecursive(right))
        } yield leftResult + rightResult
    sizeRecursive(this).result

  def contains(x: A)(using eq: Eq[A]): Boolean =
    @annotation.tailrec
    def containsRecursive(remaining: List[Tree[A]]): Boolean =
      remaining match
        case Nil => false
        case head :: tail =>
          head match
            case Leaf(a) if a === x => true
            case Leaf(_)            => containsRecursive(tail)
            case Node(left, right)  => containsRecursive(left :: right :: tail)
    containsRecursive(List(this))

  def map[B](f: A => B): Tree[B] =
    // Note: unable to add @annotation.tailrec, but tests prove its safety
    def mapRecursive(tree: Tree[A]): TailRec[Tree[B]] = tree match
      case Leaf(a) => done(Leaf(f(a)))
      case Node(left, right) =>
        for {
          leftResult  <- tailcall(mapRecursive(left))
          rightResult <- tailcall(mapRecursive(right))
        } yield Node(leftResult, rightResult)
    mapRecursive(this).result

}
