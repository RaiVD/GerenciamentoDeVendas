package view.LoginVendedor

import model.InputUserModel

class MenuVenda {
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
                "1. Cadastar Venda | " +
                "2. Listar Minhas Vendas | " +
                "3. Deletar Minhas Vendas | " +
                "4. Editar Minhas Vendas")
    }
}