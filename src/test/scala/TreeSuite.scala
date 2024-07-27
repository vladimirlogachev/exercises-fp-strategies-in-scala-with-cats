package exercises

import scala.language.strictEquality
import weaver._

object TreeSuite extends SimpleIOSuite {

  pureTest("size") {
    val tree = Tree.Node(Tree.Leaf(1), Tree.Node(Tree.Leaf(2), Tree.Leaf(4)))

    expect(tree.size == 3)
  }

  pureTest("contains") {
    val tree = Tree.Node(Tree.Leaf(1), Tree.Node(Tree.Leaf(2), Tree.Leaf(4)))

    expect(tree.contains(1)) && expect(tree.contains(2)) && expect(!tree.contains(3))
  }

  pureTest("map") {
    val intTree    = Tree.Node(Tree.Leaf(1), Tree.Node(Tree.Leaf(2), Tree.Leaf(4)))
    val stringTree = Tree.Node(Tree.Leaf("1"), Tree.Node(Tree.Leaf("2"), Tree.Leaf("4")))

    expect(intTree.map(_.toString) == stringTree)
  }

}
