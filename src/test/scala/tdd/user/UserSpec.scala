package tdd.user

import org.scalatest._

class UserSpec extends FunSpec with Matchers {

  def createUser(
                name: String = "",
                companyName: String = "",
                email: String = "",
                password: String = ""
                ) = User(name, companyName, email, password = password)

  describe("ユーザー") {
    describe("検証できる") {
      it("パスワード8文字以上の場合") {
        // Given
        val user = createUser(password = "12345678")
        // When
        val actual = user.validate()
        // Then
        assert(actual == "OK")
      }

      it("パスワードが8文字未満の場合") {
        // Given
        val user = createUser(password = "1234567")
        // When
        val actual = user.validate()
        // Then
        assert(actual == "NG")
      }
    }

    describe("署名が取得できる") {
      val userTable = Seq(("jumpei.toyoda", "12345678", "jumpei.toyoda OK"), ("fumiyasu.sumiya", "1234567", "fumiyasu.sumiya NG"))
      it("会社名がないかつバリデーションをチェック") {
        userTable.foreach{ case (name, password, expectedSignature) =>
          // Given
          val user = createUser(name = name, password = password)
          // When
          val signature = user.signature()
          // Then
          assert(signature == expectedSignature)
        }
      }

      it("会社名がある、かつパスワードが8文字以上の場合") {
        // Given
        val user = createUser(name = "田中太郎", companyName = "株式会社タナカ", password = "12345678")
        // When
        val signature = user.signature()
        // Then
        assert(signature == "株式会社タナカ: 田中太郎 OK")
      }

      it("会社名がある、かつパスワードが8文字未満の場合") {
        // Given
        val user = createUser(name = "田中太郎", companyName = "株式会社タナカ", password = "1234567")
        // When
        val signature = user.signature()
        // Then
        assert(signature == "株式会社タナカ: 田中太郎 NG")
      }
    }
  }

}
