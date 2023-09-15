package service.consultas

import connection.Connect
import model.ValidDataBaseModel
import service.table.TableClienteService
import java.sql.SQLException

class QueryInDatabaseService {
    var connection = Connect().creatConnect()

    fun listItensVendidosAcimaDe10() {
        val sql = """
            SELECT Venda.id_venda AS venda, Venda.preco_total AS preco
            FROM Venda
            WHERE preco_total > 10.00
        """.trimIndent()
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                val id_venda = resultSet.getInt("venda")
                val preco_total = resultSet.getDouble("preco")
                println("ID Venda: $id_venda | Preço: $preco_total")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

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

    fun deleteCliente(id: Int) {
        if (!ValidDataBaseModel.isValidClienteId(id)) {
            println("ID de cliente inválido!")
            return
        }

        try {
            val consultaVendasSql = "SELECT id_venda FROM venda WHERE id_cliente = $id"
            val statementConsulta = TableClienteService.connection.createStatement()
            val resultadoConsulta = statementConsulta.executeQuery(consultaVendasSql)

            val idsVendasRelacionadas = mutableListOf<Int>()

            while (resultadoConsulta.next()) {
                val idVendaRelacionada = resultadoConsulta.getInt("id_venda")
                idsVendasRelacionadas.add(idVendaRelacionada)
            }

            for (idVenda in idsVendasRelacionadas) {
                val atualizarVendaSql = "UPDATE venda SET id_cliente = NULL WHERE id_venda = $idVenda"
                val statementAtualizacao = TableClienteService.connection.createStatement()
                statementAtualizacao.executeUpdate(atualizarVendaSql)
            }

            val sql = "DELETE FROM cliente WHERE id_cliente = $id"
            val statement = TableClienteService.connection.createStatement()
            statement.executeUpdate(sql)
            println("Cliente deletado com sucesso!")

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

    fun listSalarioVendedoresOrdenados() {
        val sql = """
            SELECT salario_vendedor, nome_vendedor
            FROM vendedor 
            ORDER BY salario_vendedor DESC
            """.trimIndent()
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                val salario = resultSet.getDouble("salario_vendedor")
                val nome = resultSet.getString("nome_vendedor")
                println("Nome do Vendedor: $nome | Salario: $salario")
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}