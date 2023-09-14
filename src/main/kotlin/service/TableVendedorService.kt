package service

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
                val sql = "INSERT INTO vendedor (nome_vendedor, email_vendedor, cpf, salario, senha) VALUES ('$nome_vendedor', '$email_vendedor', '$cpf', $salario, $senha)"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Vendedor $nome_vendedor adicionado com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun deleteVendedor(id: Int) {
            if (!ValidDataBaseModel.isValidVendedorId(id)) {
                println("ID de vendedor inválido!")
                return
            }
            val sql = "DELETE FROM vendedor WHERE id_vendedor=$id"

            try {
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Vendedor deletado com sucesso!")
                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        // Função para atualizar um vendedor
        fun updateVendedor(id: Int, nome_vendedor: String, email_vendedor: String, cpf: String, salario: Double) {
            if (!ValidDataBaseModel.isValidVendedorId(id)) {
                println("ID de vendedor inválido!")
                return
            }
            try {
                // Validação de dados aqui, se necessário

                val sql = "UPDATE vendedor SET nome_vendedor='$nome_vendedor', email_vendedor='$email_vendedor', cpf='$cpf', salario=$salario WHERE id_vendedor=$id"
                val statement = connection.createStatement()
                statement.executeUpdate(sql)
                println("Vendedor $id atualizado com sucesso!")
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        fun listVendedores() {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT id_vendedor, nome_vendedor, email_vendedor, cpf, salario FROM vendedor")

            try {
                while (resultSet.next()) {
                    val id_vendedor = resultSet.getInt("id_vendedor")
                    val nome_vendedor = resultSet.getString("nome_vendedor")
                    val email_vendedor = resultSet.getString("email_vendedor")
                    val cpf = resultSet.getString("cpf")
                    val salario = resultSet.getDouble("salario")

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
                    val cpf = resultSet.getString("cpf")
                    val salario = resultSet.getDouble("salario")

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
