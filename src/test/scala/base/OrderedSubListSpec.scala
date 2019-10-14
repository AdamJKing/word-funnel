package base

import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import base.word.funnel.OrderedSubset._

class OrderedSubListSpec extends FlatSpec {

  behavior of "orderedSubListOf"

  it should "identify sub-strings" in {
    "abc" orderedSubsetOf "abcd" shouldBe true
    "abc" orderedSubsetOf "qwerty" shouldBe false
  }

  it should "identify substrings even when they are non-contiguous" in {
    "abc" orderedSubsetOf "axbxcx" shouldBe true
  }

}
