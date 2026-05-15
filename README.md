# 🟩 Wordle ITA — Backend

Backend del gioco **Wordle in italiano**, costruito con **Quarkus** e **Ebean ORM**.  
Espone una REST API per gestire partite, tentativi e confronto delle lettere.

---

## 🛠 Tech Stack

- **Java 17+**
- **Quarkus** — framework backend
- **Ebean ORM** — persistenza con PostgreSQL
- **JAX-RS** — REST API
- **Lombok** — riduzione boilerplate
- **Docker + Docker Compose** — ambiente di sviluppo

---

## 📐 Architettura

```
src/
├── api/          # REST Resources (GameResource, ParolaResource)
├── Services/     # Business logic (GameService, ParolaService)
├── repository/   # Accesso al database (ParolaRepository)
├── model/        # Entità JPA (Game, Tentativo, Parola, RisultatoLettera)
├── dto/          # Data Transfer Objects
└── config/       # Configurazione Ebean, ExceptionMapper
```

---

## 🎮 Funzionalità

- **Avvio partita** — scelta della lunghezza della parola e numero massimo di tentativi
- **Tentativo** — confronto lettera per lettera con la parola segreta
- **Risultato per lettera**:
  - `CORRETTA` — lettera nella posizione giusta 🟩
  - `PRESENTE` — lettera presente ma nella posizione sbagliata 🟨
  - `ASSENTE` — lettera non presente ⬛
- **Stato partita** — `IN_CORSO`, `VINTA`, `PERSA`
- La parola segreta viene rivelata solo a partita terminata

---

## 🚀 Avvio in locale

### Prerequisiti
- Java 17+
- Maven
- Docker e Docker Compose

### 1. Avvia il database

```bash
docker-compose up -d
```

### 2. Avvia l'applicazione in dev mode

```bash
./mvnw quarkus:dev
```

L'API sarà disponibile su `http://localhost:8080`.  
La Swagger UI è accessibile su `http://localhost:8080/q/swagger-ui`.

---

## 📡 API Endpoints

### Partite — `/wordleGame/Game`

| Metodo | Path | Descrizione |
|--------|------|-------------|
| `GET` | `/wordleGame/Game` | Restituisce tutte le partite |
| `POST` | `/wordleGame/Game` | Avvia una nuova partita |
| `PUT` | `/wordleGame/Game/{idGame}` | Invia un tentativo |

### Parole — `/wordleGame/Parola`

| Metodo | Path | Descrizione |
|--------|------|-------------|
| `GET` | `/wordleGame/Parola` | Restituisce tutte le parole disponibili |

---

### Esempio — Avvio partita

**Request:**
```json
POST /wordleGame/Game
{
  "lunghezzaSelezionataDellaParola": 5,
  "tentativiMassimi": 6
}
```

**Response:**
```json
{
  "id": 1,
  "lunghezzaSelezionataDellaParola": 5,
  "tentativi": [],
  "statoPartita": "IN_CORSO"
}
```

---

### Esempio — Tentativo

**Request:**
```json
PUT /wordleGame/Game/1
{
  "tentativo": "carne"
}
```

**Response:**
```json
{
  "id": 1,
  "lunghezzaSelezionataDellaParola": 5,
  "tentativi": [
    {
      "numeroTentativo": 1,
      "risultati": [
        { "lettera": "c", "stato": "ASSENTE" },
        { "lettera": "a", "stato": "PRESENTE" },
        { "lettera": "r", "stato": "CORRETTA" },
        { "lettera": "n", "stato": "ASSENTE" },
        { "lettera": "e", "stato": "CORRETTA" }
      ]
    }
  ],
  "statoPartita": "IN_CORSO"
}
```

---

## 📦 Modello dati

```
Game
├── id
├── lunghezzaSelezionataDellaParola
├── tentativiMassimi
├── statoPartita (IN_CORSO | VINTA | PERSA)
├── parola → Parola
└── tentativi → List<Tentativo>
         └── risultati → List<RisultatoLettera>
                  ├── lettera
                  └── stato (CORRETTA | PRESENTE | ASSENTE)
```
