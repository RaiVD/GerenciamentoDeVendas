package service.consultas

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class QueryInDatabaseServiceTest {
    private lateinit var queryService: QueryInDatabaseService
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

        queryService = QueryInDatabaseService()
        queryService.connection = mockConnection
    }

    @Test
    fun testListItensVendidosAcimaDe10() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("venda")).thenReturn(1, 2)
        `when`(mockResultSet.getDouble("preco")).thenReturn(15.0, 12.0)

        queryService.listItensVendidosAcimaDe10()
        verify(mockStatement, times(1)).executeQuery(anyString())
    }

    @Test
    fun testDeleteClienteValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)
        `when`(mockResultSet.next()).thenReturn(true, false)
        `when`(mockResultSet.getInt("id_venda")).thenReturn(1)

        queryService.deleteCliente(1)

        verify(mockStatement, times(2)).executeQuery(anyString())
        verify(mockStatement, times(2)).executeUpdate(anyString())
    }

    @Test
    fun testDeleteClienteInvalidId() {
        queryService.deleteCliente(-1)
        verify(mockStatement, never()).executeQuery(anyString())
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testListeVendasDosClientesExcluidos() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("id_venda")).thenReturn(1, 2)
        `when`(mockResultSet.getInt("id_cliente")).thenReturn(3, 4)
        `when`(mockResultSet.getInt("id_vendedor")).thenReturn(5, 6)
        `when`(mockResultSet.getInt("id_produto")).thenReturn(7, 8)
        `when`(mockResultSet.getInt("qtd_produto")).thenReturn(9, 10)
        `when`(mockResultSet.getDouble("preco_total")).thenReturn(11.0, 12.0)

        queryService.listeVendasDosClientesExcluidos()
        verify(mockStatement, times(1)).executeQuery(anyString())
    }

    @Test
    fun testCountUsersWithZupEmail() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true)
        `when`(mockResultSet.getInt("total")).thenReturn(5)

        queryService.countUsersWithZupEmail()
        verify(mockStatement, times(1)).executeQuery(anyString())
    }

    @Test
    fun testListSalarioVendedoresOrdenados() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getDouble("salario_vendedor")).thenReturn(2500.0, 2000.0)
        `when`(mockResultSet.getString("nome_vendedor")).thenReturn("Vendedor 1", "Vendedor 2")

        queryService.listSalarioVendedoresOrdenados()
        verify(mockStatement, times(1)).executeQuery(anyString())
    }
}
