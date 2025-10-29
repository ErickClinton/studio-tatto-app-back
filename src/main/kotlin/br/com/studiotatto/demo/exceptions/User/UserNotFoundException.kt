package br.com.studiotatto.demo.exceptions.User

import br.com.studiotatto.demo.exceptions.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class UserNotFoundException(private val detail: String) : UserException(detail) {
    override fun toProblemDetail(): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND)
        pd.title = "User not found"
        pd.detail = detail

        return pd
    }
}