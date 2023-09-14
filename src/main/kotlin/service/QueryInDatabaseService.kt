package service

import connection.Connect
import model.ValidDataBaseModel
import java.sql.SQLException
import java.text.DecimalFormat

class QueryInDatabaseService {
    var connection = Connect().creatConnect()

    // Consulta: Itens vendidos acima de 10,00
    fun listItensVendidosAcimaDe10() {
        val sql = "SELECT * FROM venda WHERE preco_total > 10.00"
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                // Processar os resultados conforme necessário
            }
            resultSet.close()
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    // Consulta: Altere o valor do VALOR_TOTAL para zero onde for nulo
    fun updateValoresNulosParaZero() {
        val sql = "UPDATE venda SET preco_total = 0.00 WHERE preco_total IS NULL"
        try {
            val statement = connection.createStatement()
            statement.executeUpdate(sql)
            println("Valores nulos de VALOR_TOTAL atualizados para zero com sucesso!")
            statement.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    // Consulta: Salário dos vendedores, ordenados do maior para o menor
    fun listSalarioVendedoresOrdenados() {
        val sql = "SELECT salario FROM vendedor ORDER BY salario DESC"
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                val salario = resultSet.getDouble("salario")
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
        val sql = "SELECT COUNT(*) as total FROM cliente WHERE email_cliente LIKE '%zup.com.br'"
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
}