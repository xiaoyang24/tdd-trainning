package tdd.user

import org.scalatest._

class UserSpec extends FunSpec with Matchers {

  def createCase(password: String) = User(name = "", companyName = "", email = "", password = password)

  describe("ユーザー") {
    describe("検証できる") {
      it("パスワード8文字以上の場合") {
        // Given
        val user = createCase("12345678")
        // When
        val actual = user.validate()
        // Then
        assert(actual)
      }

      it("パスワードが8文字未満の場合") {
        // Given
        val user = createCase("1234567")
        // When
        val actual = user.validate()
        // Then
        assert(!actual)
      }
    }

    describe("署名が取得できる") {
      it("会社名がない場合") {
        // Given
        val user = User(name = "田中太郎", companyName = "", email = "", password = "1234567")
        // When
        val signature = user.signature()
        // Then
        assert(signature == "田中太郎 NG")
      }

      it("会社名がある場合") {
        // Given
        val user = User(name = "田中太郎", companyName = "株式会社タナカ", email = "", password = "1234567")
        // When
        val signature = user.signature()
        // Then
        assert(signature == "株式会社タナカ: 田中太郎 NG")
      }
    }
  }

}
