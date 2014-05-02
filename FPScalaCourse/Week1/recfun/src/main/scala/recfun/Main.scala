package recfun
import common._
import scala.collection.generic.Sorted

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    //the formula of pascal's triangle have already used in my code 
    //,from http://en.wikipedia.org/wiki/Pascal%27s_triangle#Calculating_a_row_or_diagonal_by_itself
    
    if(c==0 || r==0 || c==r ) 1 else pascal(c - 1,r - 1)+pascal(c ,r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceIter(chars: List[Char],count: Int): Int = {
      if(chars.isEmpty || count < 0 ) count
      else if (chars.head == '(') balanceIter(chars.tail, count + 1)
      else if (chars.head == ')') balanceIter(chars.tail, count - 1)
      else balanceIter(chars.tail, count )
    }
    
    //if(chars==null) false 
    //else 
    balanceIter(chars,0)==0
    
    
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def compare(x: Int,y: Int): Boolean = x > y
    
    def count(money: Int,coins: List[Int]) : Int = {
      if(money == 0) 1
      else if (money < 0 || coins.isEmpty) 0
      else if (money <= 0 && !coins.isEmpty) 0
      else count(money, coins.tail) + count(money - coins.head, coins)
    }
    
    count(money, coins.sortWith(compare))
    
    
  }
}
