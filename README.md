# Server do gry Recruitment Showdown – Real-Time Multiplayer Battle
Link do gry: 
```
https://github.com/BigMoistLochu/IntershipRPGame.git
```

## Uruchomienie Servera

### 1. Sklonowanie repozytorium
Najpierw sklonuj projekt na swój lokalny komputer:

```bash
git clone https://github.com/BigMoistLochu/IntershipRPGServer
```

### 2. Zbudowanie Obrazu
Wejdz do folderu gdzie sklonowales repo, zbuduj obraz dockera uzywajac tej komendy:

```bash
docker build -t recruitment-showdown-server .
```

### 3. Odpal Kontener
Odpal Kontener z obrazem servera uzywajac tej komendy:

```bash
docker run -p 8080:8080 recruitment-showdown-server
```

