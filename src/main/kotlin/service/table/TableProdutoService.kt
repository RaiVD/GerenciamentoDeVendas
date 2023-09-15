package service.table

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

        fun listProdutos() {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT id_produto, nome_produto, preco_unit FROM produto")

            try {
                while (resultSet.next()) {
                    val id_produto = resultSet.getInt("id_produto")
                    val nome_produto = resultSet.getString("nome_produto")
                    val preco_unit = resultSet.getDouble("preco_unit")

                    println("ID: $id_produto | Nome: $nome_produto | Preço Unitário: $preco_unit")
                }
                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

//        fun listarProdutoById(id: Int) {
//            if (!ValidDataBaseModel.isValidProdutoId(id)) {
//                println("ID de produto inválido!")
//                return
//            }
//            val statement = connection.createStatement()
//            val resultSet = statement.executeQuery("SELECT * FROM produto WHERE id_produto=$id")
//
//            try {
//                while (resultSet.next()) {
//                    val id_produto = resultSet.getInt("id_produto")
//                    val nome_produto = resultSet.getString("nome_produto")
//                    val preco_unit = resultSet.getDouble("preco_unit")
//
//                    println("ID: $id_produto | Nome: $nome_produto | Preço Unitário: $preco_unit")
//                }
//                resultSet.close()
//                statement.close()
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//        }

        fun updateProduto(id: Int, novoPrecoUnit: Double) {
            if (!ValidDataBaseModel.isValidProdutoId(id)) {
                println("ID de produto inválido!")
                return
            }
            try {
                val sql = "UPDATE produto SET preco_unit=$novoPrecoUnit WHERE id_produto=$id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Produto atualizado com sucesso!")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}
