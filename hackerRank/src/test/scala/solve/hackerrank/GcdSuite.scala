package solve.hackerrank

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite

/**
  * @author naveenede
  */
@RunWith(classOf[JUnitRunner])
class GcdSuite extends FunSuite{

  test("Gcd ") {
    assert(Gcd.findGcd(17, 19) === 1)
  }
}
