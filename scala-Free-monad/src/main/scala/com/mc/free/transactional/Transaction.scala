package com.mc.free.transactional

trait Functor[F[_]]{
	def map[A, B](a: F[A])(f: A => B): F[B] 
}

class Transaction[F[_], A](implicit F: Functor[F])
{
	val em = new EntityManager()
	em.start()
	def flatMap[B](f: A => Transaction[F, B]): Transaction[F, B] = 
		this match
		{
			case CommitTransaction(k) => 
				em.commit()
				map(k)
			case RollbackTransaction(k) => 
				em.rollback()
				map(k)
			case MoreTransaction(k) => 
				em.execute()
				MoreTransaction(F.map(k)(_ flatMap f))
		}
	def map[B](f: A => B): Transaction[F, B] =
		flatMap{
			x => 
			em.close()
			TransactionDone(f(x))
		}
}

sealed case class TransactionDone[F[_]: Functor, A](a: A) extends Transaction[F, A]
sealed case class RollbackTransaction[F[_]: Functor, A](k: F[Transaction[F, A]]) extends Transaction[F, A]
sealed case class CommitTransaction[F[_]: Functor, A](k: F[Transaction[F, A]]) extends Transaction[F, A]
sealed case class MoreTransaction[F[_]: Functor, A](k: F[Transaction[F, A]]) extends Transaction[F, A]