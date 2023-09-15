package view

import model.InputUserModel
import model.ValidDataBaseModel
import view.LoginGerente.MenuGerente
import view.LoginVendedor.MenuVendedor

class MenuPrincipalView {
    private val inputUserModel = InputUserModel()
    fun start() {
        println("\n========================== RH SimCity ============================")
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> println("Encerrando o programa...")
                1 -> {
                    val email_user = inputUserModel.readStringFromUser("E-mail: ")
                    val password_user = inputUserModel.readIntFromUser("Senha: ")
                    if (ValidDataBaseModel.isValidVendedorCredentials(email_user, password_user)) {
                        MenuVendedor().start()
                    } else {
                        println("E-mail ou Senha invalidos!")
                    }
                }
                2 -> {
                    val email_user = inputUserModel.readStringFromUser("E-mail: ")
                    val password_user = inputUserModel.readIntFromUser("Senha: ")
                    if (ValidDataBaseModel.isValidGerenteCredentials(email_user, password_user)) {
                        MenuGerente().start()
                    } else {
                        println("E-mail ou Senha invalidos!")
                    }
                }
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    private fun printMenu() {
        println("\n0. Sair | 1. Login Vendedor | 2. Login Gerente")
    }
}