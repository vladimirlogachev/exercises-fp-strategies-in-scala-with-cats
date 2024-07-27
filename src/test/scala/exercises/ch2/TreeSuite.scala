package exercises.ch2

import weaver._

object TreeSuite extends SimpleIOSuite {
  import Tree._

  pureTest("size") {
    val tree = Node(Leaf(1), Node(Leaf(2), Leaf(4)))

    expect.eql(tree.size, 3: BigInt)
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
