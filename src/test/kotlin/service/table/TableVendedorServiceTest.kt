package service.table

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TableVendedorServiceTest {
    private lateinit var vendedorService: TableVendedorService.Companion
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

        vendedorService = TableVendedorService
        vendedorService.connection = mockConnection
    }

    @Test
    fun testAddVendedorValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        vendedorService.addVendedor("João", "joao@example.com", "123456789", 2000.0, 12345)
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testAddVendedorInvalidEmail() {
        vendedorService.addVendedor("Maria", "invalid_email", "987654321", 2500.0, 54321)
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testDeleteVendedorValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)
        `when`(mockResultSet.next()).thenReturn(true, false)
        `when`(mockResultSet.getInt("id_venda")).thenReturn(1)

        vendedorService.deleteVendedor(1)
        verify(mockStatement, times(2)).executeQuery(anyString())
        verify(mockStatement, times(2)).executeUpdate(anyString())
    }

    @Test
    fun testDeleteVendedorInvalidId() {
        vendedorService.deleteVendedor(-1)
        verify(mockStatement, never()).executeQuery(anyString())
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testListVendedores() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("id_vendedor")).thenReturn(1, 2)
        `when`(mockResultSet.getString("nome_vendedor")).thenReturn("Vendedor 1", "Vendedor 2")
        `when`(mockResultSet.getString("email_vendedor")).thenReturn("vendedor1@example.com", "vendedor2@example.com")
        `when`(mockResultSet.getString("cpf_vendedor")).thenReturn("123456789", "987654321")
        `when`(mockResultSet.getDouble("salario_vendedor")).thenReturn(2000.0, 2500.0)

        vendedorService.listVendedores()

        verify(mockStatement, times(1)).executeQuery(anyString())
    }

    @Test
    fun testListeVendasDosVendedoresExcluidos() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("id_venda")).thenReturn(1, 2)
        `when`(mockResultSet.getInt("id_cliente")).thenReturn(3, 4)
        `when`(mockResultSet.getInt("id_vendedor")).thenReturn(5, 6)
        `when`(mockResultSet.getInt("id_produto")).thenReturn(7, 8)
        `when`(mockResultSet.getInt("qtd_produto")).thenReturn(9, 10)
        `when`(mockResultSet.getDouble("preco_total")).thenReturn(11.0, 12.0)

        vendedorService.listeVendasDosVendedoresExcluidos()

        verify(mockStatement, times(1)).executeQuery(anyString())
    }
}
