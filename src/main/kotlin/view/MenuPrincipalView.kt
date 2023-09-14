package view

import model.InputUserModel
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
                1 -> MenuVendedor().start()
                2 -> MenuGerente().start()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    private fun printMenu() {
        println("0. Sair | 1. Login Vendedor | 2. Login Gerente")
    }
}