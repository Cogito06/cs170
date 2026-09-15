---
id: 2026-09-15-change-model-vs-change-algorithm
course: cs170
concepts: [计算模型, computational model, 分治, divide and conquer, Karatsuba, FFT, 整数乘法, integer multiplication, 渐近复杂度, asymptotic complexity]
hook: 进制/字长/查表属于「改模型」只赚 log，Karatsuba/FFT 属于「改算法」赚指数——n log n 猜错了竖式，却猜中了整数乘法的真实上界
refs:
  - book/chap1.pdf    # 算术、乘法
  - book/chap2.pdf    # 分治、Karatsuba、FFT（还没读到）
  - slides/lec-1_full.pdf
status: open
---

## 触发

追问「进制是不是个时空 trade」（[[2026-09-15-base-tradeoff-asymmetry]]）之后的收束。
这条是前两条的骨架，单独拿出来是因为它能往后挂 ch2 的内容。

## 卡点 / 误解

猜 `n log n` 的时候以为自己在猜**竖式**的复杂度（错，那是 Θ(n²)，
见 [[2026-09-15-halve-is-not-log]]）。

但这个数字本身不是凭空来的——它是**另一个问题的正确答案**：

- **Karatsuba**：`O(n^1.585)` ← ch2 分治，这才是 "can we do better" 的答案
- **Harvey–van der Hoeven (2019)**：`O(n log n)`，整数乘法的真实上界，而且被猜测最优

所以「猜错了，但猜到了一个真东西」。留着这个对照，别下次又把它安到竖式头上。

## 关键 insight

**让算法变快有两条完全不同的路子**，整个「进制」话题住在 A 里：

```
路子 A：改模型      重新定义什么算 O(1)
                    查表 / 加宽字长 / 换硬件 / 更大的 base
                    → 只能赚常数或 log 因子
                    → 换个模型就失效

路子 B：改算法      在同一个模型里减少操作总数
                    Karatsuba 的 4→3 次递归乘法
                    FFT 的换域（系数表示 → 点值表示，卷积变逐点乘）
                    → 赚指数上的改进（n² → n^1.585 → n log n）
                    → 在任何合理模型里都成立
```

路子 A 真实、工程上极重要（GMP 快几千倍就靠它），但它**碰不到问题的本质难度**。
路子 B 不关心你用什么进制、字长多宽——把 base 换成 10 或 2⁶⁴，Karatsuba 还是 Karatsuba。

收束成一句：

> **进制决定竖式跑多快；分治和 FFT 决定还要不要用竖式。**

另一个角度（这部分 DPV ch2 讲 FFT 时会正式讲，指针在 refs，不在这里复述）：
位置记数法把整数变成多项式，于是乘法 = **卷积 + 进位**，而进位只要一次线性扫描。
一旦看成卷积，路子 B 的所有手段才有了施展的地方——包括把 base 当成**分块旋钮**
（竖式选 B=2 或 10，Karatsuba 选 B=2^(n/2)）。

## 遗留问题

- [ ] Karatsuba 和 FFT 都还没学（ch2）。学完回来验证「改算法 vs 改模型」这个二分是不是站得住，特别是 FFT——它换的是表示法，算 A 还是 B？
