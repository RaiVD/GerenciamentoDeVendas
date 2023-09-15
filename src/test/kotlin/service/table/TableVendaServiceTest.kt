package service.table

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TableVendaServiceTest {
    private lateinit var vendaService: TableVendaService.Companion
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

        vendaService = TableVendaService
        vendaService.connection = mockConnection
    }

    @Test
    fun testAddVendaValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        vendaService.addVenda(1, 2, 3, 5)
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testAddVendaInvalidQuantity() {
        vendaService.addVenda(1, 2, 3, -5)
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testDeleteVendaValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        vendaService.deleteVenda(1)
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testDeleteVendaInvalidId() {
        vendaService.deleteVenda(-1)
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testListVendas() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("id_venda")).thenReturn(1, 2)
        `when`(mockResultSet.getInt("id_cliente")).thenReturn(3, 4)
        `when`(mockResultSet.getInt("id_vendedor")).thenReturn(5, 6)
        `when`(mockResultSet.getInt("id_produto")).thenReturn(7, 8)
        `when`(mockResultSet.getInt("qtd_produto")).thenReturn(9, 10)
        `when`(mockResultSet.getDouble("preco_total")).thenReturn(11.0, 12.0)

        vendaService.listVendas()

        verify(mockStatement, times(1)).executeQuery(anyString())
    }

    @Test
    fun testUpdateVendaQuantidadeValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)
        `when`(mockResultSet.next()).thenReturn(true)
        `when`(mockResultSet.getInt("total")).thenReturn(1)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        vendaService.updateVendaQuantidade(1, 15)
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testUpdateVendaQuantidadeInvalidId() {
        vendaService.updateVendaQuantidade(-1, 15)
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testUpdateVendaQuantidadeInvalidQuantity() {
        vendaService.updateVendaQuantidade(1, -15)
        verify(mockStatement, never()).executeUpdate(anyString())
    }
}
