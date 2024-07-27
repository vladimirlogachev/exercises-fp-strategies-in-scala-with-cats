package exercises.ch2

import cats.implicits._
import weaver._

object TreeSuite extends SimpleIOSuite {
  import Tree._

  def buildTree[A](size: BigInt, fillFromSize: BigInt => A): Tree[A] =
    @scala.annotation.tailrec
    def buildTreeRecursive(remainingSize: BigInt, accumulator: Tree[A]): Tree[A] = {
      if (remainingSize === 0) accumulator
      else {
        val newTree = Node(accumulator, Leaf(fillFromSize(remainingSize)))
        buildTreeRecursive(remainingSize - 1, newTree)
      }
    }
    buildTreeRecursive(size - 1, Leaf(fillFromSize(size)))

  pureTest("size") {
    val tree = Node(Leaf(1), Node(Leaf(2), Leaf(4)))

    expect.eql(tree.size, 3: BigInt)
  }

  pureTest("tree built of size 3") {
    val tree = buildTree(3, _.toString)

    expect.eql(tree.size, 3: BigInt) and expect.eql(tree, Node(Node(Leaf("3"), Leaf("2")), Leaf("1")))
  }

  pureTest("size is stack-safe (supports large trees)") {
    val size = BigInt(1_000_000)
    val tree = buildTree(size, _.toString)

    expect.eql(tree.size, size)
  }

  pureTest("contains") {
    val tree = Node(Leaf(1), Node(Leaf(2), Leaf(4)))

    expect(tree.contains(1)) and expect(tree.contains(2)) and expect(!tree.contains(3))
  }

  pureTest("map") {
    val intTree    = Node(Leaf(1), Node(Leaf(2), Leaf(4)))
    val stringTree = Node(Leaf("1"), Node(Leaf("2"), Leaf("4")))

    expect.eql(intTree.map(_.toString), stringTree)
  }

}
