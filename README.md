# 🎮 Projet Pacman - JavaFX  

*Un jeu d'arcade classique revisité par une équipe d'étudiants de l'Université Paris Cité*  

---

## 🌟 À propos  
Ce projet est une réimplémentation du célèbre **Pacman** (1980) en **Java 17 avec JavaFX**, configuré via **Gradle**. Développé dans le cadre d'un cours universitaire.  

### Fonctionnalités actuelles  
- Déplacement basique de Pacman (flèches directionnelles)  
- Système de compilation/exécution via Gradle  
- **À venir** : Correction des bugs, ajout de sons, et optimisations  

---

## 📥 Installation & Exécution  

### Prérequis  
- **Java 17** (obligatoire)  
- Git (pour cloner le dépôt)  

### Instructions  
1. **Cloner le dépôt** :  
   ```bash
   git clone https://github.com/Nano-a/pacman-project.git
   cd pacman-project
   git checkout develop
   ```  
   *Pour les machines de TP (Université Paris Cité)* :  
   ```bash
   git clone -c http.sslVerify=false https://gaufre.informatique.univ-paris-diderot.fr/myteam/pacman
   cd pacman
   git config http.sslVerify false
   ```  

2. **Compiler & exécuter** :  
   ```bash
   ./gradlew build   # Compilation
   ./gradlew run     # Lancement (sons désactivés temporairement)
   ```  

### 🛠 Cas particuliers  
| Environnement | Commande/Configuration |  
|---------------|------------------------|  
| Machines de TP | `source SCRIPT/envsetup` avant Gradle |  
| Java 8-16 | Modifier `build.gradle` (non recommandé) |  
| Eclipse | Importer via *File > Import > Gradle* |  

---

## 📚 Ressources complémentaires  
- [📝 Rapport](https://gitlab.com/...) (GitLab Wiki)  
- [🎥 Vidéo de présentation](https://youtu.be/0knIP1q6Q6w)  
- [📖 Principe du jeu](https://fr.wikipedia.org/wiki/Pac-Man)  

---

## 👥 Équipe  
- **Coordinateur** : Nano-A (Abderrahman Ajinou)  
- **Contributeurs** :  
  - Naim Cherchour  
  - Paul Nabti 
  - Maroun Gebrayel  
  - Rayane Arkam  

---

## 🙏 Remerciements  
- [GitHub](https://github.com) pour l'hébergement  
- L'équipe enseignante de l'Université Paris Cité  

---

✨ *"Un projet collaboratif alliant nostalgie et modernité !"*  

> 💡 **Note** : Les bugs connus sont documentés dans les *issues* du dépôt. Contributions bienvenues via PRs (branche `develop`).
```
