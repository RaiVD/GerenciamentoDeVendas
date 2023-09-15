package view.LoginVendedor

import model.InputUserModel
import service.table.TableClienteService
import service.table.TableProdutoService
import service.table.TableVendaService

class MenuVenda {
    private val inputUserModel = InputUserModel()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuVendedor().start()
                1 -> addVenda()
                2 -> listarMinhasVendas()
                3 -> deletarVenda()
                4 -> editarVenda()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    fun addVenda(){
        TableClienteService.listCliente()
        println("-----------------------------------------------------------------------------------")
        TableProdutoService.listProdutos()

        val id_cliente = inputUserModel.readIntFromUser("ID Cliente: ")
        val id_vendedor = inputUserModel.readIntFromUser("ID Vendedor: ")
        val id_Produto = inputUserModel.readIntFromUser("ID Produto: ")
        val quantidade = inputUserModel.readIntFromUser("Quantidade: ")
        TableVendaService.addVenda(id_cliente,id_vendedor,id_Produto, quantidade)
    }
    fun listarMinhasVendas(){
        TableVendaService.listVendas()
    }
    fun deletarVenda(){
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        TableVendaService.deleteVenda(id)
    }
    fun editarVenda(){
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        val quantidade = inputUserModel.readIntFromUser("Quantidade: ")
        TableVendaService.updateVendaQuantidade(id, quantidade)
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