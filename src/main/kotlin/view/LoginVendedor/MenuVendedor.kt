package view.LoginVendedor

import model.InputUserModel
import view.MenuPrincipalView

class MenuVendedor {
    private val inputUserModel = InputUserModel()
    fun start() {
        println("\n========================== RH SimCity ============================")
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuPrincipalView()
                1 -> MenuEstoque().start()
                2 -> MenuVenda().start()
                3 -> MenuCliente().start()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    private fun printMenu() {
        println("0. Menu Principal | " +
                "1. Estoques | " +
                "2. Vendas | " +
                "3. Clientes")
    }
}