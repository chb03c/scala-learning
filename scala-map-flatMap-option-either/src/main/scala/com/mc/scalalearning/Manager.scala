abstract class Manager
{
	def update(): Either[Error, Long]

	def validate(): Either[Error, Long]

	def create(): Either[Error, Long]

	def delete(): Either[Error, Long]
}