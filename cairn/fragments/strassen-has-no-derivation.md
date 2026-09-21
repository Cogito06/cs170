---
course: cs170
cluster: 复杂度从哪来
date: 2026-09-15
concepts: [Strassen算法, Strassen's algorithm, 张量秩, tensor rank, 双线性复杂度, bilinear complexity, 矩阵乘法指数, matrix multiplication exponent, 主定理, Master Theorem]
hook: Strassen 的 7 个乘积没有类似 Karatsuba 的构造性推导——它是张量秩问题的搜索结果，「找到 7」和「证明下界是 7」是两件独立的事，AlphaTensor 现在直接用强化学习搜索它
refs:
  - slides/lec-3_full.pdf   # Strassen 算法本讲，lec-4 开头有 recap
  - book/chap2.pdf
---

## 触发

问 Strassen 矩阵乘法只用 7 次乘法是怎么想出来的——紧跟着刚推完的主定理框架
（[[per-node-not-per-level]]）问的，本以为会有个类似 Karatsuba 的推导过程。

## 卡点 / 误解

**我原以为**存在类似 Karatsuba 的构造性推导——两行代数就能逼出那 7 个乘积。

**实际不存在。** Karatsuba 的推导是构造性的：

```
(a+b)(c+d) = ac+ad+bc+bd  →  ad+bc = (a+b)(c+d) − ac − bd
```

两行就能看着代数式一步步逼出结果。Strassen 的 7 个式子**没有对应的构造性推导**，
教科书和论文里能做的只是「写出公式，展开验证它对」。原因是矩阵乘法是个双线性映射，
等价于一个张量，「最少几次乘法算完」精确等价于**求这个张量的秩**——这是没有公式解
的组合搜索问题，Strassen 找到的是一个秩为 7 的分解，不是推导出来的。

更进一步：**找到 7 这个构造**和**证明 7 是下界（不可能是 6）**是两件独立完成
的工作（下界证明用的是秩的论证，跟找到 7 的构造分开完成），凑在一起才让这个
结果站得住。现代做法（DeepMind AlphaTensor, 2022）直接把这问题建模成强化学习
搜索张量分解——side evidence 这从头到尾就是个搜索问题，不是洞见问题。

（存疑的一条类比，没有确凿史料支持：复数乘法只用 3 次实数乘法不用 4 次
（常归到 Gauss 名下），同样是「加法换乘法」，但矩阵乘法不可交换（AB≠BA）
让它难得多——是否这是 Strassen 的真实灵感来源，没查到确凿出处，仅作直觉参照。）

## 关键 insight

套进主定理框架，直接续上 [[per-node-not-per-level]] 和 [[karatsuba-cant-reach-balance]]：

```
朴素分块矩阵乘法   a=8, b=2, d=2   log_2 8=3 > d=2   叶重   Θ(n³)
Strassen           a=7, b=2, d=2   log_2 7≈2.807>d=2  叶重   Θ(n^2.807)
```

和整数乘法故事（[[karatsuba-cant-reach-balance]]）结构完全平行——都是「把 a
砍小但够不到平衡点 `a=b^d`（这里是 4）」，且平衡点已被下界证明封死（7 是最优的）。

但**跳出 2×2 分块递归这一种策略**，矩阵乘法真正的复杂度指数 ω 至今未知，只知道
`2 ≤ ω ≲ 2.37`——ω=2 是否可达是个真正开放的问题，对应「能不能达到平衡点」这个
问题在更大的策略空间里还没被下界封死。

## 遗留问题

- [ ] ω 目前最佳已知上界具体多少、最近进展是谁做的——没查证，回头核实。
- [ ] Gauss 复数乘法 trick 是否真是 Strassen 的历史灵感来源，没有确凿史料，回头查证或放弃这条类比。
