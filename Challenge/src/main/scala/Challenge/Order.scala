package Challenge
import java.time._

class Order(identifier:Int,orderDate:LocalDate,productList:List[Product]) {
  val id = identifier
  val date = orderDate
  val products = productList
}
