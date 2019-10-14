package base.word.funnel

import scala.annotation.tailrec

object OrderedSubset {

  @tailrec
  def orderedSubsetOf[A](smaller: Seq[A], larger: Seq[A]): Boolean =
    (smaller == larger) ||
      ((smaller sizeCompare larger) <= 0) && {
        (smaller.toList, larger.toList) match {
          case (Nil, _)                     => true
          case (_, Nil)                     => false
          case (x :: xs, y :: ys) if x == y => orderedSubsetOf(xs, ys)
          case (xs, _ :: ys)                => orderedSubsetOf(xs, ys)
        }
      }

  implicit final class OrderedSubsetOf(smaller: String) {
    @inline def orderedSubsetOf(larger: String): Boolean =
      OrderedSubset.orderedSubsetOf(smaller, larger)
  }

}
