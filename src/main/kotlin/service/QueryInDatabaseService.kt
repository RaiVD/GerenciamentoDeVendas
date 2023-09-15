package service

import connection.Connect
import model.ValidDataBaseModel
import java.sql.SQLException
import java.text.DecimalFormat

class QueryInDatabaseService {
    var connection = Connect().creatConnect()

    // Consulta: Itens vendidos acima de 10,00
    fun listItensVendidosAcimaDe10() {
        val sql = """
            SELECT Venda.id_venda AS venda, Vanda.preco_total AS preco
            FROM Venda
            WHERE preco_total > 10.00
        """.trimIndent()
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                val id_venda = resultSet.getInt("venda")
                val preco_total = resultSet.getDouble("preco")
                println("Venda: $id_venda | Preço: $preco_total")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    // Consulta: Altere o valor do VALOR_TOTAL para zero onde for nulo
//    fun updateValoresNulosParaZero() {
//        val sql = """
//            UPDATE venda
//            SET preco_total = 0.00, qtd_produto = 0.00
//            WHERE preco_total IS NULL OR qtd_produto IS NULL
//            """.trimIndent()
//        try {
//            val statement = connection.createStatement()
//            statement.executeUpdate(sql)
//            println("Valores nulos de VALOR_TOTAL e QTD_PRODUTO atualizados para zero com sucesso!")
//            statement.close()
//        } catch (e: SQLException) {
//            e.printStackTrace()
//        }
//    }

    // Consulta: Salário dos vendedores, ordenados do maior para o menor
    fun listSalarioVendedoresOrdenados() {
        val sql = "SELECT salario_vendedor FROM vendedor ORDER BY salario_vendedor DESC"
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                val salario = resultSet.getDouble("salario_vendedor")
                println("Salário: $salario")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    // Excluir um cliente (substitua <ID_DO_CLIENTE_A_SER_EXCLUIDO> pelo ID real)
    fun deleteCliente(id: Int) {
        if (!ValidDataBaseModel.isValidClienteId(id)) {
            println("ID de cliente inválido!")
            return
        }
        val sql = "DELETE FROM cliente WHERE id_cliente=$id"

        try {
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Cliente com ID $id excluído com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    // Desafio: Pesquisar quantos usuários têm o email zup.com.br
    fun countUsersWithZupEmail() {
        val sql = "SELECT COUNT(*) AS total FROM cliente WHERE email_cliente LIKE '%zup.com.br'"
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            if (resultSet.next()) {
                val total = resultSet.getInt("total")
                println("Total de usuários com email zup.com.br: $total")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun listeVendasDosClientesExcluidos() {
        val sql = """
        SELECT id_venda, id_cliente, id_vendedor, id_produto, qtd_produto, preco_total
        FROM venda
        WHERE id_cliente IS NULL
        """.trimIndent()
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                val id_venda = resultSet.getInt("id_venda")
                val id_cliente = resultSet.getInt("id_cliente")
                val id_vendedor = resultSet.getInt("id_vendedor")
                val id_produto = resultSet.getInt("id_produto")
                val qtd_produto = resultSet.getInt("qtd_produto")
                val preco_total = resultSet.getDouble("preco_total")

                println("ID Venda: $id_venda | ID Cliente: $id_cliente | ID Vendedor: $id_vendedor | ID Produto: $id_produto | Quantidade: $qtd_produto | Preço Total: $preco_total")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}