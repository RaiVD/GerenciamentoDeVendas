
# Comandos SQL usados no projeto

### Tabela Produto
CREATE TABLE Produto (
id_produto SERIAL PRIMARY KEY,
nome_produto VARCHAR(255),
preco_unit DOUBLE
);

### Tabela Cliente
CREATE TABLE Cliente (
id_cliente SERIAL PRIMARY KEY,
nome VARCHAR(255),
email VARCHAR(255),
cpf VARCHAR(14),
endereco VARCHAR(255)
);

### Tabela Vendedor
CREATE TABLE Vendedor (
id_vendedor SERIAL PRIMARY KEY,
nome VARCHAR(255),
email VARCHAR(255),
cpf VARCHAR(14),
salario DOUBLE
);

### Tabela Venda
CREATE TABLE Venda (
id_venda SERIAL PRIMARY KEY,
id_cliente INT,
id_vendedor INT,
id_produto INT,
qtd_produto INT,
preco_total DOUBLE,
FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
FOREIGN KEY (id_vendedor) REFERENCES Vendedor(id_vendedor),
FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);


### SQL
"""
SELECT Venda.id_venda AS venda, Venda.preco_total AS preco
FROM Venda
WHERE preco_total > 10.00
"""

### SQL
"""
UPDATE venda
SET preco_total = 0.00, qtd_produto = 0.00
WHERE preco_total IS NULL OR qtd_produto IS NULL
"""

### SQL
"SELECT id_venda FROM venda WHERE id_cliente = $id"

### SQL
"UPDATE venda SET id_cliente = NULL WHERE id_venda = $idVenda"

### SQL
"DELETE FROM cliente WHERE id_cliente = $id"

### SQL
"""
SELECT id_venda, id_cliente, id_vendedor, id_produto, qtd_produto, preco_total
FROM venda
WHERE id_cliente IS NULL
"""

### SQL
"""
SELECT salario_vendedor, nome_vendedor
FROM vendedor
ORDER BY salario_vendedor DESC
"""

### SQL
"INSERT INTO cliente (nome_cliente, email_cliente, cpf_cliente, endereco_cliente) VALUES ('$nome_cliente', '$email_cliente', '$cpf', '$endereco_cliente')"

### SQL
"SELECT id_cliente, nome_cliente, email_cliente, cpf_cliente, endereco_cliente FROM cliente"

### SQL 
"UPDATE cliente SET email_cliente='$email', endereco_cliente='$endereco' WHERE id_cliente=$id"

### SQL
"INSERT INTO produto (nome_produto, preco_unit) VALUES ('$nome_produto', $preco_unit)"

### SQL
"DELETE FROM produto WHERE id_produto=$id"

### SQL
"SELECT id_produto, nome_produto, preco_unit FROM produto"

### SQL
"UPDATE produto SET preco_unit=$novoPrecoUnit WHERE id_produto=$id"

### SQL
"""
INSERT INTO venda (id_cliente, id_vendedor, id_produto, qtd_produto, preco_total)
SELECT $id_cliente, $id_vendedor, $id_produto, $qtd_produto, Produto.preco_unit * $qtd_produto
FROM Produto
WHERE Produto.id_produto = $id_produto
"""

### SQL
"UPDATE venda SET qtd_produto = $qtd_produto, preco_total = qtd_produto * Produto.preco_unit " +
"FROM Produto WHERE venda.id_venda = $id_venda AND Produto.id_produto = venda.id_produto".trimIndent()

### SQL
"SELECT COUNT(*) AS total FROM venda WHERE id_venda = $id_venda"

### SQL
"INSERT INTO vendedor (nome_vendedor, email_vendedor, cpf_vendedor, salario_vendedor, senha) VALUES ('$nome_vendedor', '$email_vendedor', '$cpf', $salario, $senha)"

### SQL
"SELECT id_venda FROM venda WHERE id_vendedor = $id"

### SQL
"UPDATE venda SET id_vendedor = NULL WHERE id_venda = $idVenda"

### SQL
"DELETE FROM vendedor WHERE id_vendedor = $id"

### SQL
"""
SELECT id_venda, id_cliente, id_vendedor, id_produto, qtd_produto, preco_total
FROM venda
WHERE id_vendedor IS NULL
"""

### SQL 
"SELECT id_vendedor, nome_vendedor, email_vendedor, cpf_vendedor, salario_vendedor FROM vendedor"
