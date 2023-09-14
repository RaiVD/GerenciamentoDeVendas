package service

import connection.Connect
import model.ValidDataBaseModel
import java.sql.SQLException

class TableVendaService {
    companion object {
        var connection = Connect().creatConnect()

        fun addVenda(id_cliente: Int, id_vendedor: Int, id_produto: Int, qtd_produto: Int, preco_total: Double) {
            try {
                val sql = "INSERT INTO venda (id_cliente, id_vendedor, id_produto, qtd_produto, preco_total) VALUES ($id_cliente, $id_vendedor, $id_produto, $qtd_produto, $preco_total)"
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

        fun listSpecificVenda(id: Int) {
            if (!ValidDataBaseModel.isValidVendaId(id)) {
                println("ID de venda inválido!")
                return
            }
            val sql = "SELECT * FROM venda WHERE id_venda=$id"
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

        fun updateVenda(id: Int, id_cliente: Int, id_vendedor: Int, id_produto: Int, qtd_produto: Int, preco_total: Double) {
            if (!ValidDataBaseModel.isValidVendaId(id)) {
                println("ID de venda inválido!")
                return
            }
            try {
                val sql = "UPDATE venda SET id_cliente=$id_cliente, id_vendedor=$id_vendedor, id_produto=$id_produto, qtd_produto=$qtd_produto, preco_total=$preco_total WHERE id_venda=$id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Venda com ID $id atualizada com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}
