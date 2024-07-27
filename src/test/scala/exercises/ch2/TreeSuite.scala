package exercises.ch2

import scala.language.strictEquality
import weaver._

object TreeSuite extends SimpleIOSuite {
  import Tree._

  pureTest("size") {
    val tree = Node(Leaf(1), Node(Leaf(2), Leaf(4)))

    expect(tree.size == 3)
  }

  pureTest("contains") {
    val tree = Node(Leaf(1), Node(Leaf(2), Leaf(4)))

    expect(tree.contains(1)) && expect(tree.contains(2)) && expect(!tree.contains(3))
  }

  pureTest("map") {
    val intTree    = Node(Leaf(1), Node(Leaf(2), Leaf(4)))
    val stringTree = Node(Leaf("1"), Node(Leaf("2"), Leaf("4")))

    expect(intTree.map(_.toString) == stringTree)
  }

}
