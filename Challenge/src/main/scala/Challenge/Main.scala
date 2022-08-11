package Challenge
import java.time._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {
    val orders = List[Order]()
    val startFilter = LocalDate.parse(args[0].toString())
    val endFilter = LocalDate.parse(args[1].toString())

    def filterOrder(orders:List[Order],startFilter:LocalDate,endFilter:LocalDate):List[Order] = ???
    def groupProduct(orders:List[Order]):List[(String,Int)] = ???
    
    @tailrec
    def printer(groupedProducts: List[(String, Int)]): List[(String,Int)] = {groupedProducts match {
      case Nil => Nil
      case x::xs => {
        println(x._1 + x._2 + "orders")
        printer(xs)
      }
    }}
    printer(groupProduct(filterOrder(orders,startFilter,endFilter)))
  }
}
