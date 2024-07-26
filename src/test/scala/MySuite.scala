package exercises

class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val intTree    = Tree.Node(Tree.Leaf(1), Tree.Node(Tree.Leaf(2), Tree.Leaf(4)))
    val stringTree = Tree.Node(Tree.Leaf("1"), Tree.Node(Tree.Leaf("2"), Tree.Leaf("4")))

    // size
    assertEquals(intTree.size, 3: BigInt)

    // contains
    assertEquals(intTree.contains(1), true)
    assertEquals(intTree.contains(2), true)
    assertEquals(intTree.contains(3), false)

    // map
    assertEquals(intTree.map(_.toString), stringTree)
  }

}
