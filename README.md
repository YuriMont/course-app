# Venda de Cursos

Este projeto é um sistema desenvolvido utilizando Spring Boot, PostgreSQL e Spring Security, focado na gestão eficiente de usuários, cursos e pedidos em um ambiente de vendas de cursos.

### Principais recursos
* Cadastro de Usuários
* Gestão de Cursos
* Gestão de Pedidos e Pagamentos via Pix
* Rotas privadas
* Autenticação via JWT

### Tecnologias utilizadas
* Java
* Spring boot
* JPA
* Postgresql
* Spring Security
* Json Web Token
* Mercado pago

---

## Rotas

> `Base URL` http://localhost:8080

### Registro

> `POST` /auth/register

Registre-se na plataforma acresentado seus dados e ususfrua das demais funções deste sistema


#### Exemplo de entrada
~~~
{
	"first_name": "John",
	"last_name": "Doe",
	"email": "john@gmail.com",
	"cpf": "23805498004",
	"password": "12345678",
	"confirmation_password": "12345678",
	"birth_date": "2000-03-12"
}
~~~

#### Exemplo de Saída
~~~
{
	"first_name": "John",
	"last_name": "Doe",
	"cpf": "12345678",
	"email": "23805498004",
	"birthDate": "2000-03-12T00:00:00.000+00:00"
}
~~~
---

### Login
> `GET` /auth/login

Faça login e fique autenticado por duas horas

#### Exemplo de entrada
~~~
{
	"email": "john@gmail.com",
	"password": "12345678"
}
~~~

### Exemplo de Saída
~~~
{
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImJlZTcyODFmLWMwMDQtNDg3Yy05OTJlLWE0NmQxZTcxZDUyMSIsImV4cCI6MTcwNjA1MTk3MX0.tO71PwgWBg0NwcjA5-CRwLGXh_jDQBOiF2fPNPkF5PA"
}
~~~
---
### Criar curso
>  `POST` /api/courses/create

Crie cursos e os venda para outros usuários

#### Exemplo de Entrada (cURL)
~~~
    curl --request POST \
  --url http://localhost:8080/api/courses/create \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImJlZTcyODFmLWMwMDQtNDg3Yy05OTJlLWE0NmQxZTcxZDUyMSIsImV4cCI6MTcwNjA1MTk3MX0.tO71PwgWBg0NwcjA5-CRwLGXh_jDQBOiF2fPNPkF5PA' \
  --header 'Content-Type: application/json' \
  --data '{
	"name": "java",
	"description": "curso de java",
	"hours": "14",
	"day_and_hour": "2024-03-12T00:00:00.000",
	"price": "90"
}'
~~~

#### Exemplo de Saída
~~~
{
	"id": 1,
	"name": "java",
	"description": "curso de java",
	"hours": 14,
	"day_and_hour": "2024-03-12T00:00:00",
	"price": 90.0
}
~~~
---

### Obter Curso

> `GET` /api/courses/{id_course}

Consulte todas as informações do curso através do ID

#### Exemplo de entrada (cURL)

~~~
curl --request GET \
  --url http://localhost:8080/api/courses/1 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImJlZTcyODFmLWMwMDQtNDg3Yy05OTJlLWE0NmQxZTcxZDUyMSIsImV4cCI6MTcwNjA1MTk3MX0.tO71PwgWBg0NwcjA5-CRwLGXh_jDQBOiF2fPNPkF5PA' \
~~~

#### Exemplo de Saída
~~~
{
	"id": 1,
	"name": "java",
	"description": "curso de java",
	"hours": 14,
	"day_and_hour": "2024-03-12T00:00:00",
	"price": 90.0
}
~~~
---

### Adicionar curso a carrinho

> `POST` /api/orders/add/{id_course}

Adicione um curso ao seu carrinho atraves do ID do curso

#### Exemplo de entrada (cURL)
~~~
curl --request POST \
  --url http://localhost:8080/api/orders/add/1 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImJlZTcyODFmLWMwMDQtNDg3Yy05OTJlLWE0NmQxZTcxZDUyMSIsImV4cCI6MTcwNjA1MTk3MX0.tO71PwgWBg0NwcjA5-CRwLGXh_jDQBOiF2fPNPkF5PA' \
~~~

#### Exemplo de Saída
~~~
Curso adicinado com sucesso!
~~~
---

### Obter informações para realizar o pagamento

> `GET` /api/orders/pay/{order_id}

Obtenha o qrCode do pix para realizar a compra de itens do carrinho atraves do ID do pedido

#### Exemplo de entrada
~~~
curl --request GET \
  --url http://localhost:8080/api/orders/pay/746c7725-172a-4cff-a437-7e0ef1a5bdd4 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImJlZTcyODFmLWMwMDQtNDg3Yy05OTJlLWE0NmQxZTcxZDUyMSIsImV4cCI6MTcwNjA1MTk3MX0.tO71PwgWBg0NwcjA5-CRwLGXh_jDQBOiF2fPNPkF5PA' \
~~~

#### Exemplo de Saída
~~~
{
	"id": "70920563631",
	"status": "pending",
	"description": "Payment payment related to John Doe",
	"date_of_expiration": "2024-01-23T22:05:40.697+00:00",
	"transaction_amount": 90,
	"external_reference": "746c7725-172a-4cff-a437-7e0ef1a5bdd4",
	"notification_url": null,
	"point_of_interaction": {
		"transaction_data": {
			"qr_code": "00020126580014br.gov.bcb.pix01367694c333-1193-4b8d-8e79-6c8b8f450c6c52040000530398654040.025802BR5924SANTOSYURI202310301840426009Sao Paulo62240520mpqrinter709205636316304367A",
			"ticket_url": "https://www.mercadopago.com.br/payments/70920563631/ticket?caller_id=1640242528&hash=2cc103de-69c8-48e6-8d71-63a456dfc3f7",
			"qr_code_base64": "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"
		}
	}
}
~~~
