package tdd.user

case class User(
  name: String,
  companyName: String,
  email: String,
  password: String
){
  def validate(): Boolean = password.length >= 8

  def signature(): String = {
    if (companyName.nonEmpty) {
      s"$companyName: 田中太郎 NG"
    } else {
      "田中太郎 NG"
    }
  }
}
