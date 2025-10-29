package br.com.studiotatto.demo.Core.User

import CreateUserRequestBaseDto
import br.com.studiotatto.demo.Core.User.Contract.IUserContract
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping(value = ["/user"])
class UserController(private val IUserContract: IUserContract) {

    @PostMapping("/user")
    fun createUser(@RequestBody @Validated createUserRequestDto: CreateUserRequestBaseDto) = this.IUserContract.createUser(createUserRequestDto)
}