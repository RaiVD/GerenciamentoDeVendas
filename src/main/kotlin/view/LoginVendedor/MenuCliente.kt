package view.LoginVendedor

import model.InputUserModel
import service.table.TableClienteService

class MenuCliente {
    private val inputUserModel = InputUserModel()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuVendedor()
                1 -> addCliente()
                2 -> listarCliente()
                3 -> deletarCliente()
                4 -> editarCliente()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    fun addCliente(){
        val nome_produto = inputUserModel.readStringFromUser("Nome: ")
        val email_cliente = inputUserModel.readStringFromUser("E-mail: ")
        val cpf_cliente = inputUserModel.readStringFromUser("CPF: ")
        val endereco_cliente = inputUserModel.readStringFromUser("Endereço: ")
        TableClienteService.addCliente(nome_produto, email_cliente, cpf_cliente, endereco_cliente)
    }
    fun listarCliente(){
        TableClienteService.listCliente()
    }
    fun deletarCliente(){
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        TableClienteService.deleteCliente(id)
    }
    fun editarCliente(){
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        val email_cliente = inputUserModel.readStringFromUser("E-mail: ")
        val endereco_cliente = inputUserModel.readStringFromUser("Endereço: ")
        TableClienteService.updateCliente(id, email_cliente, endereco_cliente)
    }

    private fun printMenu() {
        println("\n0. Menu Vendedor | " +
                "1. Cadastar Cliente | " +
                "2. Clientes Cadastrados | " +
                "3. Deletar Cliente | " +
                "4. Editar Endereço do Cliente | "
        )
    }
}