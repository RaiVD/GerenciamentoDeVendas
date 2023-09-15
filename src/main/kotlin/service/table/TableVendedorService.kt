package service.table

import connection.Connect
import model.ValidDataBaseModel
import java.sql.SQLException

class TableVendedorService {
    companion object {
        var connection = Connect().creatConnect()

        fun addVendedor(nome_vendedor: String, email_vendedor: String, cpf: String, salario: Double, senha: Int) {
            try {
                if (!ValidDataBaseModel.isValidVendedorInfo(nome_vendedor, email_vendedor, cpf)) {
                    println("As informações do vendedor não podem estar vazias ou nulas.")
                    return
                }
                if (!ValidDataBaseModel.isValidEmail(email_vendedor)){
                    println("Email inválido")
                    return
                }
                val sql = "INSERT INTO vendedor (nome_vendedor, email_vendedor, cpf_vendedor, salario_vendedor, senha) VALUES ('$nome_vendedor', '$email_vendedor', '$cpf', $salario, $senha)"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Vendedor $nome_vendedor adicionado com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun deleteVendedor(id: Int) {
            if (!ValidDataBaseModel.isValidVendaId(id)) {
                println("ID de Vendedor inválido!")
                return
            }

            try {
                val consultaVendasSql = "SELECT id_venda FROM venda WHERE id_vendedor = $id"
                val statementConsulta = TableClienteService.connection.createStatement()
                val resultadoConsulta = statementConsulta.executeQuery(consultaVendasSql)

                val idsVendasRelacionadas = mutableListOf<Int>()

                while (resultadoConsulta.next()) {
                    val idVendaRelacionada = resultadoConsulta.getInt("id_venda")
                    idsVendasRelacionadas.add(idVendaRelacionada)
                }

                for (idVenda in idsVendasRelacionadas) {
                    val atualizarVendaSql = "UPDATE venda SET id_vendedor = NULL WHERE id_venda = $idVenda"
                    val statementAtualizacao = TableClienteService.connection.createStatement()
                    statementAtualizacao.executeUpdate(atualizarVendaSql)
                }

                val sql = "DELETE FROM vendedor WHERE id_vendedor = $id"
                val statement = TableClienteService.connection.createStatement()
                statement.executeUpdate(sql)
                println("Vendedor deletado com sucesso!")

            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun listeVendasDosVendedoresExcluidos() {
            val sql = """
        SELECT id_venda, id_cliente, id_vendedor, id_produto, qtd_produto, preco_total
        FROM venda
        WHERE id_vendedor IS NULL
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
        // Função para atualizar um vendedor
        fun updateVendedor(id: Int, email_vendedor: String,salario: Double) {
            if (!ValidDataBaseModel.isValidVendedorId(id)) {
                println("ID de vendedor inválido!")
                return
            }
            try {
                // Validação de dados aqui, se necessário

                val sql = "UPDATE vendedor SET email_vendedor='$email_vendedor', salario_vendedor=$salario WHERE id_vendedor=$id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Vendedor $id atualizado com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun listVendedores() {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT id_vendedor, nome_vendedor, email_vendedor, cpf_vendedor, salario_vendedor FROM vendedor")

            try {
                while (resultSet.next()) {
                    val id_vendedor = resultSet.getInt("id_vendedor")
                    val nome_vendedor = resultSet.getString("nome_vendedor")
                    val email_vendedor = resultSet.getString("email_vendedor")
                    val cpf = resultSet.getString("cpf_vendedor")
                    val salario = resultSet.getDouble("salario_vendedor")

                    println("ID: $id_vendedor | Nome: $nome_vendedor | Email: $email_vendedor | CPF: $cpf | Salário: $salario")
                }
                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun listVendedorById(id: Int) {
            if (!ValidDataBaseModel.isValidVendedorId(id)) {
                println("ID de vendedor inválido!")
                return
            }
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM vendedor WHERE id_vendedor=$id")

            try {
                while (resultSet.next()) {
                    val id_vendedor = resultSet.getInt("id_vendedor")
                    val nome_vendedor = resultSet.getString("nome_vendedor")
                    val email_vendedor = resultSet.getString("email_vendedor")
                    val cpf = resultSet.getString("cpf_vendedor")
                    val salario = resultSet.getDouble("salario_vendedor")

                    println("ID: $id_vendedor | Nome: $nome_vendedor | Email: $email_vendedor | CPF: $cpf | Salário: $salario")
                }
                resultSet.close()
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}
