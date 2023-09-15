package service.table

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class TableProdutoServiceTest {
    private lateinit var produtoService: TableProdutoService.Companion
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

        produtoService = TableProdutoService
        produtoService.connection = mockConnection
    }

    @Test
    fun testAddProductValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        produtoService.addProduto("Uva", 0.50)
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testAddProductInvalidInfo() {
        produtoService.addProduto("", 0.50)
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testAddProductInvalidValue() {
        produtoService.addProduto("Uva", -10.50)
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testDeleteProductValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        produtoService.deleteProduto(1)
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testDeleteProductInvalidId() {
        produtoService.deleteProduto(-1)
        verify(mockStatement, never()).executeUpdate(anyString())
    }

    @Test
    fun testListProducts() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet)

        // Configurar o ResultSet simulado com os dados esperados
        `when`(mockResultSet.next()).thenReturn(true, true, false)
        `when`(mockResultSet.getInt("id_produto")).thenReturn(1, 2)
        `when`(mockResultSet.getString("nome_produto")).thenReturn("Maçã", "Banana")
        `when`(mockResultSet.getDouble("preco_unit")).thenReturn(1.0, 1.5)

        produtoService.listProdutos()

        verify(mockStatement, times(1)).executeQuery(anyString())
    }

    @Test
    fun testUpdateProductValid() {
        `when`(mockConnection.createStatement()).thenReturn(mockStatement)
        `when`(mockStatement.executeUpdate(anyString())).thenReturn(1)

        produtoService.updateProduto(1, 2.0)
        verify(mockStatement, times(1)).executeUpdate(anyString())
    }

    @Test
    fun testUpdateProductInvalidId() {
        produtoService.updateProduto(-1, 2.0)
        verify(mockStatement, never()).executeUpdate(anyString())
    }
}
