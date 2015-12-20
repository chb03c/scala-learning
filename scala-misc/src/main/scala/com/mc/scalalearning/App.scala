package com.mc.scalalearning

import java.util.Date

object App {
  
  def pimpMyLib(args : Array[String]) {
    	val memberType = MemberType(7L, "Member", 1)
    	println(memberType.calculateExpirationDate())
  }

  def typeClasses()
  {

  }

  case class MemberType(id: Long, name: String, memberTypeType: Int)

  implicit class MemberTypeHelper(memberType: MemberType) extends AnyVal{
  	 def calculateExpirationDate(): Date = {
  	 		new Date()
  	 }
  }
}
