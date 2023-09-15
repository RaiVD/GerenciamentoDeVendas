package service.table

import connection.Connect
import model.ValidDataBaseModel
import java.sql.SQLException

class TableVendaService {
    companion object {
        var connection = Connect().creatConnect()

        fun addVenda(id_cliente: Int, id_vendedor: Int, id_produto: Int, qtd_produto: Int) {
            try {
                ValidDataBaseModel.validarQtd(qtd_produto)
                val sql = """
                    INSERT INTO venda (id_cliente, id_vendedor, id_produto, qtd_produto, preco_total)
                    SELECT $id_cliente, $id_vendedor, $id_produto, $qtd_produto, Produto.preco_unit * $qtd_produto
                    FROM Produto
                    WHERE Produto.id_produto = $id_produto
                """.trimIndent()
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Venda adicionada com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun deleteVenda(id: Int) {
            if (!ValidDataBaseModel.isValidVendaId(id)) {
                println("ID de venda inválido!")
                return
            }
            val sql = "DELETE FROM venda WHERE id_venda=$id"

            try {
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Venda deletada com sucesso!")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun listVendas() {
            val sql = "SELECT id_venda, id_cliente, id_vendedor, id_produto, qtd_produto, preco_total FROM venda"
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
        fun updateVendaQuantidade(id_venda: Int, qtd_produto: Int) {
            try {
                ValidDataBaseModel.validarQtd(qtd_produto)
                if (!ValidDataBaseModel.isValidVendaId(id_venda)) {
                    println("ID de venda inválido!")
                    return
                }
                // Verificar se a venda existe antes de atualizar
                val verificaVenda = "SELECT COUNT(*) AS total FROM venda WHERE id_venda = $id_venda"
                val statementVerifica = connection.createStatement()
                val resultSetVerifica = statementVerifica.executeQuery(verificaVenda)
                resultSetVerifica.next()
                val totalVendas = resultSetVerifica.getInt("total")
                resultSetVerifica.close()
                statementVerifica.close()

                if (totalVendas == 0) {
                    println("Venda com ID $id_venda não encontrada.")
                    return
                }

                // Atualizar a quantidade do produto na venda
                val sql = "UPDATE venda SET qtd_produto = $qtd_produto, preco_total = qtd_produto * Produto.preco_unit " +
                        "FROM Produto WHERE venda.id_venda = $id_venda AND Produto.id_produto = venda.id_produto".trimIndent()

                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Quantidade do produto na venda com ID $id_venda atualizada com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}
