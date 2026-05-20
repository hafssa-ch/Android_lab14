# 🔐 LAB 14 : Sauvegarde des données – SharedPreferences et fichiers (avec bonnes pratiques de sécurité)
Cours : Programmation Mobile : Android avec Java

**Application Android (Java)** démontrant les bonnes pratiques de persistance locale :  
`SharedPreferences`, `EncryptedSharedPreferences`, stockage interne, cache, et stockage externe `app-specific`.

---

## 📚 Table des matières

- [Objectifs pédagogiques](#objectifs-pédagogiques)
- [Prérequis](#prérequis)
- [Architecture du projet](#architecture-du-projet)
- [Fonctionnalités](#fonctionnalités)
- [Bonnes pratiques de sécurité](#bonnes-pratiques-de-sécurité)
- [Installation et exécution](#installation-et-exécution)
- [Guide d’utilisation](#guide-dutilisation)
- [Technologies utilisées](#technologies-utilisées)
- [LResultat](#Resultatt)

---

## 🎯 Objectifs pédagogiques

- Utiliser `SharedPreferences` (apply vs commit) pour stocker des données non sensibles.
- Chiffrer des secrets avec `EncryptedSharedPreferences` et `MasterKey` (AndroidX Security Crypto).
- Gérer des fichiers internes (texte UTF-8, JSON).
- Exploiter le cache temporaire (`cacheDir`) et le purger.
- Écrire/lire dans le stockage externe **app-specific** (sans permission dangereuse).
- Appliquer une checklist de sécurité : logs contrôlés, `MODE_PRIVATE`, nettoyage complet.

---

## ⚙️ Prérequis

- **Android Studio** (dernière version stable)
- **Min SDK** : 24 (Android 7.0)
- **JDK** 8 ou supérieur
- Connaissances de base en Java et Android

---

## 🧱 Architecture du projet
```
com.example.securestoragelabjava14/
├── ui/
│ └── MainActivity.java
├── prefs/
│ ├── AppPrefs.java // SharedPreferences simples
│ └── SecurePrefs.java // EncryptedSharedPreferences
├── files/
│ ├── InternalTextStore.java // Fichier texte interne
│ └── StudentsJsonStore.java // JSON (modèle Student)
├── cache/
│ └── CacheStore.java
├── external/
│ └── ExternalAppFilesStore.java
└── model/
└── Student.java
```

---

## ✨ Fonctionnalités

| Zone                     | Technologie                           | Données exemple                |
|--------------------------|---------------------------------------|--------------------------------|
| Préférences non sensibles | SharedPreferences (MODE_PRIVATE)     | Nom, langue, thème             |
| Secret (token)           | EncryptedSharedPreferences + MasterKey| Token chiffré                  |
| Fichier interne texte    | openFileOutput / openFileInput (UTF-8)| `note.txt`                     |
| Fichier JSON             | Org.JSON + stockage interne           | Liste d’étudiants              |
| Cache temporaire         | `cacheDir`                            | `last_ui.txt`                  |
| Externe app-specific     | `getExternalFilesDir(null)`           | `demo_export.txt` (export)     |
| Nettoyage global         | Clear all (prefs, fichiers, cache)    | Bouton "Effacer"               |

---

## 🔒 Bonnes pratiques de sécurité

- ✅ **Pas de secret en clair** – token chiffré avec `EncryptedSharedPreferences`
- ✅ **Aucun log de token** – on affiche seulement sa longueur
- ✅ `MODE_PRIVATE` pour toutes les écritures de fichiers internes
- ✅ Cache réservé aux données temporaires / régénérables
- ✅ Nettoyage complet disponible (prefs + fichiers + cache)
- ✅ Exceptions gérées sans fuite d’informations
- ✅ Stockage externe **app-specific** (pas de permission `READ_EXTERNAL_STORAGE`)
- ✅ Encodage UTF-8 imposé pour tous les fichiers texte
- ✅ Champ token masqué (`inputType="textPassword"`)

---

## 🚀 Installation et exécution

1. **Cloner le dépôt** (ou créer le projet avec les fichiers fournis)
2. **Ouvrir avec Android Studio**
3. **Ajouter la dépendance** dans `build.gradle (Module: app)` :
   ```
   dependencies {
       implementation 'androidx.security:security-crypto:1.1.0-alpha06'
   }
   ```
Synchroniser le projet (Sync Now)

Exécuter sur un émulateur ou un appareil physique (API ≥ 24)

💡 Si des erreurs de version persistent, désinstallez l’application puis relancez-la pour recréer le MasterKey.

## 📱 Guide d’utilisation
Saisissez un nom, choisissez une langue, activez/désactivez le thème sombre.

Entrez un token (sera chiffré automatiquement).

Appuyez sur « Sauvegarder prefs » → les données non sensibles + token chiffré sont stockés.

Redémarrez l’application → « Charger prefs » restaure les champs.

« Sauvegarder fichier JSON » écrit students.json + note.txt dans le stockage interne.

« Charger fichier JSON » lit et affiche les données.

« Effacer toutes les données » supprime préférences, fichiers et cache.

Un fichier demo_export.txt est automatiquement créé dans le stockage externe app-specific (visible dans le Device File Explorer).


## 🛠️ Technologies utilisées
Langage : Java 8

Framework : Android SDK (API 24+)

UI : Material Design 3 (Material Components for Android)

Sécurité : AndroidX Security Crypto (EncryptedSharedPreferences, MasterKey)

JSON : org.json (intégré)

## Resultat



https://github.com/user-attachments/assets/9f402bb8-2c43-4396-90a3-3d243e4f7cda

