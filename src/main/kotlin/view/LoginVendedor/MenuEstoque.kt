package view.LoginVendedor

import model.InputUserModel
import service.table.TableProdutoService

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
                1 -> addProduto()
                2 -> listarProdutos()
                3 -> deletarProduto()
                4 -> editarProduto()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    fun addProduto(){
        val nome_produto = inputUserModel.readStringFromUser("Nome do Produto: ")
        val preco_unit = inputUserModel.readDoubleFromUser("Valor do Produto: ")
        TableProdutoService.addProduto(nome_produto, preco_unit)
    }
    fun listarProdutos(){
        TableProdutoService.listProdutos()
    }
    fun deletarProduto(){
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        TableProdutoService.deleteProduto(id)
    }
    fun editarProduto(){
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        val preco_unit = inputUserModel.readDoubleFromUser("Novo Valor do Produto: ")
        TableProdutoService.updateProduto(id, preco_unit)
    }
    private fun printMenu() {
        println("0. Menu Vendedor | " +
                "1. Adicionar Produtos ao Estoque | " +
                "2. Listar Estoque de Produtos | " +
                "3. Deletar Produto | " +
                "4. Editar produto ")
    }
}