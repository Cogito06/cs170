# MergeSort

CS170 分治算法练习：归并排序（Merge Sort）的 Java 实现。

## 项目结构

```
MergeSort/
├── src/
│   ├── MergeSort.java           # 核心算法：MergeSort（分治入口）、Merge（合并两个有序数组）
│   └── MergeSortLauncher.java   # 程序入口，演示排序过程
└── test/
    └── MergeSortTest.java       # 单元测试
```

沿用 CS61B 的项目风格：不用 Maven/Gradle，依赖直接以 jar 包的形式引入。

## 依赖

JUnit 4 + Google Truth，jar 包放在仓库根目录的 `../../../library`（即 `cs170/library`）下，
IDE 层面作为一个 project library 引入（见 `.idea/libraries/cs170_library.xml`），
同一个 `cs170` 仓库下的其他练习也可以复用这份 library，不用每个子项目各存一份。

## 运行

在 IDE 中直接运行 `MergeSortLauncher.main`，或运行 `MergeSortTest` 中的测试用例。
