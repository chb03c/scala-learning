package com.mc.scalalearning

class MemberTypeManager extends Manager
{
	def update(): Either[Error, Long] = Left(Error("Failed Dum Dum!"))

	def validate(): Either[Error, Long] = Right(7L)

	def create(): Either[Error, Long] = Left(Error("Failed to create"))

	def delete(): Either[Error, Long] = Right(8L)
}