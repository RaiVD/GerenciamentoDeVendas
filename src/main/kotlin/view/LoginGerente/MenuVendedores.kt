package view.LoginGerente

import model.InputUserModel
import service.table.TableClienteService
import service.table.TableProdutoService
import service.table.TableVendedorService

class MenuVendedores {
    private val inputUserModel = InputUserModel()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuGerente()
                1 -> addVendedor()
                2 -> listarVendedores()
                3 -> deletarVendedor()
                4 -> listarVendasDosVendedoresExcluidos()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    fun addVendedor(){
        val nome_produto = inputUserModel.readStringFromUser("Nome: ")
        val email_cliente = inputUserModel.readStringFromUser("E-mail: ")
        val cpf_cliente = inputUserModel.readStringFromUser("CPF: ")
        val salario = inputUserModel.readDoubleFromUser("Salario do Vendedor: ")
        val senha = inputUserModel.readIntFromUser("Cadastre uma senha: ")
        TableVendedorService.addVendedor(nome_produto, email_cliente, cpf_cliente, salario, senha)
    }
    fun listarVendedores(){
        TableVendedorService.listVendedores()
    }
    fun deletarVendedor(){
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        TableVendedorService.deleteVendedor(id)
    }
    fun listarVendasDosVendedoresExcluidos(){
        TableVendedorService.listeVendasDosVendedoresExcluidos()
    }
    private fun printMenu() {
        println("\n0. Menu Gerente | " +
                "1. Adicionar Vendedor | " +
                "2. Listar Vendedores | " +
                "3. Deletar Vendedor | " +
                "4. Listar Vendas Dos Vendedores Deletados")
    }
}