package view.LoginVendedor

import model.InputUserModel

class MenuCliente {
    private val inputUserModel = InputUserModel()
    fun start() {
        println("\n========================== RH SimCity ============================")
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuVendedor().start()
                1 -> {}
                2 -> {}
                3 -> {}
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    private fun printMenu() {
        println("0. Menu Vendedor | " +
                "1. Cadastar Cliente | " +
                "2. Clientes Cadastrados | " +
                "3. Deletar Cliente | " +
                "4. Editar Endereço do Cliente | "
        )
    }
}