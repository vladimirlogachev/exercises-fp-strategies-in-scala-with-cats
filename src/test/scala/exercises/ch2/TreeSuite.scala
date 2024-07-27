package exercises.ch2

import cats.implicits._
import weaver._

object TreeSuite extends SimpleIOSuite {
  import Tree._

  def buildTreeLeft[A](size: BigInt, fillFromIndex: BigInt => A): Tree[A] =
    @scala.annotation.tailrec
    def buildTreeLeftRecursive(remainingSize: BigInt, accumulator: Tree[A]): Tree[A] = {
      if (remainingSize === 0) accumulator
      else {
        val newTree = Node(accumulator, Leaf(fillFromIndex(remainingSize - 1)))
        buildTreeLeftRecursive(remainingSize - 1, newTree)
      }
    }
    buildTreeLeftRecursive(size - 1, Leaf(fillFromIndex(size - 1)))

  pureTest("size") {
    val tree = Node(Leaf(1), Node(Leaf(2), Leaf(4)))

    expect.eql(tree.size, 3: BigInt)
  }

  pureTest("tree built of size 3") {
    val tree                       = buildTreeLeft(3, identity)
    val expectedTree: Tree[BigInt] = Node(Node(Leaf(2), Leaf(1)), Leaf(0))

    expect.eql(tree.size, 3: BigInt) and expect.eql(tree, expectedTree)
  }

  pureTest("size is stack-safe (supports large trees)") {
    val size = BigInt(1_000_000)
    val tree = buildTreeLeft(size, identity)

    expect.eql(tree.size, size)
  }

  pureTest("contains") {
    val tree = Node(Leaf(1), Node(Leaf(2), Leaf(4)))

    expect(tree.contains(1)) and expect(tree.contains(2)) and expect(!tree.contains(3))
  }

  pureTest("contains is stack-safe (supports large trees)") {
    val size = BigInt(1_000_000)
    val tree = buildTreeLeft(size, identity)

    expect(tree.contains(0)) and expect(tree.contains(size - 1))
  }

  pureTest("map") {
    val intTree    = Node(Leaf(1), Node(Leaf(2), Leaf(4)))
    val stringTree = Node(Leaf("1"), Node(Leaf("2"), Leaf("4")))

    expect.eql(intTree.map(_.toString), stringTree)
  }

}
