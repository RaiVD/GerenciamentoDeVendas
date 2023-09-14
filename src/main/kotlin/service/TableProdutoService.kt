package service

import connection.Connect
import model.ValidDataBaseModel
import java.sql.SQLException

class TableProdutoService {
    companion object {
        var connection = Connect().creatConnect()

        fun addProduto(nome_produto: String, preco_unit: Double) {
            try {
                if (!ValidDataBaseModel.isValidProdutoInfo(nome_produto)) {
                    println("As informações do produto não podem estar vazias ou nulas.")
                    return
                }
                val sql = "INSERT INTO produto (nome_produto, preco_unit) VALUES ('$nome_produto', $preco_unit)"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Produto $nome_produto adicionado com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun deleteProduto(id: Int) {
            if (!ValidDataBaseModel.isValidProdutoId(id)) {
                println("ID de produto inválido!")
                return
            }
            val sql = "DELETE FROM produto WHERE id_produto=$id"

            try {
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Produto deletado com sucesso!")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }


    }
}
