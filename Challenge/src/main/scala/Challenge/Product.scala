package Challenge
import java.time._

class Product(identifier:Int,creationDate:LocalDate) extends Item{
  val id = identifier
  val date = creationDate
}
