package Challenge
import java.time.*
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {
    val orders = List[Order]()
    val startFilter = LocalDate.parse(args(0))
    val endFilter = LocalDate.parse(args(1))
    printer(groupProduct(filterOrder(orders,startFilter,endFilter)))
  }
  /**
   * [2021-xx-xx  ->  2022-xx-xx]
   * @param orders      Raw list of orders
   * @param startFilter old date
   * @param endFilter   new date
   * @return List of orders between startFilter and endFilter
   */
  def filterOrder(orders: List[Order], startFilter: LocalDate, endFilter: LocalDate): List[Order] = {
    orders.filter(x => (x.date.isAfter(startFilter) && (x.date.isBefore(endFilter))))
  }

  /**
   *
   * @param orders List[Order]
   * @return A list of [String,Int] where it says the filter and the corresponding value
   */
  def groupProduct(orders: List[Order]): List[(String, Int)] = {
    val today = LocalDate.now()
    val filters = Array[LocalDate](
      today.minusMonths(12),
      today.minusMonths(6),
      today.minusMonths(3)
    )

    @tailrec
    def grouper(orders: List[Order], filters: Array[LocalDate], older12: Int, younger12: Int, younger6: Int, younger3: Int): Array[Int] = orders match {
      case Nil => Array[Int](younger3, younger6, younger12, older12)
      case order :: xs => {

        val accMore12 = older12 + order.products.count(product => product.date.isBefore(filters(0)))
        val accLess12 = younger12 + order.products.count(product => product.date.isAfter(filters(0)) && product.date.isBefore(filters(1)))
        val accLess6 = younger6 + order.products.count(product => product.date.isAfter(filters(1)) && product.date.isBefore(filters(2)))
        val accLess3 = younger3 + order.products.count(product => product.date.isAfter(filters(2)))

        grouper(xs, filters, older12 + accMore12, younger12 + accLess12, younger6 + accLess6, younger3 + accLess3)
      }
    }

    val groups = grouper(orders, filters, 0, 0, 0, 0)
    List[(String, Int)](
      ("1-3 months", groups(0)),
      ("4-6 months", groups(1)),
      ("7-12 months", groups(2)),
      (">12 months", groups(3))
    )
  }

  @tailrec
  def printer(groupedProducts: List[(String, Int)]): List[(String, Int)] = {
    groupedProducts match {
      case Nil => Nil
      case x :: xs => {
        println(x._1 + x._2 + "orders")
        printer(xs)
      }
    }
  }
}
