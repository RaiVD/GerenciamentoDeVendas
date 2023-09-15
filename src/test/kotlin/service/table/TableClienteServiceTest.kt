package service.table

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TableClienteServiceTest {
    private lateinit var clienteService: TableClienteService.Companion
    private lateinit var mockConnection: Connection
    private lateinit var mockStatement: Statement
    private lateinit var mockPreparedStatement: PreparedStatement
    private lateinit var mockResultSet: ResultSet

    @BeforeEach
    fun setUp() {
        mockConnection = mock(Connection::class.java)
        mockStatement = mock(Statement::class.java)
        mockPreparedStatement = mock(PreparedStatement::class.java)
        mockResultSet = mock(ResultSet::class.java)

        clienteService = TableClienteService
        clienteService.connection = mockConnection
    }

    @Test
    fun testAddClienteValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        clienteService.addCliente("João", "joao@example.com", "12345678901", "Rua A")
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testAddClienteInvalidInfo() {
        clienteService.addCliente("", "joao@example.com", "12345678901", "Rua A")
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testAddClienteInvalidEmail() {
        clienteService.addCliente("João", "emailinvalido", "12345678901", "Rua A")
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testListClientes() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("id_cliente")).thenReturn(1, 2)
        `when`(mockResultSet.getString("nome_cliente")).thenReturn("Maria", "Pedro")
        `when`(mockResultSet.getString("email_cliente")).thenReturn("maria@example.com", "pedro@example.com")
        `when`(mockResultSet.getString("cpf_cliente")).thenReturn("98765432109", "87654321098")
        `when`(mockResultSet.getString("endereco_cliente")).thenReturn("Rua B", "Rua C")

        clienteService.listCliente()

        verify(mockStatement, times(1)).executeQuery(anyString())
    }

    @Test
    fun testUpdateClienteValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        clienteService.updateCliente(1, "novoemail@example.com", "Nova Rua")
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testUpdateClienteInvalidId() {
        clienteService.updateCliente(-1, "novoemail@example.com", "Nova Rua")
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testUpdateClienteInvalidEmail() {
        clienteService.updateCliente(1, "emailinvalido", "Nova Rua")
        verify(mockStatement, never()).executeUpdate(anyString())
    }
}
