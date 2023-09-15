package view.LoginGerente

import model.InputUserModel
import view.LoginVendedor.MenuVendedor

class MenuGerente {
    private val inputUserModel = InputUserModel()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> println("Encerrando o programa...")
                1 -> MenuConsultas().start()
                2 -> MenuVendas().start()
                3 -> MenuVendedor().start()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    private fun printMenu() {
        println("\n0. Menu Gerente | " +
                "1. Consultas | " +
                "2. Vendas | " +
                "3. Vendedores")
    }
}