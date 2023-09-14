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
    }
}
