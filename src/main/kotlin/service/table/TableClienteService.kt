package service.table

import connection.Connect
import model.ValidDataBaseModel
import java.sql.SQLException

class TableClienteService {
    companion object {
        var connection = Connect().creatConnect()

        fun addCliente(nome_cliente: String, email_cliente: String, cpf: String, endereco_cliente: String) {
            try {
                if (!ValidDataBaseModel.isValidClienteInfo(nome_cliente, email_cliente, cpf, endereco_cliente)) {
                    println("As informações do cliente não podem estar vazias ou nulas.")
                    return
                }
                if (!ValidDataBaseModel.isValidEmail(email_cliente)) {
                    println("Email invalido")
                }
                val sql =
                    "INSERT INTO cliente (nome_cliente, email_cliente, cpf_cliente, endereco_cliente) VALUES ('$nome_cliente', '$email_cliente', '$cpf', '$endereco_cliente')"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente $nome_cliente adicionado com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun deleteCliente(id: Int) {
            if (!ValidDataBaseModel.isValidClienteId(id)) {
                println("ID de cliente inválido!")
                return
            }

            try {
                // Passo 1: Identificar registros de vendas relacionados ao cliente
                val consultaVendasSql = "SELECT id_venda FROM venda WHERE id_cliente = $id"
                val statementConsulta = connection.createStatement()
                val resultadoConsulta = statementConsulta.executeQuery(consultaVendasSql)

                val idsVendasRelacionadas = mutableListOf<Int>()

                while (resultadoConsulta.next()) {
                    val idVendaRelacionada = resultadoConsulta.getInt("id_venda")
                    idsVendasRelacionadas.add(idVendaRelacionada)
                }

                // Passo 2: Atualizar registros de vendas relacionados ao cliente
                for (idVenda in idsVendasRelacionadas) {
                    val atualizarVendaSql = "UPDATE venda SET id_cliente = NULL WHERE id_venda = $idVenda"
                    val statementAtualizacao = connection.createStatement()
                    statementAtualizacao.executeUpdate(atualizarVendaSql)
                }

                // Passo 3: Excluir o cliente
                val sql = "DELETE FROM cliente WHERE id_cliente = $id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente deletado com sucesso!")

            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun listCliente() {
            val statement = connection.createStatement()
            val resultSet =
                statement.executeQuery("SELECT id_cliente, nome_cliente, email_cliente, cpf_cliente, endereco_cliente FROM cliente")

            try {
                while (resultSet.next()) {
                    val id_cliente = resultSet.getInt("id_cliente")
                    val nome_cliente = resultSet.getString("nome_cliente")
                    val email_cliente = resultSet.getString("email_cliente")
                    val cpf = resultSet.getString("cpf_cliente")
                    val endereco_cliente = resultSet.getString("endereco_cliente")

                    println("ID: $id_cliente | Nome: $nome_cliente | Email: $email_cliente | CPF: $cpf | Endereço: $endereco_cliente")
                }
                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun updateCliente(id: Int,email:String,endereco:String) {
            try {
            if (!ValidDataBaseModel.isValidClienteId(id)) {
                println("ID de livro inválido!")
                return
            }
            if (!ValidDataBaseModel.isValidEmail(email)) {
                println("Email Invalido.")
                return
            }
                val sql =
                    "UPDATE cliente SET email_cliente='$email', endereco_cliente='$endereco' WHERE id_cliente=$id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Cliente com id $id atualizado com sucesso!")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun listSpecificCliente(id: Int) {
            if (!ValidDataBaseModel.isValidClienteId(id)) {
                println("ID de cliente inválido!")
                return
            }
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM cliente WHERE id_cliente=$id")

            try {
                while (resultSet.next()) {
                    val id_cliente = resultSet.getInt("id_cliente")
                    val nome_cliente = resultSet.getString("nome_cliente")
                    val email_cliente = resultSet.getString("email_cliente")
                    val cpf = resultSet.getString("cpf_cliente")
                    val endereco_cliente = resultSet.getString("endereco_cliente")

                    println("ID: $id_cliente | Nome: $nome_cliente | Email: $email_cliente | CPF: $cpf | Endereço: $endereco_cliente")
                }
                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}