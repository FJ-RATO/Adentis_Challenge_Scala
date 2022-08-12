import scala.runtime.stdLibPatches.Predef.assert

class ChallengeTest extends munit.FunSuite{
  import Challenge.*
  import java.time._

  trait TestSets:
    //dates for products
    val dateProd0 = LocalDate.parse("2022-07-12")
    val dateProd1 = LocalDate.parse("2022-04-25")
    val dateProd2 = LocalDate.parse("2022-01-12")
    val dateProd3 = LocalDate.parse("2021-01-12")
    //dates for orders
    val dateOrder0 = LocalDate.parse("2022-08-11")
    val dateOrder1 = LocalDate.parse("2022-08-05")
    val dateOrder2 = LocalDate.parse("2022-07-30")
    //products
    val prod0 = new Product(0,dateProd0)
    val prod1 = new Product(1,dateProd1)
    val prod2 = new Product(2,dateProd2)
    val prod3 = new Product(3,dateProd3)
    //orders
    val order0 = new Order(0,dateOrder0,List(prod0,prod1,prod1,prod2,prod2,prod2,prod3,prod3,prod3,prod3))
    val order1 = new Order(1,dateOrder1,List(prod3,prod1))
    val order2 = new Order(2, dateOrder2, List(prod0,prod0,prod0,prod0,prod0,prod0))

  test("Date filter") {
    new TestSets :
      assert(Main.filterOrder(List(order0, order1, order2), LocalDate.parse("2022-08-10"), LocalDate.parse("2022-08-12")) == List(order0))
      assert(Main.filterOrder(List(order0, order1, order2), LocalDate.parse("2022-08-04"), LocalDate.parse("2022-08-12")) == List(order0,order1))
      assert(Main.filterOrder(List(order0, order1, order2), LocalDate.parse("2021-08-10"), LocalDate.parse("2022-08-12")) == List(order0,order1,order2))
  }

  test("Group products"){
    new TestSets:
      println(Main.groupProduct(List(order0,order1,order2)))
      assert(Main.groupProduct(List(order0)) == List[(String, Int)](("1-3 months", 1), ("4-6 months", 2), ("7-12 months", 3), (">12 months", 4)))
      assert(Main.groupProduct(List(order1)) == List[(String, Int)](("1-3 months", 0), ("4-6 months", 1), ("7-12 months", 0), (">12 months", 1)))
      assert(Main.groupProduct(List(order2)) == List[(String, Int)](("1-3 months", 6), ("4-6 months", 0), ("7-12 months", 0), (">12 months", 0)))
      assert(Main.groupProduct(List(order0,order1,order2)) == List[(String, Int)](("1-3 months", 7), ("4-6 months", 3), ("7-12 months", 3), (">12 months", 5)))
  }
}


