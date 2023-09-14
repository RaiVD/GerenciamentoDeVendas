package view.LoginVendedor

import model.InputUserModel

class MenuEstoque {
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
                "5. Adicionar Produtos ao Estoque | " +
                "6. Listar Estoque de Produtos | " +
                "7. Deletar Produto | " +
                "8. Editar produto ")
    }
}