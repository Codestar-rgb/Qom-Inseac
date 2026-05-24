<<<<<<< HEAD
# Qom-Inseac
=======
# Subspace Parasite (Qom-Inseac)

A Minecraft 1.12.2 mod with parasite-themed mobs, forked from Scape and Run Parasites by Dhanantry.

## About

This is a decompiled and refactored source code of Scape and Run Parasites v1.10.5, renamed and reorganized as **Subspace Parasite** for porting purposes.

### Naming Changes

| Original | Subspace Parasite |
|----------|------------------|
| Mod ID: `srparasites` | `subspaceparasite` |
| Package: `com.dhanantry.scapeandrunparasites` | `com.subspaceparasite` |
| Class Prefix: `SRP` | `SP` |
| Mod Name: Scape and Run Parasites | Subspace Parasite |
| Author: Dhanantry | Codestar |

## Project Structure

```
src/main/java/com/subspaceparasite/
├── SPMain.java              # Main mod class
├── SParasites/              # Tags and constants
├── advancements/            # Advancement triggers
├── bestiary/                # Bestiary system
├── block/                   # Custom blocks
├── client/                  # Client-side rendering
├── compatibility/           # Mod compatibility (JEI, etc.)
├── container/               # GUI containers
├── entity/                  # Entity definitions & AI
├── events/                  # Event handlers
├── feature/                 # World generation features
├── fluid/                   # Custom fluids
├── gui/                     # GUI screens
├── init/                    # Registration handlers
├── item/                    # Custom items
├── network/                 # Network packets
├── phase/                   # Evolution phase system
├── potion/                  # Custom potions/effects
├── proxy/                   # Client/Server proxies
├── recipes/                 # Custom recipes
├── tileentity/              # Tile entities
├── util/                    # Utility classes
└── world/                   # World generation

src/main/resources/
├── assets/subspaceparasite/ # Textures, models, lang files
├── mcmod.info               # Mod metadata
└── pack.mcmeta              # Resource pack metadata
```

## Building

Requires:
- Java 8 JDK
- Gradle 4.10.3

```bash
./gradlew build
```

## Credits

- Original mod: [Scape and Run Parasites](https://www.curseforge.com/minecraft/mc-mods/scape-and-run-parasites) by Dhanantry
- Sound effects obtained from https://www.zapsplat.com
- Fork and refactor: Codestar

## License

This is a decompiled source code for porting purposes. The original mod belongs to Dhanantry.
>>>>>>> 632a044 (feat: Subspace Parasite v1.10.5 - Decompiled and refactored source)
