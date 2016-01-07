package com.mc.scalalearning

class ProfileManager extends Manager
{
    def update(): Either[Error, Long] = Left(Error("Failed Dum Dum!"))

    def validate(): Either[Error, Long] = Left(Error("This is an invalid profile"))

    def create(): Either[Error, Long] = Right(10L)

    def delete(): Either[Error, Long] = Right(5L)
}