# 📝 To-Do List API

> API RESTful desenvolvida com **Spring Boot 3**, seguindo arquitetura em camadas e boas práticas de mercado.  
Permite gerenciar tarefas (To-Dos) com operações de criação, listagem, atualização e exclusão, além de validação, DTOs e tratamento global de erros.

---

## 🚀 Tecnologias Utilizadas

- ☕ **Java 17+**
- 🌱 **Spring Boot 3**
- 🧩 **Spring Data JPA**
- 🗃️ **H2 / MySQL** (configurável)
- 🧠 **Jakarta Validation**
- ⚙️ **Maven**
- 🛡️ **Controller Advice / Exception Handling**

---

## 📂 Estrutura do Projeto

```
com.example.todo_list
│
├── controller
│   └── TodoController.java
│
├── dto
│   ├── TodoRequestDTO.java
│   └── TodoResponseDTO.java
│
├── service
│   └── TodoService.java
│
├── infrastructure
│   ├── entity
│   │   └── Todo.java
│   └── repository
│       └── TodoRepository.java
│
├── exception
│   └── GlobalExceptionHandler.java
│
└── util
    └── ApiResponse.java
```

---

## ⚙️ Configuração do Projeto

### 🔧 Clonar o repositório
```bash
git clone https://github.com/SEU_USUARIO/Api-ToDo-List.git
cd Api-ToDo-List
```

### 🧱 Rodar a aplicação
```bash
./mvnw spring-boot:run
```

Ou, se preferir, execute a classe principal no IntelliJ / VS Code com `@SpringBootApplication`.

---

### 💾 Banco de Dados

Por padrão, a aplicação pode rodar com **H2 (memória)**.  
Para usar **MySQL**, adicione no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todolist
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🧠 Endpoints da API

Base URL:
```
http://localhost:8080/api/v1/todos
```

---

### ➕ Criar uma tarefa
**POST** `/api/v1/todos`

#### 📤 Request Body (JSON)
```json
{
  "todoTitle": "Estudar Spring Boot",
  "todoDescription": "Praticar desenvolvimento de APIs RESTful",
  "todoDate": "2025-10-10",
  "complete": false
}
```

#### 📥 Response (201 Created)
```json
{
  "message": "Item adicionado à lista",
  "code": 201,
  "data": {
    "id": 1,
    "todoTitle": "Estudar Spring Boot",
    "todoDescription": "Praticar desenvolvimento de APIs RESTful",
    "complete": false,
    "todoDate": "2025-10-10",
    "createDate": "2025-10-06 10:30:00",
    "updateDate": "2025-10-06 10:30:00"
  }
}
```

---

### 📋 Listar todas as tarefas
**GET** `/api/v1/todos`

#### 📥 Response (200 OK)
```json
[
  {
    "id": 1,
    "todoTitle": "Estudar Spring Boot",
    "todoDescription": "Praticar desenvolvimento de APIs RESTful",
    "complete": false,
    "todoDate": "2025-10-10",
    "createDate": "2025-10-06 10:30:00",
    "updateDate": "2025-10-06 10:30:00"
  }
]
```

---

### 🔍 Buscar tarefa por ID
**GET** `/api/v1/todos/{id}`

#### Exemplo
```
GET /api/v1/todos/1
```

#### 📥 Response (200 OK)
```json
{
  "message": "Item encontrado",
  "code": 200,
  "data": {
    "id": 1,
    "todoTitle": "Estudar Spring Boot",
    "todoDescription": "Praticar desenvolvimento de APIs RESTful",
    "complete": false,
    "todoDate": "2025-10-10",
    "createDate": "2025-10-06 10:30:00",
    "updateDate": "2025-10-06 10:30:00"
  }
}
```

---

### 📝 Atualizar tarefa
**PUT** `/api/v1/todos/{id}`

#### 📤 Request Body
```json
{
  "todoTitle": "Estudar Spring Boot (Atualizado)",
  "todoDescription": "Implementar DTOs e Exception Handler",
  "todoDate": "2025-10-12",
  "complete": true
}
```

#### 📥 Response (200 OK)
```json
{
  "message": "Item atualizado",
  "code": 200,
  "data": {
    "id": 1,
    "todoTitle": "Estudar Spring Boot (Atualizado)",
    "todoDescription": "Implementar DTOs e Exception Handler",
    "complete": true,
    "todoDate": "2025-10-12",
    "createDate": "2025-10-06 10:30:00",
    "updateDate": "2025-10-06 10:45:00"
  }
}
```

---

### ❌ Deletar tarefa
**DELETE** `/api/v1/todos/{id}`

#### 📥 Response (200 OK)
```json
{
  "message": "Item deletado",
  "code": 200,
  "data": null
}
```

---

### 🔢 Contar tarefas
**GET** `/api/v1/todos/count`

#### 📥 Response (200 OK)
```json
{
  "message": "Contagem total de itens",
  "code": 200,
  "data": 5
}
```

---

## ⚠️ Tratamento de Erros

A API retorna mensagens padronizadas para erros comuns:

### 🧾 Erro de Validação
```json
{
  "message": "O título deve ter pelo menos 5 caracteres",
  "code": 400,
  "data": null
}
```

### 🧾 Recurso não encontrado
```json
{
  "message": "Item não encontrado",
  "code": 404,
  "data": null
}
```

### 🧾 Erro interno do servidor
```json
{
  "message": "Erro interno no servidor",
  "code": 500,
  "data": null
}
```

---

## 🧠 Boas Práticas Implementadas

✅ Arquitetura em camadas (Controller → Service → Repository)  
✅ DTOs de entrada e saída  
✅ Tratamento global de exceções (`@ControllerAdvice`)  
✅ Validação de dados (`@Valid`, `@NotBlank`, `@Size`)  
✅ Respostas padronizadas (`ApiResponse<T>`)  
✅ Ordenação via `JpaRepository`  
✅ Código limpo, legível e pronto para produção

---

## 🧩 Possíveis Melhorias Futuras

- 🔐 Adicionar autenticação com **Spring Security + JWT**
- 📄 Implementar paginação e filtros em `/todos`
- 🧪 Criar testes unitários com **JUnit 5 + Mockito**
- 📚 Documentação automática com **Swagger / OpenAPI**
- 🐳 Containerização com **Docker**

---

## 👨‍💻 Autor

**Saullo Paulo Dantas Felipe**  
📍 Juazeiro do Norte - CE  
🎓 Estudante de Engenharia de Software  
💼 Desenvolvedor Mobile | Backend | Software Engineer  
🌐 [GitHub: Saullo-Programador](https://github.com/Saullo-Programador)
