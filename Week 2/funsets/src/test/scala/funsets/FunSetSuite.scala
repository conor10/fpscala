package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    
    def twoAndFive: Set = x => x == 2 || x == 5
    def even: Set = x => x % 2 == 0
    def emptySet: Set = x => false
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("intersect contains certain elements") {
    new TestSets {
      val i1 = intersect(s1, s1)
      assert(contains(i1, 1), "Intersect 1, 1")
      assert(!contains(i1, 2), "Intersect 1, 2")
      
      val i2 = intersect(s1, s2)
      assert(!contains(i2, 1), "Intersect 2, 1")
      assert(!contains(i2, 2), "Intersect 2, 2")
    }
  }
  
  test("diff contains certain elements") {
    new TestSets {
      val d1 = diff(s1, s1)
      assert(!contains(d1, 1), "Diff 1, 1")
      
      val d2 = diff(s1, s2)
      assert(contains(d2, 1), "Diff 2, 1")
    }
  }
  
  test("filter contains elements accepted by predicate") {
    new TestSets {
      val predicate = (x: Int) => x > 1
      
      val f1 = filter(s1, predicate)
      assert(!contains(f1, 1), "Filter 1")
      
      val f2 = filter(s2, predicate)
      assert(contains(f2, 2), "Filter 2")
    }
  }
  
  test("forall predicate tests") {
    new TestSets {
    	assert(forall(s1, x => x > 0), "Forall 1")      
    	assert(!forall(s1, x => x < 0), "Forall 2")
    	assert(forall(twoAndFive, x => x == 2 || x == 5), "Forall 3")
    	assert(!forall(twoAndFive, x => x == 1 || x == 4), "Forall 4")
    	assert(forall(emptySet, x => x == x), "Forall 5") // predicate doesn't matter
    }
  }
  
  test("exists predicate tests") {
    new TestSets {
      assert(exists(s1, x => x > 0), "Exists 1")
      assert(!exists(s1, x => x < 0), "Exists 2")
      assert(exists(twoAndFive, x => x > 0), "Exists 3")
      assert(!exists(twoAndFive, x => x > 5), "Exists 4")
    }
  }
  
  test("map predicate test") {
    new TestSets {
      assert(contains(map(s1, x => x + 0), 1), "Map 1")
      assert(contains(map(s1, x => x + 1), 2), "Map 2")
      assert(contains(map(twoAndFive, x => x + 3), 5), "Map 3")
      assert(!contains(map(twoAndFive, x => x + 3), 6), "Map 4")
      assert(contains(map(twoAndFive, x => x + 3), 8), "Map 5")
    }
  }
}
