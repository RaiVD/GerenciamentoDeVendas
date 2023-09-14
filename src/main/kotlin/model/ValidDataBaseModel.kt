package model

import connection.Connect
import java.sql.SQLException
import java.text.SimpleDateFormat
import java.util.*

class ValidDataBaseModel {
    companion object {
        private val connection = Connect().creatConnect()

        // Validar ID
        fun isValidStudentId(id: Int): Boolean {
            val sql = "SELECT COUNT(*) FROM students WHERE id=?"

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return false
        }

        fun isValidVendaId(id: Int): Boolean {
            val sql = "SELECT COUNT(*) FROM venda WHERE id=?"

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return false
        }

        fun isValidProdutoId(id: Int): Boolean {
            val sql = "SELECT COUNT(*) FROM produto WHERE id=?"

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return false
        }

        fun isValidVendedorId(id: Int): Boolean {
            val sql = "SELECT COUNT(*) FROM vendedor WHERE id = ?"

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return false
        }

        fun isValidClienteId(id: Int): Boolean {
            val sql = "SELECT COUNT(*) FROM cliente WHERE id=?"

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return false
        }

        //        //Validar Indormações nulas ou vazias
        fun isValidStudentInfo(name_student: String, date_of_birth: String, addrss: String): Boolean {
            return name_student.isNotBlank() && date_of_birth.isNotBlank() && addrss.isNotBlank()
        }

        fun isValidProdutoInfo(nome_produto: String): Boolean {
            return nome_produto.isNotBlank()
        }

        fun isValidVendedorInfo(nome_vendedor: String, email_vendedor: String, cpf: String): Boolean {
            return nome_vendedor.isNotBlank() && email_vendedor.isNotBlank() && cpf.isNotBlank()
        }

        fun isValidClienteInfo(nome_cliente: String, email_cliente: String, cpf: String, endereco_cliente: String): Boolean {
            return nome_cliente.isNotBlank() && email_cliente.isNotBlank() && cpf.isNotBlank() && endereco_cliente.isNotBlank()
        }

        // Validar entrada de email
        fun isValidEmail(email: String): Boolean {
            return email.contains("@")
        }

        fun parseDate(dataString: String): Date? {
            val formato = SimpleDateFormat("dd/MM/yyyy")
            return formato.parse(dataString)
        }

        fun isValidAdminCredentials(email_user: String, password_user: Int): Boolean {
            if (email_user.isBlank()) {
                println("O email do usuário e a senha não podem estar vazios.")
                return false
            }

            val sql = "SELECT COUNT(*) FROM administrator WHERE email_user=? AND password_user=?"

            try {
                val preparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, email_user)
                preparedStatement.setInt(2, password_user)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                val count = resultSet.getInt(1)

                resultSet.close()
                preparedStatement.close()

                return count > 0
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            return false
        }
    }
}