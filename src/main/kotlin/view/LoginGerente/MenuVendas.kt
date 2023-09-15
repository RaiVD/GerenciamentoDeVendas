package view.LoginGerente

import model.InputUserModel
import service.table.TableClienteService
import service.table.TableProdutoService
import service.table.TableVendaService

class MenuVendas {
    private val inputUserModel = InputUserModel()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuGerente()
                1 -> deletarVenda()
                2 -> editarVenda()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }
    fun deletarVenda(){
        TableVendaService.listVendas()
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        TableVendaService.deleteVenda(id)
    }
    fun editarVenda(){
        TableVendaService.listVendas()
        val id = inputUserModel.readIntFromUser("ID do Produto: ")
        val quantidade = inputUserModel.readIntFromUser("Quantidade: ")
        TableVendaService.updateVendaQuantidade(id, quantidade)
    }
    private fun printMenu() {
        println("\n0. Menu Vendedor | " +
                "1. Deletar Venda | " +
                "2. Editar Vendas"
        )
    }
}