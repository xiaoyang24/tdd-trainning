package tdd.user

case class User(
  name: String,
  companyName: String,
  email: String,
  password: String
){
  def validate(): String = if (password.length >= 8) "OK" else "NG"

  def signature(): String = {
    if (companyName.nonEmpty) {
      s"$companyName: $name ${validate()}"
    } else {
      s"$name ${validate()}"
    }
  }
}
