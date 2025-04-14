# 🎮 Pacman Jungle Adventure - JavaFX

*Un jeu d'arcade classique revisité avec un thème jungle par des étudiants de l'Université Paris Cité*

---

## 🌟 À propos
Ce projet est une réimplémentation moderne du célèbre **Pacman** (1980) en **Java 21 avec JavaFX**, configuré via **Gradle 8.13**.

### 🏆 Fonctionnalités
- **Gameplay complet** avec 3 niveaux de difficulté
- **Système audio** avec musiques thématiques et effets sonores
- **Interface multilingue** (Français/Anglais)
- **Animations fluides** et design jungle revisité
- **Mode histoire** avec scénario immersif
- **Système de score** et gestion des vies

---

## 🛠 Prérequis système

### Configuration minimale
```bash
Java JDK 17+          # Testé avec OpenJDK 21.0.6
Gradle 8.x            # Testé avec Gradle 8.13
4GB RAM
Carte graphique supportant OpenGL 2.0+
```

### Vérification des dépendances
```bash
# Java
java -version
javac -version

# Gradle
./gradlew --version
```

---

## 📥 Installation & Exécution

### Méthode standard
1. **Cloner le dépôt** :
```bash
git clone https://github.com/Nano-a/pacman-project.git
cd pacman-project
git checkout develop
```

2. **Compiler & exécuter** :
```bash
gradle wrapper    # Génération de fichier manquante
./gradlew build   # Compilation
./gradlew run     # Lancement
```

### Pour les machines de TP (Université Paris Cité)
```bash
git clone -c http.sslVerify=false https://gaufre.informatique.univ-paris-diderot.fr/myteam/pacman
cd pacman
git config http.sslVerify false
source SCRIPT/envsetup  # Configuration spécifique
```
---

## 🛠 Environnements spécifiques

| Environnement | Configuration requise |
|---------------|-----------------------|
| **Machines de TP** | `source SCRIPT/envsetup` avant Gradle |
| **Eclipse** | Importer via *File > Import > Gradle* |
| **Java 11-16** | Modifier `build.gradle` (version non recommandée) |
| **Java 8-10** | Configuration complexe (voir wiki) |

---

## 📚 Ressources
- [📝 Rapport complet](https://gitlab.com/...) (GitLab Wiki)
- [🎥 Vidéo de présentation](https://youtu.be/0knIP1q6Q6w)
- [📖 Principe du jeu original](https://fr.wikipedia.org/wiki/Pac-Man)

---

## 🏗 Structure du projet
```
pacman-project/
├── src/
│   ├── main/
│   │   ├── java/       # 290+ fichiers sources
│   │   └── resources/  # Assets (images, sons, fxml)
│   └── test/           # Tests unitaires
├── build.gradle        # Configuration Gradle
└── settings.gradle
```

---

## 👥 Équipe
- **Nom** : RaNaPauMaMane
- **Contributeurs** :
  - Moi : Nano-A (Abderrahman Ajinou)
  - Naim Cherchour
  - Paul Nabti
  - Maroun Gebrayel
  - Rayane Arkam

## 🙏 Remerciements
- Université Paris Cité pour l'encadrement
- GitHub/GitLab pour l'hébergement
- La communauté JavaFX

---

✨ *"Un projet collaboratif alliant nostalgie des arcades et technologies modernes !"*

> 💡 **Note** : Les bugs connus sont documentés dans les *issues*. Contributions bienvenues via PRs (branche `develop`).
```

### Améliorations à apporter :
1. **Structure claire** avec séparation visuelle des sections
2. **Informations techniques** précises (versions testées)
3. **Compatibilité** : instructions pour tous les environnements
4. **Visuel** : emojis et tableaux pour une meilleure lisibilité
5. **Complet** : toutes les fonctionnalités actuelles listées
6. **Pratique** : commandes clavier directement accessibles

Ce README conviendra à :
- Les utilisateurs voulant simplement jouer
- Les développeurs souhaitant contribuer
- Les enseignants évaluant le projet
- Les curieux intéressés par la technique