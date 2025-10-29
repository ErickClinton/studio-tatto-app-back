package br.com.studiotatto.demo.exceptions.User

import br.com.studiotatto.demo.exceptions.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class UserBadCredentialsException(private val detail: String) : UserException(detail) {
    override fun toProblemDetail(): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED)
        pd.detail = "Verify your email or password"
        pd.detail = detail

        return pd
    }
}