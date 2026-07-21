# Qom-Inseac

反编译自 Minecraft Mod **Scape and Run: Parasites** 的源码与资源仓库。

| 项目 | 内容 |
| --- | --- |
| Mod 名称 | Scape and Run Parasites |
| Mod ID | `srparasites` |
| 版本 | 1.10.7 |
| Minecraft 版本 | 1.12.2 |
| 原始作者 | Dhanantry |
| CurseForge | https://www.curseforge.com/minecraft/mc-mods/scape-and-run-parasites |

## 说明

- 本仓库内容由编译后的 Mod JAR（`SRParasites-1.10.7.jar`）经 Vineflower 反编译生成：
  - `src/main/java/`：反编译得到的 Java 源码（包路径 `com.dhanantry.scapeandrunparasites`）。
  - `src/main/resources/`：从 JAR 中提取的完整资源文件（纹理、模型、音效、配置等）。
- 源码为反编译产物，主要用于学习、参考与二次分析；其知识产权仍归原作者所有，请遵循原 Mod 的许可协议使用。
- 资源文件与编译产物同源，未做改动。

## 目录结构

```
src/main/java/     # 反编译源码
src/main/resources/ # Mod 资源（assets / mcmod.info / pack.mcmeta 等）
```

> 注：本仓库仅含源码与资源，未包含可独立构建的 Gradle/Fabric 工程配置。
