package view.LoginGerente

import model.InputUserModel
import service.consultas.QueryInDatabaseService
import service.table.TableClienteService

class MenuConsultas {
    private val inputUserModel = InputUserModel()
    private val query = QueryInDatabaseService()
    fun start() {
        var option: Int
        do {
            printMenu()
            option = inputUserModel.readIntFromUser("Qual opção você deseja: ")

            when (option) {
                0 -> MenuGerente()
                1 -> vendas()
                2 -> deletarCliente()
                3 -> listVendas()
                4 -> clientesComEmailZup()
                5 -> listarSalario()
                else -> println("Opção inválida, tente novamente!")
            }
        } while (option != 0)
    }

    fun vendas() {
        query.listItensVendidosAcimaDe10()
    }

    fun deletarCliente() {
        TableClienteService.listCliente()
        val id = inputUserModel.readIntFromUser("ID do cliente: ")
        query.deleteCliente(id)
    }

    fun listVendas() {
        query.listeVendasDosClientesExcluidos()
    }

    fun clientesComEmailZup() {
        query.countUsersWithZupEmail()
    }
    fun listarSalario(){
        query.listSalarioVendedoresOrdenados()
    }
    private fun printMenu() {
        println(
            "\n0. Menu Gerente | " +
            "1. Vendas acima de R$10.00 | " +
            "2. Deletar Cliente (Mantendo Vendas Relacionadas) | " +
            "3. Listar Vendas dos Clientes Deletados | " +
            "4. Clientes com E-mail 'zup.com.br' | " +
            "5. Listar Salario dos Vendedores/Gerentes"
        )
    }
}
