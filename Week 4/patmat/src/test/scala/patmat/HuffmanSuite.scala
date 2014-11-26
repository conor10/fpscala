package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times char") {
    assert(times(List('a', 'b', 'c', 'c', 'b', 'a', 'a', 'd')) === List(('a', 3), ('b', 2), ('c', 2), ('d', 1)))
  }

  test("times empty") {
    assert(times(List()) === List())
  }

  test("ordered leaf list") {
    assert(makeOrderedLeafList(List(('a', 3), ('b', 2), ('c', 2), ('d', 1))) ===
      List(Leaf('d', 1), Leaf('c', 2), Leaf('b', 2), Leaf('a', 3)))
  }

  test("invalid singleton list 1") {
    assert(singleton(List(Leaf('d', 1), Leaf('c', 2), Leaf('b', 2), Leaf('a', 3))) === false)
  }

  test("invalid singleton list 2") {
    assert(singleton(List(Leaf('d', 1), Leaf('c', 2))) === false)
  }

  test("empty singleton list") {
    assert(singleton(List()) === false)
  }

  test("valid singleton list") {
    assert(singleton(List(Leaf('d', 1))) === true)
  }

  test("combine empty list") {
    assert(combine(List()) === List())
  }

  test("combine single element") {
    assert(combine(List(Leaf('d', 1))) == List(Leaf('d', 1)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decoded secret") {
    assert(decodedSecret === "huffmanestcool".toList)
  }

//  test("createCodeTree") {
//    assert(createCodeTree("huffmanestcool".toList) === frenchCode)
//  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("large decode and encode") {
    new TestTrees {
      assert(decode(frenchCode, encode(frenchCode)("huffmanestcool".toList)) === "huffmanestcool".toList)
    }
  }

  test("large decode and quick encode") {
    new TestTrees {
      assert(decode(frenchCode, quickEncode(frenchCode)("huffmanestcool".toList)) === "huffmanestcool".toList)
    }
  }

  test("another large decode and encode") {
    new TestTrees {
      val msg = "The Huffman encoding of this message should be three hundred and fifty-two bits long".toList
      assert(encode(createCodeTree(msg))(msg).size === 352)
    }
  }

}
