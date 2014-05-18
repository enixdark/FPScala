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

  test("union contains s1 and s2") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "u1")
      assert(contains(s, 2), "U2")
      assert(!contains(s, 3), "U3")
    }
  }
  
  test("intersect between s and t") {
    new TestSets {
      
      val s = union(s1, s2)
      val p = intersect(s,s1)
      assert(contains(p, 1), "I1")
      assert(!contains(p, 2), "I2")
      assert(!contains(p, 3), "I3")
    }
  }
  
  test("diffirent between s and t,the Set contains element in t diffrent element in s") {
    new TestSets {
      
      val s = union(s1, s2)      // Set -> {1,2}
      val d = union(s1, s3)		 // Set -> {1,3}
      val p = diff(s,d)			 // Set -> {2}
      assert(!contains(p, 1), "D1")
      assert(contains(p, 2), "D2")
      assert(!contains(p, 3), "D3")
    }
  }
  
  test("filter.............") {
    new TestSets {
      
      val s = union(union(s1, s2),s3)      // Set -> {1,2,3}
      val p = filter(s,singletonSet(2))    // Set -> {2}
      val c = filter(s,union(s1,s2)) // Set -> {1,2}
      assert(contains(p, 2), "F1")
      assert(!contains(p, 1), "F2")
      assert(contains(c, 1), "F3")
      assert(contains(c, 2), "F4")
      assert(!contains(c, 3), "F5")
    }
  }
  
  test("exists.............") {
    new TestSets {
      
      val s = union(union(s1, s2),s3)      // Set -> {1,2,3}
      
      assert(exists(s, singletonSet(1)), "E1")
      assert(exists(s, singletonSet(3)), "E2")
      assert(!exists(s, singletonSet(4)), "E3")
      
    }
  }
}
