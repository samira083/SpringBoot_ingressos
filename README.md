# Sistema de Ingressos - Spring Boot

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de eventos culturais, locais e emissão de ingressos. O projeto foi estruturado para atender aos requisitos acadêmicos da disciplina, incluindo autenticação com JWT, documentação Swagger/OpenAPI, validações, tratamento global de exceções e relacionamento entre entidades.

***

## Integrantes do Grupo

> Preencha com os dados reais antes de enviar ao professor.

| Nome Completo | RGM |
|---|---|
| Nome do integrante 1 | RGM |
| Nome do integrante 2 | RGM |
| Nome do integrante 3 | RGM |
| Nome do integrante 4 | RGM |
| Nome do integrante 5 | RGM |

***

## Objetivo do Projeto

O sistema permite cadastrar usuários, locais e eventos, além de emitir ingressos para eventos culturais. Também oferece autenticação segura via JWT e proteção das rotas privadas da API.

***

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Hibernate
- Gradle
- MySQL
- Swagger / OpenAPI
- Lombok
- Jakarta Validation

***

## Funcionalidades

- Cadastro de usuários
- Login com autenticação JWT
- CRUD de locais
- CRUD de eventos
- Emissão de ingressos
- Listagem e consulta de ingressos
- Controle de acesso por autenticação
- Validação de dados com Jakarta Validation
- Tratamento global de erros com `@RestControllerAdvice`
- Documentação interativa com Swagger

***

## Estrutura do Projeto

```bash
src/main/java/com/seuprojeto/ingressos
├── controller
├── service
├── repository
├── model
│   ├── entity
│   └── dto
├── security
├── config
└── exception
```

### Organização das camadas

- **controller**: recebe as requisições HTTP
- **service**: contém as regras de negócio
- **repository**: acesso ao banco de dados com JPA
- **entity**: entidades do banco de dados
- **dto**: objetos de entrada e saída da API
- **security**: autenticação, token e filtro JWT
- **config**: configurações gerais da aplicação
- **exception**: tratamento centralizado de erros

***

## Entidades Principais

### Usuario
Responsável por armazenar os dados do comprador ou administrador do sistema.

### Endereco
Relacionamento `@OneToOne` com `Usuario`, usado para registrar o endereço do usuário.

### Local
Representa o local onde o evento acontece, como teatro, estádio ou auditório.

### Evento
Relacionamento `@ManyToOne` com `Local`. Possui nome, data, preço do ingresso e capacidade máxima.

### Ingresso
Entidade principal da transação. Relacionamento `@ManyToOne` com `Usuario` e `Evento`.

***

## Requisitos Acadêmicos Atendidos

| Requisito solicitado | Implementação no projeto |
|---|---|
| `@OneToOne` | `Usuario` ↔ `Endereco` |
| `@ManyToOne / @OneToMany` | `Evento` ↔ `Local` e `Ingresso` ↔ `Usuario/Evento` |
| Entidade de transação | `Ingresso` |
| Regra de negócio | Limite de ingressos por usuário e capacidade do evento |
| Métricas em memória | Endpoint `/ingressos/resumo` |
| Segurança JWT | Login + rotas protegidas |
| Swagger/OpenAPI | Documentação interativa |
| DTOs separados | Entrada e saída |
| Validação | Jakarta Validation |
| Exceções globais | `@RestControllerAdvice` |

***

## Regras de Negócio

O sistema foi modelado para seguir as regras exigidas no tema de **Gestão de Ingressos**:

- Não permitir emissão de novos ingressos se a capacidade máxima do evento for atingida.
- Limitar a compra a no máximo **5 ingressos por usuário** para o mesmo evento.
- Validar os dados obrigatórios antes de persistir no banco.

***

## Segurança

As rotas públicas do sistema são:

- `POST /usuarios`
- `POST /auth/login`
- Rotas da documentação Swagger

As demais rotas exigem autenticação no cabeçalho:

```http
Authorization: Bearer SEU_TOKEN
```

***

## Banco de Dados

O sistema utiliza banco relacional **MySQL**.

Exemplo de configuração no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ingressos_db
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

***

## Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
```

### 2. Entrar na pasta do projeto

```bash
cd nome-do-projeto
```

### 3. Configurar o banco de dados

Ajuste usuário, senha e nome do banco no arquivo `application.properties`.

### 4. Executar a aplicação

No terminal:

```bash
./gradlew bootRun
```

No Windows PowerShell, se necessário:

```powershell
gradlew.bat bootRun
```

***

## Documentação Swagger

Após iniciar a aplicação, acessar:

```text
http://localhost:8080/swagger-ui.html
```

ou, dependendo da configuração:

```text
http://localhost:8080/swagger-ui/index.html
```

***

## Exemplos de Endpoints

### Autenticação
- `POST /auth/login`

### Usuários
- `POST /usuarios`
- `GET /usuarios`
- `GET /usuarios/{id}`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

### Locais
- `POST /locais`
- `GET /locais`
- `GET /locais/{id}`
- `PUT /locais/{id}`
- `DELETE /locais/{id}`

### Eventos
- `POST /eventos`
- `GET /eventos`
- `GET /eventos/{id}`
- `PUT /eventos/{id}`
- `DELETE /eventos/{id}`

### Ingressos
- `POST /ingressos`
- `GET /ingressos`
- `GET /ingressos/{id}`
- `DELETE /ingressos/{id}`
- `GET /ingressos/resumo`

***

## Exemplo de Fluxo de Uso

1. Cadastrar um usuário.
2. Realizar login para obter o token JWT.
3. Cadastrar um local.
4. Cadastrar um evento vinculado ao local.
5. Emitir ingressos para um usuário em um evento.
6. Consultar os ingressos emitidos.
7. Testar o resumo estatístico.

***

## Exemplo de Resumo Estatístico

O endpoint `/ingressos/resumo` pode retornar informações como:

```json
{
  "totalIngressosVendidos": 50,
  "receitaTotalArrecadada": 2500.00,
  "taxaOcupacaoMedia": 78.5,
  "eventoComMaiorVenda": "Festival de Música"
}
```

***

## Diferenciais Adicionais

Além dos requisitos mínimos, o projeto pode destacar:

- Interface web simples com `index.html` para demonstrar a ideia visual do sistema
- Organização em camadas para facilitar manutenção
- Código preparado para expansão futura
- Base para testes com Postman ou Swagger
- Possibilidade de separar perfis como `ADMIN` e `CLIENTE`

***

## Melhorias Futuras

- Paginação nas listagens
- Upload de imagem para eventos
- Filtro de eventos por data ou local
- Dashboard administrativo mais completo
- Testes automatizados com JUnit
- Deploy em nuvem

***

## Observações para Entrega

Para a entrega da atividade, é obrigatório:

- Deixar o repositório **público** no GitHub
- Inserir no README o **nome completo e RGM** de todos os integrantes
- Enviar o link do vídeo no YouTube como **público** ou **não listado**
- Demonstrar no vídeo o funcionamento da API e os efeitos no banco de dados

***

## Licença

Projeto acadêmico para fins educacionais.
