package br.com.studiotatto.demo.exceptions.User

import br.com.studiotatto.demo.exceptions.UserException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class UserAlreadyExistException(private val detail: String) : UserException(detail) {
    override fun toProblemDetail(): ProblemDetail {
        val pd = ProblemDetail.forStatus(HttpStatus.CONFLICT)
        pd.title = "User Already Exist"
        pd.detail = detail
        return pd
    }
}